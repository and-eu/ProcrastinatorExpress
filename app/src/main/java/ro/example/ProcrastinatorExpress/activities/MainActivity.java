package ro.example.ProcrastinatorExpress.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import ro.example.ProcrastinatorExpress.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigate to Category List
        findViewById(R.id.categ_btn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CatListActivity.class);
            startActivity(intent);
        });

        // Navigate to Chat Activity
        findViewById(R.id.chat_btn).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
        });

        // Quit the application
        findViewById(R.id.quit_btn).setOnClickListener(view -> {
            finish();
            System.exit(0);
        });
    }
}
