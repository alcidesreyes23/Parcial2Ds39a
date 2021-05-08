package com.da39a.parcial2ds39a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Promedios extends AppCompatActivity {
    EditText fechaInicio, fechaFin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promedios);

        fechaInicio = findViewById(R.id.edtFechaInicio);
        fechaFin = findViewById(R.id.edtFechaFin);
        fechaInicio.setOnClickListener(v -> {showDatePickerDialog();});
        fechaFin.setOnClickListener(v -> {showDatePickerDialog();});

    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = day + " / " + (month+1) + " / " + year;
            fechaInicio.setText(selectedDate);
            fechaFin.setText(selectedDate);

        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}