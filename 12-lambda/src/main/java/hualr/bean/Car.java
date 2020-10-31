package hualr.bean;

import java.util.Optional;
import lombok.Data;

@Data
public class Car {
    private Insurance insurance;
    private Optional<Insurance> optionalInsurance;
}