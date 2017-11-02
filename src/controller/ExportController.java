package controller;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import dao.AdministratorDAO;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

/**
 * Deze klasse is verantwoordelijk voor exporteren van data in een csv file.
 * @author rezanaser
 *
 */
public class ExportController {
	final private String commaSeparator = "comma separated (,)";
	final private String semiSeparator = "Semicolon separated(;)";
	
	private AdministratorDAO adminDao = new AdministratorDAO();
	Button chooseButton = new Button("Kies een folder");
	File selectedDirectory;
	private TextField filename = new TextField();
	private TextField directory = new TextField();
	private Node loginButton;
	
	public void exportCSV()
	{
//		Dialog<Pair<File,String>> dialog = new Dialog<>();
//		dialog.setTitle("Login Dialog");
//		dialog.setHeaderText("Look, a Custom Login Dialog");
//
//		ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
//		dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
//		
//		GridPane grid = new GridPane();
//		grid.setHgap(10);
//		grid.setVgap(10);
//		grid.setPadding(new Insets(20, 150, 10, 10));
//
//		TextField directoy = new TextField();
//		directoy.setPromptText("foldernaam");
//		TextField filename = new TextField();
//		filename.setPromptText("Bestandsnaam");
//		ComboBox<String> csvType = new ComboBox();
//		csvType.setPromptText("CSV type");
//		csvType.getItems().addAll("comma seperated (,)","Semicolon seperated(;)");
//
//		grid.add(new Label("Bestandsnaam:"), 0, 0);
//		grid.add(filename, 1, 0);
//		grid.add(new Label("CSV shit:"), 0, 1);
//		grid.add(csvType, 1, 1);
//		grid.add(directoy, 1, 2);
//		grid.add(chooseButton, 2, 2);
//		
//		File defaultDirectory = new File("c:/");
//		
//		chooseButton.setOnAction(e -> {
//			System.out.println(this.getClass().toString()+": test");
//			Stage primaryStage = new Stage();
//			DirectoryChooser chooser = new DirectoryChooser();
//			chooser.setTitle("JavaFX Projects");
//			
//			chooser.setInitialDirectory(defaultDirectory);
//			selectedDirectory = chooser.showDialog(primaryStage);
//			directoy.setText(selectedDirectory.getAbsolutePath());
//			
//		});
//		
//		dialog.getDialogPane().setContent(grid);
//		
//		Optional<Pair<File,String>> result = dialog.showAndWait();
//
//		dialog.setResultConverter(dialogButton -> {
//		    if (dialogButton == saveButtonType) {
////		        return new Pair<>(username.getText(), password.getText());
//		    	
//		    }
//		    return null;
//		});
//		
//		result.ifPresent(usernamePassword -> {
////		    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
//		    boolean success = adminDao.exportCsv(selectedDirectory, csvType.getValue());
//			if(success) {
//				System.out.println(this.getClass().toString()+": gelukt!");
//			}else {
//				System.out.println(this.getClass().toString()+": fail!");
//			}
//			System.out.println(selectedDirectory.getAbsolutePath());
//		});
		
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Exporteren naar CSV");
		dialog.setHeaderText("Kies een naam, separator en de folder waar u het bestand wilt opslaan."
				+ " De separator wordt gebruikt om gegevens van elkaar te onderscheiden en hoeft"
				+ " meestal niet veranderd te worden.");
		dialog.initStyle(StageStyle.UTILITY);
		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Opslaan", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

//		directory.setPromptText("foldernaam");
		filename.setPromptText("Bestandsnaam");
		ComboBox<String> csvType = new ComboBox();
		csvType.setValue(semiSeparator);
		csvType.getItems().addAll(commaSeparator,semiSeparator);

		grid.add(new Label("Bestandsnaam:"), 0, 0);
		grid.add(filename, 1, 0);
		grid.add(new Label("CSV separator:"), 0, 1);
		grid.add(csvType, 1, 1);
		grid.add(directory, 1, 2);
		grid.add(chooseButton, 2, 2);
		
		File defaultDirectory = new File("c:/");
		
		chooseButton.setOnAction(e -> {
			System.out.println(this.getClass().toString()+": test");
			Stage primaryStage = new Stage();
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("JavaFX Projects");
			
			chooser.setInitialDirectory(defaultDirectory);
			selectedDirectory = chooser.showDialog(primaryStage);
			directory.setText(selectedDirectory.getAbsolutePath());
		});

		// Enable/Disable login button depending on whether a username was entered.
		loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		filename.textProperty().addListener((observable, oldValue, newValue) -> {
			checkFields();
//		    loginButton.setDisable(newValue.trim().isEmpty()&&directory.getText().isEmpty());
		});
		directory.textProperty().addListener((observable, oldValue, newValue) -> {
			checkFields();
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> filename.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(filename.getText(), directory.getText());
		    }
		    return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
		    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + directory.getText());
		    String separator = ";";
		    if(csvType.getValue()==commaSeparator) {
		    	separator=",";
		    }
//		    if(selectedDirectory!=null) {
//		    	adminDao.exportCsv(selectedDirectory, separator);
//		    }else {
//		    	selectedDirectory= new File(directory.getText().trim(),filename.getText().trim());
//		    	adminDao.exportCsv(selectedDirectory, separator);
//		    }
		    
		    adminDao.exportCsv(filename.getText().trim(), separator, directory.getText().trim());
		    
			System.out.println(selectedDirectory.getAbsolutePath());
		});
	}
	
	public void checkFields() {
		if(filename!=null&&!filename.getText().trim().isEmpty()&&directory!=null&&!directory.getText().trim().isEmpty()) {
			loginButton.setDisable(false);	
		}
	}

}
