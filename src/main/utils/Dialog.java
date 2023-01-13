package main.utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

public class Dialog {
    public static Stage stage;
    public static boolean btnYesClicked;

    public static boolean confirm(String title, String msg) {
        btnYesClicked = false;

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.getIcons().add(new Image("icon.png"));
        stage.setMinWidth(300);

        Label lbl = new Label();
        lbl.setText(msg);

        Button btnYes = new Button("Oui");
        btnYes.setOnAction(e -> {
            stage.close();
            btnYesClicked = true;
        });

        Button btnNo = new Button("Non");
        btnNo.setOnAction(e -> {
            stage.close();
            btnYesClicked = false;
        });

        HBox paneBtn = new HBox(8);
        paneBtn.getChildren().addAll(btnYes, btnNo);

        paneBtn.setAlignment(Pos.CENTER);

        VBox pane = new VBox(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().addAll(lbl, paneBtn);
        pane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(pane);
        stage.setScene(scene);

        if (!title.equals("Quitter"))
            btnNo.requestFocus();

        stage.showAndWait();

        return btnYesClicked;
    }

    public static void inform(String title, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    public static void informInfo(String contentText) {
        inform("Info", contentText, Alert.AlertType.INFORMATION);
    }

    public static void informSuccess(String contentText) {
        inform("Succès", contentText, Alert.AlertType.INFORMATION);
    }

    public static void informError(String contentText) {
        inform("Erreur", contentText, Alert.AlertType.ERROR);
    }

    public static void informDBConnErrAndQuit() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Connexion échouée");
        stage.getIcons().add(new Image("icon.png"));
        stage.setWidth(430);

        Label lbl = new Label("La connexion à la base de données a échoué.");

        Button btnQuit = new Button("Quitter");
        btnQuit.setAlignment(Pos.CENTER);
        btnQuit.setOnAction(e -> {
            stage.close();
            Main.primaryStage.close();
        });

        VBox paneLbl = new VBox();
        paneLbl.getChildren().addAll(lbl);
        paneLbl.setAlignment(Pos.CENTER);

        VBox pane = new VBox(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().addAll(paneLbl, btnQuit);
        pane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
