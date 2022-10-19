package Assignment1;

import java.util.ArrayList;
import java.util.List;
/**
 * encapsulates the name, movies, and roles belonging to an actor*
 * @author Zachary Alon
 * @Date 10/18/2022
 */
public class Actor {
	
	String name;
	List<String> movies = new ArrayList<String>();
	List<String> roles = new ArrayList<String>();
	
	/**
	 * constructs an actor object*
	 * @param n - the actor's name
	 */
	public Actor(String n) {
		
		name = n;
		
	}
	
	/**
	 * gets the actor's name*
	 * @return - the actor's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * gets the movies the actor was in*
	 * @return - a List<String> of movies the actor starred in
	 */
	public List<String> getMovies() {
		return movies;
	}
	
	/**
	 * gets the roles the actor had*
	 * @return - a List<String> of roles the actor had
	 */
	public List<String> getRoles() {
		return roles;
	}
	
	/**
	 * adds a role the actor*
	 * @param movie - name of the movie
	 * @param role - name of the role/character
	 */
	public void addRole(String movie, String role) {
		movies.add(movie);
		roles.add(role);
	}
	
	/**
	 * gets the name of a role an actor played in a certain movie*
	 * @param movie - the name of the movie for the role to be found
	 * @return - the name of the role the actor played, or if the actor wasn't in the movie, "Role not found"
	 */
	public String getRole(String movie) {
		
		for(int i = 0; i < movies.size(); i++) {
			if (movies.get(i).compareTo(movie) == 0) {
				return roles.get(i);
			}
		}
		
		return "Role not found";
		
	}

}
