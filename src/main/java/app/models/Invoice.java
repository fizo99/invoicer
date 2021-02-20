package main.java.app.models;

import java.time.LocalDate;

public class Invoice {
    private LocalDate date;
    private String invoiceNumber;
    private Type invoiceType;
    private PaymentMethod paymentMethod;

    private String place;
    private String additionalInformation;


    private Buyer buyer;
    private Car car;

    private int buyerID;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Type getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Type invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }



    @Override
    public String toString() {
        return "Invoice{" +
                "date=" + date +
                ", invoiceNumber=" + invoiceNumber +
                ", invoiceType=" + invoiceType +
                ", town='" + place + '\'' +
                ", buyer=" + buyer +
                ", car=" + car +
                ", buyerID=" + buyerID +
                '}';
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public enum Type {
        VAT("VAT"),
        VAT_MARZA("VAT-MARZA"),
        PROFORMA("PROFORMA"),
        KOREKTA("PROFORMA");

        private String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    public enum PaymentMethod {
        GOTOWKA("GOTOWKA"),
        PRZELEW("PRZELEW");

        private String value;

        PaymentMethod(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
