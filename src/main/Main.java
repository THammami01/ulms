package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.nio.file.*;
import main.models.DB;
import main.utils.Dialog;

import java.util.Objects;

public class Main extends Application {
	public static Stage primaryStage;
	public static final String appDir = "/UVT-HICS/ULMS/";
	public static final String backupsDir = "/UVT-HICS/ULMS/BackUps/";

	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.primaryStage = primaryStage;
		DB.init();

		if (!DB.connected)
			Dialog.informDBConnErrAndQuit();

		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("View.fxml")));

		primaryStage.setTitle("SG de Bibliothèque Universitaire");
		primaryStage.getIcons().add(new Image("icon.png"));
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.setMinWidth(700);
		primaryStage.setMinHeight(600);

		primaryStage.show();

		primaryStage.setOnCloseRequest(e -> {
			e.consume();

			if(!Dialog.confirm("Quitter", "Voulez-vous vraiment quitter ?"))
				return;

			DB.backup();
			DB.close();
			primaryStage.close();
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void initDirs() {
		try {
			Path path = Paths.get(backupsDir);
			Files.createDirectories(path);
			path = Paths.get(appDir);
			Files.createDirectories(path);
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
}
