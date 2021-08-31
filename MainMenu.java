package Assignment1;

import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		System.out.println("Please choose an option: "); // print the options to choose the player mode.
		Scanner input = new Scanner(System.in); 
		
		System.out.println("1. New Player");
		System.out.println("2. Quit");
		
		String Option = input.nextLine();
		while(true) {
			if(Option.equals("1")) {	//check if the input is 1
				PlayerMenu P = new PlayerMenu();
				P.GameChoose();	// trigger the gamechoose method in PlayerMenu class.
			}
			else if(Option.equals("2")) {	// check if the input is 2
				LeaderBoard LeaderBoard = new LeaderBoard();
				LeaderBoard.SortLeaderBoard(); // print the leader board with points.
				break;
			}
			else {
				System.out.println("Please enter a valid input!!");
			}
			System.out.println("Please choose an option: ");
			System.out.println("1. New Player");
			System.out.println("2. Quit");
			Option = input.nextLine();
		}
		System.out.println("Run the program to play again!!");
		input.close(); // close the scanner input.
	}
	
}
