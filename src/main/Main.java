package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private double decorationWidth;
    private double decorationHeight;

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/app/views/invoiceNew.fxml"));

        final double initialSceneWidth = 720;
        final double initialSceneHeight = 640;

        final Scene scene = new Scene(root, initialSceneWidth, initialSceneHeight);

        this.decorationWidth = initialSceneWidth - scene.getWidth();
        this.decorationHeight = initialSceneHeight - scene.getHeight();

        stage.setTitle("Invoicer");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
