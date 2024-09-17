package com.example.concerttickets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    final double costPerTicket = 79.99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize UI components
        final ImageView preview = findViewById(R.id.imgPreview);
        final Spinner spnGroup = findViewById(R.id.spnGroup);
        final EditText txtTickets = findViewById(R.id.txtTickets);
        final Button calBtn = findViewById(R.id.button);
        final TextView txtResult = findViewById(R.id.txtResult);

        // Get group names from resources
        final String[] groupStrings = getResources().getStringArray(R.array.txtGroups);

        // Update image based on selected group
        spnGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Map selection to appropriate image
                int[] images = {R.drawable.android_w3_01, R.drawable.android_w3_02, R.drawable.android_w3_03};
                preview.setImageResource(images[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing if no selection is made
            }
        });

        // Handle button click to calculate total cost
        calBtn.setOnClickListener(v -> {
            try {
                // Get number of tickets and selected group
                int ticketNum = Integer.parseInt(txtTickets.getText().toString());
                double totalCost = costPerTicket * ticketNum;  // Assume costPerTicket is defined elsewhere
                DecimalFormat currency = new DecimalFormat("$###,###.##");

                // Display result
                txtResult.setText(String.format("Cost for %s is %s", spnGroup.getSelectedItem().toString(), currency.format(totalCost)));
            } catch (Exception e) {
                // Display error message if input is invalid
                txtResult.setText(R.string.txtInputError);
            }
        });

    }
}