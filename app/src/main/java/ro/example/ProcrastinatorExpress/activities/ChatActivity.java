package ro.example.ProcrastinatorExpress.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ro.example.ProcrastinatorExpress.R;
import ro.example.ProcrastinatorExpress.services.ChatService;

public class ChatActivity extends AppCompatActivity {

    private EditText chatInput;
    private TextView chatResponse;
    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        chatInput = findViewById(R.id.chat_input);
        chatResponse = findViewById(R.id.chat_response);
        Button sendButton = findViewById(R.id.send_btn);
        Button backButton = findViewById(R.id.backButton);

        chatService = new ChatService(this);

//        // Listener pentru focus pe EditText
//        chatInput.setOnFocusChangeListener((view, hasFocus) -> {
//            if (hasFocus) {
//                chatInput.setText(""); // Eliminăm textul când câmpul este selectat
//            } else if (chatInput.getText().toString().trim().isEmpty()) {
//                chatInput.setText("Reason"); // Setăm textul din nou dacă utilizatorul nu a introdus nimic
//            }
//        });

        // Trimitere mesaj către API
        sendButton.setOnClickListener(view -> {
            String userMessage = chatInput.getText().toString().trim();
            if (!userMessage.isEmpty()) {
                sendMessage(userMessage);
            } else {
                Toast.makeText(this, "Please enter a message.", Toast.LENGTH_SHORT).show();
            }
        });

        // Buton pentru întoarcere
        backButton.setOnClickListener(view -> finish());
    }

    private void sendMessage(String message) {
        chatService.getChatResponse(message, new ChatService.ChatCallback() {
            @Override
            public void onSuccess(String response) {
                runOnUiThread(() -> chatResponse.setText(response));
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> Toast.makeText(ChatActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show());
            }
        });
    }
}
