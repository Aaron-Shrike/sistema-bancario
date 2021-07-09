/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Sh
 */
public class Conexion {
    private final String controlador = "org.postgresql.Driver";
    private final String cadenaConexion = "jdbc:postgresql://localhost:5432/bd_pryBanco";
    private final String usuario = "postgres";
    private final String clave = "12345";
    private Connection conexion;
    
    public Connection abrirConexion() throws Exception{
        Class.forName(controlador);
        
        conexion = DriverManager.getConnection(cadenaConexion, usuario, clave);
        
        return conexion;
    }
    
    public void cerrarConexion(Connection conexion) throws Exception{
        conexion.close();
    }
    
    public ResultSet ejecutarSQL(String sql) throws Exception{
        Statement sentencia;
        ResultSet resultado;
        
        sentencia = this.abrirConexion().createStatement();
        resultado = sentencia.executeQuery(sql);
        
        this.cerrarConexion(conexion);
        
        return resultado;
    }
    
    public ResultSet ejecutarSQL(PreparedStatement sp) throws Exception{
        ResultSet resultado;
        
        resultado = sp.executeQuery();
        
        this.cerrarConexion(conexion);
        
        return resultado;
    }
   
    public int ejecutarSQLActualizable(PreparedStatement sp) throws Exception{        
        int valor;
                
        valor = sp.executeUpdate();
        
        this.cerrarConexion(conexion);
        
        return valor;
    }
}
