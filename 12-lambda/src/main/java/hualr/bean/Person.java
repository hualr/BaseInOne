package hualr.bean;

import java.util.Optional;
import lombok.Data;

@Data
public class Person {
    private Car car;
    private Optional<Car> optionalCar;
}