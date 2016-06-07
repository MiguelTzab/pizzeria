/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author MiguelAtocha
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Conexiones cn = new Conexiones();
        cn.crearConexion();
        ResultSet rs;
        rs = cn.ejecutarSQLSelect("Select * from cliente");
        ResultSetMetaData metadata = rs.getMetaData();
        
        //Imprimimos la cabecera de la tabla
        int columnas = metadata.getColumnCount();
        for (int i = 1; i <= columnas; i++) {
            System.out.format("%15s", metadata.getColumnName(i) + " || ");
        }
 
        while (rs.next()) {
            //Imprimimos cada una de las filas de la tabla
            System.out.println("");
            for (int j = 1; j <= columnas; j++) {
                 System.out.format("%15s", rs.getString(j) + " || ");
            }
        }
        
        
    }
    
}
