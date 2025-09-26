package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main Class
 */

public class Main extends Application {

	/** To avoid magic number. */
	private static final int WIDTH = 700;
	/** To avoid magic number. */
	private static final int HEIGHT = 500;

	/**
	 * Start the program.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("resource/TransactionManager.fxml"));
			Scene scene = new Scene(root, WIDTH, HEIGHT);
			scene.getStylesheets().add(getClass().getResource("resource/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Transaction Management");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the window.
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
