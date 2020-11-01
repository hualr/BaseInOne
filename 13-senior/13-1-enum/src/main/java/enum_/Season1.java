package enum_;

/**
 * 自定义枚举类
 * 该类主要是未来和改进的枚举类做对比
 */
public class Season1 {
    public static final Season1 SPRING = new Season1("春天", "中国");
    public static final Season1 SUMMER = new Season1("夏天", "美国");
    public static final Season1 AUTUMN = new Season1("秋天", "苏联");
    public static final Season1 WINTER = new Season1("冬天", "德国");
    private final String seasonName;
    private final String seasonLocal;
    //设置无法构造
    private Season1(String seasonName, String seasonLocal) {
        this.seasonName = seasonName;
        this.seasonLocal = seasonLocal;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonLocal() {
        return seasonLocal;
    }


    @Override
    public String toString() {
        return "Season1{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonLocal='" + seasonLocal + '\'' +
                '}';
    }
}
