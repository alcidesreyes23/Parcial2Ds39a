package com.da39a.parcial2ds39a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    ImageButton btnC, btnP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnC = findViewById(R.id.btnComprar);
        btnP = findViewById(R.id.btnProm);

        btnP.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this,Promedios.class);
            startActivity(intent);
        });

        btnC.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this,Integrantes.class);
            startActivity(intent);
        });
    }

    //CODIGO DEL ACTIONBAR PEGAR EN TODAS LAS ACTIVITIES
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menuoverflow, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.miIntegrantes){
            Intent acerca = new Intent(Menu.this,Integrantes.class);
            startActivity(acerca);
        }
        return super.onOptionsItemSelected(item);
    }
}