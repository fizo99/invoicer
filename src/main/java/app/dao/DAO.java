package main.java.app.dao;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.sql.*;

public abstract class DAO<T> {
    private final String DBURL = "jdbc:sqlite:mydatabase.db";
    private final String DBUSER = "root";
    private final String DBPASS = "****";
    private final String DBDRIVER = "org.sqlite.JDBC";

    private Connection connection = null;
    private Statement statement = null;
    private String query = null;

    public void save(T object) throws Exception {
        query = createSaveQuery(object);

        try {
            Class.forName(DBDRIVER).newInstance();
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            statement = connection.createStatement();
            statement.executeUpdate(query);

            //zwolnienie zasobów i zamknięcie połączenia
            statement.close();
            connection.close();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Błąd przy zapisie do bazy");
        }
    }
    protected abstract String createSaveQuery(T object);
}
