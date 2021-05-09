package com.da39a.parcial2ds39a;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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
        promediosAdapter = new PromediosAdapter(mostrarDatos(fechaInicio.getText().toString(), fechaFin.getText().toString()));
        rcvProm.setAdapter(promediosAdapter);



        promediosAdapter.setOnClickListener(v -> {
            AppDataBase db = Room.databaseBuilder(Promedios.this, AppDataBase.class, "dbcompras").allowMainThreadQueries().build();
            List<ModeloForm> lista;
            StringBuilder cadenaDiesel = new StringBuilder();
            StringBuilder cadenaRegular = new StringBuilder();
            StringBuilder cadenaPre = new StringBuilder();
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
                        cadenaDiesel.append(contD).append(" - ").append(lista.get(i).getFechaCompra()).append(":  $").append(lista.get(i).getMonto()).append("\n");
                        break;
                    case "Regular":
                        contR++;
                        cadenaRegular.append(contR).append(" - ").append(lista.get(i).getFechaCompra()).append(":  $").append(lista.get(i).getMonto()).append("\n");
                        break;
                    case "Premium":
                        contP++;
                        cadenaPre.append(contP).append(" - ").append(lista.get(i).getFechaCompra()).append(":  $").append(lista.get(i).getMonto()).append("\n");
                        break;
                }
            }
            String compare = promediosAdapter.listaProm.get(rcvProm.getChildAdapterPosition(v)).getTipoCombustible();
            switch (compare) {
                case "Diesel":
                    createDialog(compare, cadenaDiesel.toString(),contD);
                    break;
                case "Regular":
                    createDialog(compare, cadenaRegular.toString(),contR);
                    break;
                case "Premium":
                    createDialog(compare, cadenaPre.toString(),contP);
                    break;
            }
        });

        //Filtrar las fechas
        btnFiltrar.setOnClickListener(v -> {
            rcvProm.setLayoutManager(new LinearLayoutManager(this));
            promediosAdapter = new PromediosAdapter(mostrarDatos(fechaInicio.getText().toString(),fechaFin.getText().toString()));
            rcvProm.setAdapter(promediosAdapter);
        });
    }

    //Crea el alertDialog
    public void createDialog(String title, String desc, int op){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Detalles de compras:\n" + title);
        if (op == 0 ) builder.setMessage("No hay compras registradas en el periodo");
        else builder.setMessage(desc);
        builder.setPositiveButton("Aceptar",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Guarda los datos de la base al Modelo
    public List<ModelProm> mostrarDatos(String fi, String ff){
        AppDataBase db = Room.databaseBuilder(Promedios.this, AppDataBase.class, "dbcompras").allowMainThreadQueries().build();
        NumberFormat nformat = new DecimalFormat("##,###,###.##");

        double  promDM,  promRM, promPM, promDkm , promRKm, promPKm;

        if (fi.equals("") || ff.equals("")){
            promDkm = db.formDAO().promedioAllKm("Diesel");
            promPKm = db.formDAO().promedioAllKm("Premium");
            promRKm = db.formDAO().promedioAllKm("Regular");
            promDM = db.formDAO().promedioAll("Diesel");
            promPM = db.formDAO().promedioAll("Premium");
            promRM = db.formDAO().promedioAll("Regular");
        } else {
            promDkm = db.formDAO().promedioKm(fi,ff,"Diesel");
            promPKm = db.formDAO().promedioKm(fi,ff,"Premium");
            promRKm = db.formDAO().promedioKm(fi,ff,"Regular");
            promDM = db.formDAO().promedio(fi,ff,"Diesel");
            promPM = db.formDAO().promedio(fi,ff,"Premium");
            promRM = db.formDAO().promedio(fi,ff,"Regular");
        }

        List<ModelProm> form = new ArrayList<>();
        form.add(new ModelProm("Diesel", nformat.format(promDkm), nformat.format(promDM)));
        form.add(new ModelProm("Regular",nformat.format(promRKm),nformat.format(promRM)));
        form.add(new ModelProm("Premium",nformat.format(promPKm),nformat.format(promPM)));
        return form;
    }

    //DatePicker para la fecha inicial
    private void showDatePickerDialogI() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = twoDigits(day) + "/" + twoDigits(month+1) + "/" + year;
            fechaInicio.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    //DatePicker para la fecha final
    private void showDatePickerDialogF() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = twoDigits(day) + "/" + twoDigits(month+1) + "/" + year;
            fechaFin.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    //Convierte en formato de dos digitos las fechas
    private String twoDigits(int n) {  return (n<=9) ? ("0"+n) : String.valueOf(n); }
}