package helpers;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import java.io.IOException;
import java.lang.reflect.Type;

public class Serializer {
    static String serialize(Object obj){
      //  String json = new Gson().toJson(obj);
        String json="";
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    static Object deSerialize(String json, TypeReference any)  {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json,any);
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }

        //return null; // new Gson().fromJson(json, type);
    }

}
