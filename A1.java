
package Assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Runs the user interface and parses, sorts, and searches the csv file* 
 * @author Zachary Alon
 * @date 10/18/2022
 */
public class A1 {
	
	/**
	 * Adds an actor's role to a list of actors*
	 * @param movie - the movie the role was in
	 * @param role - the name of the character the actor played
	 * @param actorName - the name of the actor who starred in the movie
	 * @param actors - the List of actors for the role to be added to 
	 */
	private static void addRoletoList(String movie, String role, String actorName, List<Actor> actors) {
		boolean hasActor = false;
	
		int actorIndex = 0;
		//checks if the actor is already in the arrayList
		for(int i = 0; i < actors.size(); i++){
			if (actors.get(i).getName().compareTo(actorName) == 0) {
				hasActor = true;
				actorIndex = i;
			}
		}
		
		
		if (hasActor) {
			actors.get(actorIndex).addRole(movie, role);
		} else {
			//creates a new Actor object and adds it to the actors List
			Actor newActor = new Actor(actorName);
			newActor.addRole(movie, role);
			actors.add(newActor);
		}
		
	}
	
	/**
	 * Parses one line of the input movie csv file*
	 * @param line - the line of the movie csv file to be parsed
	 * @param actors - List of actors for the data to be recorded into
	 */
	private static void parseLine(String line, List<Actor> actors) {
		
		String movie = getMovie(line);
		int index = line.indexOf(movie) + movie.length();
		
		while (line.indexOf("character", index) >= 0){
			String character = "";
			//uses indexOf to find the character field in the line
			//uses index to keep track of which part of the file has already been parsed
			index = line.indexOf("character", index) + 11;
			while(line.charAt(index) == '\"' || line.charAt(index) == ':' || line.charAt(index) == ' ') {
				index++;
			}
			while(line.charAt(index) != '\"' && line.charAt(index) != ':') {
				character += line.charAt(index);
				index++;
			}
			
			//same method as character to find the actor's name attatched to the character
			index = line.indexOf("name", index) + 4;
			String actorName = "";
			while(line.charAt(index) == '\"' || line.charAt(index) == ':' || line.charAt(index) == ' ') {
				index++;
			}
			while(line.charAt(index) != '\"' && line.charAt(index) != ':') {
				actorName += line.charAt(index);
				index++;
			}
			
			addRoletoList(movie, character, actorName, actors);
			
		}
	}
	
	/**
	 * gets the movie from a line of the movie csv file
	 * @param line - the line for the movie to be derived from
	 * @return - the movie that was in the line of the csv file
	 */
	private static String getMovie(String line) {
		String result = "";
		int index = 0;
		
		//pushes the index past the movie id and to the beginning of the movie name field
		while(line.charAt(index) != ',') {
			index++;
		}
		index++;
		
		boolean hasQuotes = line.charAt(index) == '\"';
		if (hasQuotes)
			index++;
		
		//adds characters to result until the field ends
		do {
			result += line.charAt(index);
			index++;
		} while(line.charAt(index) != '\"' && line.charAt(index) != '[');
			
		
		
		if (!hasQuotes)
			result = result.substring(0, result.length() - 1);
		
		return result;
		
	}
	
	/**
	 * takes data from the csv file and parses it into a List of actors*
	 * @param scan - the Scanner object that has the csv file
	 * @param actors - the List for the actors from the csv file to be added into
	 */
	private static void parseRawData(Scanner scan, List<Actor> actors) {
		
        List<String> allData = new ArrayList<String>();
        while(scan.hasNextLine()) {
        	allData.add(scan.nextLine());
        }
        
        
        for(int i = 1; i < allData.size(); i++) {
        	
        	parseLine(allData.get(i), actors);
        	
        }
        
		
	}
	
	/**
	 * Sorts a List of Actor objects alphabetically according to their name*
	 * @param actors - the List to be sorted
	 */
	private static void insertionSort(List<Actor> actors) {
		//insertion sort algorithm
		for (int i = 1; i < actors.size(); i++) {  
	        Actor temp = actors.get(i);    //copies to temp variable
	        int j = i - 1; 
	        while (j >= 0 && actors.get(j).getName().compareTo(temp.getName()) > 0) {    // moves the next object in the List into place
	            actors.set(j + 1, actors.get(j));
	            --j;
	        }
	        actors.set(j + 1, temp);  
	    }
		
	}
	
	/**
	 * uses binary search algorithm to search for an actor using their name*
	 * @param actors - List of actors to be searched, must be sorted by name alphabetically
	 * @param target - actor name to be searched for
	 * @return - index of the actor in the List, if the actor is not in the list, returns closest actor alphabetically
	 */
	private static int binarySearch(List<Actor> actors, String target) {
		
		return binarySearchHelper(actors, 0, actors.size() - 1, target.toLowerCase());
		
	}
	
	/**
	 * uses binary search algorithm to search for an actor using their name*
	 * @param actors - List of actors to be searched, must be sorted by name alphabetically
	 * @param low - lower bound of the binary search
	 * @param high - higher bound of the binary search
	 * @param target - actor name to be searched for
	 * @return index of the actor in the List, if the actor is not in the list, returns closest actor alphabetically
	 */
	private static int binarySearchHelper(List<Actor> actors, int low, int high, String target) {
		
		if (low > high) {
			return high;
		}
		
		int mid = (low + high) / 2;
		//checks middle index
		if (target.compareTo(actors.get(mid).getName().toLowerCase()) == 0) {
			return mid;
		}
		//checks higher half if target is greater, lower if target is lower
		if (target.compareTo(actors.get(mid).getName().toLowerCase()) > 0) {
			return binarySearchHelper(actors, mid + 1, high, target);
		}
		if (target.compareTo(actors.get(mid).getName().toLowerCase()) < 0) {
			return binarySearchHelper(actors, low, mid - 1, target);
		}
		
		return -1;
	}
	
	/**
	 * Displays an actor's movie wall*
	 * @param actor - actor to be displayed
	 */
	private static void displayActor(Actor actor) {
		System.out.println("Actor: " + actor.getName());
		//prints out all movies and roles
		for(int i = 0; i < actor.getMovies().size(); i++) {
			System.out.println("* Movie: " + actor.getMovies().get(i) + " as " + actor.getRoles().get(i));
		}
		System.out.println();
	}

	/**
	 * Allows the user to view movie walls of actors in a movie csv file*
	 * @param args - args[0] must be the directory of the movie csv file
	 * @throws FileNotFoundException - if args[0] is not the movie csv file
	 */
	public static void main(String args[]) throws FileNotFoundException{
		
		try { 
	        Scanner scan = new Scanner(new File(args[0]));
	        List<Actor> actors = new ArrayList<Actor>();
	        parseRawData(scan, actors);
	        scan.close();
	        
	        insertionSort(actors);
	        
	        Scanner user = new Scanner(System.in);
	        
	        System.out.println("Welcome to the Movie Wall!");
	        String input = "";
	        while(!input.equalsIgnoreCase("EXIT")) {
		        
		        System.out.print("Enter the name of an actor (or \"EXIT\" to quit): ");
		        input = user.nextLine();
		        
		        if (!input.equalsIgnoreCase("EXIT")) {
		        
			        Actor searchedActor = actors.get(binarySearch(actors, input));
			        
			        if (!searchedActor.getName().equalsIgnoreCase(input)) {
			        	System.out.print("No such actor. Did you mean \"" + searchedActor.getName() + "\" (Y/N): ");
			        	String input2 = user.nextLine();
			        	
			        	while(!input2.equalsIgnoreCase("Y") && !input2.equalsIgnoreCase("N")) {
			        		System.out.print("Please enter a valid option (Y/N): ");
			        		input2 = user.nextLine();
			        	}
			        	
			        	if (input2.equalsIgnoreCase("Y")) {
			        		displayActor(searchedActor);
			        	}
			        } else {
			        	displayActor(searchedActor);
			        }
		        
		        }
	        }
	        System.out.println("Thanks for using the Movie Wall!");
	        user.close();
	    } 
	    catch (Exception e) { 
	        throw new FileNotFoundException();
	    } 
	}
	
}
