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

        List<ModeloForm> lista  = db.formDAO().getAll();
        String valores = "";
        valores += "Num Factura\tFecha Compra\tCombustible\tKM\tMonto\n\n";

        int contD = 0, contR = 0, contP = 0;
        double promDkm = 0, promDM = 0, promRKm = 0, promRM = 0, promPKm = 0, promPM = 0;
        int acumD = 0, acumR = 0, acumP = 0;

        for (int i = 0; i < lista.size(); i++){
            valores += lista.get(i).numFactura +" - "+ lista.get(i).fechaCompra +" - "+ lista.get(i).tipoCombustible +" - "+ lista.get(i).km +" - "+ lista.get(i).monto + "\n\n";
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
        promDkm /= contD;
        promDM /= contD;
        promRKm /= contR;
        promRM /= contR;
        promPKm /= contP;
        promPM /= contP;
        valores += "\n\nTipo:\t\tKm Promedio\t\tDinero Promedio\n";
        valores += "Diesel - " + promDkm + " - " + promDM + "\n";
        valores += "Regular - " + promRKm + " - " + promRM + "\n";
        valores += "Premium - " + promPKm + " - " + promPM + "\n\n";

        List<ModeloForm> list = db.formDAO().rango("09/05/2021","10/05/2021");
        for (int i = 0; i < list.size(); i++) {
            valores += list.get(i).numFactura + " - " + list.get(i).fechaCompra + " - " + list.get(i).tipoCombustible + " - " + list.get(i).km + " - " + list.get(i).monto + "\n\n";
        }

        double monto = db.formDAO().promedioKm("09/05/2021","10/05/2021","Diesel");
        double monto2 = db.formDAO().promedio("02/05/2021","04/05/2021","Diesel");
        double monto3 = db.formDAO().promedioAll("Diesel");
        double monto4 = db.formDAO().promedioAllKm("Diesel");
        valores += "\n\n\n SUMATORIA" + monto + "\n\nSUM " + monto2 + "\n\nSUM " + monto3 + "\n\nSUM " + monto4;
        txM.setText(valores);


    }
}