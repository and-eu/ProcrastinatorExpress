package ro.example.ProcrastinatorExpress.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ro.example.ProcrastinatorExpress.R;
import ro.example.ProcrastinatorExpress.services.CategoryService;

public class DetailActivity extends AppCompatActivity {

    private TextView excuseView;
    private CategoryService categoryService;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        excuseView = findViewById(R.id.escuseView);
        categoryService = new CategoryService();

        category = getIntent().getStringExtra("category");
        loadExcuse();

        findViewById(R.id.refresh_btn).setOnClickListener(view -> loadExcuse());

        // Back button functionality
        findViewById(R.id.backButton).setOnClickListener(view -> finish());
    }

    private void loadExcuse() {
        categoryService.getExcuse(category, new CategoryService.ExcuseCallback() {
            @Override
            public void onSuccess(String excuse) {
                runOnUiThread(() -> excuseView.setText(excuse));
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> Toast.makeText(DetailActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show());
            }
        });
    }
}
