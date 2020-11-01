package tPro;

import java.util.ArrayList;
import java.util.Collection;

public class Main1 {
    public static void main(String[] args) {
        Collection<Integer> list1 = new ArrayList<>();
        Collection<String> list2 = new ArrayList<>();
        Collection<Number> list3 = new ArrayList<>();
        Collection<Object> list4 = new ArrayList<>();

        getElement1(list1);
        getElement2(list4);

        //对于extends 由于?是未知的,因此我们想要存,实际上是不知道

    }

    public static void getElement1(Collection<? extends Number> collection) {

    }

    public static void getElement2(Collection<? super Number> collection) {

    }
}
