package main;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import main.models.*;
import main.utils.Dialog;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

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
    @FXML
    private TableView<BookToLoan> tblLoanedBooks06;
    @FXML
    private TableView<BookToLoan> tblAvailableBooks06;
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
        tblSubscribers03.setPlaceholder(new Label("Aucun abonné trouvé."));

        TableColumn<Subscriber, Integer> idCol = new TableColumn<>("Identifiant");
        idCol.setMinWidth(125);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Subscriber, String> fullnameCol = new TableColumn<>("Nom");
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
        initializeBooksTable();
        btnSearch04.setOnMouseClicked(e -> searchBooks());
        btnInsert04.setOnMouseClicked(e -> insertBook());
        btnUpdate04.setOnMouseClicked(e -> updateBook());
        btnDelete04.setOnMouseClicked(e -> deleteBook());
        btnGoBack04.setOnMouseClicked(e -> showPane(paneMain));
    }

    private void initializeBooksTable() {
        tblBooks04.setPlaceholder(new Label("Aucun livre trouvé."));

        TableColumn<Book, Integer> idCol = new TableColumn<>("Identifiant");
        idCol.setMinWidth(125);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleCol = new TableColumn<>("Titre");
        titleCol.setMinWidth(125);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        tblBooks04.getColumns().addAll(idCol, titleCol);
    }

    private void updateBooksTableRows() {
        tblBooks04.getItems().clear();
        tblBooks04.getItems().addAll(DB.getBooks());
    }

    private void searchBooks() {
        Book book;

        if (txtId04.getText().isEmpty() && txtTitle04.getText().isEmpty())
            book = null;
        else
            book = new Book(
                    txtId04.getText().isEmpty() ? -1 : Integer.parseInt(txtId04.getText()),
                    txtTitle04.getText()
            );

        tblBooks04.getItems().clear();
        tblBooks04.getItems().addAll(DB.getBooks(book));
    }

    private void insertBook() {
        if (txtTitle04.getText().isEmpty()) {
            Dialog.informInfo("Le titre du livre doit être rempli.");
            return;
        }

        try {
            Book book = new Book();
            book.setTitle(txtTitle04.getText());

            if (DB.insertBook(book)) {
                Dialog.informSuccess("Livre ajouté avec succès.");
                updateBooksTableRows();
            } else throw new RuntimeException();
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

            if (DB.updateBook(book)) {
                Dialog.informSuccess("Livre modifié avec succès.");
                updateBooksTableRows();
            } else throw new RuntimeException();
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

            if (DB.deleteBook(book)) {
                Dialog.informSuccess("Livre supprimé avec succès.");
                updateBooksTableRows();
            } else throw new RuntimeException();
        } catch (Exception e) {
            Dialog.informError("Erreur lors de la suppression.");
        }
    }
    // === END OF BOOKS PANE LOGIC ===

    // === START OF LOAN PANE LOGIC ===
    private void runLoanPaneLogic() {
        updateLoanComboBoxesOptions();
        btnValidateLoan05.setOnMouseClicked(e -> validateLoan());
        btnValidateReturn05.setOnMouseClicked(e -> validateReturn());
        btnGoBack05.setOnMouseClicked(e -> showPane(paneMain));
    }

    private void updateLoanComboBoxesOptions() {
        List<Subscriber> subscribers = DB.getSubscribers();
        List<BookToLoan> availableBooks = DB.getAvailableBooks();
        List<BookToLoan> loanedBooks = DB.getLoanedBooks();

        List<String> subscribersOptions = subscribers.stream()
                .map(subscriber -> "[" + subscriber.getId() + "] " + subscriber.getFullname())
                .collect(Collectors.toList());
        List<String> availableBooksOptions = availableBooks.stream()
                .map(book -> "[" + book.getBookId() + "] " + book.getBookTitle())
                .collect(Collectors.toList());
        List<String> loanedBooksOptions = loanedBooks.stream()
                .map(book -> "[" + book.getBookId() + "] " + book.getBookTitle())
                .collect(Collectors.toList());

        cbSubscriberLoan05.getItems().clear();
        cbSubscriberLoan05.getItems().addAll(subscribersOptions);
        cbBookLoan05.getItems().clear();
        cbBookLoan05.getItems().addAll(availableBooksOptions);
        cbSubscriberReturn05.getItems().clear();
        cbSubscriberReturn05.getItems().addAll(subscribersOptions);
        cbBookReturn05.getItems().clear();
        cbBookReturn05.getItems().addAll(loanedBooksOptions);
    }

    private void validateLoan() {
        if (cbSubscriberLoan05.getSelectionModel().isEmpty() || cbBookLoan05.getSelectionModel().isEmpty()) {
            Dialog.informInfo("Le livre et son emprunteur doivent être sélectionnés.");
            return;
        }

        try {
            int subscriberId = getIdFromOptionValue(cbSubscriberLoan05.getValue());
            int bookId = getIdFromOptionValue(cbBookLoan05.getValue());

            if (DB.loanBook(new Loan(bookId, subscriberId))) {
                Dialog.informSuccess("Livre emprunté avec succès.");
                updateLoanComboBoxesOptions();
            } else throw new RuntimeException();
        } catch (Exception e) {
            Dialog.informError("Erreur lors de l'emprunt.");
        }
    }

    private void validateReturn() {
        if (cbSubscriberReturn05.getSelectionModel().isEmpty() || cbBookReturn05.getSelectionModel().isEmpty()) {
            Dialog.informInfo("Le livre et son emprunteur doivent être sélectionnés.");
            return;
        }

        try {
            int subscriberId = getIdFromOptionValue(cbSubscriberReturn05.getValue());
            int bookId = getIdFromOptionValue(cbBookReturn05.getValue());

            if (DB.returnBook(new Loan(bookId, subscriberId))) {
                Dialog.informSuccess("Livre retourné avec succès.");
                updateLoanComboBoxesOptions();
            } else throw new RuntimeException();
        } catch (Exception e) {
            Dialog.informError("Erreur lors du retour.");
        }
    }

    private Integer getIdFromOptionValue(String optionValue) {
        return Integer.parseInt(optionValue.split("]")[0].substring(1));
    }
    // === END OF LOAN PANE LOGIC ===

    // === START OF DISPONIBILITY PANE LOGIC ===
    private void runDisponibilityPaneLogic() {
        initializeDisponibilityTables();
        btnGoBack06.setOnMouseClicked(e -> showPane(paneMain));
    }

    private void initializeDisponibilityTables() {
        tblLoanedBooks06.setPlaceholder(new Label("Aucun livre prêté trouvé."));
        tblAvailableBooks06.setPlaceholder(new Label("Aucun livre disponible trouvé."));

        TableColumn<BookToLoan, Integer> loanedBookIdCol = new TableColumn<>("ID Livre");
        loanedBookIdCol.setPrefWidth(70);
        loanedBookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<BookToLoan, String> loanedBookTitleCol = new TableColumn<>("Titre Livre");
        loanedBookTitleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));

        TableColumn<BookToLoan, Integer> loanedSubscriberIdCol = new TableColumn<>("ID Emprunteur");
        loanedSubscriberIdCol.setPrefWidth(70);
        loanedSubscriberIdCol.setCellValueFactory(new PropertyValueFactory<>("subscriberId"));

        TableColumn<BookToLoan, String> loanedSubscriberFullnameCol = new TableColumn<>("Nom Emprunteur");
        loanedSubscriberFullnameCol.setCellValueFactory(new PropertyValueFactory<>("subscriberFullname"));

        tblLoanedBooks06.getColumns().addAll(
                loanedBookIdCol,
                loanedBookTitleCol,
                loanedSubscriberIdCol,
                loanedSubscriberFullnameCol
        );

        TableColumn<BookToLoan, Integer> availableBookIdCol = new TableColumn<>("ID Livre");
        availableBookIdCol.setPrefWidth(70);
        availableBookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<BookToLoan, String> availableBookTitleCol = new TableColumn<>("Titre Livre");
        availableBookTitleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));

        tblAvailableBooks06.getColumns().addAll(availableBookIdCol, availableBookTitleCol);
    }

    private void updateDisponibilityTablesRows() {
        tblLoanedBooks06.getItems().clear();
        tblLoanedBooks06.getItems().addAll(DB.getLoanedBooks(true));
        tblAvailableBooks06.getItems().clear();
        tblAvailableBooks06.getItems().addAll(DB.getAvailableBooks());
    }
    // === END OF DISPONIBILITY PANE LOGIC ===

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
        if (pane == paneBooks) updateBooksTableRows();
        if (pane == paneLoan) updateLoanComboBoxesOptions();
        if (pane == paneDisponibility) updateDisponibilityTablesRows();

        pane.setVisible(true);
        currPane = pane;
    }
}
