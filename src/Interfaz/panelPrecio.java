/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Controllers.CostoservicioJpaController;
import Controllers.ServicioJpaController;
import Controllers.TamanioJpaController;
import Controllers.TipovehiculoJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import EntityClasses.Costoservicio;
import EntityClasses.Servicio;
import EntityClasses.Tamanio;
import EntityClasses.Tipovehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author J0SE_
 */
public class panelPrecio extends javax.swing.JPanel {

    /**
     * Creates new form panelPrecio
     */
    //ATRIBUTOS EXTERNOS DE LOS COMPONENTES QUE INTEGRAN LA INTERFAZ
    //EntityClasses
    private Servicio service;
    private Tamanio size;
    private Tipovehiculo type;
    private Costoservicio cost;
    //Controllers
    private ServicioJpaController cService;
    private TamanioJpaController cSize;
    private TipovehiculoJpaController cType;
    private CostoservicioJpaController cCost;
    //Listas
    private List<Servicio> listService;
    private List<Tamanio> listSize;
    private List<Tipovehiculo> listType;
    private List<Costoservicio> listCost;

    public panelPrecio() {
        initComponents();
        LoadList();

        assignPrice(jLabel3, jComboBox1, jComboBox4, jLabel6);
        assignPrice(jLabel7, jComboBox2, jComboBox5, jLabel8);
        assignPrice(jLabel11, jComboBox3, jComboBox12, jLabel13);

        assignPrice();

    }

    /*public void LoadOldPrices(){
        
    }*/
    public void LoadList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LavaAutos_DERSPU");
        //Instancia de los controladores con el administrador de entidades
        cService = new ServicioJpaController(emf);
        cSize = new TamanioJpaController(emf);
        cType = new TipovehiculoJpaController(emf);
        cCost = new CostoservicioJpaController(emf);

        //Cargar a la listas los datos en la base de datos
        listService = cService.findServicioEntities();
        listSize = cSize.findTamanioEntities();
        listType = cType.findTipovehiculoEntities();
        listCost = cCost.findCostoservicioEntities();
    }

    public void assignPrice(JLabel etiqueta, JComboBox c1, JComboBox c2, JLabel etiqueta2) {
        String nc1 = c1.getItemAt(c1.getSelectedIndex()).toString();
        String tipo = etiqueta.getText();
        String nc2 = c2.getItemAt(c2.getSelectedIndex()).toString();

        for (Tipovehiculo tv : listType) {
            if (tipo.equalsIgnoreCase(tv.getDescripcion())) {
                type = tv;
            }
        }

        for (Tamanio tam : listSize) {
            if (nc1.equalsIgnoreCase(tam.getDescripcion())) {
                size = tam;
            }
        }

        for (Servicio id : listService) {
            if (nc2.equalsIgnoreCase(id.getNombreServicio())) {
                service = id;
            }
        }

        for (Costoservicio cs : listCost) {
            try {
                if (type.getIdTipoVehiculo() == cs.getTipoVehiculoidTipoVehiculo().getIdTipoVehiculo()) {
                    if (size.getIdTamanio() == cs.getTamanioidTamanio().getIdTamanio()) {
                        if (service.getIdServicio() == cs.getServicioidServicio().getIdServicio()) {
                            etiqueta2.setText("" + cs.getPrecio());
                        }
                    }
                }
            } catch (NullPointerException npe) {
            }
        }
    }

    public void assignPrice() {
        String auto = jLabel9.getText();
        String item = jComboBox6.getItemAt(jComboBox6.getSelectedIndex());
        int x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0;
        int index1 = jComboBox10.getSelectedIndex() + 201;
        int index2 = jComboBox11.getSelectedIndex() + 201;

        for (Tipovehiculo tv : listType) {
            if (tv.getDescripcion().equalsIgnoreCase(auto)) {
                type = tv;
            }
            if (index1 == tv.getIdTipoVehiculo()) {
                x6 = tv.getIdTipoVehiculo();
            }
            if (index2 == tv.getIdTipoVehiculo()) {
                x7 = tv.getIdTipoVehiculo();
            }
        }

        for (Tamanio tam : listSize) {
            if (tam.getDescripcion().equalsIgnoreCase(" ")) {
                size = tam;
            }
        }

        for (Servicio ser : listService) {
            if (ser.getNombreServicio().equalsIgnoreCase("Exceso de suciedad")) {
                x1 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase("Lavado de asiento (individual)")) {
                x2 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase(item)) {
                x3 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase("Lavado con encerado (pasta)")) {
                x4 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase("Lavado con encerado (pasta premium)")) {
                x5 = ser.getIdServicio();
            }
        }

        for (Costoservicio cs : listCost) {
            if (x1 == cs.getServicioidServicio().getIdServicio()) {
                jLabel14.setText("" + cs.getPrecio());
            }

            if (x2 == cs.getServicioidServicio().getIdServicio()) {
                jLabel23.setText("" + cs.getPrecio());
            }

            try {
                if (type.getIdTipoVehiculo() == cs.getTipoVehiculoidTipoVehiculo().getIdTipoVehiculo()) {
                    if (size.getIdTamanio() == cs.getTamanioidTamanio().getIdTamanio()) {
                        if (x3 == cs.getServicioidServicio().getIdServicio()) {
                            jLabel10.setText("" + cs.getPrecio());
                        }
                    }
                }
            } catch (NullPointerException npe) {
            }

            if (406 == cs.getServicioidServicio().getIdServicio()) {
                if (jComboBox7.getItemAt(jComboBox7.getSelectedIndex()).equalsIgnoreCase(cs.getTamanioidTamanio().getDescripcion())) {
                    jLabel17.setText("" + cs.getPrecio());
                }
            }

            if (407 == cs.getServicioidServicio().getIdServicio()) {
                if (jComboBox8.getItemAt(jComboBox8.getSelectedIndex()).equalsIgnoreCase(cs.getTamanioidTamanio().getDescripcion())) {
                    jLabel20.setText("" + cs.getPrecio());
                }
            }

            if (408 == cs.getServicioidServicio().getIdServicio()) {
                if (jComboBox9.getItemAt(jComboBox9.getSelectedIndex()).equalsIgnoreCase(cs.getTamanioidTamanio().getDescripcion())) {
                    jLabel21.setText("" + cs.getPrecio());
                }
            }
            if (x4 == cs.getServicioidServicio().getIdServicio()) {
                if (x6 == cs.getTipoVehiculoidTipoVehiculo().getIdTipoVehiculo()) {
                    jLabel27.setText("" + cs.getPrecio());
                }
            }
            if (x5 == cs.getServicioidServicio().getIdServicio()) {
                if (x7 == cs.getTipoVehiculoidTipoVehiculo().getIdTipoVehiculo()) {
                    jLabel28.setText("" + cs.getPrecio());
                }
            }
        }
    }

    public boolean verify(JTextField campo) {
        boolean b1;
        if (campo.getText().equalsIgnoreCase("")) {
            b1 = true;
        } else {
            try {
                double txt = Double.parseDouble(campo.getText());
                b1 = true;
            } catch (NumberFormatException nfe) {
                b1 = false;
            }
        }
        return b1;
    }

    public void edit(JLabel etiqueta, JComboBox c1, JComboBox c2, JTextField tf) {
        String nc1 = c1.getItemAt(c1.getSelectedIndex()).toString();
        String tipo = etiqueta.getText();
        String nc2 = c2.getItemAt(c2.getSelectedIndex()).toString();

        if (!tf.getText().equalsIgnoreCase("")) {

            for (Tipovehiculo tv : listType) {
                if (tipo.equalsIgnoreCase(tv.getDescripcion())) {
                    type = tv;
                }
            }

            for (Tamanio tam : listSize) {
                if (nc1.equalsIgnoreCase(tam.getDescripcion())) {
                    size = tam;
                }
            }

            for (Servicio id : listService) {
                if (nc2.equalsIgnoreCase(id.getNombreServicio())) {
                    service = id;
                }
            }

            for (Costoservicio cs : listCost) {
                try {
                    if (type.getIdTipoVehiculo() == cs.getTipoVehiculoidTipoVehiculo().getIdTipoVehiculo()) {
                        if (size.getIdTamanio() == cs.getTamanioidTamanio().getIdTamanio()) {
                            if (service.getIdServicio() == cs.getServicioidServicio().getIdServicio()) {
                                double newPrice = Double.parseDouble(tf.getText());
                                cs.setPrecio(newPrice);
                                cCost.edit(cs);
                                listCost = cCost.findCostoservicioEntities();
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void edit() {
        //Taxi
        String auto = jLabel9.getText();
        String item = jComboBox6.getItemAt(jComboBox6.getSelectedIndex());

        //Lavado de interiores y Encerado
        int x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0, x8 = 0, x9 = 0, x10 = 0;
        int index1 = jComboBox10.getSelectedIndex() + 201;
        int index2 = jComboBox11.getSelectedIndex() + 201;

        for (Tipovehiculo tv : listType) {
            if (tv.getDescripcion().equalsIgnoreCase(auto)) {
                type = tv;
            }
            if (index1 == tv.getIdTipoVehiculo()) {
                x6 = tv.getIdTipoVehiculo();
            }
            if (index2 == tv.getIdTipoVehiculo()) {
                x7 = tv.getIdTipoVehiculo();
            }
        }

        for (Tamanio tam : listSize) {
            if (tam.getDescripcion().equalsIgnoreCase(" ")) {
                size = tam;
            }
        }

        for (Servicio ser : listService) {
            if (ser.getNombreServicio().equalsIgnoreCase("Exceso de suciedad")) {
                x1 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase("Lavado de asiento (individual)")) {
                x2 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase(item)) {
                x3 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase("Lavado con encerado (pasta)")) {
                x4 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase("Lavado con encerado (pasta premium)")) {
                x5 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase("Lavado Interior Paquete LITE")) {
                x8 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase("Lavado Interior Paquete MEDIO")) {
                x9 = ser.getIdServicio();
            }
            if (ser.getNombreServicio().equalsIgnoreCase("Lavado Interior Paquete FULL")) {
                x10 = ser.getIdServicio();
            }
        }

        for (Costoservicio cs : listCost) {
            try {
                if (!jTextField5.getText().equalsIgnoreCase("")) {
                    if (x1 == cs.getServicioidServicio().getIdServicio()) {
                        double price = Double.parseDouble(jTextField5.getText());
                        cs.setPrecio(price);
                        cCost.edit(cs);
                    }
                }

                if (!jTextField9.getText().equalsIgnoreCase("")) {
                    if (x2 == cs.getServicioidServicio().getIdServicio()) {
                        double price = Double.parseDouble(jTextField9.getText());
                        cs.setPrecio(price);
                        cCost.edit(cs);
                    }
                }

                if (!jTextField3.getText().equalsIgnoreCase("")) {
                    if (type.getIdTipoVehiculo() == cs.getTipoVehiculoidTipoVehiculo().getIdTipoVehiculo()) {
                        if (size.getIdTamanio() == cs.getTamanioidTamanio().getIdTamanio()) {
                            if (x3 == cs.getServicioidServicio().getIdServicio()) {
                                double price = Double.parseDouble(jTextField3.getText());
                                cs.setPrecio(price);
                                cCost.edit(cs);
                            }
                        }
                    }
                }

                //Lavado de interiores (Paquetes LITE, MEDIO, FULL)
                String tam = cs.getTamanioidTamanio().getDescripcion();

                if (!jTextField6.getText().equalsIgnoreCase("")) {
                    if (x8 == cs.getServicioidServicio().getIdServicio()) {
                        String option = jComboBox7.getItemAt(jComboBox7.getSelectedIndex());

                        if (option.equalsIgnoreCase(tam)) {
                            double price = Double.parseDouble(jTextField6.getText());
                            cs.setPrecio(price);
                            cCost.edit(cs);
                        }
                    }
                }

                if (!jTextField7.getText().equalsIgnoreCase("")) {
                    if (x9 == cs.getServicioidServicio().getIdServicio()) {
                        String option = jComboBox8.getItemAt(jComboBox8.getSelectedIndex());

                        if (option.equalsIgnoreCase(tam)) {
                            double price = Double.parseDouble(jTextField7.getText());
                            cs.setPrecio(price);
                            cCost.edit(cs);
                        }
                    }
                }

                if (!jTextField8.getText().equalsIgnoreCase("")) {
                    if (x10 == cs.getServicioidServicio().getIdServicio()) {
                        String option = jComboBox9.getItemAt(jComboBox9.getSelectedIndex());

                        if (option.equalsIgnoreCase(tam)) {
                            double price = Double.parseDouble(jTextField8.getText());
                            cs.setPrecio(price);
                            cCost.edit(cs);
                        }
                    }
                }

                //Encerado
                if (!jTextField10.getText().equalsIgnoreCase("")) {
                    if (x4 == cs.getServicioidServicio().getIdServicio()) {
                        if (x6 == cs.getTipoVehiculoidTipoVehiculo().getIdTipoVehiculo()) {
                            double price = Double.parseDouble(jTextField10.getText());
                            cs.setPrecio(price);
                            cCost.edit(cs);
                        }
                    }
                }

                if (!jTextField11.getText().equalsIgnoreCase("")) {
                    if (x5 == cs.getServicioidServicio().getIdServicio()) {
                        if (x7 == cs.getTipoVehiculoidTipoVehiculo().getIdTipoVehiculo()) {
                            double price = Double.parseDouble(jTextField11.getText());
                            cs.setPrecio(price);
                            cCost.edit(cs);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public void clean() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator7 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jComboBox11 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JPanel();
        txtLimpiar = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JPanel();
        txtGuardar = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jComboBox12 = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 255), 1, true));
        setPreferredSize(new java.awt.Dimension(1020, 570));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 1, 22)); // NOI18N
        jLabel1.setText("CONFIGURACIÓN DE LOS PRECIOS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, 20));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel2.setText("Lavado");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, -1, 20));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel3.setText("Auto");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, 30));

        jComboBox1.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chico", "Mediano", "Grande" }));
        jComboBox1.setBorder(null);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 120, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel4.setText("Precio actual");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel5.setText("Nuevo precio");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel6.setText("jLabel6");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, -1, 30));

        jTextField1.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 80, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel7.setText("Camioneta");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, 30));

        jComboBox2.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chico", "Mediano", "Grande", "4x4" }));
        jComboBox2.setBorder(null);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 120, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel8.setText("jLabel8");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, -1, 30));

        jTextField2.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField2.setBorder(null);
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 80, -1));

        jComboBox6.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solo lavado", "Lavado con cera", "Lavado con crema" }));
        jComboBox6.setBorder(null);
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, -1));

        jComboBox4.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solo lavado", "Lavado con cera", "Lavado con crema" }));
        jComboBox4.setBorder(null);
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel9.setText("Taxi");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 50, 30));

        jComboBox5.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solo lavado", "Lavado con cera", "Lavado con crema" }));
        jComboBox5.setBorder(null);
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel10.setText("jLabel10");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, -1, 30));

        jTextField3.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField3.setBorder(null);
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 150, 80, -1));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel11.setText("Motocicleta");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, 30));

        jComboBox3.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chico", "Mediano" }));
        jComboBox3.setBorder(null);
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 120, -1));

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel13.setText("jLabel13");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, -1, 30));

        jTextField4.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField4.setBorder(null);
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 190, 80, -1));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel12.setText("Extra por exceso de suciedad");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, 30));

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel14.setText("jLabel14");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, -1, 30));

        jTextField5.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField5.setBorder(null);
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 230, 80, -1));

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel15.setText("Lavado de interiores");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, -1, -1));

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel16.setText("PAQUETE LITE");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, -1, 30));

        jComboBox7.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chico", "Mediano", "Grande", "Familiar" }));
        jComboBox7.setBorder(null);
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });
        add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 290, 120, -1));

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel17.setText("jLabel17");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, -1, 30));

        jTextField6.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField6.setBorder(null);
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });
        add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 290, 80, -1));

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel18.setText("PAQUETE MEDIO");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, -1, 30));

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel19.setText("PAQUETE FULL");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, -1, 30));

        jComboBox8.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chico", "Mediano", "Grande", "Familiar" }));
        jComboBox8.setBorder(null);
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });
        add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 120, -1));

        jComboBox9.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chico", "Mediano", "Grande", "Familiar" }));
        jComboBox9.setBorder(null);
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });
        add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 120, -1));

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel20.setText("jLabel20");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 330, -1, 30));

        jLabel21.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel21.setText("jLabel21");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 370, -1, 30));

        jTextField7.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField7.setBorder(null);
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });
        add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 330, 80, -1));

        jTextField8.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField8.setBorder(null);
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });
        add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 370, 80, -1));

        jLabel22.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel22.setText("Lavado de asiento individual ");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, -1, 30));

        jLabel23.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel23.setText("jLabel23");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 410, -1, 30));

        jTextField9.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField9.setBorder(null);
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 410, 80, -1));

        jLabel24.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel24.setText("Encerado");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, -1, 20));

        jLabel25.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel25.setText("Pasta");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, -1, 30));

        jLabel26.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel26.setText("Pasta PREMIUM");
        add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, -1, 30));

        jComboBox10.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Auto (chico, mediano, grande)", "Camioneta (chico, mediano, grande)" }));
        jComboBox10.setBorder(null);
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });
        add(jComboBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 470, -1, -1));

        jComboBox11.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Auto (chico, mediano, grande)", "Camioneta (chico, mediano, grande)" }));
        jComboBox11.setBorder(null);
        jComboBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox11ActionPerformed(evt);
            }
        });
        add(jComboBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 510, -1, -1));

        jLabel27.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel27.setText("jLabel27");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 470, -1, 30));

        jTextField10.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField10.setBorder(null);
        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField10FocusLost(evt);
            }
        });
        add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 470, 80, -1));

        jLabel28.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel28.setText("jLabel28");
        add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 510, -1, 30));

        jTextField11.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jTextField11.setBorder(null);
        jTextField11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField11FocusLost(evt);
            }
        });
        add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 510, 80, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 660, 10));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, 660, 10));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 660, 10));

        jSeparator4.setToolTipText("");
        jSeparator4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 660, 10));
        add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 660, 10));
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 660, 10));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/precios (1).jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 540));

        btnLimpiar.setBackground(new java.awt.Color(237, 193, 193));

        txtLimpiar.setFont(new java.awt.Font("Roboto Medium", 1, 15)); // NOI18N
        txtLimpiar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLimpiar.setText("Limpiar");
        txtLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLimpiarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnLimpiarLayout = new javax.swing.GroupLayout(btnLimpiar);
        btnLimpiar.setLayout(btnLimpiarLayout);
        btnLimpiarLayout.setHorizontalGroup(
            btnLimpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        btnLimpiarLayout.setVerticalGroup(
            btnLimpiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 110, 90, 30));

        btnGuardar.setBackground(new java.awt.Color(237, 193, 193));

        txtGuardar.setFont(new java.awt.Font("Roboto Medium", 1, 15)); // NOI18N
        txtGuardar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtGuardar.setText("Guardar");
        txtGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGuardarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnGuardarLayout = new javax.swing.GroupLayout(btnGuardar);
        btnGuardar.setLayout(btnGuardarLayout);
        btnGuardarLayout.setHorizontalGroup(
            btnGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        btnGuardarLayout.setVerticalGroup(
            btnGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 170, -1, 30));
        add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 80, 10));
        add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 530, 80, 10));
        add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 80, 10));
        add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 80, 10));
        add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, 80, 10));
        add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 250, 80, 10));
        add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 310, 80, 10));
        add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, 80, 10));
        add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, 80, 10));
        add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 430, 80, 10));
        add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 490, 80, 10));

        jComboBox12.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solo lavado", "Lavado con cera", "Lavado con crema" }));
        jComboBox12.setBorder(null);
        jComboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox12ActionPerformed(evt);
            }
        });
        add(jComboBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 150, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLimpiarMouseClicked
        clean();
    }//GEN-LAST:event_txtLimpiarMouseClicked

    private void txtGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGuardarMouseClicked
        if (verify(jTextField1) && verify(jTextField2) && verify(jTextField3) && verify(jTextField4)
                && verify(jTextField5) && verify(jTextField6) && verify(jTextField7) && verify(jTextField8)
                && verify(jTextField9) && verify(jTextField10) && verify(jTextField11)) {
            edit(jLabel3, jComboBox1, jComboBox4, jTextField1);
            edit(jLabel7, jComboBox2, jComboBox5, jTextField2);
            edit(jLabel11, jComboBox3, jComboBox12, jTextField4);
            edit();
            listCost = cCost.findCostoservicioEntities();
            clean();

            assignPrice(jLabel3, jComboBox1, jComboBox4, jLabel6);
            assignPrice(jLabel7, jComboBox2, jComboBox5, jLabel8);
            assignPrice(jLabel11, jComboBox3, jComboBox12, jLabel13);
            assignPrice();
        }
    }//GEN-LAST:event_txtGuardarMouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        assignPrice(jLabel3, jComboBox1, jComboBox4, jLabel6);
        assignPrice(jLabel7, jComboBox2, jComboBox5, jLabel8);
        assignPrice(jLabel11, jComboBox3, jComboBox12, jLabel13);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        assignPrice(jLabel3, jComboBox1, jComboBox4, jLabel6);
        assignPrice(jLabel7, jComboBox2, jComboBox5, jLabel8);
        assignPrice(jLabel11, jComboBox3, jComboBox12, jLabel13);
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        assignPrice(jLabel3, jComboBox1, jComboBox4, jLabel6);
        assignPrice(jLabel7, jComboBox2, jComboBox5, jLabel8);
        assignPrice(jLabel11, jComboBox3, jComboBox12, jLabel13);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        assignPrice(jLabel3, jComboBox1, jComboBox4, jLabel6);
        assignPrice(jLabel7, jComboBox2, jComboBox5, jLabel8);
        assignPrice(jLabel11, jComboBox3, jComboBox12, jLabel13);
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        assignPrice(jLabel3, jComboBox1, jComboBox4, jLabel6);
        assignPrice(jLabel7, jComboBox2, jComboBox5, jLabel8);
        assignPrice(jLabel11, jComboBox3, jComboBox12, jLabel13);
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox12ActionPerformed
        assignPrice(jLabel3, jComboBox1, jComboBox4, jLabel6);
        assignPrice(jLabel7, jComboBox2, jComboBox5, jLabel8);
        assignPrice(jLabel11, jComboBox3, jComboBox12, jLabel13);
    }//GEN-LAST:event_jComboBox12ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        assignPrice();
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        assignPrice();
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        assignPrice();
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
        assignPrice();
    }//GEN-LAST:event_jComboBox9ActionPerformed

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        assignPrice();
    }//GEN-LAST:event_jComboBox10ActionPerformed

    private void jComboBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox11ActionPerformed
        assignPrice();
    }//GEN-LAST:event_jComboBox11ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if (!verify(jTextField1)) {
            jTextField1.requestFocus();
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        JTextField tf = (JTextField) evt.getComponent();
        if (!verify(tf)) {
            tf.requestFocus();
        }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        JTextField tf = (JTextField) evt.getComponent();
        if (!verify(tf)) {
            tf.requestFocus();
        }
    }//GEN-LAST:event_jTextField3FocusLost

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        JTextField tf = (JTextField) evt.getComponent();
        if (!verify(tf)) {
            tf.requestFocus();
        }
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        JTextField tf = (JTextField) evt.getComponent();
        if (!verify(tf)) {
            tf.requestFocus();
        }
    }//GEN-LAST:event_jTextField5FocusLost

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
        JTextField tf = (JTextField) evt.getComponent();
        if (!verify(tf)) {
            tf.requestFocus();
        }
    }//GEN-LAST:event_jTextField6FocusLost

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
        JTextField tf = (JTextField) evt.getComponent();
        if (!verify(tf)) {
            tf.requestFocus();
        }
    }//GEN-LAST:event_jTextField7FocusLost

    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusLost
        JTextField tf = (JTextField) evt.getComponent();
        if (!verify(tf)) {
            tf.requestFocus();
        }
    }//GEN-LAST:event_jTextField8FocusLost

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost
        JTextField tf = (JTextField) evt.getComponent();
        if (!verify(tf)) {
            tf.requestFocus();
        }
    }//GEN-LAST:event_jTextField9FocusLost

    private void jTextField10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusLost
        JTextField tf = (JTextField) evt.getComponent();
        if (!verify(tf)) {
            tf.requestFocus();
        }
    }//GEN-LAST:event_jTextField10FocusLost

    private void jTextField11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField11FocusLost
        JTextField tf = (JTextField) evt.getComponent();
        if (!verify(tf)) {
            tf.requestFocus();
        }
    }//GEN-LAST:event_jTextField11FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnGuardar;
    private javax.swing.JPanel btnLimpiar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel txtGuardar;
    private javax.swing.JLabel txtLimpiar;
    // End of variables declaration//GEN-END:variables
}
