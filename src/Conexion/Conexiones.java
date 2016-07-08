package Conexion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexiones {
    Connection con;
    public Connection getConexion()
    {
       crearConexion();
       return con;
    }

    public boolean crearConexion()
    {
       try {
          
            con = DriverManager.getConnection("jdbc:derby:pizzeriaDB;create=true");

            if(!verificarArchivo()){
                con.createStatement().execute("CREATE TABLE producto (id_Producto int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "  Nombre varchar(25) NOT NULL, " +
                    "  Descripcion varchar(50) NOT NULL, " +
                    "  Precio int NOT NULL, " +
                    "  PRIMARY KEY (id_Producto)" +
                    ")");
                con.createStatement().execute("CREATE TABLE usuario (" +
                    "  id_usuario int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "  Nombre varchar(25) NOT NULL, " +
                    "  Direccion varchar(90) NOT NULL, " +
                    "  Puesto varchar(15) NOT NULL,\n" +
                    "  Contraseña varchar(4) NOT NULL,\n" +
                    "  PRIMARY KEY (id_usuario)\n" +
                    ")");
                con.createStatement().execute("CREATE TABLE pedido_producto(\n" +
                    "  id_pedido int NOT NULL,\n" +
                    "  id_producto int NOT NULL,\n" +
                    "  cantidad int NOT NULL\n" +
                    ")");
                con.createStatement().execute("CREATE TABLE pedido(\n" +
                    "  id_Pedido int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
                    "  id_Usuario int NOT NULL,\n" +
                    "  id_Cliente int NOT NULL,\n" +
                    "  fecha date NOT NULL,\n" +
                    "  total int NOT NULL,\n" +
                    "  PRIMARY KEY (id_Pedido)\n" +
                    ") ");
                con.createStatement().execute("CREATE TABLE cliente (\n" +
                    "  id_Cliente int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) ,\n" +
                    "  Nombre varchar(25) NOT NULL,\n" +
                    "  Direccion varchar(50) NOT NULL,\n" +
                    "  Referencia varchar(50) NOT NULL,\n" +
                    "  Numero varchar(10) NOT NULL,"+
                    "  PRIMARY KEY (id_Cliente)\n" +
                    ")");
                //con.createStatement().execute("INSERT INTO usuario (Nombre, Direccion, Puesto, Contraseña) VALUES('admin', 'conocido', 'admin', 1234)");   
                PreparedStatement consulta;
                consulta = con.prepareStatement("INSERT INTO usuario" + "(Nombre, Direccion, Puesto, Contraseña) VALUES(?, ?, ?, ?)");
                consulta.setString(1, "admin");
                consulta.setString(2, "Conocido");
                consulta.setString(3, "Administrador");
                consulta.setString(4, String.valueOf(1111));
                consulta.executeUpdate();
                con.close();
                JOptionPane.showMessageDialog(null, "Se ha creado correctamente la Base de Datos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
            
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Error al crear la Base de Datos\n"+ex, "Mensaje", JOptionPane.ERROR_MESSAGE);    
          return false;
       }

       return true;
    }

    public boolean ejecutarSQL(String sql)
    {
       try {
          crearConexion();
          Statement sentencia = con.createStatement();
          sentencia.executeUpdate(sql);
       } catch (SQLException ex) {
          ex.printStackTrace();
          return false;
       }
       return true;
    }

    public ResultSet ejecutarSQLSelect(String sql)
    {
       crearConexion();
       ResultSet resultado;
       try {
          Statement sentencia = con.createStatement();
          resultado = sentencia.executeQuery(sql);
       } catch (SQLException ex) {
          ex.printStackTrace();
          return null;
       }
       return resultado;
    }
    public void cerrarConexion(){
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cerrar conexion: " +ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean verificarArchivo(){
        String ruta = "./archivo.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        try {
            if(archivo.exists()) {
                return true;
            } else {
                    bw = new BufferedWriter(new FileWriter(archivo));
                    java.util.Date utilDate = new java.util.Date(); //fecha actual
                    long lnMilisegundos = utilDate.getTime();
                    java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
                    String fec = sqlDate.toString();
                    bw.write(fec);
            }
            bw.close();
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: "+ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
