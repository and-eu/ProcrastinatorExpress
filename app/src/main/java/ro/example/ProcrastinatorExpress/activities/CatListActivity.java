package ro.example.ProcrastinatorExpress.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import ro.example.ProcrastinatorExpress.R;

public class CatListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_list_activity);

        setCategoryButton(R.id.cat1, "family");
        setCategoryButton(R.id.cat2, "office");
        setCategoryButton(R.id.cat4, "children");
        setCategoryButton(R.id.cat5, "college");
        setCategoryButton(R.id.cat6, "party");
        setCategoryButton(R.id.cat7, "unbelievable");
        setCategoryButton(R.id.cat8, "developers");
        setCategoryButton(R.id.cat9, "gaming");

        // Back button functionality
        findViewById(R.id.backButton).setOnClickListener(view -> finish());
    }

    private void setCategoryButton(int buttonId, String category) {
        findViewById(buttonId).setOnClickListener(view -> {
            Intent intent = new Intent(CatListActivity.this, DetailActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        });
    }
}

