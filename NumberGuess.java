package Assignment1;

import java.util.Random;
import java.util.Scanner;

public class NumberGuess extends PlayerMenu {
	
	public int Guess() {
		Random r = new Random();
		int NoOfMoves = 0;
		int low = 0;
		int high = 100;
		int result = r.nextInt(high-low) + low; //to generate an integer random number 
		Scanner input = new Scanner(System.in);  // to read the input from the user
		System.out.println("Guess a number from 0 - 100. ");	
		int GussesNum = input.nextInt();
		NoOfMoves = NoOfMoves+1;
		
		while(GussesNum != result) {		//enters the while loop when input and random number are not equal else it is correct
			NoOfMoves = NoOfMoves+1;
			if(GussesNum < result) {
				System.out.println("Too low.");		//if the guessed number is less than the random number it gives too low
			}
			else {
				System.out.println("Too high.");	//if the guessed number is greater than the random number it gives too high
			}
			System.out.println("Enter a number:");
			GussesNum = input.nextInt();
		}
		System.out.println("Correct!");
		if(NoOfMoves <= 3) { // check if no of moves are less than or equal to 3
			return 20; // return the points 20 
		}
		else if(NoOfMoves > 3 && NoOfMoves <= 6) {
			return 15;
		}
		else if(NoOfMoves > 6 && NoOfMoves <= 9){
			return 10;
		}
		else { // if the no of moves are greater than 9 then assign 5 points.
			return 5;
		}
	}
}
