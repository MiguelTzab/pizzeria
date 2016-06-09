/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Conexion.Conexiones;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author MiguelAtocha
 */
public class V_Login extends javax.swing.JInternalFrame {

    /**
     * Creates new form V_Login
     */
    ArrayList<Integer> id = new ArrayList<Integer>();
    Conexiones cn;
    PreparedStatement consulta;
    public V_Login(Conexiones con) {
        cn=con;
        initComponents();
        llenar();
    }
    public void llenar(){
        cmbUser.removeAllItems();
        
        try {                                         
            ResultSet rs;
            rs = cn.ejecutarSQLSelect("Select nombre, id_usuario from usuario");
            
            while (rs.next()) {
                cmbUser.addItem(rs.getObject("nombre"));
                id.add(rs.getInt("id_usuario"));
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Obtener los Usuarios\n"+ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private boolean isPasswordCorrect(char[] j1,char[] j2) { 
        boolean valor = true; 
        int puntero = 0; 
        if (j1.length != j2.length){ 
        valor = false; 
        } 
        else{ 
        while((valor)&&(puntero < j1.length)){ 
        if (j1[puntero] != j2[puntero]){ 
        valor = false; 
        } 
        puntero++; 
        } 
        } 
        return valor; 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbUser = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtContra = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setText("Usuario:");
        jPanel2.add(jLabel1);

        cmbUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cmbUser);

        jPanel1.add(jPanel2);

        jLabel2.setText("Contraseña:");
        jPanel3.add(jLabel2);

        txtContra.setPreferredSize(new java.awt.Dimension(100, 28));
        txtContra.setSize(new java.awt.Dimension(100, 0));
        jPanel3.add(txtContra);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        char[] contra = txtContra.getPassword();
        int s =id.get(cmbUser.getSelectedIndex());
        try {
            ResultSet r;
            r = cn.ejecutarSQLSelect("SELECT Contraseña FROM usuario WHERE id_usuario="+s);
            if(r.next()){
                if((String.valueOf(contra)).equals(r.getString("Contraseña"))){
                    
                    JOptionPane.showMessageDialog(null, "Acceso Correcto", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    Method[] c =Menu.class.getMethods();
                    for (Method c1 : c) {
                        if(c1.getName()=="login"){
                            System.out.println("es login");
                            
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Porfavor, verifique sus datos", "Acceso Incorrecto", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Porfavor, verifique sus datos", "Acceso Incorrecto", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:"+ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField txtContra;
    // End of variables declaration//GEN-END:variables
}
