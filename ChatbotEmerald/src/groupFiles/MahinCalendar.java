package groupFiles;

import java.util.Scanner;
import java.util.Arrays;

//import groupFiles.VictorMain;


public class MahinCalendar implements Chatbot{
	private boolean inCalendarLoop;
	private String calendarResponse;
	
	//static Scanner eventTime;
	
	static Scanner eventDay;
	private String dayString;
	static Scanner eventInfo;
	static Scanner leave;
	private String leaveString;
	private String infoString;
	
	String[] infoArray = new String[7];
	String[] dayArray = new String[7];
	boolean[] busyArray = new boolean[7];
	String[] daysOfTheWeek = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
	int dayCounter = 0;
	int infoCounter = 0;
	int busyCounter = 0;
	
	//String[] theMonths = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
	//int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	private int getIndex;
	
	public boolean isTriggered(String userInput){
		if(VictorMain.findKeyword(userInput, "date", 0)>=0){
			return true;
		}
		if(VictorMain.findKeyword(userInput, "time", 0)>=0){
			return true;
		}
		if(VictorMain.findKeyword(userInput, "calendar", 0)>=0){
			return true;
		}
		return false;
		
	}
	
	public void talk(){
		formFields();
		inCalendarLoop = true;
		VictorMain.print("Would you like to add things to my calendar for this week?");
		showEntry();
		while(inCalendarLoop){
			VictorMain.print("You can say 'quit' to exit this part.");
			leaveString = leave.nextLine();
			leaveString = leaveString.toLowerCase();
			//calendarResponse = VictorMain.promptInput();
			if(VictorMain.findKeyword(leaveString, "no", 0) >= 0||VictorMain.findKeyword(leaveString, "quit", 0) >= 0 ){
				inCalendarLoop = false;
				VictorMain.print("I don't think you value organization and punctuality.");
				VictorMain.promptForever();
			}else{
				VictorMain.print("I will take that as a yes. Which day? Monday, Tuesday, Wednesday, Thursday, or Friday?");
				dayString = enterValidDay();
				//updateBusiness();
				//determineBusiness(dayString);
				//System.out.println(getIndexOfDayEntered(dayString));
				//determineBusiness(getIndexOfDayEntered(dayString));
				getIndex = getIndexOfDayEntered(dayString);
				determineBusiness(getIndex);
				
					//proceedToDays();
					//VictorMain.print("Add some information about this day.");
					proceedToInfo();
					showEntry();
			}
		}
	}
	
	public String enterValidDay(){
		boolean waitingForInput = true;
		VictorMain.print("Please enter a day.");
		String dayString = VictorMain.promptInput();
		//showEntry();
		while(waitingForInput){
			if(!validDay(dayString)){
				VictorMain.print("Enter valid day");
				dayString = VictorMain.promptInput();
			}else{
				waitingForInput = false;
			}
		}
		return dayString;
	}
	
	
	public void proceedToDays(){
		//VictorMain.print(Arrays.toString(infoArray)); for testing
		//VictorMain.print(dayArray[0]+ " "+dayArray[1]); for testing
		VictorMain.print("Add some information about this day.");
		for(int i = 0; i < daysOfTheWeek.length; i++){
				if(VictorMain.findKeyword(dayString, daysOfTheWeek[i], 0) >= 0){
					//VictorMain.print("Cool."); for testing
					//busyArray[i] = true;
					addDay(daysOfTheWeek[i]);
					
				}
			}	
			VictorMain.print(Arrays.toString(dayArray));
			VictorMain.print(Arrays.toString(busyArray));
		}
		
	
	/*public void determineBusiness(String day){
		System.out.println("Hi");
		VictorMain.print(Arrays.toString(busyArray));
		boolean isBusy = false; //was false
		boolean passedProcess = false; //was false
		
		for(int i = 0; i < daysOfTheWeek.length; i++){
			if(busyArray[i] == true && day == daysOfTheWeek[i]){
				isBusy = true;
				passedProcess = false;
			}
		}
		for(int i = 0; i < daysOfTheWeek.length; i++){
			if(busyArray[i] == false && day == daysOfTheWeek[i]){
				isBusy = false;
				passedProcess = true;
			}
		}
		
		if(isBusy == true && passedProcess == false){
			VictorMain.print("You are busy this day. Choose another day.");
			dayString = enterValidDay();
		}
			//passedProcess = true;
		if(passedProcess == true && isBusy == false){
			proceedToDays();
		}
	}*/
		//boolean passedProcess = true;
	
	public int getIndexOfDayEntered(String dayEntered){
		int counter = 0;
		boolean notFound = true;
		while(notFound == true){
			if(dayEntered != daysOfTheWeek[counter]){
			counter = counter + 1;	
				if(counter > 6){
					return 0;
				}
			}else{
				notFound = false;
			}
		}
			return counter;
	}
	
	public void determineBusiness(int index){
		if(busyArray[index] == false){
			proceedToDays();
		}else{
			VictorMain.print("This day is busy. Choose another day.");
			inCalendarLoop = false;
		}
	}
	
	
	public void proceedToInfo(){
		infoString = eventInfo.nextLine();
		infoString = infoString.toLowerCase();
		addInfo(infoString);
		VictorMain.print(Arrays.toString(infoArray));
	}
	
	public void showEntry(){
		for(int i = 0; i < dayArray.length; i++){
			//if(dayArray[i].equals("null")){
			//	VictorMain.print(" ");
			//}
			//if(infoArray[i].equals("null")){
			//	VictorMain.print(" ");
			//}
			VictorMain.print(dayArray[i] + " - " + infoArray[i]);
			//VictorMain.print(Arrays.toString(dayArray[i])+" - "+Arrays.toString(infoArray[i]) + ";"); fix this
		}
		VictorMain.print(Arrays.toString(busyArray));
	}
	
	public void addInfo(String info){
		infoArray[infoCounter] = info;
		busyArray[busyCounter] = true; //used infoCounter before
		infoCounter++;
		busyCounter++;
	}
	
	public void updateBusiness(){
		busyArray[busyCounter] = true;
		busyCounter++;
	}
	
	public boolean validDay(String input){
		boolean valid = false;
		for(int i = 0; i < daysOfTheWeek.length; i++){
			if(input.equals(daysOfTheWeek[i])){
				valid = true;
			}
		}
		return valid;
	}
	
	private void formFields(){
		eventDay = new Scanner(System.in);
		eventInfo = new Scanner(System.in);
		leave = new Scanner(System.in);
	}
	
	public void addDay(String day){
		for(int i = 0; i < daysOfTheWeek.length; i++){
			if(day.equals(daysOfTheWeek[i])){
				boolean isFilled = false;
				while(isFilled == false){
					dayArray[dayCounter] = daysOfTheWeek[i];
					isFilled = true;
					dayCounter++;
				}
			}
		}
	}
}
