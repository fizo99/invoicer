package main.java.app.dao;

import main.java.app.models.Buyer;
import main.java.app.models.Car;
import main.java.app.models.Invoice;

import java.sql.RowId;
import java.sql.SQLException;
import java.time.LocalDate;

public class InvoiceDAO extends DAO<Invoice>{
    @Override
    protected String createSaveQuery(Invoice invoice) {
        return String
                    .format("INSERT INTO " +
                            "Invoice (date, invoiceNumber, invoiceType, buyer, car) " +
                            "VALUES (\"%s\",\"%s\",\"%s\",\"%d\",\"%s\"); ",
                            invoice.getDate().toString(),
                            invoice.getInvoiceNumber().toString(),
                            invoice.getInvoiceType().toString(),
                            invoice.getBuyerID(),
                            invoice.getCar().getVIN());

    }
}
