package com.pdomingo.util;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import java.io.IOException;
import java.text.*;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

public class InputValidation{


	private InputValidation(){

	}

	public static final class Validate{

		static Scanner scan = new Scanner(System.in);
		final static String DOUBLE_PATTERN = "[0-9]+(\\.){0,1}[0-9]*";

		public static void setScanner(Scanner newScan)
		{
			scan = newScan;
		}

		public static String getInput() {
			String text = scan.nextLine();

			return StringUtils.defaultString(text);
    	}

		public static String getRequiredInput() {
			String text;

			do{
				text = scan.nextLine();

				if(StringUtils.isNotBlank(text) == false){
					System.out.println("Invalid Input");
				}

			}while(StringUtils.isNotBlank(text) == false);


			return text;
		}

		public static Boolean getYesOrNo(String question) {
			System.out.println(question);
			String text;
			Boolean isYes = true;

			do{
				text = scan.nextLine().toUpperCase();

				if(text.equals("Y") == false && text.equals("N") == false){
					System.out.println("Invalid Input");
				}else{
					if(text.equals("Y")){
						isYes = true;
					}else{
						isYes = false;
					}
				}

			}while(text.equals("Y") == false && text.equals("N") == false);


			return isYes;
		}

		public static Integer getInteger() {
			Integer outValue;
			String inValue;
			do{

				inValue = getInput();

				if(NumberUtils.isDigits(inValue) == false){
					System.out.println("Invalid Input");
				}

			}while(NumberUtils.isDigits(inValue) == false);

			outValue = Integer.valueOf(inValue);
			return outValue;
    	}

		public static Date getDate() {
			String inValue;
			Date date = new Date();
			Boolean isValid = true;
			do{

				try{
						isValid = true;
						inValue = getInput();
						date = new SimpleDateFormat("yyyy-MM-dd").parse(inValue);
				}catch(ParseException e){
					System.out.println("Invalid date");
					isValid = false;
				}

			}while(isValid.equals(false));

			return date;
    	}

		public static Integer getIntegerInRange(int min, int max) {
			Integer value;
			do{
				value = getInteger();

			}while(value < min || value > max);

			return value;
		}

		public static String getMatchedNumber(String matcher) {
			String mobileNumber;

			do{
				mobileNumber = scan.nextLine();

				if(mobileNumber.matches(matcher) == false){
					System.out.println("Invalid Input");
				}
			}while(mobileNumber.matches(matcher) == false);

			return mobileNumber;
		}

		public static String getEmailAddress() {
			String text;

			do{
				text = scan.nextLine();

				if(StringUtils.isNotBlank(text) == false){
					System.out.println("Invalid Email Address");
				}

			}while(EmailValidator.getInstance().isValid(text) == false);


			return text;
		}

		public static Double getGWA(){
			System.out.println("\n \t --- Input GWA ---");
			String input;
			DecimalFormat df = new DecimalFormat("#.##");
			String formattedGWA;
			Double GWA = 0.0;
			do{
				do{
					input = scan.nextLine();

					if(input.matches(DOUBLE_PATTERN) == false){
						System.out.println("Invalid Input");
					}

				}while(input.matches(DOUBLE_PATTERN) == false);
				formattedGWA = df.format(Double.valueOf(input));
				GWA = Double.valueOf(formattedGWA);

				if(GWA > 5.0 || GWA < 1.0){
					System.out.println("Invalid Input");
				}
			}while(GWA > 5.0 || GWA < 1.0);

			return GWA;
		}




	}

}
