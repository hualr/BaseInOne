import java.util.Objects;

public class ObjectDemo {
    public static void main(String[] args) {
        //以后用这个,原因是可以指出哪里报错 尤其在debug阶段好用
        Objects.requireNonNull(null, "引入空值");
    }
}
