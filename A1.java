package Assignment1;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;


public class A1 {

	public static void main(String args[]) throws Exception{
		
		try { 
	        FileReader filereader = new FileReader(args[0]); 
	  
	        CSVReader csvReader = new CSVReader(filereader);
	        List<String[]> allData = csvReader.readAll(); 
	    } 
	    catch (Exception e) { 
	        throw new Exception(e);
	    } 
	}
	
}
