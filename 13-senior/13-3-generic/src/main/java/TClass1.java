import bean.Egg;

public class TClass1<T> {
    private T key;

    public TClass1(T key) {
        this.key = key;
    }

    public static void main(String[] args) {
        TClass1<String> object1 = new TClass1<>("key_string");
        TClass1<Integer> object2 = new TClass1<>(12345);
        //可以不设置即便是有<T>
        TClass1 object3 = new TClass1<>(new Egg());

        System.out.println(object1.getKey());
        System.out.println(object2.getKey());
        System.out.println(object3.getKey());


    }

    public T getKey() {
        return key;
    }
}
