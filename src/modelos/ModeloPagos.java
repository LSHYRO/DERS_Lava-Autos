/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import EntityClasses.Pago;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rob99
 */
public class ModeloPagos extends AbstractTableModel{
    private String[] encabezados = {"ID Pago", "Fecha", "ID Corte","Nombre Empleado"};
    private List<Pago>pagos;
    public ModeloPagos(List<Pago> p){
        pagos = p;
    }
    @Override
    public String getColumnName(int c){
        return encabezados[c];
    }
    @Override
    public int getRowCount() {
        return pagos.size();
    }

    @Override
    public int getColumnCount() {
        return encabezados.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        Date hoy = pagos.get(r).getFecha();
        switch(c){
            case 0:
                return pagos.get(r).getIdPago();
            case 1:
                    long fecha = hoy.getTime();
                    java.sql.Date fecha1 = new java.sql.Date(fecha);
                return fecha1;
            case 2:
                return pagos.get(r).getCorteidCorte().getIdCorte();
            default:
                return pagos.get(r).getCorteidCorte().getLavadoridLavador().getUsuarioidUsuario().getNombre();
        }
    }
    
}
