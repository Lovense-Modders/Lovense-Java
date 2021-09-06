package eu.nogitsu.lovense;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Toy {
    public String id;
    public String name;
    public int status;
    public int battery;
    public String fVersion;
    public boolean control;
    public String nickName;
    public String mode;
    public String version;

    public boolean Request(String action){
        return Lovense.Request(action,Map.of("t",id));
    }

    public boolean Request(String action, Map<String,Object> parameters){
        Map<String,Object> params = new HashMap<>();

        for(Map.Entry<String,Object> entry : parameters.entrySet()){
            params.put(entry.getKey(),entry.getValue());
        }

        params.put("t", id);
        return Lovense.Request(action,params);
    }

    public boolean Vibrate(int speed){
        return Request("Vibrate",Map.of("v", speed));
    }

    public boolean AVibrate(int speed, double seconds){
        return Request("AVibrate",Map.of("v", speed, "sec", seconds));
    }

    public int Battery(){
        boolean found = Lovense.Connect();
        if (!found){
            return 0;
        }

        try {
            StringBuilder sb = new StringBuilder("Battery?t=");
            sb.append(id);

            URL url = new URL(Lovense.GetDomain() + sb.toString());
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");

            int code = http.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {
                JsonReader reader = new JsonReader(new InputStreamReader(http.getInputStream()));
                JsonObject object = new Gson().fromJson(reader, JsonObject.class);

                return object.get("data").getAsInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
