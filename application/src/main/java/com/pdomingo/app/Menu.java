package com.pdomingo.app;

import java.io.IOException;
import java.util.*;

import com.pdomingo.model.entities.*;
import com.pdomingo.model.dto.*;
import com.pdomingo.service.*;
import com.pdomingo.util.*;

import com.pdomingo.util.InputValidation;

public class Menu {
	Scanner scan = new Scanner(System.in);
	PersonIO personIO = new PersonIO();
	ContactIO contactIO = new ContactIO();
	RoleIO roleIO = new RoleIO();

	public Menu(){

	}

	public Menu(Scanner newScanner){
		scan = newScanner;
	}

	public Integer chooseCategory(){

		System.out.println("\n \t--- Choose Category --- \n");
		System.out.println("\n \t1. Person");
		System.out.println("\n \t2. Role");
		System.out.println("\n \t3. Exit");

		return InputValidation.getIntegerInRange(1,3);
	}



	public void doChooseCategory(Integer choice){

		switch(choice){
			case 1:
				personIO.start();
				break;
			case 2:
				roleIO.start();
				break;
			case 3:
				System.exit(0);
				break;
		}
	}

	public void start(){
		Integer choice;
		do{
			choice = chooseCategory();
			doChooseCategory(choice);
		}while(choice != 3);

	}


}
