package collection;

import lombok.Data;

@Data
public class Apple {
    private String color;
    private Integer weight;


    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }


}
