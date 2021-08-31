package Assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe extends PlayerMenu {
	// declare 2 arraylist to store the user and computer moves
	static ArrayList<Integer> UserPositions = new ArrayList<Integer>();
	static ArrayList<Integer> CompPositions = new ArrayList<Integer>();
	int Positons[] = {1,2,3,4,5,6,7,8,9};
	static int MovesCount = 0; // declare the variable to check the player moves
	static String Winner = ""; // declare a variable to check if user wins.
	public static int main() {
		// create a board to play
		char[][] Board = {{' ', '|', ' ', '|', ' '}, {'-', '-', '-', '-', '-'},
						{' ', '|', ' ', '|', ' '}, {'-', '-', '-', '-', '-'},
						{' ', '|', ' ', '|', ' '}};
		
		printBoard(Board);
		
		while(true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter your position between 1-9: ");
			int UserPos = input.nextInt(); // take the input position from the user
			while(UserPositions.contains(UserPos) || CompPositions.contains(UserPos)) {
				System.out.println("Position taken!! Enter a correct position");
				MovesCount = MovesCount+1;
				UserPos = input.nextInt();
			}
			
			placepiece(Board, UserPos, "user");
			Winner = checkWinner();
			if(Winner.length() > 0) { // check if the user wins
				printBoard(Board);
				System.out.println(Winner);
				UserPositions.clear();
				CompPositions.clear();
				break;
			}
			
			Random r = new Random(); 
			int compPos = r.nextInt(9) + 1; // initialise a random value to the variable.
			
			// check if user or system positions is already taken
			while(UserPositions.contains(compPos) || CompPositions.contains(compPos)) { 
				compPos = r.nextInt(9) + 1;
			}
			// update the board with selected input.
			placepiece(Board, compPos, "comp");
			
			printBoard(Board);
			Winner = checkWinner(); // check if the user wins.
			if(Winner.length() > 0) {
				System.out.println(Winner);
			}
		}
		// return the points according to the no of moves.
		if(MovesCount <= 3 && Winner.length() > 0) { 
			MovesCount = 0;
			return 20;
		}
		else if(MovesCount > 3 && Winner.length() > 0){
			MovesCount = 0;
			return 10;
		}
		else {
			MovesCount = 0;
			return 0;
		}
	}
	
	public static void printBoard(char[][] Board) { // print the board to the console after each move.
		for(char[] row : Board) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	// update the board with user or systems move.
	public static void placepiece(char[][] Board, int pos, String user) { 
		char symbol = ' ';
		
		if(user.equals("user")) { // check if the move is made by user or system
			symbol = 'x';
			UserPositions.add(pos); // add the user move to the array list
			MovesCount = MovesCount+1;
		}
		else if(user.equals("comp")) { // check if the move is made by system.
			symbol = 'o';
			CompPositions.add(pos);
		}
		
		switch(pos) { // update the board with appropriate symbol
			case 1:
				Board[0][0] = symbol;
				break;
			case 2:
				Board[0][2] = symbol;
				break;
			case 3:
				Board[0][4] = symbol;
				break;
			case 4:
				Board[2][0] = symbol;
				break;
			case 5:
				Board[2][2] = symbol;
				break;
			case 6:
				Board[2][4] = symbol;
				break;
			case 7:
				Board[4][0] = symbol;
				break;
			case 8:
				Board[4][2] = symbol;
				break;
			case 9:
				Board[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	// ckeck if the user wins with appropriate places.
	public static String checkWinner() {
		// add all the possibilites for the user to win.
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List lastRow = Arrays.asList(7,8,9);
		List leftcol = Arrays.asList(1,4,7);
		List midcol = Arrays.asList(2,5,8);
		List rightcol = Arrays.asList(3,6,9);
		List diag1 = Arrays.asList(1,5,9);
		List diag2 = Arrays.asList(7,5,3);
		
		List<List> WinningCond = new ArrayList<List>();
		WinningCond.add(topRow);
		WinningCond.add(midRow);
		WinningCond.add(lastRow);
		WinningCond.add(leftcol);
		WinningCond.add(midcol);
		WinningCond.add(rightcol);
		WinningCond.add(diag1);
		WinningCond.add(diag2);

		for(List l: WinningCond) { // check if user moves matches with the above conditions
			if(UserPositions.containsAll(l)) { 
				return "Congratulations you won"; // return the winning message
			}
			else if(CompPositions.containsAll(l)) {
				return "Computer wins! Better luck next time";
			}
			else if(UserPositions.size() + CompPositions.size() == 9) { // check if the user or system does not win.
				return "TIE Match!";
			}
		}
		return "";
	}
}
