package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.java.app.dao.BuyerDAO;
import main.java.app.dao.CarDAO;
import main.java.app.dao.InvoiceDAO;
import main.java.app.models.Buyer;
import main.java.app.models.Car;
import main.java.app.models.Invoice;
import main.java.app.util.FetchNIP;

import java.beans.IndexedPropertyDescriptor;
import java.math.BigInteger;
import java.net.URL;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

public class New implements Initializable {
    @FXML
    private JFXDatePicker formDate;
    @FXML
    private JFXTextField formInvoiceNumber;
    @FXML
    private JFXComboBox<String> formInvoiceType;

    //buyer data fields
    @FXML
    private JFXTextField formBuyerNIP,formBuyerFullName,formBuyerFullAddress;

    //car data fields
    @FXML
    private JFXTextField formCarVIN,formCarDescription,formCarNettoPrice,
            formCarVatPercent, formCarVatAmount, formCarBruttoPrice;

    //buttons
    @FXML
    private JFXButton buttonSave;
    @FXML
    private JFXButton buttonFind;


    private LocalDate date;
    private Integer invoiceNumber;
    private Invoice.Type invoiceType;

    private final Buyer buyer = new Buyer();
    private final Car car = new Car();

    private final DecimalFormat formatter = new DecimalFormat(
            "0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listLeagues = FXCollections.observableArrayList(
                "VAT", "VAT_MARZA", "PROFORMA", "KOREKTA");
        formInvoiceType.setItems(listLeagues);

        //set validators
        formCarNettoPrice.setValidators(priceFieldValidator());
        formCarNettoPrice.focusedProperty().addListener(carNettoPriceUnFocus());

        //action handlers
        formDate.setOnAction(dateOnChange());
        formInvoiceType.setOnAction(typeOnChange());
        formInvoiceNumber.textProperty().addListener(invoiceNumberOnChange());

        formBuyerNIP.textProperty().addListener(buyerNIPOnChange());
        formBuyerFullName.textProperty().addListener(buyerFullNameOnChange());
        formBuyerFullAddress.textProperty().addListener(buyerFullAddressOnChange());

        formCarVIN.textProperty().addListener(carVinOnChange());
        formCarDescription.textProperty().addListener(carDescriptionOnChange());
        formCarNettoPrice.textProperty().addListener(carNettoPriceOnChange());

        //TODO
        buttonFind.setOnAction(e -> {
            LocalDate date = LocalDate.now();
            String NIP = formBuyerNIP.getText();
            try {
                Buyer newBuyer = FetchNIP.makeRequest(NIP,date.toString());

                buyer.setNIP(newBuyer.getNIP());
                buyer.setFullName(newBuyer.getFullName());
                buyer.setFullAddress(newBuyer.getFullAddress());

                formBuyerNIP.setText(buyer.getNIP());
                formBuyerFullName.setText(buyer.getFullName());
                formBuyerFullAddress.setText(buyer.getFullAddress());
            } catch (Exception exc) {
                System.out.println(exc.getMessage());
                //exc.printStackTrace();
            }
        });
        buttonSave.setOnAction(e -> {
            Invoice invoice = new Invoice();
            invoice.setDate(date);
            invoice.setBuyer(buyer);
            invoice.setCar(car);
            invoice.setInvoiceType(invoiceType);
            invoice.setInvoiceNumber(invoiceNumber);

            //save car to DB
            CarDAO carDAO = new CarDAO();
            try {
                carDAO.save(car);
            } catch (Exception exception) {
                System.out.println("blad dodawania samochodu");
                exception.printStackTrace();
            }

            //save buyer to DB
            BuyerDAO buyerDAO = new BuyerDAO();
            try {
                buyerDAO.save(buyer);
            } catch (Exception exception) {
                System.out.println("blad dodawania kupujacego");
                exception.printStackTrace();
            }

            invoice.setBuyerID((Integer) buyerDAO.getGeneratedKey());

            //save invoice to DB
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            try {
                invoiceDAO.save(invoice);
            } catch (Exception exception) {
                System.out.println("blad dodawania faktury");
                exception.printStackTrace();
            }
            //create pdf

            //print pdf

        });
    }

    //action handlers
    private EventHandler<ActionEvent> dateOnChange(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                date = formDate.getValue();
            }
        };
    }
    private ChangeListener<String> invoiceNumberOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (formInvoiceNumber.validate()) {
                    invoiceNumber = Integer.parseInt(newValue);
                }
            }
        };
    }
    private EventHandler<ActionEvent> typeOnChange(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                invoiceType = Invoice.Type.valueOf(formInvoiceType.getValue());
                if(invoiceType == Invoice.Type.VAT_MARZA){
                    formCarVatPercent.setText("0");
                }else{
                    formCarVatPercent.setText("23");
                }
            }
        };
    }

    private ChangeListener<String> buyerNIPOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buyer.setNIP(newValue);
            }
        };
    }
    private ChangeListener<String> buyerFullNameOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buyer.setFullName(newValue);
            }
        };
    }
    private ChangeListener<String> buyerFullAddressOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buyer.setFullAddress(newValue);
            }
        };
    }

    private ChangeListener<String> carVinOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                car.setVIN(newValue);
            }
        };
    }
    private ChangeListener<String> carDescriptionOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                car.setDescription(newValue);
            }
        };
    }
    private ChangeListener<String> carNettoPriceOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(formCarNettoPrice.validate()){
                    float netto = Float.parseFloat(formCarNettoPrice.getText());
                    float vatPercent = Float.parseFloat(formCarVatPercent.getText()) / 100.0f;

                    float brutto = netto * (1.0f + vatPercent);
                    float vatAmount = brutto - netto;

                    car.setNettoPrice(netto);
                    car.setBruttoPrice(brutto);
                    car.setVatPercent(vatPercent);
                    car.setVatAmount(vatAmount);

                    formCarBruttoPrice.setText(formatter.format(brutto));
                    formCarVatAmount.setText(formatter.format(vatAmount));
                }
            }
        };
    }
    private ChangeListener<Boolean> carNettoPriceUnFocus() {
        return new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    if(formCarNettoPrice.validate()){
                        float value = Float.parseFloat(formCarNettoPrice.getText());
                        formCarNettoPrice.setText(formatter.format(value));
                    }
                }
            }
        };
    }


    //validators

    private RegexValidator priceFieldValidator(){
        RegexValidator validator = new RegexValidator();
        validator.setRegexPattern("^\\s*(?=.*[0-9])\\d*(?:\\.\\d{1,2})?\\s*$");
        validator.setMessage("Zła wartość");
        return validator;
    }

    private void initializeForm() {
//        RegexValidator validator = priceFieldValidator();
//
//        formNettoPrice.getValidators().add(validator);
//        formBruttoPrice.getValidators().add(validator);
//        formVatAmount.getValidators().add(validator);
//
//        formNettoPrice.textProperty().addListener((observable, oldValue, newValue) -> {
//            if(!formNettoPrice.validate()) return;
//
//            Float netto;
//            Float brutto = null;
//            Float vatAmount = null;
//            Float vatPercent;
//
//
//            if(invoiceType == Invoice.Type.VAT){
//                netto = Float.parseFloat(newValue);
//                vatPercent = Float.parseFloat(formVatPercent.getText());
//                brutto = netto * (1.0f + vatPercent / 100.0f);
//                vatAmount = brutto - netto;
//            }else if (invoiceType == Invoice.Type.VAT_MARZA){
//                netto = Float.parseFloat(newValue);
//                vatAmount = 0.0f;
//                brutto = netto;
//
//                formVatPercent.setText("0");
//            }
//
//            formBruttoPrice.setText(String.format("%.0f", brutto));
//            formVatAmount.setText(String.format("%.0f", vatAmount));
//        });
//
//        formVatPercent.setEditable(false);
//        formVatAmount.setEditable(false);
    }

    private void initializeBuyerForm(){

    }

    private void initializeTypeField(){
//        ObservableList<String> listLeagues = FXCollections.observableArrayList("VAT", "VAT_MARZA", "PROFORMA", "KOREKTA");
//        invoiceTypeField.setItems(listLeagues);
//        invoiceTypeField.setOnAction(e -> {
//            switch(invoiceTypeField.getValue()){
//                case "VAT":
//                    invoiceType = Invoice.Type.VAT;
//                    formVatPercent.setText("23");
//                    break;
//                case "VAT_MARZA":
//                    invoiceType = Invoice.Type.VAT_MARZA;
//                    break;
//                case "PROFORMA":
//                    invoiceType = Invoice.Type.PROFORMA;
//                    break;
//                case "KOREKTA":
//                    invoiceType = Invoice.Type.KOREKTA;
//                    break;
//            }
//        });
    }


    @Override
    public String toString() {
        return "New{" +
                "date=" + date +
                ", invoiceNumber=" + invoiceNumber +
                ", invoiceType=" + invoiceType +
                ", buyer=" + buyer +
                ", car=" + car +
                '}';
    }
}
