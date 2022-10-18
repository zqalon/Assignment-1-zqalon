package Assignment1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


public class A1 {
	
	public static void parseRawData(List<String> movies, List<String[]> movieData, List<String[]> allData) {
		
        for(int i = 4; i < allData.size(); i += 3) {
        	movies.add(allData.get(i)[0]);
        }
        
        for (int i = 5; i < allData.size(); i += 3) {
        	movieData.add(allData.get(i));
        }
		
	}

	public static void main(String args[]) throws Exception{
		
		try { 
	        FileReader filereader = new FileReader(args[0]); 
	  
	        CSVReader csvReader = new CSVReaderBuilder(filereader) 
                    .withSkipLines(1) 
                    .build(); 
	        List<String[]> allData = csvReader.readAll(); 
	        filereader.close();
	        
//	        List<String> movies = new ArrayList<String>();
//	        List<String[]> movieData = new ArrayList<String[]>();
//	        parseRawData(movies, movieData, allData);
	        
	        
	        
	        
	    } 
	    catch (Exception e) { 
	        throw new Exception(e);
	    } 
	}
	
}
