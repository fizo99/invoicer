package main.java.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class Buttons {
    public void handleLogBtn(ActionEvent actionEvent) {
        try{
            changeRoot((Node) actionEvent.getSource(),"../view/invoiceLog.fxml");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void handleAddBtn(ActionEvent actionEvent) {
        try{
            changeRoot((Node) actionEvent.getSource(),"../view/invoiceNew.fxml");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void handleSettingsBtn(ActionEvent actionEvent) {
        try{
            changeRoot((Node) actionEvent.getSource(),"../view/invoiceSettings.fxml");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void changeRoot(Node source, String path) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource(path));
        Stage stage = (Stage) source.getScene().getWindow();
        stage.getScene().setRoot(newRoot);
    }
}
