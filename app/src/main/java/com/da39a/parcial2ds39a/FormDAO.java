package com.da39a.parcial2ds39a;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FormDAO {
    @Query("SELECT * FROM MODELOFORM")
    List<ModeloForm> getAll();

    @Query("SELECT * FROM MODELOFORM   WHERE tipoCombustible = :tipo")
    List<ModeloForm> tipoCombustible (String tipo);

    @Query("SELECT * FROM MODELOFORM WHERE fechaCompra BETWEEN :fechaIni AND :fechaFin")
    List<ModeloForm> rango (String fechaIni, String fechaFin);
    @Insert
    Long insert(ModeloForm modeloForm);
}
