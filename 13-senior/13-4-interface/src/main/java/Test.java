public class Test {
    public static void main(String[] args) {
        new Kid().say(() -> System.out.println("say!!!"));
        new Kid().cry(() -> System.out.println("cry!!!"));
        new Kid().doing(new Behavior());

    }
}

class Behavior implements Demo3 {

    @Override
    public void say() {
        System.out.println("zongqi say");
    }

    @Override
    public void cry() {
        System.out.println("xiaomi cry");
    }
}

class Kid {

    void say(Demo1 demo1) {
        demo1.say();
    }

    void cry(Demo2 demo2) {
        demo2.cry();
    }

    void doing(Demo3 demo3) {
        demo3.cry();
        demo3.say();
    }
}
