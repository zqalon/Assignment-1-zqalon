package Assignment1;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


public class A1 {

	public static void main(String args[]) throws Exception{
		
		try { 
	        // Create an object of file reader 
	        // class with CSV file as a parameter. 
	        FileReader filereader = new FileReader(args[0]); 
	  
	        // create csvReader object and skip first Line 
	        CSVReader csvReader = new CSVReader(filereader);
	        List<String[]> allData = csvReader.readAll(); 
	  
	        // print Data 
	        for (String[] row : allData) { 
	            for (String cell : row) { 
	                System.out.print(cell + "\t"); 
	            } 
	            System.out.println(); 
	        } 
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	}
	
}
