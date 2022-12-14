/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import EntityClasses.*;
import Controllers.*;
import modelos.ModeloCortes;
import modelos.ModeloPagos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.PrintException;
import modelos.TicketCorte;
import modelos.TicketPago;

/**
 *
 * @author rob99
 */
public class panelPago extends javax.swing.JPanel {

    /**
     * Creates new form panelPago2
     */
    //Clases Entidad
    private Administrador administrdor;
    private Corte corte;
    private Lavador lavador;
    private Costoservicio costo;
    private Ticket ticket;
    private Automovil auto;
    private Tamanio tamanio;
    private Tipovehiculo tipoV;
    private Serviciosolicitado servicioS;
    private Pago pago;
    private Servicio servicio;
    private Usuario usuario;

    //Clases Controlador
    private LavadorJpaController jpaLavador;
    private CostoservicioJpaController jpaCosto;
    private TicketJpaController jpaTicket;
    private AutomovilJpaController jpaAutomovil;
    private TamanioJpaController jpaTamanio;
    private TipovehiculoJpaController jpaTipoV;
    private ServiciosolicitadoJpaController jpaServicioS;
    private AdministradorJpaController jpaAdministrador;
    private CorteJpaController jpaCorte;
    private PagoJpaController jpaPago;
    private ServicioJpaController jpaServicio;
    private UsuarioJpaController jpaUsuario;
    //Listas
    private List<Administrador> listAdministrador;
    private List<Lavador> listLavador;
    private List<Costoservicio> listCosto;
    private List<Ticket> listTicket;
    private List<Automovil> listAutomovil;
    private List<Tamanio> listTamanio;
    private List<Tipovehiculo> listTipoV;
    private List<Serviciosolicitado> listServicioS;
    private List<Corte> listCorte;
    private List<Pago> listPago;
    private List<Servicio> listServicio;
    private List<Usuario> listUsuario;
    //Otras variables 
    private List<Corte> cortesSinPagar ;
    private List<Pago> pagosEnElMes;
    private List<Corte> cortesEnElMes;
    private Date date;
    private int idcorte;
    private boolean select;
    private ModeloPagos modPagos;
    private ModeloCortes  modCortes;
    private Date dtS;
    private List<Corte> cortesFiltrados;
    private TicketPago ticketP ;
    private double totalServicios;
    private List<Serviciosolicitado> servLav;
    private boolean filtrado;

    public panelPago() {
        initComponents();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LavaAutos_DERSPU");
        jpaLavador = new LavadorJpaController(emf);
        jpaCosto = new CostoservicioJpaController(emf);
        jpaTicket = new TicketJpaController(emf);
        jpaAutomovil = new AutomovilJpaController(emf);
        jpaTamanio = new TamanioJpaController(emf);
        jpaTipoV = new TipovehiculoJpaController(emf);
        jpaAdministrador = new AdministradorJpaController(emf);
        jpaCorte = new CorteJpaController(emf);
        jpaPago = new PagoJpaController(emf);
        jpaServicioS = new ServiciosolicitadoJpaController(emf);
        jpaServicio = new ServicioJpaController(emf);
        jpaUsuario = new UsuarioJpaController(emf);

        listLavador = jpaLavador.findLavadorEntities();
        listCosto = jpaCosto.findCostoservicioEntities();
        listTicket = jpaTicket.findTicketEntities();
        listAutomovil = jpaAutomovil.findAutomovilEntities();
        listTamanio = jpaTamanio.findTamanioEntities();
        listTipoV = jpaTipoV.findTipovehiculoEntities();
        listAdministrador = jpaAdministrador.findAdministradorEntities();
        listCorte = jpaCorte.findCorteEntities();
        listPago = jpaPago.findPagoEntities();
        listServicioS = jpaServicioS.findServiciosolicitadoEntities();
        listServicio = jpaServicio.findServicioEntities();
        listUsuario = jpaUsuario.findUsuarioEntities();
        //
        cortesSinPagar = new ArrayList<Corte>();
        pagosEnElMes = new ArrayList<Pago>();
        cortesEnElMes = new ArrayList<Corte>();
        //Obtener la fecha actual
        long miliseconds = System.currentTimeMillis();
        date = new Date(miliseconds);
        
        //Equis somos chavos
        initTables();
    }
    private String cortarCad(int i, int f, String cad){
        if(cad.length()>f){
            return cad.substring(i,f);
        }
        return cad;
    }
    public void initTables(){
        //Inicializamos las tablas
        sacarEnELMesPagos();
        ModeloPagos modPagos = new ModeloPagos(pagosEnElMes);
        jTable1.setModel(modPagos);
        jTable1.repaint();
        sacarEnELMesCortes();
        ModeloCortes modCortes = new ModeloCortes(cortesSinPagar);
        jTable2.setModel(modCortes);
        jTable2.repaint();
    }

    public void sacarEnELMesPagos() {
        if (listPago.isEmpty()) {
            return;
        }
        int month = date.getMonth();
        for (int i = 0; i < listPago.size(); i++) {
            if (listPago.get(i).getFecha().getMonth() == month - 1 || listPago.get(i).getFecha().getMonth() == month ) {
                pagosEnElMes.add(listPago.get(i));
            }
        }
    }
    public void sacarEnELMesCortes() {
        if (listCorte.isEmpty()) {
            return;
        }
        int month = date.getMonth();
        for (int i = 0; i < listCorte.size(); i++) {
            if (listCorte.get(i).getFecha().getMonth() == month - 1 || listCorte.get(i).getFecha().getMonth() == month ) {
                cortesEnElMes.add(listCorte.get(i));
            }
        }
        deudasPagos();
    }

    public void deudasPagos() {
        if (listCorte.isEmpty()) {
            return;
        }
        if(pagosEnElMes.isEmpty()){
            for(int i=0;i< cortesEnElMes.size();i++){
                cortesSinPagar.add(cortesEnElMes.get(i));
                
            }
            return;
        }
        boolean nopagado = false;
        for (int i = 0; i < cortesEnElMes.size(); i++) {
            //int idPago = pagosEnElMes.get(i).getCorteidCorte().getIdCorte();
            
               // int idC = listCorte.get(j).getIdCorte();
                if(!pagado(cortesEnElMes.get(i).getIdCorte(),0)){
                    cortesSinPagar.add(cortesEnElMes.get(i));
                }
            
        }
    }
    public boolean pagado(int idcorte, int index){
        if(index == pagosEnElMes.size())
            return false;
        else if(idcorte == pagosEnElMes.get(index).getCorteidCorte().getIdCorte()){
            return true;
        }else{
            return pagado(idcorte,index+1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtNombreLav = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnBuscar = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JLabel();
        btnRealizarPagar = new javax.swing.JPanel();
        txtRealizarPagar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 255), 1, true));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel1.setText("Gesti??n de pagos");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 17, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pagos ya realizados en el mes");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 230, 220, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel3.setText("Cortes no pagados");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 230, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 19)); // NOI18N
        jLabel4.setText("Pagar un corte");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel5.setText("Fecha del corte");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 108, -1, 20));

        jTable1.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 250, 400, 260));

        jTable2.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 250, 390, 260));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel6.setText("El corte es del empleado:  ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, 20));

        txtNombreLav.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txtNombreLav.setForeground(new java.awt.Color(102, 102, 102));
        txtNombreLav.setText("******");
        txtNombreLav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtNombreLavMousePressed(evt);
            }
        });
        add(txtNombreLav, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 160, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel8.setText("A la fecha:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, -1, -1));

        txtFecha.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(102, 102, 102));
        txtFecha.setText("*****");
        txtFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtFechaMousePressed(evt);
            }
        });
        add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 110, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, 110, 10));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 160, 10));

        btnBuscar.setBackground(new java.awt.Color(255, 204, 204));
        btnBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtBuscar.setFont(new java.awt.Font("Roboto Medium", 1, 15)); // NOI18N
        txtBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBuscar.setText("Buscar");
        txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtBuscarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnBuscarLayout = new javax.swing.GroupLayout(btnBuscar);
        btnBuscar.setLayout(btnBuscarLayout);
        btnBuscarLayout.setHorizontalGroup(
            btnBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );
        btnBuscarLayout.setVerticalGroup(
            btnBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 100, 110, 30));

        btnRealizarPagar.setBackground(new java.awt.Color(255, 204, 204));
        btnRealizarPagar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnRealizarPagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtRealizarPagar.setFont(new java.awt.Font("Roboto Medium", 1, 15)); // NOI18N
        txtRealizarPagar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRealizarPagar.setText("Realizar el pago");
        txtRealizarPagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRealizarPagarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtRealizarPagarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtRealizarPagarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnRealizarPagarLayout = new javax.swing.GroupLayout(btnRealizarPagar);
        btnRealizarPagar.setLayout(btnRealizarPagarLayout);
        btnRealizarPagarLayout.setHorizontalGroup(
            btnRealizarPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRealizarPagarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtRealizarPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnRealizarPagarLayout.setVerticalGroup(
            btnRealizarPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtRealizarPagar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        add(btnRealizarPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, -1, 30));

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/pago.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 540));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 350, 30));
        add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 160, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseClicked
        //Aqu?? va lo que realizar?? el boton buscar
        // = false;
        /*if (listCorte.isEmpty()) {
            jLabel10.setText("Al parecer no se ha realizado ningun corte");
            return;
        }
        if (numIdCorte.getText() == "" || numIdCorte.getText() == null) {
            jLabel10.setText("Ingresa un ID de corte");
            return;
        }
        
        try {
            idcorte = Integer.parseInt(numIdCorte.getText());
            for (Corte cut : listCorte) {
                if (idcorte == cut.getIdCorte()) {
                    find = true;
                    corte = cut;
                    Date fecha = cut.getFecha();
                    long sfecha = fecha.getTime();
                    java.sql.Date fecha1 = new java.sql.Date(sfecha);
                    //System.out.println("fecha: "+cut.getFecha().getMonth()+" dia "+cut.getFecha().getDay());
                    jLabel11.setText("Id encontrado");
                    txtNombreLav.setText(cut.getLavadoridLavador().getUsuarioidUsuario().getNombre());
                    txtFecha.setText(""+fecha1);
                    btnRealizarPagar.setEnabled(true);
                    txtNombreLav.setForeground(Color.BLACK);
                    txtFecha.setForeground(Color.BLACK);
                    break;
                }
            }
        } catch (NumberFormatException nfe) {
            jLabel11.setText("Ingresa solo numeros");
        }
        if(!find){
            find = false;
            jLabel11.setText("ID No encontrado");
        }
        */
        filtrado = false;
        cortesFiltrados = new ArrayList<Corte>();
        Date dt1;
        dtS = jDateChooser1.getDate();
        long fecha = dtS.getTime();
        java.sql.Date fecha1 = new java.sql.Date(fecha);
        for(int i=0; i< cortesSinPagar.size(); i++){
            dt1 = cortesSinPagar.get(i).getFecha();
            long fech = dt1.getTime();
            java.sql.Date fecha2 = new java.sql.Date(fech);
            if(fecha1.toString().equals(fecha2.toString())){
                cortesFiltrados.add(cortesSinPagar.get(i));
            }
        }
        if(cortesFiltrados.isEmpty()){
            jLabel11.setText("Al parecer no tienes cortes SIN PAGAR a esa fecha");
            ModeloCortes modCortes = new ModeloCortes(cortesSinPagar);
            jTable2.setModel(modCortes);
            jTable2.repaint();
            return;
        }
        filtrado = true;
        select = false;
        ModeloCortes modCortes = new ModeloCortes(cortesFiltrados);
        jLabel11.setText("");
        jTable2.setModel(modCortes);
        jTable2.repaint();
        txtRealizarPagar.setEnabled(true);
    }//GEN-LAST:event_txtBuscarMouseClicked

    private void txtRealizarPagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRealizarPagarMouseClicked
        if(select == false){ 
            return;
        }
        if(corte == null){
            return;
        }
        
        for(int i=0; i<pagosEnElMes.size(); i++){
            if(pagosEnElMes.get(i).getCorteidCorte().getIdCorte() == corte.getIdCorte()){
                jLabel11.setText("Este corte ya fue pagado");
                return;
            }
        }
        
        
        
        
        //Imprimir tickets
        /*
        dtS = jDateChooser1.getDate();
        long fecha = dtS.getTime();
        java.sql.Date fecha1 = new java.sql.Date(fecha);
        servLav = new ArrayList<Serviciosolicitado>();
        totalServicios =0.0;
        //System.out.println("Feacha DCH "+dt);
        for(int i =0; i<listServicioS.size(); i++){
            if(listServicioS.get(i).getTicketidTicket().getLavadoridLavador().getIdLavador() == lavador.getIdLavador()
                    &&  fecha1.equals(listServicioS.get(i).getTicketidTicket().getFecha())){
                servLav.add(listServicioS.get(i));
                totalServicios +=listServicioS.get(i).getCostoServicioidServicioCosto().getPrecio();
                
            }
            //System.out.println("ID lavador "+listServicioS.get(i).getTicketidTicket().getLavadoridLavador().getIdLavador());
            //System.out.println("fecha for "+listServicioS.get(i).getTicketidTicket().getFecha());
        }
        int [][] totales = new int[2][listCosto.size()];
        for(int i=0; i<listCosto.size();i++){
            totales[0][i] = listCosto.get(i).getIdServicioCosto();
            totales[1][i] =0;
            
        }
        
        for(int i=0;i< listCosto.size(); i++){
            for (int j=0; j< servLav.size(); j++){
                if(totales[0][i] == servLav.get(j).getCostoServicioidServicioCosto().getIdServicioCosto()){
                    totales[1][i] ++;
                }
                //System.out.println(totales[0][i]+"  "+totales[1][i]);
            }
            
        }
        String lista = "";
        
        for(int i=0; i< listCosto.size(); i++){
            
            if(totales[1][i] >0){
                try{
            lista+= String.format("%15s %7s %5s %2d\n",
                    cortarCad(0,14,listCosto.get(i).getServicioidServicio().getNombreServicio()),
                    cortarCad(0,6,listCosto.get(i).getTipoVehiculoidTipoVehiculo().getDescripcion()),
                    cortarCad(0,4,listCosto.get(i).getTamanioidTamanio().getDescripcion()),
                    totales[1][i]);
            }catch(NullPointerException e){
                lista+= String.format("%15s %7s %5s %2d\n",
                    cortarCad(0,14,listCosto.get(i).getServicioidServicio().getNombreServicio()),
                    "NA",
                    cortarCad(0,4,listCosto.get(i).getTamanioidTamanio().getDescripcion()),
                    totales[1][i]);
            }
                    }
        }
        System.out.println(lista);*/
        if(ticketP!=null){
            ticketP = null;
        }
        Date dtC = corte.getFecha();
        long fecha = dtC.getTime();
        java.sql.Date fechaC = new java.sql.Date(fecha);
        
        long fecha1 = date.getTime();
        java.sql.Date fechaP = new java.sql.Date(fecha1);
        
        double comision = 0;
        for(Lavador lav : listLavador){
            if(corte.getLavadoridLavador().getIdLavador() == lav.getIdLavador()){
                comision = lav.getComision();
                break;
            }
        }
        
        ticketP = new TicketPago();
        
        ticketP.setFolio(corte.getIdCorte()+"");
        ticketP.setFechaC(fechaC+"");
        ticketP.setFechaP(fechaP+"");
        ticketP.setTotal(corte.getMonto()+"");
        ticketP.setPorcentaje(comision+"");
        ticketP.setSalario(corte.getMonto()*(comision/100)+"");
        ticketP.setIDVendedor(corte.getLavadoridLavador().getIdLavador()+"");
        ticketP.setVendedor(corte.getLavadoridLavador().getUsuarioidUsuario().getNombre());
        
        ticketP.imprimirTicket();
        
        try {
            ticketP.print(true);
        } catch (IOException ex) {
            Logger.getLogger(panelPago.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrintException ex) {
            Logger.getLogger(panelPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Agregacion del corte sin pagar a la tabla pago
        int idPago;
        if (!listPago.isEmpty()) {
            idPago = listPago.get(listPago.size() - 1).getIdPago() + 1;
        } else {
            idPago = 1;
        }
        pago = new Pago();
        pago.setCorteidCorte(corte);
        pago.setIdPago(idPago);
        pago.setFecha(date);
        jpaPago.create(pago);
        listPago.add(pago);
        jLabel11.setText("PAGO REALIZADO");
        cortesSinPagar = new ArrayList<Corte>();
        pagosEnElMes = new ArrayList<Pago>();
        cortesEnElMes = new ArrayList<Corte>();
        jTable1.removeAll();
        jTable2.removeAll();
        initTables();
        jScrollPane1.repaint();
        jScrollPane2.repaint();
        btnRealizarPagar.setEnabled(false);
        
        filtrado = false;
        txtRealizarPagar.setEnabled(false);
    }//GEN-LAST:event_txtRealizarPagarMouseClicked

    private void txtBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseEntered
        btnBuscar.setBackground(new Color(245, 214, 184));//[255,204,204]
    }//GEN-LAST:event_txtBuscarMouseEntered

    private void txtBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseExited
        btnBuscar.setBackground(new Color(255, 204, 204));
    }//GEN-LAST:event_txtBuscarMouseExited

    private void txtRealizarPagarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRealizarPagarMouseEntered
        btnRealizarPagar.setBackground(new Color(245, 214, 184));//[255,204,204]
    }//GEN-LAST:event_txtRealizarPagarMouseEntered

    private void txtRealizarPagarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRealizarPagarMouseExited
        btnRealizarPagar.setBackground(new Color(255, 204, 204));//[255,204,204]
    }//GEN-LAST:event_txtRealizarPagarMouseExited

    private void txtNombreLavMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreLavMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreLavMousePressed

    private void txtFechaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFechaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaMousePressed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // Para regresar  lo que
        //if(jTable1.)
        corte = null;
        if(filtrado){
        corte =  cortesFiltrados.get(jTable2.getSelectedRow());
        
        }
        else{
            corte = cortesSinPagar.get(jTable2.getSelectedRow());
        }
        txtNombreLav.setText(corte.getLavadoridLavador().getUsuarioidUsuario().getNombre());
        Date dt = corte.getFecha();
        long fecha = dt.getTime();
        java.sql.Date fecha1 = new java.sql.Date(fecha);
        txtFecha.setText(fecha1+"");
        select = true;
    }//GEN-LAST:event_jTable2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnBuscar;
    private javax.swing.JPanel btnRealizarPagar;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel txtBuscar;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JLabel txtNombreLav;
    private javax.swing.JLabel txtRealizarPagar;
    // End of variables declaration//GEN-END:variables
}
