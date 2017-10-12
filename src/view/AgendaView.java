package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.EmployeeDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.EmployeeModel;

public class AgendaView {
	Label lbl_ma, lbl_di, lbl_wo, lbl_do, lbl_fri;
	Label lbl_day1, lbl_day2, lbl_day3, lbl_day4, lbl_day5;
	Button[] buttons_monday = new Button[10];
	Button[] buttons_tuesday = new Button[10];
	Button[] buttons_wednesday = new Button[10];
	Button[] buttons_thursday = new Button[10];
	Button[] buttons_friday = new Button[10];
	
	public void agendaShow(EmployeeModel em) throws ParseException {
		Stage agenda_stage = new Stage();
		GridPane grid = new GridPane();
		initButtons();
		
		Date date_now = new Date();
		SimpleDateFormat day_df = new SimpleDateFormat("d"); //Day in numbers
		SimpleDateFormat week_nr_df = new SimpleDateFormat("w"); //Week of the year nr
		SimpleDateFormat month_df = new SimpleDateFormat("M"); //Month in numbers
		SimpleDateFormat day_description_df = new SimpleDateFormat("E"); //Day in text ie. Mon/Tues etc.
		
		String week_nr = week_nr_df.format(date_now).toString();
		String day_description = day_description_df.format(date_now).toString();
		String day = day_df.format(date_now).toString();
		String month = month_df.format(date_now).toString();
		
		int day_int = Integer.parseInt(day);
		int day_di = day_int+1;
		int day_wo = day_int+2;
		int day_do = day_int+3;
		int day_fri = day_int+4;
		
		lbl_ma = new Label();
		lbl_di = new Label();
		lbl_wo = new Label();
		lbl_do = new Label();
		lbl_fri = new Label();
		
		lbl_day1 = new Label("Maandag");
		lbl_day2 = new Label("Dinsdag");
		lbl_day3 = new Label("Woensdag");
		lbl_day4 = new Label("Donderdag");
		lbl_day5 = new Label("Vrijdag");
		
		lbl_ma.setText(" " + week_nr + "  " + " " + day + "  " + month + "  |  ");
		lbl_di.setText(" " + week_nr + "  " + " " + day_di + "  " + month + "  |  ");
		lbl_wo.setText(" " + week_nr + "  " + " " + day_wo + "  " + month + "  |  ");
		lbl_do.setText(" " + week_nr + "  " + " " + day_do + "  " + month + "  |  ");
		lbl_fri.setText(" " + week_nr + "  " + " " + day_fri + "  " + month);
		
		grid.add(lbl_ma, 0, 0);
		grid.add(lbl_di, 1, 0);
		grid.add(lbl_wo, 2, 0);
		grid.add(lbl_do, 3, 0);
		grid.add(lbl_fri, 4, 0);
		
		grid.add(lbl_day1, 0, 1);
		grid.add(lbl_day2, 1, 1);
		grid.add(lbl_day3, 2, 1);
		grid.add(lbl_day4, 3, 1);
		grid.add(lbl_day5, 4, 1);
		
		System.out.println(em.getEmployeeEmail());
		
		EmployeeDAO edao = new EmployeeDAO();
		String[] start_time = edao.entry_list(em.getEmployeeId()).get(0).getEntryStartTime().split(":", 3);
		String[] end_time = edao.entry_list(em.getEmployeeId()).get(0).getEntryEndTime().split(":", 3);
		int time_difference = (Integer.parseInt(end_time[0])-1) - Integer.parseInt(start_time[0]);
		
		int start = Integer.parseInt(start_time[0]);
		int end = (Integer.parseInt(end_time[0])-1);
		
		SimpleDateFormat getEntryDay = new SimpleDateFormat("E");
		Date entryDay = getEntryDay.parse(edao.entry_list(em.getEmployeeId()).get(0).getEntryDate());
		System.out.println(entryDay);
		
		//Monday
		for(int i = 2; i < 12; i++) {
			grid.add(buttons_monday[i-2], 0, i);
			if(start == Integer.parseInt(buttons_monday[i-2].getText())){
				buttons_monday[i-2].setStyle("-fx-background-color: green");
			}
			else if(Integer.parseInt(buttons_monday[i-2].getText()) >= start && Integer.parseInt(buttons_monday[i-2].getText()) <= end) {
					buttons_monday[i-2].setStyle("-fx-background-color: green");
			}
			else {
				buttons_monday[i-2].setStyle("-fx-background-color: red");
			}
		}
		
		//Tuesday
		for(int i = 2; i < 12; i++) {
			grid.add(buttons_tuesday[i-2], 1, i);
		}
		
		//Wednesday
		for(int i = 2; i < 12; i++) {
			grid.add(buttons_wednesday[i-2], 2, i);
		}
		
		//Thursday
		for(int i = 2; i < 12; i++) {
			grid.add(buttons_thursday[i-2], 3, i);
		}
		
		//Friday
		for(int i = 2; i < 12; i++) {
			grid.add(buttons_friday[i-2], 4, i);
		}
		
		Scene agenda_scene = new Scene(grid, 600, 400);
		agenda_stage.setScene(agenda_scene);
		agenda_stage.show();
		
	}
	
	public void initButtons(){
		int hour = 8; // start hour
		for(int i = 0; i < 10; i++) {
			buttons_monday[i] = new Button(Integer.toString(hour));
			buttons_tuesday[i] = new Button(Integer.toString(hour));
			buttons_wednesday[i] = new Button(Integer.toString(hour));
			buttons_thursday[i] = new Button(Integer.toString(hour));
			buttons_friday[i] = new Button(Integer.toString(hour));
			hour++;
		}
	}
}
