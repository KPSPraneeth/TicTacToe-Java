package Assignment1;

import java.util.Scanner;

public class PlayerMenu extends MainMenu {
	public String Name;
	
	// return the player name
	public String GetName(){
		return Name;
	}
	
	//set the player from the input
	public void SetName(String Name) {
			this.Name = Name;
	}
	
	// provide the user with appropriate games.
	public void GameChoose(){
		System.out.println("Please enter a name: "); // provide the user enter the name.
		Scanner input = new Scanner(System.in);
		String PlayerName = input.nextLine();
		while(PlayerName.isBlank()) { // check if the input is blank
			System.out.println("Please enter a valid input!");
			PlayerName = input.nextLine();
		}
		SetName(PlayerName); // set the player name
		
		System.out.println("Are you a VIP player(type Yes or No) to submit:"); // take the input if the user is vip player.
		String VipOrNot = input.nextLine().toLowerCase();
		if(VipOrNot.equals("yes")) { // if the user is a VIP then change the players name.
			Name = Name+" (VIP)";
		}
		LeaderBoard LeaderBoard = new LeaderBoard();
		int playerIndex = LeaderBoard.SetPlayer(Name); // send the final player name to leader board.
		
		System.out.println("Hello "+Name+" Please  choose a game, or -1 to quit:"); // provide the games to the user
		System.out.println("1. Guess a number.");
		System.out.println("2. Tic-Tac-Toe.");
		
		String choice = input.nextLine();
		while(choice.equals("1") || choice.equals("2")) { // check if the user selects 1 or 2
			if(choice.equals("1")) {
				NumberGuess GuessGame = new NumberGuess(); // if the choice is 1 then activate guessing game.
				int gamepoints = GuessGame.Guess();
				System.out.println(gamepoints);
				LeaderBoard.SetPoints(playerIndex, gamepoints);
			}
			else if(choice.equals("2")) { 
				TicTacToe TicTacToe = new TicTacToe(); // activate tic tac toe if the user selects 2 and create instance.
				int gamepoints = TicTacToe.main();
				System.out.println(gamepoints);
				LeaderBoard.SetPoints(playerIndex, gamepoints);
			}
			System.out.println("Hello "+Name+" Please  choose a game, or -1 to quit:");
			System.out.println("1. Guess a number.");
			System.out.println("2. Tic-Tac-Toe.");
			choice = input.nextLine();
		}
		if(!choice.equals("-1")) { // if choice is other than -1 then print error message.
			System.out.println("Please enter a valid input!.");
		}
	}
}
