/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Controllers.*;
import EntityClasses.*;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.PrintException;
import modelos.ModeloServiciosSolicitados;
import modelos.TicketCorte;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author juan6
 */
public class panelCorte extends javax.swing.JPanel {

    /**
     * Creates new form panelCorte
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
    
    //otras variables 
    private List<Serviciosolicitado> servLav;
    private double totalServicios;
    private TicketCorte ticketC;
    private Date dt;
    private boolean find;
    public panelCorte() {
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
        
        find = false;
        vaciar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreEmpleado = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JLabel();
        txtMensajeCorteRe = new javax.swing.JLabel();
        txtMensajeCodUsu = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnCalcCorte = new javax.swing.JPanel();
        txtCalcCorte = new javax.swing.JLabel();
        btnImpComp = new javax.swing.JPanel();
        txtImpComp = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooser1.setDateFormatString("yyyy-MM-d");
        add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 180, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel1.setText("Ingresa tu codigo de usuario: ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, -1, 30));

        jTextField1.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField1.setBorder(null);
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 137, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel2.setText("Efectivo generado por ti:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, -1, 30));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel3.setText("$00.00");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 150, 80, 30));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel4.setText("Sus trabajos realizados al dia son:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 1, 22)); // NOI18N
        jLabel5.setText("CORTE DE CAJA DIARIO");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 520, 220));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel6.setText("Selecciona el dia: ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, 30));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel7.setText("Nombre de empleado: ");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, 30));

        txtNombreEmpleado.setFont(new java.awt.Font("Roboto Medium", 0, 15)); // NOI18N
        txtNombreEmpleado.setForeground(new java.awt.Color(153, 153, 153));
        txtNombreEmpleado.setText("Nombre empleado");
        add(txtNombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 230, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel9.setText("Porcentaje de comisión: ");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, -1, 30));

        txtPorcentaje.setFont(new java.awt.Font("Roboto Medium", 0, 15)); // NOI18N
        txtPorcentaje.setForeground(new java.awt.Color(153, 153, 153));
        txtPorcentaje.setText("0%");
        add(txtPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 60, -1));

        txtMensajeCorteRe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add(txtMensajeCorteRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, 420, 30));

        txtMensajeCodUsu.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txtMensajeCodUsu.setForeground(new java.awt.Color(255, 0, 0));
        add(txtMensajeCodUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 280, 20));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 230, 10));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 50, 10));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 210, 240, 290));

        jLabel13.setFont(new java.awt.Font("Roboto Light", 1, 15)); // NOI18N
        jLabel13.setText("TICKET");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 190, -1, -1));

        btnBuscar.setBackground(new java.awt.Color(153, 153, 255));
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
            .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        btnBuscarLayout.setVerticalGroup(
            btnBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, -1, 30));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, 140, 10));

        btnCalcCorte.setBackground(new java.awt.Color(153, 153, 255));
        btnCalcCorte.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnCalcCorte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtCalcCorte.setFont(new java.awt.Font("Roboto Medium", 1, 15)); // NOI18N
        txtCalcCorte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCalcCorte.setText("Calcular sueldo");
        txtCalcCorte.setEnabled(false);
        txtCalcCorte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCalcCorteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtCalcCorteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtCalcCorteMouseExited(evt);
            }
        });
        txtCalcCorte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCalcCorteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout btnCalcCorteLayout = new javax.swing.GroupLayout(btnCalcCorte);
        btnCalcCorte.setLayout(btnCalcCorteLayout);
        btnCalcCorteLayout.setHorizontalGroup(
            btnCalcCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCalcCorte, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        btnCalcCorteLayout.setVerticalGroup(
            btnCalcCorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCalcCorte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        add(btnCalcCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, -1, 30));

        btnImpComp.setBackground(new java.awt.Color(153, 153, 255));
        btnImpComp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnImpComp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtImpComp.setFont(new java.awt.Font("Roboto Medium", 1, 15)); // NOI18N
        txtImpComp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtImpComp.setText("Imprimir comprobante");
        txtImpComp.setEnabled(false);
        txtImpComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtImpCompMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtImpCompMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtImpCompMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnImpCompLayout = new javax.swing.GroupLayout(btnImpComp);
        btnImpComp.setLayout(btnImpCompLayout);
        btnImpCompLayout.setHorizontalGroup(
            btnImpCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtImpComp, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );
        btnImpCompLayout.setVerticalGroup(
            btnImpCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtImpComp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        add(btnImpComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 180, 30));

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/corte.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 570));
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseClicked
        // BotonBuscar
        find = false;
        if(listServicioS.isEmpty() ){
            txtMensajeCodUsu.setText("No hay ningun registro en la BD");
            vaciar();
            return;
        }
        
        if(jDateChooser1.getDate() == null){
            txtMensajeCodUsu.setText("Selecciona una fecha");
            vaciar();
            return;
        }
        
        if(jTextField1.getText() == null || jTextField1.getText() == ""){
            txtMensajeCodUsu.setText("Ingresa un ID de empleado");
            vaciar();
            return;
        }
        
        for (int i=0; i < listLavador.size(); i++){
            //System.out.println(i);
            try{
            if(listLavador.get(i).getIdLavador() == Integer.parseInt(jTextField1.getText())){
                txtNombreEmpleado.setForeground(Color.BLACK);
                txtPorcentaje.setForeground(Color.BLACK);
                txtMensajeCodUsu.setText("ID encontrado");
                lavador = listLavador.get(i);
                txtNombreEmpleado.setText(lavador.getUsuarioidUsuario().getNombre());
                txtPorcentaje.setText(lavador.getComision()+" % ");
                find = true;
                break;
            }
            }catch(NumberFormatException e){
                txtMensajeCodUsu.setText("Ingrese valores numericos");
                vaciar();
                return;
            }
        }
        if(lavador == null){
                txtMensajeCodUsu.setText("ID no encontrado");
                vaciar();
                return;
            }
         dt = jDateChooser1.getDate();

        servLav = new ArrayList<Serviciosolicitado>();
        totalServicios =0.0;
        //System.out.println("Feacha DCH "+dt);
        for(int i =0; i<listServicioS.size(); i++){
            if(listServicioS.get(i).getTicketidTicket().getLavadoridLavador().getIdLavador() == lavador.getIdLavador()
                    &&  dt.equals(listServicioS.get(i).getTicketidTicket().getFecha())){
                servLav.add(listServicioS.get(i));
                totalServicios +=listServicioS.get(i).getCostoServicioidServicioCosto().getPrecio();
                
            }
            //System.out.println("ID lavador "+listServicioS.get(i).getTicketidTicket().getLavadoridLavador().getIdLavador());
            //System.out.println("fecha for "+listServicioS.get(i).getTicketidTicket().getFecha());
        }
        jLabel3.setText(totalServicios+"");
        if(servLav.isEmpty()){
            find = false;
            txtMensajeCodUsu.setText("Al parecer no tienes ningun registro");
            vaciar();
            return;
        }
        ModeloServiciosSolicitados modSS = new ModeloServiciosSolicitados(servLav);
        jTable1.setModel(modSS);
        jTable1.repaint();
        jLabel3.setText(totalServicios+"");
        txtCalcCorte.setEnabled(true);
    }//GEN-LAST:event_txtBuscarMouseClicked
    
    private void vaciar(){
        servLav = new ArrayList<Serviciosolicitado>();
        ModeloServiciosSolicitados modSS = new ModeloServiciosSolicitados(servLav);
        jTable1.setModel(modSS);
        jTable1.repaint();
        jLabel3.setText("00.0");
        txtCalcCorte.setEnabled(false);
        txtImpComp.setEnabled(false);
        jTextArea1.setText("");
        //jScrollPane2.removeAll();
        //jScrollPane2.add(jTextArea1);
        jScrollPane2.repaint();
        txtMensajeCorteRe.setText("");
    }
    
    private void txtCalcCorteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCalcCorteKeyPressed
        // Boton Calcular Sueldo
        
    }//GEN-LAST:event_txtCalcCorteKeyPressed

    private void txtImpCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtImpCompMouseClicked
        if(!txtImpComp.isEnabled()){
            return;
        }
        if(find == false){
            return;
        }
        try {
            // TODO add your handling code here:
            ticketC.print(true);
        } catch (IOException ex) {
            Logger.getLogger(panelCorte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrintException ex) {
            Logger.getLogger(panelCorte.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtCalcCorte.setEnabled(false);
        txtImpComp.setEnabled(false);
        jTextArea1.setText("");
        jTable1.removeAll();
        jTable1.repaint();
    }//GEN-LAST:event_txtImpCompMouseClicked

    private void txtCalcCorteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCalcCorteMouseClicked
        // Boton para calcular el sueldo
        
        if(!txtCalcCorte.isEnabled())
            return;
        if(find == false){
            return;
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
        System.out.println(lista);
        int idcorte;
        if (!listCorte.isEmpty()) {
            idcorte = listCorte.get(listCorte.size() - 1).getIdCorte() + 1;
        } else {
            idcorte = 1;
        }
        if(ticketC!=null){
            ticketC = null;
        }
        
        
        if(!listCorte.isEmpty()){
            for(int i=0; i< listCorte.size(); i++){
                
                if(listCorte.get(i).getLavadoridLavador().getIdLavador() == lavador.getIdLavador()
                        && dt.equals(listCorte.get(i).getFecha() )){
                    Date hoy = jDateChooser1.getDate();
                    long fecha = hoy.getTime();
                    java.sql.Date fecha1 = new java.sql.Date(fecha);
                    txtMensajeCorteRe.setText(" ");
                    idcorte =listCorte.get(i).getIdCorte();
                    ticketC = new TicketCorte();
                    ticketC.setFecha(""+fecha1);
                    ticketC.setArticulos(lista);
                    ticketC.setTotal(totalServicios+"");
                    ticketC.setPorcentaje(lavador.getComision()+"%");
                    ticketC.setSubTotal(totalServicios*(lavador.getComision()/100)+"");
                    ticketC.setIDVendedor(lavador.getIdLavador()+"");
                    ticketC.setVendedor(lavador.getUsuarioidUsuario().getNombre());
                    ticketC.setFolio(idcorte+"");
                    jTextArea1.setText(ticketC.imprimirTicket());
                    //jScrollPane2.removeAll();
                    //jScrollPane2.add(jTextArea1);
                    jScrollPane2.repaint();
                    txtMensajeCorteRe.setText("EL CORTE YA FUE REALIZADO ANTERIORMENTE");
                    txtImpComp.setEnabled(true);
                    return;
                }
            }
        }
        ticketC = new TicketCorte();
        Date hoy = jDateChooser1.getDate();
        long fecha = hoy.getTime();
        java.sql.Date fecha1 = new java.sql.Date(fecha);
        ticketC.setFecha(fecha1+"");
        ticketC.setArticulos(lista);
        ticketC.setTotal(totalServicios+"");
        ticketC.setPorcentaje(lavador.getComision()+"%");
        ticketC.setSubTotal(totalServicios*(lavador.getComision()/100)+"");
        ticketC.setIDVendedor(lavador.getIdLavador()+"");
        ticketC.setVendedor(lavador.getUsuarioidUsuario().getNombre());
        ticketC.setFolio(idcorte+"");
        jTextArea1.setText(ticketC.imprimirTicket());
        //jScrollPane2.removeAll();
        //jScrollPane2.add(jTextArea1);
        jScrollPane2.repaint();
        corte= new Corte();
        corte.setFecha(dt);
        corte.setMonto(totalServicios);
        corte.setLavadoridLavador(lavador);
        corte.setIdCorte(idcorte);
        jpaCorte.create(corte);
        listCorte.add(corte);
        txtImpComp.setEnabled(true);
        txtMensajeCorteRe.setText("CORTE REALIZADO CORRECTAMENTE");
    }//GEN-LAST:event_txtCalcCorteMouseClicked

    private void txtBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseEntered
        btnBuscar.setBackground(new Color(202,217,203));//[214,217,223]
    }//GEN-LAST:event_txtBuscarMouseEntered

    private void txtBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseExited
        btnBuscar.setBackground(new Color(214,217,223));//214,217,223
    }//GEN-LAST:event_txtBuscarMouseExited

    private void txtImpCompMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtImpCompMouseEntered
        btnImpComp.setBackground(new Color(202,217,203));//[214,217,223]
    }//GEN-LAST:event_txtImpCompMouseEntered

    private void txtImpCompMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtImpCompMouseExited
        btnImpComp.setBackground(new Color(214,217,223));
    }//GEN-LAST:event_txtImpCompMouseExited

    private void txtCalcCorteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCalcCorteMouseEntered
        btnCalcCorte.setBackground(new Color(202,217,203));//[214,217,223]
    }//GEN-LAST:event_txtCalcCorteMouseEntered

    private void txtCalcCorteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCalcCorteMouseExited
        btnCalcCorte.setBackground(new Color(214,217,223));
    }//GEN-LAST:event_txtCalcCorteMouseExited
    
    
    private String cortarCad(int i, int f, String cad){
        if(cad.length()>f){
            return cad.substring(i,f);
        }
        return cad;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnBuscar;
    private javax.swing.JPanel btnCalcCorte;
    private javax.swing.JPanel btnImpComp;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel txtBuscar;
    private javax.swing.JLabel txtCalcCorte;
    private javax.swing.JLabel txtImpComp;
    private javax.swing.JLabel txtMensajeCodUsu;
    private javax.swing.JLabel txtMensajeCorteRe;
    private javax.swing.JLabel txtNombreEmpleado;
    private javax.swing.JLabel txtPorcentaje;
    // End of variables declaration//GEN-END:variables
}
