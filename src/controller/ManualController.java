package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ManualController {
	@FXML Button nextBtn;
	@FXML Button preBtn;
	@FXML Button sluit;
	@FXML ImageView img;
	@FXML Pane pane;
	private int count = 1;
	
	public void nextPage()
	{
		if(count > 3)
		{
			count = 0;
		}
		count++;
		img.setImage(new Image("/view/images/manual"+count+".jpg"));
		
		
	}
	public void prePage()
	{
		if(count < 1)
		{
			count = 3;
		}
		count--;
		img.setImage(new Image("/view/images/manual"+count+".jpg"));
		
	}
	
	public void openHandleidingMenu()
	{
		pane.setVisible(true);
	}
	
	public void sluitHandleidingMenu()
	{
		pane.setVisible(false);
	}

}
