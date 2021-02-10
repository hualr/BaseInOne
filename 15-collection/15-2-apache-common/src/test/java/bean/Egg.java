package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function: egg Bean<br>
 * Creating Time: 2021/1/23 <br>
 * Version: 1.0.0
 *
 * @author 宗旗
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Egg {
    private String colorName;
    private Integer weight;
}