package helpers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MobileCapablities {
    public String deviceJson;

    public MobileCapablities() throws IOException {
        this.deviceJson = FileUtils.readFileToString(new File("env/devices.json"));
    }
    public HashMap<String, String> getDeviceCapablities(String device) {
        JsonObject capablityObj = JsonParser.parseString(deviceJson).getAsJsonObject().get(device).getAsJsonObject();
        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        return new Gson().fromJson(capablityObj, type);
    }
}
