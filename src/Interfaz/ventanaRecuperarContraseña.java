/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Controllers.AdministradorJpaController;
import EntityClasses.Administrador;
import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author juan6
 */
public class ventanaRecuperarContrase├▒a extends javax.swing.JDialog {

    private Administrador admin;
    private AdministradorJpaController cAdmin;
    private List<Administrador> listaAdmins;

    /**
     * Creates new form ventanaRecuperarContrase├▒a
     */
    public ventanaRecuperarContrase├▒a(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(371, 467);
        setVisible(true);
        setLocationRelativeTo(null);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LavaAutos_DERSPU");
        cAdmin = new AdministradorJpaController(emf);
        listaAdmins = cAdmin.findAdministradorEntities();

        //setFocusableWindowState(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cuerpo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JPanel();
        txtCancelar = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JPanel();
        txtAceptar = new javax.swing.JLabel();
        tUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreUsuario = new javax.swing.JTextField();
        nuevaContra = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        pregunta = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        cuerpo.setBackground(new java.awt.Color(255, 255, 255));
        cuerpo.setBorder(null);
        cuerpo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        cuerpo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 371, -1));

        btnCancelar.setBackground(new java.awt.Color(163, 163, 204));
        btnCancelar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtCancelar.setFont(new java.awt.Font("Roboto Medium", 1, 15)); // NOI18N
        txtCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCancelar.setText("Cancelar");
        txtCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtCancelarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnCancelarLayout = new javax.swing.GroupLayout(btnCancelar);
        btnCancelar.setLayout(btnCancelarLayout);
        btnCancelarLayout.setHorizontalGroup(
            btnCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        btnCancelarLayout.setVerticalGroup(
            btnCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        cuerpo.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, -1, -1));

        btnAceptar.setBackground(new java.awt.Color(163, 163, 204));
        btnAceptar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtAceptar.setFont(new java.awt.Font("Roboto Medium", 1, 15)); // NOI18N
        txtAceptar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAceptar.setText("Aceptar");
        txtAceptar.setBorder(null);
        txtAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAceptarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtAceptarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtAceptarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnAceptarLayout = new javax.swing.GroupLayout(btnAceptar);
        btnAceptar.setLayout(btnAceptarLayout);
        btnAceptarLayout.setHorizontalGroup(
            btnAceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAceptar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
        btnAceptarLayout.setVerticalGroup(
            btnAceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        cuerpo.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        tUsuario.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        tUsuario.setText("Usuario: ");
        cuerpo.add(tUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel1.setText("Nueva contrase├▒a: ");
        cuerpo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel2.setText("Pregunta de seguridad:");
        cuerpo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        nombreUsuario.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        nombreUsuario.setForeground(new java.awt.Color(153, 153, 153));
        nombreUsuario.setText("Ingrese el usuario");
        nombreUsuario.setBorder(null);
        nombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreUsuarioActionPerformed(evt);
            }
        });
        cuerpo.add(nombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 200, -1));

        nuevaContra.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        nuevaContra.setForeground(new java.awt.Color(153, 153, 153));
        nuevaContra.setText("********");
        nuevaContra.setBorder(null);
        nuevaContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaContraActionPerformed(evt);
            }
        });
        cuerpo.add(nuevaContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 202, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        jLabel3.setText("┬┐Cu├íl es el nombre de tu abuelo?");
        cuerpo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        pregunta.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        pregunta.setForeground(new java.awt.Color(153, 153, 153));
        pregunta.setText("Ingrese respuesta");
        pregunta.setBorder(null);
        cuerpo.add(pregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 202, -1));
        cuerpo.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 335, 220, 7));
        cuerpo.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 220, 10));
        cuerpo.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 245, 220, 7));

        jLabel4.setFont(new java.awt.Font("Roboto Condensed", 1, 22)); // NOI18N
        jLabel4.setText("Cambiar contrase├▒a");
        cuerpo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/contrasena.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        cuerpo.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 100, 50, 40));

        txtMsg.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuerpo.add(txtMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 310, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaContraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevaContraActionPerformed

    private void nombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreUsuarioActionPerformed

    private void txtCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCancelarMouseClicked
        this.setVisible(false);
        this.dispose();

    }//GEN-LAST:event_txtCancelarMouseClicked

    private void txtCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCancelarMouseEntered
        btnCancelar.setForeground(new Color(150, 167, 198));//[163,163,204]
    }//GEN-LAST:event_txtCancelarMouseEntered

    private void txtCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCancelarMouseExited
        btnCancelar.setForeground(new Color(163, 163, 204));
    }//GEN-LAST:event_txtCancelarMouseExited

    private void txtAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAceptarMouseClicked
        if (!(nombreUsuario.getText().equalsIgnoreCase("Ingrese el usuario")) && !(nombreUsuario.getText().equalsIgnoreCase(""))
                && !(String.valueOf(nuevaContra.getPassword()).equalsIgnoreCase("********")) && !(String.valueOf(nuevaContra.getPassword()).equalsIgnoreCase("")
                && !(pregunta.getText().equalsIgnoreCase("Ingrese respuesta") && !(pregunta.getText().equalsIgnoreCase(""))))) {
            for (int efe = 0; efe < listaAdmins.size(); efe++) {
                if (listaAdmins.get(efe).getUsuarioidUsuario().getNombre().equalsIgnoreCase(nombreUsuario.getText())
                        && listaAdmins.get(efe).getRespuesta().equalsIgnoreCase(pregunta.getText())) {
                    listaAdmins.get(efe).setContrasenia(String.valueOf(nuevaContra.getPassword()));
                    try {
                        cAdmin.edit(listaAdmins.get(efe));
                        txtMsg.setForeground(Color.BLACK);
                        txtMsg.setText("La contrase├▒a ha sido modificada");
                        repaint();
                    } catch (Exception ex) {
                        Logger.getLogger(ventanaRecuperarContrase├▒a.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                } else {
                    if (efe == (listaAdmins.size() - 1)) {
                        txtMsg.setForeground(Color.red);
                        txtMsg.setText("Nombre de usuario o respuesta incorrecto");
                        repaint();
                    }
                }
            }
        } else {
            txtMsg.setForeground(Color.red);
            txtMsg.setText("Ingrese todos los datos");
            repaint();
        }

    }//GEN-LAST:event_txtAceptarMouseClicked

    private void txtAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAceptarMouseEntered
        btnAceptar.setForeground(new Color(150, 167, 198));//[163,163,204]
    }//GEN-LAST:event_txtAceptarMouseEntered

    private void txtAceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAceptarMouseExited
        btnAceptar.setForeground(new Color(163, 163, 204));
    }//GEN-LAST:event_txtAceptarMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaRecuperarContrase├▒a.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaRecuperarContrase├▒a.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaRecuperarContrase├▒a.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaRecuperarContrase├▒a.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ventanaRecuperarContrase├▒a dialog = new ventanaRecuperarContrase├▒a(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAceptar;
    private javax.swing.JPanel btnCancelar;
    private javax.swing.JPanel cuerpo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField nombreUsuario;
    private javax.swing.JPasswordField nuevaContra;
    private javax.swing.JTextField pregunta;
    private javax.swing.JLabel tUsuario;
    private javax.swing.JLabel txtAceptar;
    private javax.swing.JLabel txtCancelar;
    private javax.swing.JLabel txtMsg;
    // End of variables declaration//GEN-END:variables
}
