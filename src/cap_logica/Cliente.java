/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap_logica;

import cap_datos.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Sh
 */
public class Cliente {
    private String dni;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;

    public Cliente() {
        dni = "";
        nombre = "";
        apellidoPaterno = "";
        apellidoMaterno = "";
        fechaNacimiento = null;
        direccion = "";
        telefono = "";
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public boolean agregar() throws Exception{
        String sql = "insert into cliente(dni_cliente, nombre_cliente, apellido_paterno_cliente, "
                + "apellido_materno_cliente, fecha_nacimiento_cliente, direccion_cliente, "
                + "telefono_cliente) values (?, ?, ?, ?, ?, ?, ?)";
        Conexion transaccion = new Conexion();

        PreparedStatement spInsertar = transaccion.abrirConexion().prepareStatement(sql);
            
        spInsertar.setString(1, getDni());
        spInsertar.setString(2, getNombre());
        spInsertar.setString(3, getApellidoPaterno());
        spInsertar.setString(4, getApellidoMaterno());
        spInsertar.setDate(5, getFechaNacimiento());
        spInsertar.setString(6, getDireccion());
        spInsertar.setString(7, getTelefono());
        
        int valor = transaccion.ejecutarSQLActualizable(spInsertar);

        return true;
    }
    
    public ResultSet buscarPorDni(String dni) throws Exception{
        String sql = "select * from cliente where dni_cliente = ?";
        Conexion objConectarse = new Conexion();
        
        PreparedStatement spListar = objConectarse.abrirConexion().prepareStatement(sql);
        spListar.setString(1, dni);              
        ResultSet resultado = objConectarse.ejecutarSQL(spListar);
        
        return resultado;
    }
}
