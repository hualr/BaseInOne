package serviceImpl;

import service.TInterface;

//这个地方一定要写T
public class Interface1_impl1<T> implements TInterface {

    @Override
    public T test() {
        return null;
    }
}
