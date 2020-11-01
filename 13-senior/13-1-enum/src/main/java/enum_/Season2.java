package enum_;

public enum Season2 {
    /**
     * ZNN
     * 1. 只有方法体.现场构造
     * 2. 相当于Season1中将相同的修饰全部删除
     * 3. 所有对象之间以.为分隔符
     * 4. 一定是以对象开头的
     */

    SPRING("春天", "中国"),
    SUMMER("夏天", "美国"),
    AUTUMN("秋天", "苏联"),
    WINTER("冬天", "德国");

    /**
     * what
     * 1. 方法默认继承于enum
     * 2. toString直接返回对象名称
     */

    private final String seasonName;
    private final String seasonLocal;

    Season2(String seasonName, String seasonLocal) {
        this.seasonName = seasonName;
        this.seasonLocal = seasonLocal;
    }
}
