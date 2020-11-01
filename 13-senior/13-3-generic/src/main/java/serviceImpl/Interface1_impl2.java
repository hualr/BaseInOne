package serviceImpl;

import service.TInterface;

//第二种实现方法 实现了泛型
public class Interface1_impl2 implements TInterface<String> {
    @Override
    public String test() {
        return "哈皮";
    }
}
