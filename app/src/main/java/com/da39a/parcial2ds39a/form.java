package com.da39a.parcial2ds39a;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

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
            AppDataBase db = Room.databaseBuilder(form.this, AppDataBase.class, "dbcompras").allowMainThreadQueries().build();

            ModeloForm modeloForm = new ModeloForm(
                    Integer.parseInt(edtFactura.getText().toString()),
                    edtFecha.getText().toString(),
                    opciones.getSelectedItem().toString(),
                    edtKm.getText().toString(),
                    Double.parseDouble(edtMonto.getText().toString())
            );
            Long reg = db.formDAO().insert(modeloForm);
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

    //CODIGO DEL ACTIONBAR PEGAR EN TODAS LAS ACTIVITIES
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menuoverflow, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.miIntegrantes)
        {
            Intent acerca = new Intent(form.this,Integrantes.class);
            startActivity(acerca);
        }
        return super.onOptionsItemSelected(item);
    }
}