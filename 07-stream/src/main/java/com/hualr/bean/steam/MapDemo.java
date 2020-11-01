package com.hualr.bean.steam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapDemo {
    public static void main(String[] args) {
        //合并1
        String[] words1 = {"111", "java8"};
        //将String变为Stream
        Stream<String> steamWords = Arrays.stream(words1);
        // 不太理解flatMap 但是总归是将stream变成value??
        List<String> s1 = Arrays.stream(words1)
                .map(word -> word.split(""))
                .flatMap(word -> Arrays.stream(word))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("s1: " + s1);


        List<String> s = Arrays.asList("I", "love", "I");
        List s2 = s.stream().map(word -> word.split(""))
                .distinct().collect(Collectors.toList());
        System.out.println("s2: " + s2);

        List s3 = s.stream().map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("s3: " + s3);

        List s4 = s.stream().map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("s4: " + s4);
    }
}
