package ro.example.ProcrastinatorExpress.services;

import android.content.Context;
import android.content.res.Resources;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import ro.example.ProcrastinatorExpress.R;

public class ChatService {

    private final String apiUrl = "https://fresedgpt.space/v1/chat/completions";
    private final String apiKey;

    public interface ChatCallback {
        void onSuccess(String response);
        void onError(String error);
    }

    public ChatService(Context context) {
        Resources res = context.getResources();
        apiKey = res.getString(R.string.api_key);
    }

    public void getChatResponse(String userMessage, ChatCallback callback) {
        new Thread(() -> {
            try {
                // Construim request-ul JSON
                JSONObject requestBody = new JSONObject();
                requestBody.put("model", "gpt-3.5-turbo");

                JSONArray messages = new JSONArray();
                JSONObject systemMessage = new JSONObject();
                systemMessage.put("role", "system");
                systemMessage.put("content", "You are a helpful assistant and respond instead of me in short message. Your response will not be very seriously.");
                messages.put(systemMessage);

                JSONObject userMessageObj = new JSONObject();
                userMessageObj.put("role", "user");
                userMessageObj.put("content", "Formulate an excuse for " + userMessage);
                messages.put(userMessageObj);

                requestBody.put("messages", messages);

                // Configurăm conexiunea HTTP
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Authorization", apiKey);
                connection.setDoOutput(true);

                // Scriem request-ul în output stream
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(requestBody.toString());
                writer.flush();
                writer.close();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Parsăm răspunsul
                    String chatResponse = parseChatResponse(response.toString());
                    callback.onSuccess(chatResponse);
                } else {
                    callback.onError("Error: " + responseCode);
                }
                connection.disconnect();
            } catch (Exception e) {
                callback.onError(e.getMessage());
            }
        }).start();
    }

    private String parseChatResponse(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray choices = jsonObject.getJSONArray("choices");
            return choices.getJSONObject(0).getJSONObject("message").getString("content");
        } catch (Exception e) {
            return "Error parsing response.";
        }
    }
}
