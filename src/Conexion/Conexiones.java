package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexiones {
    Connection con;
    public Connection getConexion()
    {
       return con;
    }

    public boolean crearConexion()
    {
       try {
          
            con = DriverManager.getConnection("jdbc:derby:test2;create=true");

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
            con.close();
            System.out.println("Se ha creado la tabla correctamente");
            
       } catch (SQLException ex) {
          ex.printStackTrace();
          return false;
       }

       return true;
    }

    public boolean ejecutarSQL(String sql)
    {
       try {
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
}
