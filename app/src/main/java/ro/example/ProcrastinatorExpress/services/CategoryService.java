package ro.example.ProcrastinatorExpress.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CategoryService {

    public interface ExcuseCallback {
        void onSuccess(String excuse);
        void onError(String error);
    }

    public void getExcuse(String category, ExcuseCallback callback) {
        new Thread(() -> {
            try {
                String apiUrl = "https://excuser-three.vercel.app/v1/excuse/" + category + "/";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();
                    String excuse = parseExcuse(response.toString());
                    callback.onSuccess(excuse);
                } else {
                    callback.onError("Error: " + responseCode);
                }
                connection.disconnect();
            } catch (Exception e) {
                callback.onError(e.getMessage());
            }
        }).start();
    }

    private String parseExcuse(String json) {
        int start = json.indexOf("\"excuse\":\"") + 10;
        int end = json.indexOf("\",", start);
        return json.substring(start, end);
    }
}
