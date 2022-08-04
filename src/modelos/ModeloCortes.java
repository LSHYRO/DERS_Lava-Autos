/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import EntityClasses.Corte;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rob99
 */
public class ModeloCortes extends AbstractTableModel {
    private String[] encabezados = {"ID Corte","Nombre Empleado","Monto", "Fecha del corte"};
    private List<Corte>cortes;
    public ModeloCortes(List<Corte> cort){
        cortes = cort;
    }
    
    @Override
    public String getColumnName(int c){
        return encabezados[c];
    }

    @Override
    public int getRowCount() {
        return cortes.size();
    }

    @Override
    public int getColumnCount() {
        return encabezados.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        Date dat = cortes.get(r).getFecha();
        switch(c){
            case 0:
                return cortes.get(r).getIdCorte();
            case 1: 
                return cortes.get(r).getLavadoridLavador().getUsuarioidUsuario().getNombre();
            case 2:
                return cortes.get(r).getMonto();
            default:
                long fecha = dat.getTime();
                    java.sql.Date fecha1 = new java.sql.Date(fecha);
                return fecha1;
        }
    }
    
}
