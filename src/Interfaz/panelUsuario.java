/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Controllers.LavadorJpaController;
import Controllers.UsuarioJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import EntityClasses.Lavador;
import EntityClasses.Usuario;
import modelos.ModeloLavadores;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rob99
 */
public class panelUsuario extends javax.swing.JPanel {

    private Usuario usuario;
    private UsuarioJpaController cUsuario;
    private List<Usuario> usuarios;
    private Lavador lavador;
    private LavadorJpaController cLavador;
    private List<Lavador> lavadores;
    private ModeloLavadores modelLav;

    /**
     * Creates new form panelUsuario
     */
    public panelUsuario() {
        initComponents();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LavaAutos_DERSPU");
        cLavador = new LavadorJpaController(emf);
        lavadores = cLavador.findLavadorEntities();
        modelLav = new ModeloLavadores(lavadores);
        tLavadores.setModel(modelLav);
        tLavadores.repaint();
        cUsuario = new UsuarioJpaController(emf);
        usuarios = cUsuario.findUsuarioEntities();

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tLavadores = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nombreUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comision = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnAgregar = new javax.swing.JPanel();
        txtAgregar = new javax.swing.JLabel();
        btnGuardarCambios = new javax.swing.JPanel();
        txtGuardarCambios = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnEliminarUser = new javax.swing.JPanel();
        txtEliminarUser = new javax.swing.JLabel();
        txtMensaje = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 255), 1, true));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 1, 22)); // NOI18N
        jLabel1.setText("Usuarios");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(607, 23, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel2.setText("Usuarios en existencia");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, -1));

        tLavadores.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 255), 1, true));
        tLavadores.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tLavadores.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tLavadores);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 380, 337));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel3.setText("Nombre: ");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, -1, 30));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel4.setText("Agregar un usuario nuevo");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, -1, -1));

        nombreUsuario.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        nombreUsuario.setForeground(new java.awt.Color(153, 153, 153));
        nombreUsuario.setText("Ingrese nombre");
        nombreUsuario.setBorder(null);
        nombreUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nombreUsuarioMousePressed(evt);
            }
        });
        add(nombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 230, 204, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel5.setText("Comisi??n: ");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, 80, 30));

        comision.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        comision.setForeground(new java.awt.Color(153, 153, 153));
        comision.setText("00");
        comision.setBorder(null);
        comision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comisionMousePressed(evt);
            }
        });
        comision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comisionActionPerformed(evt);
            }
        });
        add(comision, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 260, 30, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel6.setText("%");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 260, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuariosNM.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 540));
        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 250, 210, 10));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, 40, 10));

        btnAgregar.setBackground(new java.awt.Color(153, 153, 255));
        btnAgregar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtAgregar.setFont(new java.awt.Font("Roboto Medium", 0, 15)); // NOI18N
        txtAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAgregar.setText("Agregar");
        txtAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAgregarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtAgregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtAgregarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnAgregarLayout = new javax.swing.GroupLayout(btnAgregar);
        btnAgregar.setLayout(btnAgregarLayout);
        btnAgregarLayout.setHorizontalGroup(
            btnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
        );
        btnAgregarLayout.setVerticalGroup(
            btnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 330, 120, 40));

        btnGuardarCambios.setBackground(new java.awt.Color(153, 153, 255));
        btnGuardarCambios.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnGuardarCambios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtGuardarCambios.setFont(new java.awt.Font("Roboto Medium", 0, 15)); // NOI18N
        txtGuardarCambios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtGuardarCambios.setText("Guardar cambios");
        txtGuardarCambios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtGuardarCambios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGuardarCambiosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtGuardarCambiosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtGuardarCambiosMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnGuardarCambiosLayout = new javax.swing.GroupLayout(btnGuardarCambios);
        btnGuardarCambios.setLayout(btnGuardarCambiosLayout);
        btnGuardarCambiosLayout.setHorizontalGroup(
            btnGuardarCambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtGuardarCambios, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        btnGuardarCambiosLayout.setVerticalGroup(
            btnGuardarCambiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtGuardarCambios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        add(btnGuardarCambios, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 490, 140, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(null);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/hombre (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, 80, 80));

        btnEliminarUser.setBackground(new java.awt.Color(153, 153, 255));
        btnEliminarUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnEliminarUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txtEliminarUser.setFont(new java.awt.Font("Roboto Medium", 0, 15)); // NOI18N
        txtEliminarUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtEliminarUser.setText("Eliminar usuario");
        txtEliminarUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtEliminarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEliminarUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtEliminarUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtEliminarUserMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnEliminarUserLayout = new javax.swing.GroupLayout(btnEliminarUser);
        btnEliminarUser.setLayout(btnEliminarUserLayout);
        btnEliminarUserLayout.setHorizontalGroup(
            btnEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtEliminarUser, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        btnEliminarUserLayout.setVerticalGroup(
            btnEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtEliminarUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        add(btnEliminarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, 140, 40));

        txtMensaje.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txtMensaje.setForeground(new java.awt.Color(255, 0, 0));
        txtMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(txtMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 400, 330, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void comisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comisionActionPerformed

    private void txtAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAgregarMouseClicked
        boolean encuentra = false;
        try {
            double comi = Double.parseDouble(comision.getText());
            if (!(nombreUsuario.getText().equalsIgnoreCase("Ingrese nombre")) && !(nombreUsuario.getText().equalsIgnoreCase(""))) {
                for (int n = 0; n < usuarios.size(); n++) {
                    if (nombreUsuario.getText().equalsIgnoreCase(usuarios.get(n).getNombre())) {
                        encuentra = true;
                    }
                }
                if (encuentra == false) {
                    if (comi > 0.0 && comi < 100.0) {
                        lavador = new Lavador();
                        usuario = new Usuario();
                        usuario.setNombre(nombreUsuario.getText());
                        lavador.setUsuarioidUsuario(usuario);
                        lavador.setComision(comi);
                        usuarios.add(usuario);
                        lavadores.add(lavador);
                        modelLav.fireTableDataChanged();
                        tLavadores.repaint();
                        txtMensaje.setForeground(Color.BLACK);
                        txtMensaje.setText("El usuario se ha creado correctamente");
                        repaint();
                        cUsuario.create(usuario);
                        cLavador.create(lavador);

                        nombreUsuario.setText("Ingrese nombre");
                        comision.setText("00");
                        nombreUsuario.setForeground(Color.GRAY);
                        comision.setForeground(Color.GRAY);

                    } else {
                        txtMensaje.setForeground(Color.RED);
                        txtMensaje.setText("Ingrese un porcentaje valido");

                        repaint();
                    }
                } else {
                    txtMensaje.setForeground(Color.RED);
                    txtMensaje.setText("Usuario ya existente");
                    repaint();
                }
            } else {
                txtMensaje.setForeground(Color.RED);
                txtMensaje.setText("Ingrese un nombre");
                repaint();
            }
        } catch (NumberFormatException e) {

        }

    }//GEN-LAST:event_txtAgregarMouseClicked

    private void txtGuardarCambiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGuardarCambiosMouseClicked
        for (int a = 0; a < usuarios.size(); a++) {
            try {
                cUsuario.edit(usuarios.get(a));
            } catch (Exception ex) {

            }
            modelLav.fireTableDataChanged();
            tLavadores.repaint();
        }

    }//GEN-LAST:event_txtGuardarCambiosMouseClicked

    private void txtAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAgregarMouseEntered
        btnAgregar.setBackground(new Color(138, 155, 242));
    }//GEN-LAST:event_txtAgregarMouseEntered

    private void txtAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAgregarMouseExited
        btnAgregar.setBackground(new Color(153, 153, 255));
    }//GEN-LAST:event_txtAgregarMouseExited

    private void txtGuardarCambiosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGuardarCambiosMouseExited
        btnGuardarCambios.setBackground(new Color(153, 153, 255));
    }//GEN-LAST:event_txtGuardarCambiosMouseExited

    private void txtGuardarCambiosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGuardarCambiosMouseEntered
        btnGuardarCambios.setBackground(new Color(138, 155, 242));
    }//GEN-LAST:event_txtGuardarCambiosMouseEntered

    private void txtEliminarUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEliminarUserMouseClicked
        try {
            int id = lavadores.get(tLavadores.getSelectedRow()).getIdLavador();
            int idUsuario = lavadores.get(tLavadores.getSelectedRow()).getUsuarioidUsuario().getIdUsuario();
            usuario = lavadores.get(tLavadores.getSelectedRow()).getUsuarioidUsuario();
            cLavador.destroy(id);
            cUsuario.destroy(idUsuario);
            lavadores.remove(tLavadores.getSelectedRow());
            usuarios.remove(usuario);
            modelLav.fireTableDataChanged();
            tLavadores.repaint();
            txtMensaje.setForeground(Color.BLACK);
            txtMensaje.setText("El usuario se ha eliminado correctamente");
            repaint();

        } catch (Exception ex) {

        }

    }//GEN-LAST:event_txtEliminarUserMouseClicked

    private void txtEliminarUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEliminarUserMouseEntered
        btnEliminarUser.setBackground(new Color(138, 155, 242));
    }//GEN-LAST:event_txtEliminarUserMouseEntered

    private void txtEliminarUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEliminarUserMouseExited
        btnEliminarUser.setBackground(new Color(153, 153, 255));
    }//GEN-LAST:event_txtEliminarUserMouseExited

    private void nombreUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombreUsuarioMousePressed
        if (nombreUsuario.getText().equalsIgnoreCase("Ingrese nombre")) {
            nombreUsuario.setText("");
            nombreUsuario.setForeground(Color.black);
        }
        if (comision.getText().equalsIgnoreCase("00") || comision.getText().equalsIgnoreCase("")) {
            comision.setText("00");
            comision.setForeground(Color.gray);
        }
    }//GEN-LAST:event_nombreUsuarioMousePressed

    private void comisionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comisionMousePressed
        if (comision.getText().equals("00")) {
            comision.setText("");
            comision.setForeground(Color.black);
        }
        if (nombreUsuario.getText().equalsIgnoreCase("Ingrese nombre") || nombreUsuario.getText().equalsIgnoreCase("")) {
            nombreUsuario.setText("Ingrese nombre");
            nombreUsuario.setForeground(Color.gray);
        }
    }//GEN-LAST:event_comisionMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAgregar;
    private javax.swing.JPanel btnEliminarUser;
    private javax.swing.JPanel btnGuardarCambios;
    private javax.swing.JTextField comision;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nombreUsuario;
    private javax.swing.JTable tLavadores;
    private javax.swing.JLabel txtAgregar;
    private javax.swing.JLabel txtEliminarUser;
    private javax.swing.JLabel txtGuardarCambios;
    private javax.swing.JLabel txtMensaje;
    // End of variables declaration//GEN-END:variables
}
