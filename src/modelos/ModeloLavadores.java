/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import EntityClasses.Lavador;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rob99
 */
public class ModeloLavadores extends AbstractTableModel {

    private String[] encabezados = {"ID Lavador", "Nombre Lavador", "Comision del lavador"};
    private List<Lavador> lav;

    public ModeloLavadores(List<Lavador> lavadores) {
        lav = lavadores;
    }

    @Override
    public String getColumnName(int i) {
        return encabezados[i];
    }

    @Override
    public int getRowCount() {
        return lav.size();
    }

    @Override
    public int getColumnCount() {
        return encabezados.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return lav.get(r).getIdLavador();
            case 1:
                return lav.get(r).getUsuarioidUsuario().getNombre();
            default:
                return lav.get(r).getComision();
        }
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return c == 2;
    }

    @Override
    public void setValueAt(Object a, int r, int c) {
        try {
            double nuevaComi = Double.parseDouble(a.toString());
            if (nuevaComi > 0.0 && nuevaComi < 100.0) {
                lav.get(r).setComision(nuevaComi);
            }

        } catch (NumberFormatException ex) {
            //System.out.println("Error");
        }

    }

}
