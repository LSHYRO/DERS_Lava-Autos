/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.IOException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

/**
 *
 * @author J0SE_
 */
public class TicketPago {
    private String folio="#$%";
    private String cliente="";
    //private String rfcCliente;
    private String articulos="";
    private String salario="";
    private String total="";
    //private String recibo;
    private String cambio="";
    //private String totalLetra;
    private String vendedor="";
    private String idvendedor="";
    private String fechaC="";
    private String fechaP="";
    private String porcentaje="";

    /**
    *Atributo que almacena la estructura del contenido del ticket
    *Los campos que tengan la siguiente estructura {{nombreAtributo}}, ejemplo: {{telefono}}
    *serán reemplazados por los datos correspondientes.
    */
    private String formatoTicket;/* = 
    "********************************\n"+
    "       \"Lava Autos DERS\" \n"+
    "*********PAGO DE CORTE**********\n"+
    "\n"+
    "CORTE No: {{folio}}\n"+
    "FECHA DEL CORTE: {{fechaC}}\n"+
    "FECHA DEL PAGO: {{fechaP}}\n"+
    "--------------------------------\n"+
    "TOTAL EN SERVICIOS:  $ {{total}}\n"+
    "COMISION:   $ {{porcentaje}} %\n"+
    "SALARIO:   $ {{subtotal}}\n"+
    "\n"+
    "IDEmpleado:{{idvendedor}}\n"+
    "Nombre:{{vendedor}}\n"+        
    "********************************\n\n\n\n ";*/

    //Definimos los metodos SET de cada atributo para asignar los datos al TICKET.
    public TicketPago(){
         formatoTicket = 
    "********************************\n"+
    "       \"Lava Autos DERS\" \n"+
    "*********PAGO DE CORTE**********\n"+
    "\n"+
    "CORTE No: {{folio}}\n"+
    "FECHA DEL CORTE: {{fechaC}}\n"+
    "FECHA DEL PAGO: {{fechaP}}\n"+
    "--------------------------------\n"+
    "TOTAL EN SERVICIOS:  $ {{total}}\n"+
    "COMISION:   {{porcentaje}} %\n"+
    "SALARIO:   $ {{salario}}\n"+
    "\n"+
    "IDEmpleado:{{idvendedor}}\n"+
    "Nombre:{{vendedor}}\n"+        
    "********************************\n\n\n\n ";
    }
    
    public String imprimirTicket(){
        crearTicket();
        return formatoTicket;
    }
    
    public void crearTicket(){
        //Datos del corte
        this.formatoTicket=formatoTicket.replace("{{folio}}", this.folio);
        this.formatoTicket=formatoTicket.replace("{{fechaC}}",this.fechaC);
        this.formatoTicket=formatoTicket.replace("{{fechaP}}",this.fechaP);
        this.formatoTicket=formatoTicket.replace("{{salario}}", this.salario);
        this.formatoTicket=formatoTicket.replace("{{total}}", this.total);
        //Datos del vendedor
        this.formatoTicket=formatoTicket.replace("{{vendedor}}", this.vendedor);
        this.formatoTicket=formatoTicket.replace("{{idvendedor}}",this.idvendedor);
        this.formatoTicket=formatoTicket.replace("{{porcentaje}}",this.porcentaje);
    }
    
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @param rfcCliente registro del cliente
     */
    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
    /**
     * @param subTotal subtotal de la compra
     */
    
    public void setSalario(String salario) {
        this.salario = salario;
    }

    /**
     * @param total total de la compra
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @param vendedor datos del vendedor
     */
    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
    
    public void setIDVendedor(String idvendedor) {
        this.idvendedor = idvendedor;
    }

    /**
     * @param fecha de corte
     */
    public void setFechaC(String fecha) {
        this.fechaC = fecha;
    }
    
    /**
     * @param fecha fecha de pago
     */
    public void setFechaP(String fecha) {
        this.fechaP = fecha;
    }
    /**
    *PARA ESTE EJEMPLO USAMOS UNA IMPRESORA TERMICA CON EL NOMBRE DE  SUBARASI
    *EL CUAL LE ASIGNAMOS DESDE LA VENTANA DE IMPRESORA Y DISPOSITIVOS (WINDOWS)
    *Configuración del documento de impresión y reemplazo de los campos
    *con el valor de las propiedades
    */
    public void print(boolean flagServicio) throws IOException, PrintException {
        crearTicket();
        
        //Especificamos el tipo de dato a imprimir
        //Tipo: bytes -- Subtipo: autodetectado
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        //Creamos un arreglo de tipo byte y le agregamos el string convertido (cuerpo del ticket)
        //a bytes tal como lo maneja la impresora.
        byte[] bytes= this.formatoTicket.getBytes();
        //Creamos un documento a imprimir pasandole el arreglo de byte
        Doc doc = new SimpleDoc(bytes,flavor,null);

        //Creamos un trabajo de impresión
        DocPrintJob job =null;
        //Creamos una bandera para determinar si se encontro la impresora
        //que especificamos en este caso "subarasi" O si usamos la impresora predeterminada del S.O.
        boolean flagJob=false;

        //Opcion 1 ->nos da el array de los servicios de impresion
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        //Opcion 2-> servicios de impresion por default
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        //Opcion 3-> mostramos dialogo para seleccionar impresoras que soporten arreglos de bits
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        
        /*flagServicio
        *   
        *true --si queremos imprimir en una impresora especifica  en este caso "subarasi"
        *       o si deseamos imprimir en la impresora establecida como predeterminada en el Sistema Operativo
        *false --si queremos visualizar el cuadro de dialogo de impresion y elegir una impresora
        *        que soporte arreglo de bits
        */

        if(flagServicio==true){
            if(services.length>0){//usamos la opcion 1 y comprobamos si hay impresoras disponibles
                //Recorremos el arreglo de impresoras
                for (PrintService service1 : services) {
                    //Aqui definimos el nombre de la impresora (para este ejemplo: subarasi)
                    // y comparamos si esta dentro del arreglo de impresoras
                    if (service1.getName().equals("DERS_Printer")) {
                        job = service1.createPrintJob(); // creamos el trabajo de impresion
                        flagJob=true; //si la impresora existe ponemos en TRUE la bandera
                    }
                }
            }
            //En caso de que la opcion 1 no encuentre la impresora que buscamos
            // el flagJob es false y job es null, entonces empleamos la opcion 2
            if(job==null && flagJob==false){
                //creamos el trabajo de impresion con el servicio de impresion por default
                //(la impresora establecida como predeterminada en el sistema operativo)
                job = defaultService.createPrintJob();
            }
            flagJob=false;
        }else{
            //si flagServicio es false, usamos la opcion 3 para crear el trabajo de impresion
            //seleccionando la impresora desde el cuadro de dialogo de impresion
            PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);
            job = service.createPrintJob();
        }       
        
        //por ultimo Imprimimos dentro de un try obligatoriamente para el contro de excepciones
        try{
            job.print(doc, null);
        }catch(PrintException ex){
            JOptionPane.showMessageDialog(null, "IMPRIMIR TICKET (Compruebe impresion) "+ex.getMessage());
        }
    }
}

