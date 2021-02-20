package main.java.app.dao;

import main.java.app.models.Buyer;

public class BuyerDAO extends DAO<Buyer>{
    @Override
    protected String createSaveQuery(Buyer buyer) {
        return String
                .format("INSERT INTO " +
                                "Buyer (NIP, fullName, street, streetNumber, town, ZIPCode) " +
                                "VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"); ",
                        buyer.getNIP(),
                        buyer.getFullName(),
                        buyer.getStreet(),
                        buyer.getStreetNumber(),
                        buyer.getTown(),
                        buyer.getZIPCode());
    }
}
