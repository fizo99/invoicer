package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import main.java.app.models.Car;
import main.java.app.util.FetchNIP;
import main.java.app.models.Service;
import main.java.app.models.Subject;
import main.java.app.util.StringConverterUtil;

import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class New implements Initializable {

    @FXML
    private JFXComboBox<String> invoiceType;

    @FXML
    private JFXButton buttonFind;
    @FXML
    private JFXTextField formNIP;
    @FXML
    private JFXTextField formFullName;
    @FXML
    private JFXTextField formFullAddress;


    @FXML
    private JFXTextField formNettoPrice;

    @FXML
    private JFXTextField formVatPercent;

    @FXML
    private JFXTextField formVatAmount;

    @FXML
    private JFXTextField formBruttoPrice;

    @FXML
    private JFXTextField formDescription;

    @FXML
    private JFXTextField formVIN;

    @FXML
    private JFXButton buttonSave;


    private Car car = new Car();
    private boolean changingNetto = false;
    private boolean changingBrutto = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listLeagues = FXCollections.observableArrayList("Faktura VAT", "Faktura VAT-marza");
        invoiceType.setItems(listLeagues);

        buttonFind.setOnAction(e -> {
            LocalDate date = LocalDate.now();
            String NIP = formNIP.getText();
            try {
                Subject subject = FetchNIP.makeRequest(NIP,date.toString());

                formNIP.setText(subject.getNIP());
                formFullName.setText(subject.getFullName());
                formFullAddress.setText(subject.getFullAddress());
            } catch (Exception exc) {
                System.out.println(exc.getMessage());
                //exc.printStackTrace();
            }
        });

        buttonSave.setOnAction(e -> {
            //validate all data
            //update car object
            System.out.println(car);

            //create invoice object
            //create buyer object

            //save to database
            //create pdf
            //print pdf
        });

        RegexValidator validator = priceFieldValidator();

        formNettoPrice.getValidators().add(validator);
        formBruttoPrice.getValidators().add(validator);
        formVatAmount.getValidators().add(validator);

        formNettoPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if(changingBrutto == true) return;
            if(!formNettoPrice.validate()) return;

            changingNetto = true;

            Float netto = Float.parseFloat(newValue);
            car.setNettoPrice(netto);

            formBruttoPrice.setText(String.format("%.0f", car.getBruttoPrice()));
            formVatAmount.setText(String.format("%.0f", car.getVatAmount()));

            changingNetto = false;
        });
        formBruttoPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if(changingNetto == true) return;
            if(!formBruttoPrice.validate()) return;

            changingBrutto = true;

            Float brutto = Float.parseFloat(newValue);
            car.setBruttoPrice(brutto);

            formVatAmount.setText(String.format("%.0f", car.getVatAmount()));
            formNettoPrice.setText(String.format("%.0f", car.getNettoPrice()));

            changingBrutto = false;
        });

        formVIN.textProperty().addListener((observable, oldValue, newValue) -> car.setVIN(newValue));
        formDescription.textProperty().addListener((observable, oldValue, newValue) -> car.setDescription(newValue));

        formVatPercent.setEditable(false);
    }

    private RegexValidator priceFieldValidator(){
        RegexValidator validator = new RegexValidator();
        validator.setRegexPattern("^[0-9]+$");
        validator.setMessage("Zła wartość");
        return validator;
    }





}
