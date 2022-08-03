/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import EntityClasses.Serviciosolicitado;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rob99
 */

public class ModeloServiciosSolicitados extends AbstractTableModel{
    private String[] encabezados = {"ID Ticket","Servicio","TamañoVehiculo","TipoVehiculo","Costo","Hora"};
    private List<Serviciosolicitado> servicios;
    public ModeloServiciosSolicitados(List<Serviciosolicitado>sers){
        servicios = sers;
    }
    @Override
    public String getColumnName(int c){
        return encabezados[c];
    }
    @Override
    public int getRowCount() {
        return servicios.size();
    }

    @Override
    public int getColumnCount() {
        return encabezados.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        Date hrs = servicios.get(r).getTicketidTicket().getHora();
        
        switch(c){
            case 0:
                return servicios.get(r).getTicketidTicket().getIdTicket();
            case 1:
                return servicios.get(r).getCostoServicioidServicioCosto().getServicioidServicio().getNombreServicio();
            case 2:
                return servicios.get(r).getCostoServicioidServicioCosto().getTamanioidTamanio().getDescripcion();
            case 3:
                try{
                    return servicios.get(r).getCostoServicioidServicioCosto().getTipoVehiculoidTipoVehiculo().getDescripcion();
                }catch(NullPointerException e){
                    return " ";
                }
            case 4:
                return servicios.get(r).getCostoServicioidServicioCosto().getPrecio();
            default:
                return hrs.getHours()+" : "+hrs.getMinutes();
        }
    }
}
