package helpers;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import cucumber.api.java.it.Date;
@Component
public class ScenarioContext {

    private HashMap<String, Object> map;
    public TestVariables vars;
    @Autowired
    TimeHelper time;

    public ScenarioContext() {
        map = new HashMap<String, Object>();

        vars = new TestVariables();
    }

    public void put(String key, Object value)  {
        map.put(key, value);
        if (this.contains("testName") && value.getClass() == String.class) {
            this.vars.setVariable((String) this.get("testName"), key, (String) value);
//            String StartDate = null;
//            try {
//                StartDate = time.toString((Date)this.get("testStart"), "yyyy-MM-dd'T'HH:mm:00");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            this.vars.setVariable((String) this.get("testName"), "testStart", StartDate);
        } else if(this.contains("testName")) {
            String json = Serializer.serialize(value);
            this.vars.setVariable((String) this.get("testName"), key, json);
        }
    }

    public Object get(String key) {
        if (map.containsKey(key)) {
            if (key.equals("testStart")) {
                try {
                    if (map.get(key).getClass() == String.class)
                        return TimeHelper.parseDate((String) map.get(key), "yyyy-MM-dd'T'HH:mm:00");

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return map.get(key);
        } else if (map.containsKey("testName")) {
            return this.vars.getVariable((String) map.get("testName"), key);

        } else
            return null;
    }

    public Object get(String key, TypeReference typeOf) throws ClassNotFoundException {
        if (map.containsKey("testName")) {
            String value = this.vars.getVariable((String) map.get("testName"), key);
            return Serializer.deSerialize(value,typeOf);
        }
        else
            return null;
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public void clear() {
        map.clear();
    }

    public void commit() throws IOException {
        this.vars.commit();
    }
}
