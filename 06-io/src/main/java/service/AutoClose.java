package service;

public class AutoClose implements AutoCloseable {

    public void test() {
        System.out.println("测试");
    }

    @Override
    public void close() throws Exception {
        System.out.println("关闭");
    }
}
