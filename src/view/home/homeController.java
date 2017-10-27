package view.home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
/**
 * Deze klasse is de controller van HomeView
 * @author rezanaser
 *
 */
public class homeController {
	@FXML Button homeButton;
	@FXML Label userName;
	@FXML Pane mainPane;
	
	/**
	 * Deze methode wordt aangeropen door de gebruikerViewController en geeft een string van gebruikersnaam mee 
	 * 
	 * @param name: Dit is naam dat meekegregen is van de hoofd controller
	 * @author rezanaser
	 */
	public void setUserName(String name)
	{
		this.userName.setText("Welkom "+name);
	}
	
	/**
	 * Deze methode wordt aangeropen door de gebruikerViewController en geeft de pane mee
	 * 
	 * @param name: Dit is de maonPane dat  meekegregen is van  de hoofd controller
	 * @author rezanaser
	 */
	public void setUserPane(Pane pane)
	{
		this.mainPane = pane;
	}
	
	/**
	 * Deze methode wordt aangeropen wanneer er gklikt wordt op de HOME button en gaat terug naar de mainPane
	 * @author rezanaser
	 */
	public void backToHome()
	{
		this.mainPane.setVisible(true);
	}
	
}
