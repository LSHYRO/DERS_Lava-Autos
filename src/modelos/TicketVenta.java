/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.FileInputStream;
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
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;

/**
 *
 * @author J0SE_
 */
public class TicketVenta {

    //Atributos que almacenan los datos de la empresa y de la compra
    //private String identificacion;
    //private String horario1;
    //private String horario2;
    //private String propietario;
    //private String rfc;
    //private String direccion;
    //private String telefono;
    private String folio;
    private String cliente;
    //private String rfcCliente;
    private String articulos;
    //private String subTotal;
    private String total;
    //private String recibo;
    private String cambio;
    //private String totalLetra;
    private String vendedor;
    //private String idvendedor;
    private String fecha;

    private String marca;
    private String modelo;
    private String color;
    private String placas;
    private String efectivo;
    private String hora;

    /**
     * Atributo que almacena la estructura del contenido del ticket Los campos
     * que tengan la siguiente estructura {{nombreAtributo}}, ejemplo:
     * {{telefono}} serán reemplazados por los datos correspondientes.
     */
    private String formatoTicket
            = "********************************\n"
            + "       \"Lava Autos DERS\" \n"
            + "FOLIO No: {{folio}}\n"
            + "FECHA : {{fecha}} {{hora}}\n"
            + "ATENDIDO POR:{{vendedor}}\n"
            + "======DATOS DEL AUTOMOVIL=====\n"
            + "CLIENTE : {{cliente}}\n"
            + "automovil {{marca}} modelo {{modelo}} color {{color}} con placas {{placas}} \n"
            + "======SERVICIOS SOLICITADOS=====\n"
            + "SERVICIO                   COSTO\n"
            + "{{articulos}}"
            + "================================\n"
            + "TOTAL A PAGAR:   $ {{total}}\n"
            + "EFECTIVO:   $ {{efectivo}}\n"
            + "CAMBIO:   $ {{cambio}}\n\n"
            + "NO PERDER ESTE TICKET, PUEDE SERVIRLE PARA ACLARACIONES  CON RESPECTO A SU SERVICIO\n"
            + "********************************\n\n\n\n ";

    //Definimos los metodos SET de cada atributo para asignar los datos al TICKET.
    public TicketVenta() {
        formatoTicket
                = "********************************\n"
                + "       \"Lava Autos DERS\" \n"
                + "FOLIO No: {{folio}}\n"
                + "FECHA : {{fecha}} {{hora}}\n"
                + "ATENDIDO POR:{{vendedor}}\n"
                + "======DATOS DEL AUTOMOVIL=====\n"
                + "CLIENTE : {{cliente}}\n"
                + "automovil {{marca}} modelo {{modelo}} color {{color}} con placas {{placas}} \n"
                + "======SERVICIOS SOLICITADOS=====\n"
                + "SERVICIO                   COSTO\n"
                + "{{articulos}}"
                + "================================\n"
                + "TOTAL A PAGAR:   $ {{total}}\n"
                + "EFECTIVO:   $ {{efectivo}}\n"
                + "CAMBIO:   $ {{cambio}}\n\n"
                + "NO PERDER ESTE TICKET, PUEDE SERVIRLE PARA ACLARACIONES  CON RESPECTO A SU SERVICIO\n"
                + "********************************\n\n\n\n ";
    }

    public String imprimirTicket() {
        crearTicket();
        return formatoTicket;
    }

    public void crearTicket() {
        //Datos del local
//        this.formatoTicket=formatoTicket.replace("{{identificacion}}", this.identificacion);
//        this.formatoTicket=formatoTicket.replace("{{horario1}}", this.horario1);
        //      this.formatoTicket=formatoTicket.replace("{{horario2}}", this.horario2);
//        this.formatoTicket=formatoTicket.replace("{{direccion}}", this.direccion);
        //Datos de la venta
        this.formatoTicket = formatoTicket.replace("{{folio}}", this.folio);
        this.formatoTicket = formatoTicket.replace("{{articulos}}", this.articulos);
//        this.formatoTicket=formatoTicket.replace("{{subtotal}}", this.subTotal);
        this.formatoTicket = formatoTicket.replace("{{total}}", this.total);
        //    this.formatoTicket=formatoTicket.replace("{{recibo}}", this.recibo);
        this.formatoTicket = formatoTicket.replace("{{cambio}}", this.cambio);
        //this.formatoTicket=formatoTicket.replace("{{totalLetra}}", this.totalLetra);
        //Datos del vendedor
        this.formatoTicket = formatoTicket.replace("{{vendedor}}", this.vendedor);
        //this.formatoTicket=formatoTicket.replace("{{idvendedor}}",this.idvendedor);
        this.formatoTicket = formatoTicket.replace("{{fecha}}", this.fecha);
        //    this.formatoTicket=formatoTicket.replace("{{porcentaje}}",this.porcentaje);
        this.formatoTicket = formatoTicket.replace("{{marca}}", this.marca);
        this.formatoTicket = formatoTicket.replace("{{modelo}}", this.modelo);
        this.formatoTicket = formatoTicket.replace("{{color}}", this.color);
        this.formatoTicket = formatoTicket.replace("{{placas}}", this.placas);
        this.formatoTicket = formatoTicket.replace("{{efectivo}}", this.efectivo);
        this.formatoTicket = formatoTicket.replace("{{cliente}}", this.cliente);
        this.formatoTicket = formatoTicket.replace("{{hora}}", this.hora);
    }

    /*imprime una imagen en la impresora predefinida.*/
    public static void printIMG() {
        try {
            PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob job = ps.createPrintJob();
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            if (services.length > 0) {//usamos la opcion 1 y comprobamos si hay impresoras disponibles
                //Recorremos el arreglo de impresoras
                for (PrintService service1 : services) {
                    //Aqui definimos el nombre de la impresora (para este ejemplo: subarasi)
                    // y comparamos si esta dentro del arreglo de impresoras
                    if (service1.getName().equals("DERS_Printer")) {
                        job = service1.createPrintJob(); // creamos el trabajo de impresion

                    }
                }
            }

            DocFlavor DF = DocFlavor.INPUT_STREAM.JPEG;
            FileInputStream FIS = new FileInputStream("src/recursos/DRS-07.bmp");
            Doc doc = new SimpleDoc(FIS, DF, null);
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//          attrib.add(new Copies(1));  
            aset.add(OrientationRequested.PORTRAIT);
            aset.add(MediaSizeName.INVOICE);
//          job.print(doc, attrib);
            job.print(doc, aset);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public void setEfectivo(String efectivo) {
        this.efectivo = efectivo;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    /*
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
    public void setHorario1(String horario1) {
        this.horario1 = horario1;
    }
    
    public void setHorario2(String horario2) {
        this.horario2 = horario2;
    }*/
    /**
     * @param propietario dueño del negocio
     */
    /*public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    /**
     * @param rfc registro del negocio
     */
 /*public void setRfc(String rfc) {
        this.rfc = rfc;
    }*/
    /**
     * @param direccion del negocio
     */
    /*public void setDireccion(String direccion) {
        this.direccion = direccion;
    }*/
    /**
     * @param telefono del negocio
     */
    /*public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @param folio el folio de la venta
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @param cliente nombre del cliente
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * @param rfcCliente registro del cliente
     */
    /*public void setRfcCliente(String rfcCliente) {
        this.rfcCliente = rfcCliente;
    }

    /**
     * @param articulos datos del producto vendido
     */
    public void setArticulos(String articulos) {
        this.articulos = articulos;
    }

    /*
    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
    /**
     * @param subTotal subtotal de la compra
     */
 /*
    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @param total total de la compra
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @param recibo contidad con la que pago el cliente
     */
    /*public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

    /**
     * @param cambio cantidad a devolver
     */
    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    /**
     * @param totalLetra total de la compra en letra
     */
    /*
    public void setTotalLetra(String totalLetra) {
        this.totalLetra = totalLetra;
    }

    /**
     * @param vendedor datos del vendedor
     */
    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    /*
    public void setIDVendedor(String idvendedor) {
        this.idvendedor = idvendedor;
    }

    /**
     * @param matriz nombre de la sucursal principal
     */
 /*public void setMatriz(String matriz) {
        this.matriz = matriz;
    }

    /**
     * @param fecha fecha de impresion
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * PARA ESTE EJEMPLO USAMOS UNA IMPRESORA TERMICA CON EL NOMBRE DE SUBARASI
     * EL CUAL LE ASIGNAMOS DESDE LA VENTANA DE IMPRESORA Y DISPOSITIVOS
     * (WINDOWS) Configuración del documento de impresión y reemplazo de los
     * campos con el valor de las propiedades
     */
    public void print(boolean flagServicio) throws IOException, PrintException {
        //Datos de impresion.
        //Datos de la Empresa y/o negocio
        //this.formatoTicket=formatoTicket.replace("{{empresa}}", this.empresa);
        //this.formatoTicket=formatoTicket.replace("{{propietario}}", this.propietario);
        //this.formatoTicket=formatoTicket.replace("{{rfc}}", this.rfc);
        //this.formatoTicket=formatoTicket.replace("{{direccion}}", this.direccion);
        //this.formatoTicket=formatoTicket.replace("{{telefono}}", this.telefono);
        //Datos de la venta
        this.formatoTicket = formatoTicket.replace("{{folio}}", this.folio);
        this.formatoTicket = formatoTicket.replace("{{cliente}}", this.cliente);
        //this.formatoTicket=formatoTicket.replace("{{rfcCliente}}", this.rfcCliente);
        this.formatoTicket = formatoTicket.replace("{{articulos}}", this.articulos);
        //this.formatoTicket=formatoTicket.replace("{{descuento}}", this.subTotal);
        this.formatoTicket = formatoTicket.replace("{{total}}", this.total);
        //this.formatoTicket=formatoTicket.replace("{{recibo}}", this.recibo);
        //this.formatoTicket=formatoTicket.replace("{{cambio}}", this.cambio);
        //this.formatoTicket=formatoTicket.replace("{{totalLetra}}", this.totalLetra);
        //Datos del vendedor, lugar y fecha
        this.formatoTicket = formatoTicket.replace("{{vendedor}}", this.vendedor);
        //this.formatoTicket=formatoTicket.replace("{{matriz}}", this.matriz);
        this.formatoTicket = formatoTicket.replace("{{fecha}}", this.fecha);
        //this.formatoTicket=formatoTicket.replace("{{porcentaje}}", this.porcentaje);

        //Especificamos el tipo de dato a imprimir
        //Tipo: bytes -- Subtipo: autodetectado
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        //Creamos un arreglo de tipo byte y le agregamos el string convertido (cuerpo del ticket)
        //a bytes tal como lo maneja la impresora.
        byte[] bytes = this.formatoTicket.getBytes();
        //Creamos un documento a imprimir pasandole el arreglo de byte
        Doc doc = new SimpleDoc(bytes, flavor, null);

        //Creamos un trabajo de impresión
        DocPrintJob job = null;
        //Creamos una bandera para determinar si se encontro la impresora
        //que especificamos en este caso "subarasi" O si usamos la impresora predeterminada del S.O.
        boolean flagJob = false;

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
        if (flagServicio == true) {
            if (services.length > 0) {//usamos la opcion 1 y comprobamos si hay impresoras disponibles
                //Recorremos el arreglo de impresoras
                for (PrintService service1 : services) {
                    //Aqui definimos el nombre de la impresora (para este ejemplo: subarasi)
                    // y comparamos si esta dentro del arreglo de impresoras
                    if (service1.getName().equals("DERS_Printer")) {
                        job = service1.createPrintJob(); // creamos el trabajo de impresion
                        flagJob = true; //si la impresora existe ponemos en TRUE la bandera
                    }
                }
            }
            //En caso de que la opcion 1 no encuentre la impresora que buscamos
            // el flagJob es false y job es null, entonces empleamos la opcion 2
            if (job == null && flagJob == false) {
                //creamos el trabajo de impresion con el servicio de impresion por default
                //(la impresora establecida como predeterminada en el sistema operativo)
                job = defaultService.createPrintJob();
            }
            flagJob = false;
        } else {
            //si flagServicio es false, usamos la opcion 3 para crear el trabajo de impresion
            //seleccionando la impresora desde el cuadro de dialogo de impresion
            PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);
            job = service.createPrintJob();
        }

        //por ultimo Imprimimos dentro de un try obligatoriamente para el contro de excepciones
        try {
            job.print(doc, null);
        } catch (PrintException ex) {
            JOptionPane.showMessageDialog(null, "IMPRIMIR TICKET (Compruebe impresion) " + ex.getMessage());
        }
    }
}
