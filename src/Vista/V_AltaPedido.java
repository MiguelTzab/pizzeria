/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Conexion.Conexiones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MiguelAtocha
 */
public class V_AltaPedido extends javax.swing.JInternalFrame implements Runnable{

    /**
     * Creates new form V_AltaUsuario
     */
    Conexiones cn;
    DefaultTableModel modTable;
    int id_cliente=0;
    int id_pedido=0;
    String hora,minutos,segundos,ampm;
    Calendar calendario;    
    Thread h1;
    public V_AltaPedido(Conexiones con) {
        cn=con;
        initComponents();
        cargaTabla();
        h1 = new Thread(this);
        h1.start();
        
    }
    public V_AltaPedido(Conexiones con, int id_pedido)throws SQLException {
        cn=con;
        cargaTabla();
        initComponents();
        h1 = new Thread(this);
        h1.start();
        this.id_pedido=id_pedido;
        cargarPedido(id_pedido);
    }
    public void cargarPedido(int id)throws SQLException{
        ResultSet rs;
        rs = cn.ejecutarSQLSelect("Select id_cliente from pedido WHERE id_Pedido =" + id);
        if(rs.next()) {
            id_cliente = rs.getInt("id_cliente");
        }
        rs = cn.ejecutarSQLSelect("Select Nombre,Direccion,Referencia, Numero from cliente WHERE id_Cliente =" + id_cliente);
        if(rs.next()) {
            txtNombre.setText(rs.getString("Nombre"));
            txtNumero.setText(rs.getString("Numero"));
            txtReferencia.setText(rs.getString("Referencia"));
            txtDireccion.setText(rs.getString("Direccion"));
        }
        ResultSet mr;
        mr = cn.ejecutarSQLSelect("Select id_producto, cantidad from pedido_producto WHERE id_Pedido =" + id);
        while (mr.next()) {
            rs = cn.ejecutarSQLSelect("Select id_producto,nombre,precio from producto WHERE id_Producto =" + mr.getString("id_producto"));
            Object[] filas = new Object[4];
            while (rs.next()) {
               for (int j = 1; j <= 3; j++) {
                    filas[j-1]=(rs.getString(j));
                }
                filas[3]= mr.getInt("cantidad");
                modTable.addRow(filas);
            }
        }
        ActualizarTotal(modTable);
        Table.setModel(modTable);
        cn.ejecutarSQL("DELETE FROM pedido_producto WHERE id_pedido ="+id);
        cn.cerrarConexion();
    }
    public void calcula () {        
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();


        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";

        if(ampm.equals("PM")){
         int h = calendario.get(Calendar.HOUR_OF_DAY)-12;
         hora = h>9?""+h:"0"+h;
        }else{
         hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);            
        }
        minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND); 
    }
    public void cargaTabla(){
        Object col[] = new Object[]{"id_Producto","Nombre","Precio","Cantidad"};
        modTable = new DefaultTableModel(null, col);
    }
    public void AgregarTabla(int id_Producto, int cantidad, DefaultTableModel mod)throws SQLException{
        ResultSet rs;
        rs = cn.ejecutarSQLSelect("Select id_producto,nombre,precio from producto WHERE id_Producto =" + id_Producto);
        Object[] filas = new Object[4];
        while (rs.next()) {
            for (int j = 1; j <= 3; j++) {
                filas[j-1]=(rs.getString(j));
            }
            filas[3]= cantidad;
            mod.addRow(filas);
        }
        this.Table.setModel(mod);
        ActualizarTotal(mod);
        cn.cerrarConexion();
            
    }
    public void AgregarTabla(int id_Producto, int cantidad, DefaultTableModel mod, int row)throws SQLException{
        ResultSet rs;
        rs = cn.ejecutarSQLSelect("Select id_producto,nombre,precio from producto WHERE id_Producto =" + id_Producto);
        Object[] filas = new Object[4];
        while (rs.next()) {
           for (int j = 1; j <= 3; j++) {
                filas[j-1]=(rs.getString(j));
                mod.setValueAt(rs.getString(j), row, j-1);
            }
            mod.setValueAt(cantidad, row, 3);
        }
        this.Table.setModel(mod);
        ActualizarTotal(mod);
        cn.cerrarConexion();
    }
    public void AgregarTabla(DefaultTableModel d, int row){
        d.removeRow(row);
        this.Table.setModel(d);
    }
    public void ActualizarTotal(DefaultTableModel se){
        int s=se.getRowCount(), t=0;
        for(int f=0;f<s;f++){
            //System.out.println((se.getValueAt(f, 2).toString()));
            t+=(Integer.parseInt((se.getValueAt(f, 2).toString())))*(Integer.parseInt((se.getValueAt(f, 3).toString())));
        }
        lblTotal.setText(Integer.toString(t));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelNorte = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        PanelCentro = new javax.swing.JPanel();
        Datos = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 32767));
        txtNumero = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(800, 0), new java.awt.Dimension(800, 0), new java.awt.Dimension(800, 32767));
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(25, 32767));
        txtNombre = new javax.swing.JTextField();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(16, 0), new java.awt.Dimension(16, 0), new java.awt.Dimension(16, 32767));
        txtDireccion = new javax.swing.JTextField();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(11, 0), new java.awt.Dimension(11, 0), new java.awt.Dimension(11, 32767));
        txtReferencia = new javax.swing.JTextField();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(4, 0), new java.awt.Dimension(4, 0), new java.awt.Dimension(4, 32767));
        txtProducto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        Tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        PanelSur = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jla = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Alta Pedido");

        PanelNorte.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ufgkjh.jpg"))); // NOI18N
        jPanel4.add(jLabel6);

        PanelNorte.add(jPanel4);

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

        lblUser.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblUser.setText("Usuario:");
        jPanel5.add(lblUser);

        lblFecha.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblFecha.setText("Fecha:");
        jPanel5.add(lblFecha);

        PanelNorte.add(jPanel5);

        getContentPane().add(PanelNorte, java.awt.BorderLayout.PAGE_START);

        PanelCentro.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PanelCentro.setLayout(new javax.swing.BoxLayout(PanelCentro, javax.swing.BoxLayout.Y_AXIS));

        Datos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Cliente"));
        Datos.setLayout(new javax.swing.BoxLayout(Datos, javax.swing.BoxLayout.Y_AXIS));

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("Numero:");
        jPanel3.add(jLabel1);
        jPanel3.add(filler4);

        txtNumero.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });
        jPanel3.add(txtNumero);
        jPanel3.add(filler1);

        Datos.add(jPanel3);

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.X_AXIS));

        jLabel2.setText("Nombre:");
        jPanel6.add(jLabel2);
        jPanel6.add(filler5);

        txtNombre.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        jPanel6.add(txtNombre);
        jPanel6.add(filler2);

        Datos.add(jPanel6);

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setText("Direccion:");
        jPanel7.add(jLabel3);
        jPanel7.add(filler6);

        txtDireccion.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        jPanel7.add(txtDireccion);
        jPanel7.add(filler3);

        Datos.add(jPanel7);

        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setText("Referencia:");
        jPanel8.add(jLabel4);
        jPanel8.add(filler8);

        txtReferencia.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        jPanel8.add(txtReferencia);
        jPanel8.add(filler7);

        Datos.add(jPanel8);

        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        jLabel5.setText("Producto(s):");
        jPanel9.add(jLabel5);
        jPanel9.add(filler10);

        txtProducto.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        jPanel9.add(txtProducto);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1);
        jPanel9.add(filler9);

        Datos.add(jPanel9);

        PanelCentro.add(Datos);

        Tabla.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(454, 250));

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_producto", "Nombre", "Precio", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Table);

        Tabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        PanelCentro.add(Tabla);

        getContentPane().add(PanelCentro, java.awt.BorderLayout.CENTER);

        PanelSur.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        PanelSur.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jla.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jla.setText("Total:");
        jPanel2.add(jla);

        lblTotal.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 153, 51));
        lblTotal.setText("$");
        jPanel2.add(lblTotal);

        jPanel1.add(jPanel2);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(120, 55));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);

        PanelSur.add(jPanel1, java.awt.BorderLayout.EAST);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pencil_medium.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(130, 55));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel10.add(btnEditar);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/remove.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(130, 55));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel10.add(btnEliminar);

        PanelSur.add(jPanel10, java.awt.BorderLayout.CENTER);

        getContentPane().add(PanelSur, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {                                         
            ResultSet rs;
            rs = cn.ejecutarSQLSelect("Select nombre, id_producto from producto");
            //ResultSetMetaData metadata = rs.getMetaData();
            ArrayList<String> list = new ArrayList<String>();
            ArrayList<String> id = new ArrayList<String>();
            while (rs.next()) {
                list.add(rs.getString(1));
                id.add(rs.getString(2));
            }
            try{
                Object res = JOptionPane.showInputDialog(null, "Selecciona uno", "Buscar Producto", JOptionPane.QUESTION_MESSAGE,
                        null, list.toArray(), "Pizzeria");
                String r = JOptionPane.showInputDialog(null, "Cantidad");
                AgregarTabla(Integer.parseInt(id.get(list.indexOf(res))),Integer.parseInt(r), modTable);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error Ningun Producto Seleccionado\n"+ex, "Error", JOptionPane.ERROR_MESSAGE);
        
            }
            cn.cerrarConexion();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Obtener los productos\n"+ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int s = this.Table.getSelectedRow();
        if(s!=(-1)){
            try {                                         
                ResultSet rs;
                rs = cn.ejecutarSQLSelect("Select nombre, id_producto from producto");
                ResultSetMetaData metadata = rs.getMetaData();
                ArrayList<String> list = new ArrayList<String>();
                ArrayList<String> id = new ArrayList<String>();
                while (rs.next()) {
                    list.add(rs.getString(1));
                    id.add(rs.getString(2));
                }

                Object res = JOptionPane.showInputDialog(null, "Selecciona uno", "Buscar Producto", JOptionPane.QUESTION_MESSAGE,
                        null, list.toArray(), "Pizzeria");
                String r = JOptionPane.showInputDialog(null, "Cantidad");
                AgregarTabla(Integer.parseInt(id.get(list.indexOf(res))),Integer.parseInt(r), modTable, s);
                cn.cerrarConexion();
                //System.out.println(id.get(list.indexOf(res)));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al Obtener los productos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Porfavor seleccione un producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int s = this.Table.getSelectedRow();
        if(s!=(-1)){
            AgregarTabla(modTable,s);
        }else{
            JOptionPane.showMessageDialog(null, "Porfavor seleccione un producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ActualizarTotal(modTable);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
        if(txtNumero.getText().length()>7){
            try {
                ResultSet rs;
                rs = cn.ejecutarSQLSelect("Select * from cliente WHERE numero='"+txtNumero.getText()+"'");
                if(rs.next()){
                    txtNombre.setText(rs.getString(2));
                    txtDireccion.setText(rs.getString(3));
                    txtReferencia.setText(rs.getString(4));
                    id_cliente=rs.getInt(1);
                }else{
                    id_cliente = 0;
                }
                cn.cerrarConexion();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al obtener datos de la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtNumeroKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ResultSet rs;
        PreparedStatement consulta;
        if(id_pedido==0){
            try {
                if(id_cliente==0){
                    String nombre = txtNombre.getText(), direccion=txtDireccion.getText(),
                            referencia = txtReferencia.getText();
                    String numero=txtNumero.getText();
                    
                    try {
                        consulta = cn.getConexion().prepareStatement("INSERT INTO cliente" + "(Nombre, Direccion, Referencia, Numero) VALUES(?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
                        consulta.setString(1, nombre);
                        consulta.setString(2, direccion);
                        consulta.setString(3, referencia);
                        consulta.setString(4, numero);
                        consulta.executeUpdate();
                        rs =consulta.getGeneratedKeys();
                        //rs = cn.ejecutarSQLSelect("Select id_Cliente from cliente WHERE (numero='"+numero+"' AND nombre='"+nombre+"'");
                        if(rs.next()){
                            id_cliente=rs.getInt(1);
                        }else{
                            JOptionPane.showMessageDialog(null, "Error al guardar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error:"+ex, "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }   
                consulta = cn.getConexion().prepareStatement("INSERT INTO pedido" + "(id_usuario, id_cliente, fecha, total) VALUES(?, ?, ?, ?)");
                consulta.setInt(1, 1);
                consulta.setInt(2, id_cliente);
                java.util.Date utilDate = new java.util.Date(); //fecha actual
                long lnMilisegundos = utilDate.getTime();
                java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
                String fec = sqlDate.toString();
                consulta.setString(3, fec);
                consulta.setInt(4, Integer.parseInt(lblTotal.getText()));
                consulta.executeUpdate();
                id_pedido=0;
                rs = cn.ejecutarSQLSelect("SELECT id_pedido FROM pedido WHERE (id_cliente="+id_cliente+" AND fecha='"+fec+"')");
                if(rs.next()){
                    id_pedido=rs.getInt(1);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al guardar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                //Implementacion faltante
                int columnas=4;
                int filas = modTable.getRowCount();
                int id_producto=0, cantidad=0;
                for(int q=0;q<filas;q++){
                    id_producto = Integer.parseInt(modTable.getValueAt(q, 0).toString());
                    cantidad = Integer.parseInt(modTable.getValueAt(q, 3).toString());
                    consulta = cn.getConexion().prepareStatement("INSERT INTO pedido_producto" + "(id_producto, id_pedido, cantidad) VALUES(?, ?, ?)");
                    consulta.setInt(1, id_producto);
                    consulta.setInt(2, id_pedido);
                    consulta.setInt(3, cantidad);
                    consulta.executeUpdate();
                }
                //cn.cerrarConexion();
                JOptionPane.showMessageDialog(null, "Guardado con Exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar el pedido\n"+ex, "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            try {
                consulta = cn.getConexion().prepareStatement("UPDATE pedido SET id_usuario=?, id_cliente=?, total=? WHERE id_pedido=?");
                consulta.setInt(1, 1);
                consulta.setInt(2, id_cliente);
                consulta.setInt(3, Integer.parseInt(lblTotal.getText()));
                consulta.setInt(4, id_pedido);
                consulta.executeUpdate();
                //Implementacion faltante
                int columnas=4;
                int filas = modTable.getRowCount();
                int id_producto=0, cantidad=0;
                for(int q=0;q<filas;q++){
                    id_producto = Integer.parseInt(modTable.getValueAt(q, 0).toString());
                    cantidad = Integer.parseInt(modTable.getValueAt(q, 3).toString());
                    consulta = cn.getConexion().prepareStatement("INSERT INTO pedido_producto" + "(id_producto, id_pedido, cantidad) VALUES(?, ?, ?)");
                    consulta.setInt(1, id_producto);
                    consulta.setInt(2, id_pedido);
                    consulta.setInt(3, cantidad);
                    consulta.executeUpdate();
                }
                //cn.cerrarConexion();
                JOptionPane.showMessageDialog(null, "Guardado con Exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);   
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar el pedido\n"+ex, "Mensaje", JOptionPane.ERROR_MESSAGE);
            
            }
        }
        cn.cerrarConexion();
        this.txtDireccion.setText("");      
        this.txtNombre.setText("");
        this.txtNumero.setText(""); 
        this.txtReferencia.setText(""); 
        this.txtProducto.setText("");
        int f=modTable.getRowCount();
        for(int y=0;y<f;y++){
            modTable.removeRow(y);
        }
        this.lblTotal.setText("$");
        
        
  
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyTyped
        if(txtNumero.getText().length()==10){
            evt.consume();
        }
    }//GEN-LAST:event_txtNumeroKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Datos;
    private javax.swing.JPanel PanelCentro;
    private javax.swing.JPanel PanelNorte;
    private javax.swing.JPanel PanelSur;
    private javax.swing.JPanel Tabla;
    private javax.swing.JTable Table;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jla;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtReferencia;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while(ct == h1) {   
            calcula();
            this.lblFecha.setText(hora + ":" + minutos + ":" + segundos + " "+ampm);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {}
        }
    }
}
