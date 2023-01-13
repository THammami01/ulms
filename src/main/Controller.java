package main;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import main.models.Book;
import main.models.DB;
import main.models.Subscriber;
import main.utils.Dialog;

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
    @FXML
    private TableView<Subscriber> tblSubscribers03;

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
    @FXML
    private TableView<Book> tblBooks04;

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

        runWelcomePaneLogic();
        runMainPaneLogic();
        runSubscribersPaneLogic();
        runBooksPaneLogic();
        runLoanPaneLogic();
        runDisponibilityPaneLogic();
    }

    private void runWelcomePaneLogic() {
        lblWelcome01.setText(getWelcomeMsg());
        btnContinue01.setOnMouseClicked(e -> showPane(paneMain));
    }

    private void runMainPaneLogic() {
        btnSubscribers02.setOnMouseClicked(e -> showPane(paneSubscribers));
        btnBooks02.setOnMouseClicked(e -> showPane(paneBooks));
        btnLoan02.setOnMouseClicked(e -> showPane(paneLoan));
        btnDisponibility02.setOnMouseClicked(e -> showPane(paneDisponibility));
    }

    // === START OF SUBSCRIBERS PANE LOGIC ===
    private void runSubscribersPaneLogic() {
        initializeSubscribersTable();
        btnSearch03.setOnMouseClicked(e -> searchSubscribers());
        btnInsert03.setOnMouseClicked(e -> insertSubscriber());
        btnUpdate03.setOnMouseClicked(e -> updateSubscriber());
        btnDelete03.setOnMouseClicked(e -> deleteSubscriber());
        btnGoBack03.setOnMouseClicked(e -> showPane(paneMain));
    }

    private void initializeSubscribersTable() {
        TableColumn<Subscriber, Integer> idCol = new TableColumn<>("Identifiant");
        idCol.setMinWidth(125);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Subscriber, String> fullnameCol = new TableColumn<>("Nom complet");
        fullnameCol.setMinWidth(125);
        fullnameCol.setCellValueFactory(new PropertyValueFactory<>("fullname"));

        tblSubscribers03.getColumns().addAll(idCol, fullnameCol);
    }

    private void updateSubscribersTableRows() {
        tblSubscribers03.getItems().clear();
        tblSubscribers03.getItems().addAll(DB.getSubscribers());
    }

    private void searchSubscribers() {
        Subscriber subscriber;

        if (txtId03.getText().isEmpty() && txtFullname03.getText().isEmpty())
            subscriber = null;
        else
            subscriber = new Subscriber(
                    txtId03.getText().isEmpty() ? -1 : Integer.parseInt(txtId03.getText()),
                    txtFullname03.getText()
            );

        tblSubscribers03.getItems().clear();
        tblSubscribers03.getItems().addAll(DB.getSubscribers(subscriber));
    }

    private void insertSubscriber() {
        if (txtFullname03.getText().isEmpty()) {
            Dialog.informInfo("Le nom de l'abonné doit être rempli.");
            return;
        }

        try {
            Subscriber subscriber = new Subscriber();
            subscriber.setFullname(txtFullname03.getText());

            if (DB.insertSubscriber(subscriber)) {
                Dialog.informSuccess("Abonné ajouté avec succès.");
                updateSubscribersTableRows();
            } else throw new RuntimeException();
        } catch (Exception e) {
            Dialog.informError("Erreur lors de l'ajout.");
        }
    }

    private void updateSubscriber() {
        if (txtId03.getText().isEmpty() || txtFullname03.getText().isEmpty()) {
            Dialog.informInfo("L'identifiant et le nom de l'abonné doivent être remplis.");
            return;
        }

        try {
            Subscriber subscriber = new Subscriber(
                    Integer.parseInt(txtId03.getText()),
                    txtFullname03.getText()
            );

            if (DB.updateSubscriber(subscriber)) {
                Dialog.informSuccess("Abonné modifié avec succès.");
                updateSubscribersTableRows();
            } else throw new RuntimeException();
        } catch (Exception e) {
            Dialog.informError("Erreur lors de la modification.");
        }
    }

    private void deleteSubscriber() {
        if (txtId03.getText().isEmpty()) {
            Dialog.informInfo("L'identifiant de l'abonné doit être rempli.");
            return;
        }

        try {
            Subscriber subscriber = new Subscriber();
            subscriber.setId(Integer.parseInt(txtId03.getText()));

            if (DB.deleteSubscriber(subscriber)) {
                Dialog.informSuccess("Abonné supprimé avec succès.");
                updateSubscribersTableRows();
            } else throw new RuntimeException();
        } catch (Exception e) {
            Dialog.informError("Erreur lors de la suppression.");
        }
    }
    // === END OF SUBSCRIBERS PANE LOGIC ===

    // === START OF BOOKS PANE LOGIC ===
    private void runBooksPaneLogic() {
        setBooksTableRows();
        btnInsert04.setOnMouseClicked(e -> insertBook());
        btnUpdate04.setOnMouseClicked(e -> updateBook());
        btnDelete04.setOnMouseClicked(e -> deleteBook());
        btnGoBack04.setOnMouseClicked(e -> showPane(paneMain));
    }

    private void setBooksTableRows() {
        // TODO
    }

    private void insertBook() {
        if (txtTitle04.getText().isEmpty()) {
            Dialog.informInfo("Le titre du livre doit être rempli.");
            return;
        }

        try {
            Book book = new Book();
            book.setTitle(txtTitle04.getText());

            if (DB.insertBook(book))
                Dialog.informSuccess("Livre ajouté avec succès.");
            else throw new RuntimeException();
        } catch (Exception e) {
            Dialog.informError("Erreur lors de l'ajout.");
        }
    }

    private void updateBook() {
        if (txtId04.getText().isEmpty() || txtTitle04.getText().isEmpty()) {
            Dialog.informInfo("L'identifiant et le titre du livre doivent être remplis.");
            return;
        }

        try {
            Book book = new Book(
                    Integer.parseInt(txtId04.getText()),
                    txtTitle04.getText()
            );

            if (DB.updateBook(book))
                Dialog.informSuccess("Livre modifié avec succès.");
            else throw new RuntimeException();
        } catch (Exception e) {
            Dialog.informError("Erreur lors de la modification.");
        }
    }

    private void deleteBook() {
        if (txtId04.getText().isEmpty()) {
            Dialog.informInfo("L'identifiant du livre doit être rempli.");
            return;
        }

        try {
            Book book = new Book();
            book.setId(Integer.parseInt(txtId04.getText()));

            if (DB.deleteBook(book))
                Dialog.informSuccess("Le livre à été supprimé avec succès..");
            else throw new RuntimeException();
        } catch (Exception e) {
            Dialog.informError("Erreur lors de la suppression.");
        }
    }
    // === END OF BOOKS PANE LOGIC ===

    private void runLoanPaneLogic() {
        btnGoBack05.setOnMouseClicked(e -> showPane(paneMain));
    }

    private void runDisponibilityPaneLogic() {
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

        if (pane == paneSubscribers) updateSubscribersTableRows();

        pane.setVisible(true);
        currPane = pane;
    }
}
