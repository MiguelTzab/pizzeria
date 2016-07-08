package Conexion;
import java.sql.*;

public class Conexiones1 {
   private static Connection cnx = null;
   public void realizarConexion() throws SQLException, ClassNotFoundException {
      if (cnx == null) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://192.168.1.64:3306/pizzeria?useSSL=false", "miguel", "");
            System.out.println("Acceso a la bd");
         } catch (SQLException ex) {
            throw new SQLException(ex);
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
      }
   }
   public void cerrar() throws SQLException {
      if (cnx != null) {
         cnx.close();
      }
   }
}

