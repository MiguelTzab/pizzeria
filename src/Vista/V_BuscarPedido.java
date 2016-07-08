package Vista;

import Conexion.Conexiones;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class V_BuscarPedido extends javax.swing.JInternalFrame implements Runnable {

    Conexiones cn;
    boolean editar = false;
    int id_pedido = 0;
    String hora,minutos,segundos,ampm;
    Calendar calendario;    
    Thread h1;
    public V_BuscarPedido(Conexiones con) throws SQLException {
        initComponents();
        cn = con;
        CargarTabla();
        h1 = new Thread(this);
        h1.start();
    }

    public void CargarTabla() throws SQLException{
        ResultSet rs;
        String sen = "SELECT pedido.id_pedido, pedido.fecha, pedido.total, cliente.Nombre FROM pedido, cliente WHERE pedido.id_cliente=cliente.id_cliente";
        rs = cn.ejecutarSQLSelect(sen);
        ResultSetMetaData metadata = rs.getMetaData();
        
        //Agregamos los datos de la cabecera de la tabla
        
        Object[] col = new Object[metadata.getColumnCount()];
        int columnas = metadata.getColumnCount();
        for (int i = 1; i <= columnas; i++) {
            col[i-1]=metadata.getColumnName(i);
        }
        
        DefaultTableModel modTable = new DefaultTableModel(null,col);
        Object[] filas = new Object[columnas];
        while (rs.next()) {
            //agregamos una de las filas de la tabla
            for (int j = 1; j <= columnas; j++) {
                filas[j-1]=(rs.getString(j));
            }
            modTable.addRow(filas);
        }
        this.Table.setModel(modTable);
        rs.close();
        cn.cerrarConexion();
    }
    public void CargarTabla(String nombre, String numero) throws SQLException{
        ResultSet rs;
        String sen = "SELECT pedido.id_pedido,pedido.fecha, pedido.total, cliente.Nombre FROM pedido, cliente "
                + "WHERE (pedido.id_cliente=cliente.id_cliente) AND (cliente.Nombre='"+nombre+"' AND cliente.Numero='"+numero+"')";
        rs = cn.ejecutarSQLSelect(sen);
        ResultSetMetaData metadata = rs.getMetaData();
        
        //Agregamos los datos de la cabecera de la tabla
        
        Object[] col = new Object[metadata.getColumnCount()];
        int columnas = metadata.getColumnCount();
        for (int i = 1; i <= columnas; i++) {
            col[i-1]=metadata.getColumnName(i);
        }
        
        DefaultTableModel modTable = new DefaultTableModel(null,col);
        Object[] filas = new Object[columnas];
        while (rs.next()) {
            //agregamos una de las filas de la tabla
            for (int j = 1; j <= columnas; j++) {
                filas[j-1]=(rs.getString(j));
            }
            modTable.addRow(filas);
        }
        this.Table.setModel(modTable);
        rs.close();
        cn.cerrarConexion();
    }
    public void CargarTabla(String nombre) throws SQLException{
        ResultSet rs;
        String sen = "SELECT pedido.id_pedido,pedido.fecha, pedido.total, cliente.Nombre FROM pedido, cliente "
                + "WHERE (pedido.id_cliente=cliente.id_cliente) AND (cliente.Nombre='"+nombre+"')";
        rs = cn.ejecutarSQLSelect(sen);
        ResultSetMetaData metadata = rs.getMetaData();
        
        //Agregamos los datos de la cabecera de la tabla
        
        Object[] col = new Object[metadata.getColumnCount()];
        int columnas = metadata.getColumnCount();
        for (int i = 1; i <= columnas; i++) {
            col[i-1]=metadata.getColumnName(i);
        }
        
        DefaultTableModel modTable = new DefaultTableModel(null,col);
        Object[] filas = new Object[columnas];
        while (rs.next()) {
            //agregamos una de las filas de la tabla
            for (int j = 1; j <= columnas; j++) {
                filas[j-1]=(rs.getString(j));
            }
            modTable.addRow(filas);
        }
        this.Table.setModel(modTable);
        rs.close();
        cn.cerrarConexion();
    }
    public void CargarTabla(int numero , String num) throws SQLException{
        ResultSet rs;
        String sen = "SELECT pedido.id_pedido,pedido.fecha, pedido.total, cliente.Nombre FROM pedido, cliente "
                + "WHERE (pedido.id_cliente=cliente.id_cliente) AND (cliente.Numero='"+num+"')";
        rs = cn.ejecutarSQLSelect(sen);
        ResultSetMetaData metadata = rs.getMetaData();
        
        //Agregamos los datos de la cabecera de la tabla
        
        Object[] col = new Object[metadata.getColumnCount()];
        int columnas = metadata.getColumnCount();
        for (int i = 1; i <= columnas; i++) {
            col[i-1]=metadata.getColumnName(i);
        }
        
        DefaultTableModel modTable = new DefaultTableModel(null,col);
        Object[] filas = new Object[columnas];
        while (rs.next()) {
            //agregamos una de las filas de la tabla
            for (int j = 1; j <= columnas; j++) {
                filas[j-1]=(rs.getString(j));
            }
            modTable.addRow(filas);
        }
        this.Table.setModel(modTable);
        rs.close();
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
    public void Limpiar(){
        txtNombre.setText("");
        txtNumero.setText("");
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelNorte = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        PanelCentro = new javax.swing.JPanel();
        Datos = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(26, 0), new java.awt.Dimension(26, 0), new java.awt.Dimension(26, 32767));
        txtNombre = new javax.swing.JTextField();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(26, 0), new java.awt.Dimension(26, 0), new java.awt.Dimension(26, 32767));
        txtNumero = new javax.swing.JTextField();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jPanel9 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        PanelSur = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Pedidos");
        setPreferredSize(new java.awt.Dimension(130, 55));

        PanelNorte.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ufgkjh.jpg"))); // NOI18N
        jPanel4.add(jLabel1);

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

        Datos.setBorder(javax.swing.BorderFactory.createTitledBorder("Criterios de Busqueda"));
        Datos.setLayout(new javax.swing.BoxLayout(Datos, javax.swing.BoxLayout.Y_AXIS));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.X_AXIS));

        jLabel2.setText("Nombre:");
        jPanel6.add(jLabel2);
        jPanel6.add(filler5);

        txtNombre.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        jPanel6.add(txtNombre);
        jPanel6.add(filler2);

        Datos.add(jPanel6);

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setText("Numero:");
        jPanel7.add(jLabel3);
        jPanel7.add(filler6);

        txtNumero.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroKeyTyped(evt);
            }
        });
        jPanel7.add(txtNumero);
        jPanel7.add(filler3);

        Datos.add(jPanel7);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton1, java.awt.BorderLayout.EAST);

        Datos.add(jPanel9);

        PanelCentro.add(Datos);

        Tabla.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(454, 250));

        Table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Table);

        Tabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        PanelCentro.add(Tabla);

        getContentPane().add(PanelCentro, java.awt.BorderLayout.CENTER);

        PanelSur.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pencil_medium.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(130, 55));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        PanelSur.add(btnEditar);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/remove.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(130, 55));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        PanelSur.add(btnEliminar);

        getContentPane().add(PanelSur, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int s = Table.getSelectedRow();
        if(s!=(-1)){
            try {
                JDesktopPane d = this.getDesktopPane();
                V_AltaPedido p = new V_AltaPedido(cn, Integer.parseInt(Table.getValueAt(Table.getSelectedRow(), 0).toString()));
                p.setSize(d.getSize());
                if(d.getComponentCount()!=0){
                    d.removeAll();
                }
                p.setVisible(true);
                d.add(p);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error al Editar", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Ningun Elemento Seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        //JOptionPane.showMessageDialog(null, "Por el momento no se puede editar un pedido.\nPorfavor, espere nuestra proxima actualizacion", "Error al Editar", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int s = this.Table.getSelectedRow();
        if(s!=(-1)){
            id_pedido = Integer.parseInt(this.Table.getValueAt(s,0).toString());
            this.Table.setEnabled(false);
            this.btnEditar.setEnabled(false);
            this.btnEliminar.setEnabled(false);
            int dialogResult = JOptionPane.showConfirmDialog (null, "Desea eliminar el registro seleccionado?","Advertencia",JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                if(cn.ejecutarSQL("DELETE FROM pedido WHERE id_pedido =" +id_pedido )){
                    JOptionPane.showMessageDialog(null, "Eliminado con Exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    this.Table.setEnabled(true);
                    this.btnEditar.setEnabled(true);
                    this.btnEliminar.setEnabled(true);
                    cn.ejecutarSQL("DELETE FROM pedido_producto WHERE id_pedido =" +id_pedido );
                    id_pedido =0;
                    cn.cerrarConexion();
                }else{
                    JOptionPane.showMessageDialog(null, "No se logro eliminar el elemento seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    CargarTabla();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error:" + ex, "Error al cargar la Tabla", JOptionPane.ERROR_MESSAGE);

                }
            }
            this.Table.setEnabled(true);
            this.btnEditar.setEnabled(true);
            this.btnEliminar.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(null, "Ningun Elemento Seleccionado", "Error", JOptionPane.ERROR_MESSAGE);

        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!(txtNumero.getText().isEmpty())&&(!(txtNombre.getText().isEmpty()))){
            try {
                CargarTabla(txtNombre.getText(), txtNumero.getText());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error:" + ex, "Error al cargar la Tabla", JOptionPane.ERROR_MESSAGE);
            }
        }else if(!(txtNumero.getText().isEmpty())){
            try {
                CargarTabla(1,txtNumero.getText());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error:" + ex, "Error al cargar la Tabla", JOptionPane.ERROR_MESSAGE);
            }
        }else if(!(txtNombre.getText().isEmpty())){
            try {
                CargarTabla(txtNombre.getText());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error:" + ex, "Error al cargar la Tabla", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            try {
                CargarTabla();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error:" + ex, "Error al cargar la Tabla", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
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
