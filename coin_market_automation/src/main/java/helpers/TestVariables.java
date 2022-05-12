package helpers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
@Component
public class TestVariables {
	public Map<String,Map<String,String>> test;
	File f;
	Gson gson;
	@SuppressWarnings("unchecked")
	public TestVariables() {
		this.test = new HashMap<String, Map<String,String>>();
		f = new File("temp/testVariables.json");
		gson = new Gson();
		if(this.f.exists()) {
			
			try {
				this.test = this.gson.fromJson(FileUtils.readFileToString(this.f), this.test.getClass());
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getVariable(String test,String variable) {
		return this.test.get(test).get(variable);
	}

	public void setVariable(String test, Map<String,String> variables) {
		if(this.test.containsKey(test)) {
			this.test.get(test).putAll(variables);
		}
		else {
			this.test.put(test, variables);
		}
	}
	
	public void setVariable(String test, String key, String value) {
		if(this.test.containsKey(test)) {
			this.test.get(test).put(key, value);
		}
		else {
			HashMap<String,String> mp = new HashMap<String,String>();
			mp.put(key, value);
			this.test.put(test, mp);
		}
	}
	
	public void commit() throws IOException {
		FileUtils.write(this.f, this.gson.toJson(this.test));
	}
}
