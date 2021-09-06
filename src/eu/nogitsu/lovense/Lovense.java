package eu.nogitsu.lovense;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import eu.nogitsu.lovense.toys.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lovense {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static URI domain;

    public static URI GetDomain(){
        return domain;
    }

    public static boolean Request(String action) {
        return Request(action, new HashMap<String, Object>() {});
    }

    public static boolean Request(String action, Map<String,Object> parameters){
        boolean found = Connect();
        if (!found){
            return false;
        }

        try {
            StringBuilder sb = new StringBuilder(action);
            sb.append("?");

            for(Map.Entry<String, Object> param : parameters.entrySet()){
                sb.append(param.getKey());
                sb.append("=");
                sb.append(param.getValue().toString());
                sb.append("&");
            }

            URL url = new URL(domain + sb.toString());
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");

            System.out.println(url);

            int code = http.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean Connect()
    {
        URI uri1 = URI.create("http://127-0-0-1.lovense.club:20010/");
        URI uri2 = URI.create("http://127-0-0-1.lovense.club:20011/");

        try {
            HttpRequest req = HttpRequest.newBuilder(uri1).build();
            client.send(req, HttpResponse.BodyHandlers.ofString());
            domain = uri1;

            return true; // 20010 found
        } catch (IOException | InterruptedException e1) {
            try {
                HttpRequest req = HttpRequest.newBuilder(uri2).build();
                client.send(req, HttpResponse.BodyHandlers.ofString());
                domain = uri2;

                return true; // 20011 found
            } catch (IOException | InterruptedException e2) {
                return false; // None found
            }
        }
    }

    public static Toy[] GetToys()
    {
        boolean found = Connect();
        if (!found){
            return null;
        }

        try {
            URL url = new URL(domain + "GetToys");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");

            int code = http.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {
                JsonReader reader = new JsonReader(new InputStreamReader(http.getInputStream()));
                JsonObject object = new Gson().fromJson(reader, JsonObject.class);

                List<Toy> toys = new ArrayList<>();

                for (Map.Entry<String,JsonElement> entry: object.getAsJsonObject("data").entrySet()){
                    JsonObject obj = entry.getValue().getAsJsonObject();

                    Toy toy = null;

                    switch (obj.get("name").getAsString()){
                        case "Ambi":
                            toy = new Ambi();
                            break;

                        case "Diamo":
                            toy = new Diamo();
                            break;

                        case "Domi":
                            toy = new Domi();
                            break;

                        case "Edge":
                            toy = new Edge();
                            break;

                        case "Ferri":
                            toy = new Ferri();
                            break;

                        case "Hush":
                            toy = new Hush();
                            break;

                        case "Lush":
                            toy = new Lush();
                            break;

                        case "Max":
                            toy = new Max();
                            break;

                        case "Mission":
                            toy = new Mission();
                            break;

                        case "Nora":
                            toy = new Nora();
                            break;

                        case "Osci":
                            toy = new Osci();
                            break;

                        case "Quake":
                            toy = new Quake();
                            break;

                        default:
                            toy = new Toy();
                            break;
                    }

                    toy.id = obj.get("id").getAsString();
                    toy.name = obj.get("name").getAsString();
                    toy.status = obj.get("status").getAsInt();
                    toy.battery = obj.get("battery").getAsInt();
                    toy.fVersion = obj.get("fVersion").getAsString();
                    toy.control = obj.get("control").getAsBoolean();
                    toy.name = obj.get("name").getAsString();
                    toy.mode = obj.get("mode").getAsString();
                    toy.version = obj.get("version").getAsString();

                    toys.add(toy);
                }

                return toys.toArray(new Toy[toys.size()]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
