package com.hualr.future.complete;

import static java.util.stream.Collectors.toList;

import com.hualr.bean.Shop;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//查询所有商店下某个产品的价格
public class future3 {


    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("Addition"));
        long start = System.nanoTime();
        //同步搜索
        //System.out.println(new future3().findPrices1(shops,"myPhone27S"));
        //异步搜索
        //System.out.println(new future3().findPrices2(shops,"myPhone27S"));


        long duration = (System.nanoTime() - start) / 1_000_000;
        start = System.nanoTime();
        System.out.println("Done in " + duration + " msecs");


//        如果是需要输出东西,涉及到输出,那么采用异步,否则直接用流是最好的选择

        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %.2f",
                                        shop.getName(), shop.getPrice("myPhone27S"))))
                        .collect(toList());

        System.out.println(priceFutures.stream()
                .map(a -> {
                            String b = null;
                            try {
                                b = a.get();
                            } catch (ExecutionException | InterruptedException e) {
                                e.getCause();
                            }
                            return b;
                        }
                ).collect(toList())
        );

        //join 和get的区别是join不需要抛出异常 因此在上面的表达式中可以直接使用CompletableFuture::join语法糖

        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

    }

    //同步操作
    public List<String> findPrices1(List<Shop> shops, String product) {

        return shops.stream()
                .map(shop -> {
                    System.out.printf("%s price is %.2f \n", shop.getName(), shop.getPrice(product));
                    return String.format("%s price is %.2f",
                            shop.getName(), shop.getPrice(product));
                })
                .collect(toList());
    }

    //并行 :流
    public List<String> findPrices2(List<Shop> shops, String product) {

        return shops.stream().parallel()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

}
