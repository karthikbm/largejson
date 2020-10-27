package kmatjsongenerator.largejson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Iterator;
import org.json.*;

import java.time.Instant;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;


public class GenerateLargeJSON {

	public static void main(String[] args) {
		long lines = 5_00_000; // 5 lakh
		
		BufferedWriter writetoFile = null;
		// FileWriter writetoFile = null; 
		String outputFile = "./resources/MKTO_Leads_"+ Instant.now().getEpochSecond()
									+"_"+lines+".json";
		
		try {
			writetoFile = new BufferedWriter(new FileWriter(new File(outputFile), true));
			writetoFile.write("[\n");
			
			ObjectMapper objectMapper = new ObjectMapper();
		    JsonNode rootNode = objectMapper.readTree(getJsonStringFromFile());
		    
		    long id = 100000, lineCount = 1;
		    while (lineCount <= lines) {
		    	
		    	id++;
		    	((ObjectNode) rootNode).put("lastName", "Matha_" + id );
			    ((ObjectNode) rootNode).put("firstName", "Karthik_" + id );
			    ((ObjectNode) rootNode).put("company", "KMAT_Company_" + id);
			    ((ObjectNode) rootNode).put("mktoName", 
			    							rootNode.path("lastName").asText() 
			    							+ " " + rootNode.path("firstName").asText());
			    
			    if (lineCount != lines) {
			    	writetoFile.write(objectMapper.writeValueAsString(rootNode)+",\n");
				} else {
					writetoFile.write(objectMapper.writeValueAsString(rootNode)+"\n");
				}
			    
		    	++lineCount;
			    
			    // System.out.println(rootNode.path("mktoName"));
			}
		    writetoFile.write("]");
		    
		} catch (Exception e) {e.printStackTrace();}
		finally {if (writetoFile !=null) {
			try { writetoFile.flush();
			writetoFile.close();;writetoFile = null;} 
			catch (Exception fe) {fe.printStackTrace();}
		}}
		
	}
	
	public static String getJsonStringFromFile() {
		BufferedReader br = null;
		try {
			br =  new BufferedReader(
					new FileReader(
						new File("./resources/inputJson")));
			return br.readLine();
		} 
		catch (Exception e) {e.printStackTrace();}
		finally {
			if (br != null) {
				try { br.close(); br = null;} 
				catch (IOException e) {e.printStackTrace();}
			}}
		return null;
	}

}

// ****************************************************************************************************

/*
 * int cnt = 0; Iterator<String> itr = jsonObj.keys(); while (itr.hasNext()) {
 * String key = (String) itr.next(); System.out.println(++cnt + "\t" + key +
 * "\t" + jsonObj.get(key)); }
 */
