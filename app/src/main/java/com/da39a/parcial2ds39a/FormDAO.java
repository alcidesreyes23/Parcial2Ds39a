package com.da39a.parcial2ds39a;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FormDAO {
    @Query("SELECT * FROM MODELOFORM")
    List<ModeloForm> getAll();

    @Query("SELECT * FROM MODELOFORM WHERE fechaCompra BETWEEN :fechaIni AND :fechaFin")
    List<ModeloForm> rango (String fechaIni, String fechaFin);

    @Query("SELECT AVG(monto) FROM MODELOFORM WHERE tipoCombustible = :tipo")
    double promedioAll (String tipo);

    @Query("SELECT AVG(km) FROM MODELOFORM WHERE tipoCombustible = :tipo")
    double promedioAllKm (String tipo);

    @Query("SELECT AVG(monto) FROM MODELOFORM WHERE fechaCompra BETWEEN :fechaIni AND :fechaFin AND tipoCombustible = :tipo")
    double promedio (String fechaIni, String fechaFin, String tipo);

    @Query("SELECT AVG(km) FROM MODELOFORM WHERE fechaCompra BETWEEN :fechaIni AND :fechaFin AND tipoCombustible = :tipo")
    double promedioKm (String fechaIni, String fechaFin, String tipo);

    @Insert
    Long insert(ModeloForm modeloForm);
}
