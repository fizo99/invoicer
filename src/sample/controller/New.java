package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import sample.model.Service;

import java.net.URL;
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

    private ObservableList<Service> data = FXCollections.observableArrayList(new Service());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listLeagues = FXCollections.observableArrayList("Faktura VAT", "Faktura VAT-marza");
        invoiceType.setItems(listLeagues);

        prepareTable();
    }

    private void prepareTable(){
        items.setEditable(true);

        tab_LP.setCellValueFactory(new PropertyValueFactory<>("LP"));
        tab_LP.setCellFactory(TextFieldTableCell.forTableColumn(stringToInt()));

        tab_vatPrice.setCellValueFactory(new PropertyValueFactory<>("vatPrice"));
        tab_vatPrice.setCellFactory(TextFieldTableCell.forTableColumn(stringToFloat()));

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
        tab_quantity.setCellFactory(TextFieldTableCell.forTableColumn(stringToInt()));
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
        tab_vatPercent.setCellFactory(TextFieldTableCell.forTableColumn(stringToInt()));
        tab_vatPercent.setOnEditCommit(
                t ->  {
                    t.getTableView().getItems()
                            .get(t.getTablePosition().getRow())
                            .setVatPercent(t.getNewValue());
                    items.refresh();
                }
        );

        tab_nettoPrice.setCellValueFactory(new PropertyValueFactory<>("nettoPrice"));
        tab_nettoPrice.setCellFactory(TextFieldTableCell.forTableColumn(stringToFloat()));
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
        tab_bruttoPrice.setCellFactory(TextFieldTableCell.forTableColumn(stringToFloat()));
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


        tab_bruttoPrice.setCellFactory(TextFieldTableCell.forTableColumn(stringToFloat()));
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

    private StringConverter<Integer> stringToInt(){
        return new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object.toString();
            }
            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        };
    }
    private StringConverter<Float> stringToFloat(){
        return new StringConverter<Float>() {
            @Override
            public String toString(Float object) {
                return object.toString();
            }
            @Override
            public Float fromString(String string) {
                return Float.parseFloat(string);
            }
        };
    }
}
