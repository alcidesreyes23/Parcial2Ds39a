package com.da39a.parcial2ds39a;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class form extends AppCompatActivity  {
    Spinner opciones;
    EditText edtFecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        edtFecha = findViewById(R.id.edtFecha);
        opciones = findViewById(R.id.sp01);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        edtFecha.setOnClickListener(v -> {
            if (v.getId() == R.id.edtFecha) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = day + " / " + (month+1) + " / " + year;
            edtFecha.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}