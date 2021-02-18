package main.java.app.models;

import java.time.LocalDate;

public class Invoice {
    private LocalDate date;
    private Integer invoiceNumber;
    private Invoice.Type invoiceType;

    private Buyer buyer;
    private Car car;

    private int buyerID;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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
}
