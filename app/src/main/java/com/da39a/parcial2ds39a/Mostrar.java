package com.da39a.parcial2ds39a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class Mostrar extends AppCompatActivity {

    TextView txM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        txM = findViewById(R.id.txvMostrar);
        AppDataBase db = Room.databaseBuilder(Mostrar.this, AppDataBase.class, "dbcompras").allowMainThreadQueries().build();

        List<ModeloForm> lista  =db.formDAO().getAll();
        String valores = "";
        valores += "Num Factura\tFecha Compra\tCombustible\tKM\tMonto\n\n";
        for (int i = 0; i < lista.size(); i++){
            valores += lista.get(i).numFactura +" - "+ lista.get(i).fechaCompra +" - "+ lista.get(i).tipoCombustible +" - "+ lista.get(i).km +" - "+ lista.get(i).monto + "\n\n";
        }
        txM.setText(valores);
    }
}