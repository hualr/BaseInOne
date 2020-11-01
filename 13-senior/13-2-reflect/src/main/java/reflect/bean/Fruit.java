package reflect.bean;

import lombok.Data;

@Data
public class Fruit {
    private String name;

    public Fruit(String name) {
        this.name = name;
    }
}
