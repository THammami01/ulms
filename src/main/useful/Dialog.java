package main.useful;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Controller;
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
        if (Controller.lang.equals("arabic"))
            paneBtn.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        else
            paneBtn.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

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

    public static void informDBConnErrAndQuit() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Connection Failed | Connexion échouée | لم يتم الإتّصال");
        stage.getIcons().add(new Image("icon.png"));
        stage.setWidth(430);

        Label lbl1 = new Label("Cannot Connect to Database.");
        Label lbl2 = new Label("La connexion à la base de données a échoué.");
        Label lbl3 = new Label("لم يتم الإتّصال بقاعدة البيانات.");

        Button btnQuit = new Button("Quit | Quitter | الخروج");
        btnQuit.setAlignment(Pos.CENTER);
        btnQuit.setOnAction(e -> {
            stage.close();
            Main.primaryStage.close();
        });

        VBox paneLbl = new VBox();
        paneLbl.getChildren().addAll(lbl1, lbl2, lbl3);
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