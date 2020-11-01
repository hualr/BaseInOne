import com.google.common.base.Preconditions;


public class PreconditionDemo {
    public static void main(String[] args) {
        try {
            //检查原则:发现错误之后,抛出异常为msg中定义.且不会继续往后走
            //1. 检查非空值
            Preconditions.checkNotNull("", "apple is null");
            //Preconditions.checkNotNull(null,"banana is null");

            //2. 检查boolean是否为真
            //Preconditions.checkArgument(false,"A is flase");
            Preconditions.checkArgument(true, "B is flase");

            // 暂时不知道他和第二小节的区别
            Preconditions.checkState(false, "C is false");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
