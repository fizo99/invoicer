package main.java.app.dao;

import main.java.app.models.Car;

public class CarDAO extends DAO<Car>{
    @Override
    protected String createSaveQuery(Car car) {
        return String
                .format("INSERT INTO " +
                                "Car (VIN, description, nettoPrice, bruttoPrice, vatPercent, vatAmount) " +
                                "VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"); ",
                        car.getVIN(),
                        car.getDescription(),
                        car.getNettoPrice(),
                        car.getBruttoPrice(),
                        car.getVatPercent(),
                        car.getVatAmount());

    }
}
