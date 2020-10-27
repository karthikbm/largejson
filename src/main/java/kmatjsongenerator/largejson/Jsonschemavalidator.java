package kmatjsongenerator.largejson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Hello world!
 *
 */
public class Jsonschemavalidator 
{
    public static void main( String[] args )
    {
    	try {
			JSONObject rawJsonSchema = new JSONObject(
										new JSONTokener(
											new FileInputStream("./resources/OCT14_KMAT_MKTO_Lead_Schema.json")
												));
			Schema schema = SchemaLoader.load(rawJsonSchema);
			//System.out.println(schema.describeTo(writer));
			//https://github.com/joelittlejohn/jsonschema2pojo/wiki/Getting-Started#the-maven-plugin
			
		} catch (JSONException e) {	e.printStackTrace();
		} catch (FileNotFoundException e) {e.printStackTrace();
		}
    }
}
