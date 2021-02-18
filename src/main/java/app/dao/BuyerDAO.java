package main.java.app.dao;

import main.java.app.models.Buyer;

public class BuyerDAO extends DAO<Buyer>{
    @Override
    protected String createSaveQuery(Buyer buyer) {
        return String
                .format("INSERT INTO " +
                                "Buyer (NIP, fullName, fullAddress) " +
                                "VALUES (\"%s\",\"%s\",\"%s\"); ",
                        buyer.getNIP(),
                        buyer.getFullName(),
                        buyer.getFullAddress());
    }
}
