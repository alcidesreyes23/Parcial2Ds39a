package com.da39a.parcial2ds39a;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class form extends AppCompatActivity  {
    Spinner opciones;
    EditText edtFecha, edtFactura, edtMonto, edtKm;
    Button btnMostrar, btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //Botones
        btnAdd = findViewById(R.id.btnRegistrar);
        btnMostrar = findViewById(R.id.btnmostrar);
        //EditText
        edtFecha = findViewById(R.id.edtFecha);
        edtFactura = findViewById(R.id.edtNumFactura);
        edtKm = findViewById(R.id.edtKm);
        edtMonto = findViewById(R.id.edtMonto);

        //ComboBox
        opciones = findViewById(R.id.sp01);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        edtFecha.setOnClickListener(v -> showDatePickerDialog());

        btnAdd.setOnClickListener(v -> {
            AppDataBase db = Room.databaseBuilder(form.this, AppDataBase.class, "dbcompras").allowMainThreadQueries().fallbackToDestructiveMigration().build();

            ModeloForm modeloForm = new ModeloForm(
                    Integer.parseInt(edtFactura.getText().toString()),
                    edtFecha.getText().toString(),
                    opciones.getSelectedItem().toString(),
                    Integer.parseInt(edtKm.getText().toString()),
                    Double.parseDouble(edtMonto.getText().toString())
            );
            db.formDAO().insert(modeloForm);
            Toast.makeText(getApplicationContext(),"Registro almacenado correctamente",Toast.LENGTH_SHORT).show();
            limpiar();
        });

        btnMostrar.setOnClickListener(v -> {
            Intent intent = new Intent(form.this,Mostrar.class);
            startActivity(intent);
        });
    }

    public  void  limpiar()
    {
        edtFactura.setText("");
        edtFecha.setText("");
        edtKm.setText("");
        edtMonto.setText("");
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = twoDigits(day) + "/" + twoDigits(month+1) + "/" + year;
            edtFecha.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
}