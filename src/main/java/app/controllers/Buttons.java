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
            changeRoot((Node) actionEvent.getSource(),"../../../resources/app/views/logView.fxml");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void handleAddBtn(ActionEvent actionEvent) {
        try{
            changeRoot((Node) actionEvent.getSource(),"../../../resources/app/views/creatorView.fxml");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void handleSettingsBtn(ActionEvent actionEvent) {
        try{
            changeRoot((Node) actionEvent.getSource(),"../../../resources/app/views/settingsView.fxml");
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
