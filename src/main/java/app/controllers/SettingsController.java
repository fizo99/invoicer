package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import main.java.app.dao.UserDAO;
import main.java.app.models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    JFXTextField userFullName, userZIPCode,
                 userTown,userStreet,userStreetNumber,
                 userNIP, userPhoneNumber,
                 userEmail, userBDO, userGTU,
                 userAccountNumber;
    @FXML
    JFXButton buttonSettingsSave;

    @FXML
    Text errorMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO: DATA VALIDATION

        buttonSettingsSave.setOnAction(e -> {
            String fullName = userFullName.getText();
            String town = userTown.getText();
            String street = userStreet.getText();
            String streetNumber = userStreetNumber.getText();
            String ZIPCode = userZIPCode.getText();
            //String fullAddress = userFullAddress.getText();
            String NIP = userNIP.getText();
            String phoneNumber = userPhoneNumber.getText();
            String email = userEmail.getText();
            String BDO = userBDO.getText();
            String GTU = userGTU.getText();
            String accountNumber = userAccountNumber.getText();

            User user = new User.Builder()
                    .fullName(fullName)
                    //.fullAddress(fullAddress)
                    .town(town)
                    .street(street)
                    .streetNumber(streetNumber)
                    .ZIPCode(ZIPCode)
                    .NIP(NIP)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .BDO(BDO)
                    .GTU(GTU)
                    .accountNumber(accountNumber).build();

            UserDAO dao = new UserDAO();
            try{
                dao.save(user);
                errorMsg.setText("Zapisano!");
            } catch (Exception exception) {
                errorMsg.setText(exception.getMessage());
            }
        });
    }
}