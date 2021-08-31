package Assignment1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LeaderBoard extends MainMenu {
	public int points = 0;
	// declare 2 arraylists to store the player names and points to them
	static ArrayList<String> AllPlayers = new ArrayList<String>();
	static ArrayList<Integer> AllPoints = new ArrayList<Integer>();

	public int SetPlayer(String name) { // take the player name and store it in array.
		AllPlayers.add(name);
		if(name.contains("(VIP)")) { // set extra points if the player is VIP
			AllPoints.add(points+10); // VIP player gets additional 10 points.
		}
		else {
			AllPoints.add(points);
		}
		return AllPlayers.indexOf(name); // return index of that player for further updation of points.
	}
	
	public void SetPoints(int index, int NewPoints) { // set the points based on the index value of that player
		int PrevPoints = AllPoints.get(index);
		PrevPoints = PrevPoints+NewPoints;
		AllPoints.set(index, PrevPoints);
		String PrevName = AllPlayers.get(index); // set player as VIP when points are greater than 60.
		if(PrevPoints >= 60 && !PrevName.contains("(VIP)")) { // if player points are greater than 60 then make that user as VIP.
			PrevName = PrevName+" (VIP)";
			AllPlayers.set(index, PrevName);
			AllPoints.set(index, PrevPoints+10);
		}
		
	}
	
	public void SortLeaderBoard() { // to sort the array based on points and write it to file.
		Integer[] FinalPoints = new Integer[AllPoints.size()];
		String[] FinalPlayers = new String[AllPlayers.size()];
		FinalPoints = AllPoints.toArray(FinalPoints); // convert arraylist to arrays
		FinalPlayers = AllPlayers.toArray(FinalPlayers);
		int TempPoints;
		String TempPlayer;
		
		for (int i = 0; i < FinalPoints.length; i++) // sort both the arrays
        {
            for (int j = i + 1; j < FinalPoints.length; j++) 
            {
                if (FinalPoints[i] > FinalPoints[j]) 
                {
                	TempPoints = FinalPoints[i];
                	TempPlayer=FinalPlayers[i];

                	FinalPoints[i] = FinalPoints[j];
                	FinalPlayers[i] = FinalPlayers[j];

                    FinalPoints[j] = TempPoints;
                    FinalPlayers[j] = TempPlayer;
                }
            }
        }
		System.out.println();
		System.out.print("Player : Points\n"); // print the final player list on the console.
		for (int i = 0; i < FinalPlayers.length; i++) {
			System.out.print(FinalPlayers[i]);
			System.out.print(" : \t");
			System.out.print(FinalPoints[i]+"\n");
		}
		
		// write the final list to the text file
		String Desktop = System.getProperty("user.home");// take the home folder i.e. to save file on desktop
		File textFile = new File(Desktop, "LeaderBoard.txt"); // create a text file on home folder.
		PrintWriter output = null;
		try {
			// Create a PrintWriter object 
			output = new PrintWriter(new FileWriter(textFile, false)); //false to overwrite the file data
			output.println("Player : Points"); // print the results
			for (int i = 0; i < FinalPlayers.length; i++) { // loop through the elements in the arrays and write it to the file.
				output.print(FinalPlayers[i]);
				output.print(" : ");
				output.print(FinalPoints[i]+"\n");
			}
		
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// Close the PrintWriter instance
			if (output != null)
				output.close();
		}
	}
}
