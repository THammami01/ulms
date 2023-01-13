package main;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    private VBox paneWelcome;
    @FXML
    private HBox paneMain;
    @FXML
    private VBox paneSubscribers;
    @FXML
    private VBox paneBooks;
    @FXML
    private HBox paneLoan;
    @FXML
    private HBox paneDisponibility;

    // 01. WELCOME
    @FXML
    private Label lblWelcome01;
    @FXML
    private Button btnContinue01;

    // 02. MAIN
    @FXML
    private Label btnSubscribers02;
    @FXML
    private Label btnBooks02;
    @FXML
    private Label btnLoan02;
    @FXML
    private Label btnDisponibility02;

    // 03. SUBSCRIBERS
    @FXML
    private TextField txtId03;
    @FXML
    private TextField txtFullname03;
    @FXML
    private Label btnSearch03;
    @FXML
    private Label btnInsert03;
    @FXML
    private Label btnUpdate03;
    @FXML
    private Label btnDelete03;
    @FXML
    private Label btnGoBack03;
    // FXML TABLE

    // 04. BOOKS
    @FXML
    private TextField txtId04;
    @FXML
    private TextField txtTitle04;
    @FXML
    private Label btnSearch04;
    @FXML
    private Label btnInsert04;
    @FXML
    private Label btnUpdate04;
    @FXML
    private Label btnDelete04;
    @FXML
    private Label btnGoBack04;
    // FXML TABLE

    // 05. LOAN
    @FXML
    private ComboBox<String> cbSubscriberLoan05;
    @FXML
    private ComboBox<String> cbBookLoan05;
    @FXML
    private Label btnValidateLoan05;
    @FXML
    private ComboBox<String> cbSubscriberReturn05;
    @FXML
    private ComboBox<String> cbBookReturn05;
    @FXML
    private Label btnValidateReturn05;
    @FXML
    private Label btnGoBack05;

    // 06. DISPONIBILITY
    // FXML TABLES 1 AND 2
    @FXML
    private Label btnGoBack06;

    public Pane currPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showPane(paneWelcome);
        lblWelcome01.setText(getWelcomeMsg());

        btnContinue01.setOnMouseClicked(e -> showPane(paneMain));

        btnSubscribers02.setOnMouseClicked(e -> showPane(paneSubscribers));
        btnBooks02.setOnMouseClicked(e -> showPane(paneBooks));
        btnLoan02.setOnMouseClicked(e -> showPane(paneLoan));
        btnDisponibility02.setOnMouseClicked(e -> showPane(paneDisponibility));

        btnGoBack03.setOnMouseClicked(e -> showPane(paneMain));
        btnGoBack04.setOnMouseClicked(e -> showPane(paneMain));
        btnGoBack05.setOnMouseClicked(e -> showPane(paneMain));
        btnGoBack06.setOnMouseClicked(e -> showPane(paneMain));
    }

    private String getWelcomeMsg() {
        int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (h > 3 && h < 12) return "Bonjour !";
        if (h >= 12 && h < 18) return "Bon après-midi !";
        return "Bonsoir !";
    }

    public void showPane(Pane pane) {
        paneWelcome.setVisible(false);
        paneMain.setVisible(false);
        paneSubscribers.setVisible(false);
        paneBooks.setVisible(false);
        paneLoan.setVisible(false);
        paneDisponibility.setVisible(false);

        pane.setVisible(true);
        currPane = pane;
    }
}
