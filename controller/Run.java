package controller;

import model.gebruikerModel;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loginController lc = new loginController();
		gebruikerModel main_gebruiker = lc.login_assignment("lmao@lmao.com", "password");
		if(main_gebruiker == null){
			System.out.println("Login failed.");
		} else {
			System.out.println("Login success, assigning user id... " + main_gebruiker.getGebruikerId());
		}
	}

}
