package main.java.app.models;


//nie liczy vatAmount !
public class Car {
    private String VIN;
    private String brand;
    private String model;
    private Integer year;
    private String description;
    private Float nettoPrice;
    private Float bruttoPrice;
    private Float vatPercent = 0.23f;
    private Float vatAmount;

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getNettoPrice() {
        return nettoPrice;
    }

    public void setNettoPrice(Float nettoPrice) {
        this.nettoPrice = nettoPrice;
    }

    public Float getBruttoPrice() {
        return bruttoPrice;
    }

    public void setBruttoPrice(Float bruttoPrice) {
        this.bruttoPrice = bruttoPrice;
    }

    public Float getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(Float vatPercent) {
        this.vatPercent = vatPercent;
    }

    public Float getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Float vatAmount) {
        this.vatAmount = vatAmount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "VIN='" + VIN + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", nettoPrice=" + nettoPrice +
                ", bruttoPrice=" + bruttoPrice +
                ", vatPercent=" + vatPercent +
                ", vatAmount=" + vatAmount +
                '}';
    }

    public static class Builder{
        private String VIN;
        private String brand;
        private String model;
        private Integer year;
        private String description;
        private Float nettoPrice;
        private Float bruttoPrice;
        private Float vatPercent = 0.23f;
        private Float vatAmount;

        public Builder brand(String brand){
            this.brand = brand;
            return this;
        }
        public Builder model(String model){
            this.model = model;
            return this;
        }
        public Builder year(Integer year){
            this.year = year;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder vatPercent(Float vatPercent){
            this.vatPercent = vatPercent;
            return this;
        }
        public Builder nettoPrice(Float nettoPrice){
            this.nettoPrice = nettoPrice;
            return this;
        }
        public Builder bruttoPrice(Float bruttoPrice){
            this.bruttoPrice = bruttoPrice;
            return this;
        }
        public Builder vatAmount(Float vatAmount){
            this.vatAmount = vatAmount;
            return this;
        }
        public Car build(){
            Car car = new Car();
            car.setVIN(VIN);
            car.setBrand(brand);
            car.setModel(model);
            car.setYear(year);
            car.setDescription(description);
            car.setVatPercent(vatPercent);
            car.setNettoPrice(nettoPrice);
            car.setBruttoPrice(bruttoPrice);
            car.setVatAmount(vatAmount);
            return car;
        }
    }
}
