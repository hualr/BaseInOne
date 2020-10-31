package hualr.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class Apple {
    private String color;
    private int weight;

    public Apple() {
    }

    public Apple(String color) {
        this.color = color;
    }

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(Integer integer) {

    }

    public int getWeight() {
        return weight;
    }


    public String getColor() {
        return color;
    }


}
