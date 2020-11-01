package enum_;

public class EnumMethod {
    public static void main(String[] args) {
        // ZMM 枚举类的toString方法返回方法名
        System.out.println(Season1.AUTUMN);
        System.out.println(Season2.AUTUMN);

        // ZMM 返回枚举类中所有对象的数组
        Season2[] values = Season2.values();
        for (Season2 value : values) {
            System.out.println(value);
        }

        // ZMM 找到枚举类中同名的对象.没有就抛出异常
        Season2 season2 = Season2.valueOf("WINTER");
        System.out.println(season2);

        System.out.println(Season3.SPRING);
        switch (Season3.valueOf("SPRING")) {
            case SPRING: {
                System.out.println(Season3.SPRING);
                break;
            }
            case AUTUMN: {
                System.out.println(Season3.AUTUMN);
                break;
            }
            default:
                System.out.println(Season3.WINTER);

        }
    }
}
