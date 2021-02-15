package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.java.app.util.FetchNIP;
import main.java.app.models.Service;
import main.java.app.models.Subject;
import main.java.app.util.StringConverterUtil;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class New implements Initializable {

    @FXML
    private JFXComboBox<String> invoiceType;

    @FXML
    private TableView<Service> items;

    @FXML
    private TableColumn<Service,Integer> tab_LP;
    @FXML
    private TableColumn<Service,String> tab_serviceName;
    @FXML
    private TableColumn<Service,Integer> tab_quantity;
    @FXML
    private TableColumn<Service,String> tab_unit;
    @FXML
    private TableColumn<Service,Float> tab_nettoPrice;
    @FXML
    private TableColumn<Service,Integer> tab_vatPercent;
    @FXML
    private TableColumn<Service,Float> tab_vatPrice;
    @FXML
    private TableColumn<Service,Float> tab_bruttoPrice;

    @FXML
    private JFXButton tableButtonAdd;
    @FXML
    private JFXButton tableButtonRemove;

    @FXML
    private JFXButton buttonFind;

    @FXML
    private JFXTextField formNIP;
    @FXML
    private JFXTextField formFullName;
    @FXML
    private JFXTextField formFullAddress;

    private ObservableList<Service> data = FXCollections.observableArrayList(new Service());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listLeagues = FXCollections.observableArrayList("Faktura VAT", "Faktura VAT-marza");
        invoiceType.setItems(listLeagues);

        prepareTable();
    }

    private void prepareTable(){
        items.setEditable(true);
        items.setItems(data);

        tab_LP.setCellValueFactory(new PropertyValueFactory<>("LP"));
        tab_LP.setCellFactory(TextFieldTableCell.forTableColumn(StringConverterUtil.stringToInt()));

        tab_vatPrice.setCellValueFactory(new PropertyValueFactory<>("vatPrice"));
        tab_vatPrice.setCellFactory(TextFieldTableCell.forTableColumn(StringConverterUtil.stringToFloat()));

        //service name
        tab_serviceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        tab_serviceName.setCellFactory(TextFieldTableCell.forTableColumn());
        tab_serviceName.setOnEditCommit(
                t ->  {
                    t.getTableView().getItems()
                            .get(t.getTablePosition().getRow())
                            .setServiceName(t.getNewValue());
                    items.refresh();
                }
        );

        tab_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tab_quantity.setCellFactory(TextFieldTableCell.forTableColumn(StringConverterUtil.stringToInt()));
        tab_quantity.setOnEditCommit(
                t ->  {
                    t.getTableView().getItems()
                            .get(t.getTablePosition().getRow())
                            .setQuantity(t.getNewValue());
                    items.refresh();
                }
        );
        tab_unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        tab_unit.setCellFactory(TextFieldTableCell.forTableColumn());
        tab_unit.setOnEditCommit(
                t ->  {
                    t.getTableView().getItems()
                            .get(t.getTablePosition().getRow())
                            .setUnit(t.getNewValue());
                    items.refresh();
                }
        );
//

        tab_vatPercent.setCellValueFactory(new PropertyValueFactory<>("vatPercent"));
        tab_vatPercent.setCellFactory(TextFieldTableCell.forTableColumn(StringConverterUtil.stringToInt()));
        tab_vatPercent.setOnEditCommit(
                t ->  {
                    t.getTableView().getItems()
                            .get(t.getTablePosition().getRow())
                            .setVatPercent(t.getNewValue());
                    items.refresh();
                }
        );

        tab_nettoPrice.setCellValueFactory(new PropertyValueFactory<>("nettoPrice"));
        tab_nettoPrice.setCellFactory(TextFieldTableCell.forTableColumn(StringConverterUtil.stringToFloat()));
        tab_nettoPrice.setOnEditCommit(
                t -> {
                    Service service = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    float value = t.getNewValue();
                    service.setNettoPrice(value);
                    service.setVatPrice(value / 1.23f);
                    service.setBruttoPrice(value * 1.23f);
                    items.refresh();
                }
        );

        tab_bruttoPrice.setCellValueFactory(new PropertyValueFactory<>("bruttoPrice"));
        tab_bruttoPrice.setCellFactory(TextFieldTableCell.forTableColumn(StringConverterUtil.stringToFloat()));
        tab_bruttoPrice.setOnEditCommit(
                t -> {
                    Service service = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    float value = t.getNewValue();
                    service.setNettoPrice(value / 1.23f);
                    service.setVatPrice(value * 0.23f);
                    service.setBruttoPrice(value);
                }
        );

        items.setItems(data);
        tableButtonAdd.setOnAction(e -> data.add(new Service()));
        tableButtonRemove.setOnAction(e -> {
            if(data.size() == 0) return;
            data.remove(data.size() - 1);
            Service.decrementCounter();
        });
        buttonFind.setOnAction(e -> {
            LocalDate date = LocalDate.now();
            String NIP = formNIP.getText();
            System.out.println(NIP + " " + date);
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


        tab_bruttoPrice.setCellFactory(TextFieldTableCell.forTableColumn(StringConverterUtil.stringToFloat()));
        tab_bruttoPrice.setOnEditStart(
                t -> {
                    Service service = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    float value = t.getNewValue();
                    service.setNettoPrice(value / 1.23f);
                    service.setVatPrice(value * 0.23f);
                    service.setBruttoPrice(value);
                }
        );


    }


}
