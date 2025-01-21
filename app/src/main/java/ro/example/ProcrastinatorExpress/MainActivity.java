package ro.example.ProcrastinatorExpress;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {


    private TextView jokeTextView;
    private Button refreshButton;
    private TextView textfield1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ViewModel
;

        //jokeTextView = findViewById(R.id.jokeTextView);
        //refreshButton = findViewById(R.id.refreshButton);
        
        textfield1 = findViewById(R.id.title_text);


        Button quitButton = findViewById(R.id.quitButton);
        quitButton.setOnClickListener(v -> finishAffinity());


        // Observe LiveData from the ViewModel
//        mViewModel.getText().observe(this, joke -> {
//            jokeTextView.setText(joke);
//        });

        // Refresh the joke when the button is clicked
//        refreshButton.setOnClickListener(v -> mViewModel.fetchJoke());
    }
}
