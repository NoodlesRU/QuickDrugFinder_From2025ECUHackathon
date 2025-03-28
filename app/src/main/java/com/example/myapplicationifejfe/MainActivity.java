package com.example.myapplicationifejfe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private Button enterButton;
    private TextView random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Set the insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize SearchView
        searchView = findViewById(R.id.searchView);
        enterButton = findViewById(R.id.button2); // Find the Enter button by its ID
        random = findViewById(R.id.searchResultsTextView);

        // Set up the query listener for the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (when the user presses Enter on the keyboard)
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle search query text changes
                filterSearchResults(newText);
                return true;
            }
        });

        // Set up the OnClickListener for the Enter button
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simulate search query submission when the Enter button is clicked
                String query = searchView.getQuery().toString();
                if (query.equals("azaribine")) {
                    random.setText("azaribine \n \n  Orotidine 5'-phosphate decarboxylase \n \n Inhibition of Methanobacterium thermoautotrophicum ODCase \n Methanothermobacter thermautotrophicus (strain ATCC 29096 / DSM 1053 / JCM 10044 / NBRC 100330 / Delta H)");
                }
                else if (query.equals("aspirin")) {
                    random.setText("aspirin \n \n  Lorem Ipsum \n \n Lorem Ipsum \n Lorem Ipsum (strain ATCC 29096 / DSM 1053 / JCM 10044 / NBRC 100330 / Delta H)");
                }

                //searchView.setQuery(query, true); // This triggers onQueryTextSubmit.

            }
        });
    }

    private void performSearch(String query) {
        // Implement the actual search logic here
        System.out.println("Performing search for: " + query);
    }

    private void filterSearchResults(String newText) {
        // Implement the real-time filtering logic here
        String results = "Filtered results for: " + newText;
        random.setText(results);    }
}