package kmatcsvgenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class GenerateLargeCSV {

	public static void main(String[] args) {
		long lines = 125_00_000 ; // 64 million records
		
		try (
			Reader reader = Files.newBufferedReader(Paths.get(
											("./resources/inputCSV.csv")));
			CSVWriter writer = new CSVWriter(
								new FileWriter(new File(
										"./resources/outputCSV_"+ Instant.now().getEpochSecond()
										+"_"+lines+".csv"))
								, CSVWriter.DEFAULT_SEPARATOR
								, CSVWriter.NO_QUOTE_CHARACTER
								, CSVWriter.DEFAULT_ESCAPE_CHARACTER
								, CSVWriter.DEFAULT_LINE_END);		
			){
			
			
			List<String[]> csvRead = oneByOne(reader);
			String[] headerArray = csvRead.get(0);
			writer.writeNext(headerArray);
			String[] templateArray = csvRead.get(1);
//			System.out.println(Arrays.asList(templateArray));
			
			long id = 100000, lineCount = 1; 
			while (lineCount <= lines) { 
				id++;
				
				templateArray[0] = "Karthik_"+id;
				templateArray[14] = "KMAT_"+id;
				templateArray[23] = id+"KMAT";
				templateArray[26] = id+"kmat@softwareag.com";
				//System.out.println(Arrays.asList(templateArray));
				writer.writeNext(templateArray);
				
				++lineCount;
			}
			 
			
		} catch (IOException e) {	e.printStackTrace();
		} catch (Exception e) {	e.printStackTrace();
		}

	}
	
	public static List<String[]> oneByOne(Reader reader) throws Exception {
	    List<String[]> list = new ArrayList<>();
	    CSVReader csvReader = new CSVReader(reader);
	    String[] line;
	    while ((line = csvReader.readNext()) != null) {
	        list.add(line);
	    }
	    reader.close();
	    csvReader.close();
	    return list;
	}
	


}


//	*********************************************************************************************

//CSVReader csvReader = new CSVReader(reader);
//List<String[]> list = new ArrayList<>();
//list = csvReader.readAll();
//reader.close();
//csvReader.close();


//String[] tempArr = Arrays.copyOf(templateArray,templateArray.length);
//tempArr[0] = "Karthik_"+id;
//tempArr[14] = "KMAT_"+id;
//tempArr[23] = id+"KMAT";
//tempArr[26] = id+"kmat@softwareag.com";
//System.out.println(Arrays.asList(tempArr));