package main.java.app.models;

public class Car {
    private String VIN;
    private String description;
    private Float nettoPrice;
    private Float bruttoPrice;
    private Float vatPercent = 0.23f;
    private Float vatAmount;

    @Override
    public String toString() {
        return "Car{" +
                "VIN='" + VIN + '\'' +
                ", description='" + description + '\'' +
                ", nettoPrice=" + nettoPrice +
                ", bruttoPrice=" + bruttoPrice +
                ", vatPercent=" + vatPercent +
                ", vatAmount=" + vatAmount +
                '}';
    }

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
        this.bruttoPrice = nettoPrice * (1.0f + vatPercent);
        this.vatAmount = bruttoPrice - nettoPrice;
    }

    public Float getBruttoPrice() {
        return bruttoPrice;
    }

    public void setBruttoPrice(Float bruttoPrice) {
        this.bruttoPrice = bruttoPrice;
        this.nettoPrice = bruttoPrice / (1.0f + vatPercent);
        this.vatAmount = bruttoPrice - nettoPrice;
    }

    public Float getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(Float vatPercent) {
        this.vatPercent = vatPercent / 100.0f;
    }

    public Float getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Float vatAmount) {
        this.vatAmount = vatAmount;
    }
    public static class Builder{
        private String VIN;
        private String description;
        private Float nettoPrice;
        private Float bruttoPrice;
        private Float vatPercent = 0.23f;
        private Float vatAmount;

        public Builder VIN(String VIN){
            this.VIN = VIN;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder vatPercent(Float vatPercent){
            this.vatPercent = vatPercent / 100.0f;
            return this;
        }
        public Builder nettoPrice(Float nettoPrice){
            this.nettoPrice = nettoPrice;
            this.bruttoPrice = nettoPrice * (1.0f + vatPercent);
            this.vatAmount = bruttoPrice - nettoPrice;
            return this;
        }
        public Builder bruttoPrice(Float bruttoPrice){
            this.bruttoPrice = bruttoPrice;
            this.nettoPrice = bruttoPrice / (1.0f + vatPercent);
            this.vatAmount = bruttoPrice - nettoPrice;
            return this;
        }
        public Car build(){
            Car car = new Car();
            car.setVIN(VIN);
            car.setDescription(description);
            car.setVatPercent(vatPercent);
            car.setNettoPrice(nettoPrice);
            car.setBruttoPrice(bruttoPrice);
            return car;
        }
    }
}
