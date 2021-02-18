package main.java.app.dao;

import main.java.app.models.User;


public class UserDAO extends DAO<User>{
    @Override
    protected String createSaveQuery(User user) {
        return String
                .format("INSERT INTO " +
                        "User (fullName, fullAddress, NIP, phoneNumber, email, BDO, GTU, accountNumber) " +
                        "VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"); ",
                        user.getFullName(),
                        user.getFullAddress(),
                        user.getNIP(),
                        user.getPhoneNumber(),
                        user.getEmail(),
                        user.getBDO(),
                        user.getGTU(),
                        user.getAccountNumber());
    }
}
