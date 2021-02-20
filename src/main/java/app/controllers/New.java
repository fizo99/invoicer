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
import main.java.app.util.PDFCreator;

import java.beans.IndexedPropertyDescriptor;
import java.math.BigInteger;
import java.net.URL;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;


//TODO
    //RECREATE DB SCHEMA
    //MAKE NEW classDAOS
//


public class New implements Initializable {
    @FXML
    private JFXDatePicker formDate;
    @FXML
    private JFXTextField formPlace;
    @FXML
    private JFXTextField formInvoiceNumber;
    @FXML
    private JFXComboBox<String> formInvoiceType;

    //buyer data fields
    @FXML
    private JFXTextField formBuyerNIP,formBuyerFullName,
                         formBuyerTown,formBuyerStreet,
                         formBuyerStreetNumber,formBuyerZIPCode;
    //car data fields
    @FXML
    private JFXTextField formCarVIN,formCarBrand,formCarModel,
            formCarYear, formCarDescription,formCarNettoPrice,
            formCarVatPercent, formCarVatAmount, formCarBruttoPrice;

    @FXML
    private JFXTextField formAdditionalInformation;

    //buttons
    @FXML
    private JFXButton buttonSave;
    @FXML
    private JFXButton buttonFind;

    @FXML
    private JFXComboBox<String> formPaymentMethod;

    private LocalDate date;
    private String invoicePlace;
    private String invoiceNumber;
    private Invoice.Type invoiceType;

    private String additionalInformation;
    private Invoice.PaymentMethod paymentMethod;

    private Buyer buyer = new Buyer();
    private final Car car = new Car();

    private final DecimalFormat formatter = new DecimalFormat(
            "0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> invoiceTypes = FXCollections.observableArrayList(
                "VAT", "VAT_MARZA", "PROFORMA", "KOREKTA");
        formInvoiceType.setItems(invoiceTypes);

        ObservableList<String> paymentMethods = FXCollections.observableArrayList(
                "PRZELEW", "GOTOWKA");
        formPaymentMethod.setItems(paymentMethods);

        //set validators
        formCarNettoPrice.setValidators(priceFieldValidator());
        formCarNettoPrice.focusedProperty().addListener(carNettoPriceUnFocus());

        //action handlers
        formDate.setOnAction(dateOnChange());
        formPlace.textProperty().addListener(placeOnChange());
        formInvoiceType.setOnAction(typeOnChange());
        formInvoiceNumber.textProperty().addListener(invoiceNumberOnChange());

        formBuyerNIP.textProperty().addListener(buyerNIPOnChange());
        formBuyerFullName.textProperty().addListener(buyerFullNameOnChange());
        formBuyerTown.textProperty().addListener(buyerTownOnChange());
        formBuyerStreet.textProperty().addListener(buyerStreetOnChange());
        formBuyerStreetNumber.textProperty().addListener(buyerStreetNumberOnChange());
        formBuyerZIPCode.textProperty().addListener(buyerZIPOnChange());
        //formBuyerFullAddress.textProperty().addListener(buyerFullAddressOnChange());

        formCarVIN.textProperty().addListener(carVinOnChange());
        formCarBrand.textProperty().addListener(carBrandOnChange());
        formCarModel.textProperty().addListener(carModelOnChange());
        formCarYear.textProperty().addListener(carYearOnChange());
        formCarDescription.textProperty().addListener(carDescriptionOnChange());

        formAdditionalInformation.textProperty().addListener(additionalInformationOnChange());
        formCarNettoPrice.textProperty().addListener(carNettoPriceOnChange());
        formPaymentMethod.setOnAction(paymentMethodOnChange());

        //TODO
        buttonFind.setOnAction(e -> {
            LocalDate date = LocalDate.now();
            String NIP = formBuyerNIP.getText();
            try {
                buyer = FetchNIP.makeRequest(NIP,date.toString());

                formBuyerNIP.setText(buyer.getNIP());
                formBuyerFullName.setText(buyer.getFullName());
                formBuyerTown.setText(buyer.getTown());
                formBuyerStreet.setText(buyer.getStreet());
                formBuyerStreetNumber.setText(buyer.getStreetNumber());
                formBuyerZIPCode.setText(buyer.getZIPCode());

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
            invoice.setPlace(invoicePlace);
            invoice.setAdditionalInformation(additionalInformation);
            invoice.setPaymentMethod(paymentMethod);

            PDFCreator pdfCreator = new PDFCreator("test.pdf",invoice);
            pdfCreator.save();
            System.out.println(invoice);
            //save car to DB
//            CarDAO carDAO = new CarDAO();
//            try {
//                carDAO.save(car);
//            } catch (Exception exception) {
//                System.out.println("blad dodawania samochodu");
//                exception.printStackTrace();
//            }
//
//            //save buyer to DB
//            BuyerDAO buyerDAO = new BuyerDAO();
//            try {
//                buyerDAO.save(buyer);
//            } catch (Exception exception) {
//                System.out.println("blad dodawania kupujacego");
//                exception.printStackTrace();
//            }
//
//            invoice.setBuyerID((Integer) buyerDAO.getGeneratedKey());
//
//            //save invoice to DB
//            InvoiceDAO invoiceDAO = new InvoiceDAO();
//            try {
//                invoiceDAO.save(invoice);
//            } catch (Exception exception) {
//                System.out.println("blad dodawania faktury");
//                exception.printStackTrace();
//            }
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
    private ChangeListener<String> placeOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (formPlace.validate()) {
                    invoicePlace = newValue;
                }
            }
        };
    }
    private ChangeListener<String> invoiceNumberOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (formInvoiceNumber.validate()) {
                    invoiceNumber = newValue;
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

    private EventHandler<ActionEvent> paymentMethodOnChange(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                paymentMethod = Invoice.PaymentMethod.valueOf(formPaymentMethod.getValue());
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
    private ChangeListener<String> buyerTownOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buyer.setTown(newValue);
            }
        };
    }
    private ChangeListener<String> buyerStreetOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //SET FULL ADDRESS
                buyer.setStreet(newValue);
            }
        };
    }
    private ChangeListener<String> buyerStreetNumberOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buyer.setStreetNumber(newValue);
            }
        };
    }
    private ChangeListener<String> buyerZIPOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                buyer.setZIPCode(newValue);
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
    private ChangeListener<String> carBrandOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                car.setBrand(newValue);
            }
        };
    }
    private ChangeListener<String> carModelOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                car.setModel(newValue);
            }
        };
    }
    private ChangeListener<String> carYearOnChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(formCarYear.validate()){
                    car.setYear(Integer.parseInt(newValue));
                }
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

    private ChangeListener<String> additionalInformationOnChange(){
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                additionalInformation = newValue;
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
