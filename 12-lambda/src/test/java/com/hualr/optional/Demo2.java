package com.hualr.optional;

import hualr.bean.Car;
import hualr.bean.Insurance;
import hualr.bean.Person;
import java.util.Optional;

public class Demo2 {
    //不太好写
    public String getCarInsuranceName(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    //容易忘记所有属性的选择
    public String getCarInsuranceName2(Person person) {
        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }

    public String getCarInsuranceName3(Optional<Person> person) {
        return person.flatMap(Person::getOptionalCar)
                .flatMap(Car::getOptionalInsurance)
                .map(Insurance::getName)
                .orElse("UNKOWN");
    }


}
