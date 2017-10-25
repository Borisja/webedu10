package view.home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class homeController {
	@FXML Button terugNaarHome;
	@FXML Label userName;
	
	
	public void setUserName(String name)
	{
		this.userName.setText("Welkom "+name);
	}
	
}
