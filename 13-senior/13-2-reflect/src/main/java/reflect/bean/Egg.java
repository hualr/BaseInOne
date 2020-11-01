package reflect.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Egg {
    public String color;
    private Integer weight;

    public Egg(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }
}
