package helpers;


import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonPathParser {
	private DocumentContext jsonObj;

	public JsonPathParser(String body)
	{
		jsonObj = JsonPath.parse(body);
	}
	
	public JsonPathParser(File path) throws Exception
	{
		this.jsonObj = JsonPath.parse(path);
	}
	

	public String getString(String path)
	{
		
		return jsonObj.read(path).toString();
	}
	
	public List<String> getList(String path)
	{
		//TypeRef<List<String>> typeRef = new TypeRef<List<String>>() {};
		List<String> results = jsonObj.read(path);
		return  results;
	}
	
	public Double getAsDouble(String path)
	{
		
		return jsonObj.read(path);
	}
	
	public Integer getAsInteger(String path)
	{
		
		return jsonObj.read(path);
	}
	
	public void updateString(String path, String value)
	{
	//	JSONArray 
		jsonObj.set(path, value);
	}
	
	public void updateMultiplevalues(Map<String,String> updates)
	{
		Set<String> set =updates.keySet();
		for(String keys:set)
		{
			jsonObj.set(keys, updates.get(keys));
		}
		
	}
	
	public void updateDouble(String path, Double value)
	{
		jsonObj.set(path, value);
	}
	
	public String getJsonString()
	{
		return jsonObj.jsonString();
	}
	
	
	public static void main(String[] args) throws Exception
	{
		String json= FileUtils.readFileToString(new File("env/qa.json"));
		JsonPathParser p = new JsonPathParser(json);
		List<String> value = p.getList("$.users[?(@.name=='nina')].fullName");
		System.out.println(value.get(0));
	}
}
