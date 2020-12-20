import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;

/**
 * Author: zongqi<br>
 * Function: 主要理解SimpleDateFormat格式精度问题<br>
 * Creating Time：2020/12/19 20:40<br>
 * Version: 1.0.0<br>
 */
public class DateDemo {
    //使用ThreadLocal代替原来的new SimpleDateFormat
    private static final ThreadLocal<SimpleDateFormat> dateFormatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));


    /**
     * <h2>只能转化更加精确的时间</h2>
     */
    @Test
    public void test1() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time1 = "2020-12-07 10:00:00";
        String time2 = "2020-12-07";
        String time3 = "2020-12";

        System.out.println(simpleDateFormat.parse(time1));
        System.out.println(simpleDateFormat.parse(time2));
        System.out.println(simpleDateFormat.parse(time3));
    }

    /**
     * <h2>这个转化工具在多线程中,由于日历类线程不安全,会出现线程问题 想要正确被使用,只能采用第二种方法</h2>
     */
    @Test
    public void test2() throws InterruptedException {
        CountDownLatch countDownLatch1 = new CountDownLatch(10);
        CountDownLatch countDownLatch2 = new CountDownLatch(10);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                String str = simpleDateFormat.format(new Date());
                try {
                    simpleDateFormat.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                countDownLatch1.countDown();
            }).start();
        }
        countDownLatch1.await();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                String str = dateFormatter.get().format(new Date());
                try {
                    Date parse = dateFormatter.get().parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                countDownLatch2.countDown();
            }).start();
        }
        countDownLatch2.await();
    }

}
