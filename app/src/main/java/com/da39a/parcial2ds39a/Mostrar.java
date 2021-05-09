package com.da39a.parcial2ds39a;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class Mostrar extends AppCompatActivity {

    TextView txvMonto, txvFecha, txvNum, txvKm, txvtipo, txvTotalK, txvTotalM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        txvMonto = findViewById(R.id.txvMo);
        txvFecha = findViewById(R.id.txvF);
        txvKm = findViewById(R.id.txvKm);
        txvNum = findViewById(R.id.txvN);
        txvtipo = findViewById(R.id.txvT);
        txvTotalK = findViewById(R.id.txvTotalKm);
        txvTotalM = findViewById(R.id.txvTotalMo);

        AppDataBase db = Room.databaseBuilder(Mostrar.this, AppDataBase.class, "dbcompras").allowMainThreadQueries().build();

        List<ModeloForm> lista  = db.formDAO().getAll();
        StringBuilder monto = new StringBuilder();
        StringBuilder fecha = new StringBuilder();
        StringBuilder km = new StringBuilder();
        StringBuilder tipo = new StringBuilder();
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < lista.size(); i++) {
            num.append(lista.get(i).numFactura).append("\n");
            fecha.append(lista.get(i).fechaCompra).append("\n");
            tipo.append(lista.get(i).tipoCombustible).append("\n");
            km.append(lista.get(i).km).append("\n");
            monto.append("$").append(lista.get(i).monto).append("\n");
        }

        txvtipo.setText(tipo.toString());
        txvNum.setText(num.toString());
        txvKm.setText(km.toString());
        txvFecha.setText(fecha.toString());
        txvMonto.setText(monto.toString());
        txvTotalM.setText("$" + db.formDAO().totalM());
        txvTotalK.setText(String.valueOf(db.formDAO().totalKm()));
    }
}