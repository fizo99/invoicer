package main.java.app.models;

public class Service {
    private String serviceName = "";
    private String unit = "szt.";

    private static int counter = 1;
    private int LP;

    private int quantity = 1;
    private int vatPercent = 23;

    private float nettoPrice = 0.0f;
    private float vatPrice = 0.0f;
    private float bruttoPrice = 0.0f;

    public Service(){
        this.LP = counter++;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getNettoPrice() {
        return nettoPrice;
    }

    public void setNettoPrice(float nettoPrice) {
        this.nettoPrice = nettoPrice;
    }

    public int getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(int vatPercent) {
        this.vatPercent = vatPercent;
    }

    public float getVatPrice() {
        return vatPrice;
    }

    public void setVatPrice(float vatPrice) {
        this.vatPrice = vatPrice;
    }

    public float getBruttoPrice() {
        return bruttoPrice;
    }

    public void setBruttoPrice(float bruttoPrice) {
        this.bruttoPrice = bruttoPrice;
    }

    public int getLP() {
        return LP;
    }

    public void setLP(int LP) {
        this.LP = LP;
    }

    public static void decrementCounter(){
        counter--;
    }



}
