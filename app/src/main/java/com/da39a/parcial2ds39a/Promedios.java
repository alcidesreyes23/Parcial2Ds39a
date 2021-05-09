package com.da39a.parcial2ds39a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Promedios extends AppCompatActivity {
    EditText fechaInicio, fechaFin;
    RecyclerView rcvProm;
    PromediosAdapter promediosAdapter;
    Button btnFiltrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promedios);

        fechaInicio = findViewById(R.id.edtFechaInicio);
        fechaFin = findViewById(R.id.edtFechaFin);
        rcvProm = findViewById(R.id.rcvPromedios);
        btnFiltrar = findViewById(R.id.btnFiltrar);

        fechaInicio.setOnClickListener(v -> showDatePickerDialogI());
        fechaFin.setOnClickListener(v -> showDatePickerDialogF());

        rcvProm.setLayoutManager(new LinearLayoutManager(this));
        promediosAdapter = new PromediosAdapter(mostrarDatos(fechaInicio.getText().toString(),fechaFin.getText().toString()));
        rcvProm.setAdapter(promediosAdapter);

        promediosAdapter.setOnClickListener(v -> {
            AppDataBase db = Room.databaseBuilder(Promedios.this, AppDataBase.class, "dbcompras").allowMainThreadQueries().build();
            List<ModeloForm> lista;
            String cadenaDiesel = "", cadenaRegular = "", cadenaPre = "";
            int contD = 0, contR = 0, contP = 0;

            if (fechaInicio.getText().toString().equals("") || fechaFin.getText().toString().equals("")){
                lista = db.formDAO().getAll();
            } else {
                lista = db.formDAO().rango(fechaInicio.getText().toString(),fechaFin.getText().toString());
            }
            for (int i = 0; i < lista.size(); i++){
                switch (lista.get(i).tipoCombustible)
                {
                    case "Diesel":
                        contD++;
                        cadenaDiesel += contD + " - " + lista.get(i).getFechaCompra() + ":  $" + lista.get(i).getMonto() + "\n";
                        break;
                    case "Regular":
                        contR++;
                        cadenaRegular += contR + " - " + lista.get(i).getFechaCompra() + ":  $" + lista.get(i).getMonto() + "\n";
                        break;
                    case "Premium":
                        contP++;
                        cadenaPre += contP + " - " + lista.get(i).getFechaCompra() + ":  $" + lista.get(i).getMonto() + "\n";
                        break;
                }
            }
            String compare = promediosAdapter.listaProm.get(rcvProm.getChildAdapterPosition(v)).getTipoCombustible();
            switch (compare)
            {
                case "Diesel":
                    if (contD == 0)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Detalles de compras:\nDiesel");
                        builder.setMessage("No hay compras registradas en el periodo");
                        builder.setPositiveButton("Aceptar",null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Detalles de compras:\nDiesel");
                        builder.setMessage(cadenaDiesel);
                        builder.setPositiveButton("Aceptar", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    break;
                case "Regular":
                    if (contR == 0)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Detalles de compras:\nRegular");
                        builder.setMessage("No hay compras registradas en el periodo");
                        builder.setPositiveButton("Aceptar",null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Detalles de compras:\nRegular");
                        builder.setMessage(cadenaRegular);
                        builder.setPositiveButton("Aceptar", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    break;
                case "Premium":
                    if (contP == 0)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Detalles de compras:\nPremium");
                        builder.setMessage("No hay compras registradas en el periodo");
                        builder.setPositiveButton("Aceptar",null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Detalles de compras:\nPremium");
                        builder.setMessage(cadenaPre);
                        builder.setPositiveButton("Aceptar", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    break;
            }
        });

        btnFiltrar.setOnClickListener(v -> {
            rcvProm.setLayoutManager(new LinearLayoutManager(this));
            promediosAdapter = new PromediosAdapter(mostrarDatos(fechaInicio.getText().toString(),fechaFin.getText().toString()));
            rcvProm.setAdapter(promediosAdapter);
        });
    }

    public List<ModelProm> mostrarDatos(String fi, String ff){
        AppDataBase db = Room.databaseBuilder(Promedios.this, AppDataBase.class, "dbcompras").allowMainThreadQueries().build();
        NumberFormat nformat = new DecimalFormat("##,###,###.##");
        List<ModeloForm> lista;

        if (fi.equals("") || ff.equals("")){
            lista = db.formDAO().getAll();
        } else {
            lista = db.formDAO().rango(fi,ff);
        }

        int contD = 0, contR = 0, contP = 0;
        double  promDM = 0,  promRM = 0,  promPM = 0;
        int promDkm = 0, promRKm = 0, promPKm = 0;

        for (int i = 0; i < lista.size(); i++){
            switch (lista.get(i).tipoCombustible)
            {
                case "Diesel":
                    promDkm += lista.get(i).km;
                    promDM += lista.get(i).monto;
                    contD++;
                    break;
                case "Regular":
                    promRKm += lista.get(i).km;
                    promRM += lista.get(i).monto;
                    contR++;
                    break;
                case "Premium":
                    promPKm += lista.get(i).km;
                    promPM += lista.get(i).monto;
                    contP++;
                    break;
            }
        }
        if (contD != 0) {promDkm /= contD; promDM /= contD;}
        if (contP != 0) {promPKm /= contP; promPM /= contP;}
        if (contR != 0) {promRKm /= contR; promRM /= contR;}

        List<ModelProm> form = new ArrayList<>();
        form.add(new ModelProm("Diesel", promDkm, nformat.format(promDM)));
        form.add(new ModelProm("Regular",promRKm,nformat.format(promRM)));
        form.add(new ModelProm("Premium",promPKm,nformat.format(promPM)));
        return form;
    }

    private void showDatePickerDialogI() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = twoDigits(day) + "/" + twoDigits(month+1) + "/" + year;
            fechaInicio.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void showDatePickerDialogF() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = twoDigits(day) + "/" + twoDigits(month+1) + "/" + year;
            fechaFin.setText(selectedDate);
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
            Intent acerca = new Intent(Promedios.this,Integrantes.class);
            startActivity(acerca);
        }
        return super.onOptionsItemSelected(item);
    }

}