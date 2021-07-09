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
public class Cuenta {
    private String numeroCuenta;
    private Date fechaApertura;
    private double montoInicial;
    private Moneda tipoMoneda;
    private Estado tipoEstado;
    private String dniCliente;

    public Cuenta() {
        numeroCuenta = "";
        fechaApertura = null;
        montoInicial = 0.0;
        tipoMoneda = null;
        tipoEstado = Estado.ABIERTA;
        dniCliente = "";
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(double montoInicial) {
        this.montoInicial = montoInicial;
    }

    public Moneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(Moneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Estado getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(Estado tipoEstado) {
        this.tipoEstado = tipoEstado;
    }
    
    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }
    
    /*
        -- PROCESOS --
    */
    
    public boolean agregar() throws Exception{
        String sql = "insert into cuenta(numero_cuenta, fecha_apertura_cuenta, "
                + "monto_inicial_cuenta, tipo_moneda_cuenta, estado_cuenta, "
                + "dni_cliente_cuenta) values (?, ?, ?, ?, ?, ?)";
        Conexion transaccion = new Conexion();

        PreparedStatement spInsertar = transaccion.abrirConexion().prepareStatement(sql);
            
        spInsertar.setString(1, getNumeroCuenta());
        spInsertar.setDate(2, getFechaApertura());
        spInsertar.setDouble(3, getMontoInicial());
        spInsertar.setString(4, getTipoMoneda().name());
        spInsertar.setString(5, getTipoEstado().name());
        spInsertar.setString(6, getDniCliente());
        
        int valor = transaccion.ejecutarSQLActualizable(spInsertar);

        return true;
    }
    
    public ResultSet buscarPorNumCuenta(String numero) throws Exception{
        String sql = "select * from cuenta where numero_cuenta = ?";
        Conexion objConectarse = new Conexion();
        
        PreparedStatement spListar = objConectarse.abrirConexion().prepareStatement(sql);
        spListar.setString(1, numero);              
        ResultSet resultado = objConectarse.ejecutarSQL(spListar);
        
        return resultado;
    }
    
    public void modificarEstadoCerrada(String numero, String estado) throws Exception{
        String sql = "update cuenta set estado_cuenta = ? where numero_cuenta = ?";
        Conexion objConectarse = new Conexion();
        
        PreparedStatement spModificar = objConectarse.abrirConexion().prepareStatement(sql);
        spModificar.setString(1, estado);
        spModificar.setString(2, numero);
        
        int valor = objConectarse.ejecutarSQLActualizable(spModificar);        
    }
    
    public void depositarMonto(String numero, double deposito) throws Exception{       
        String sql = "select * from cuenta where numero_cuenta = ?";
        double nuevoMonto = 0;
        Conexion objConectarse = new Conexion();
        
        PreparedStatement spBuscar = objConectarse.abrirConexion().prepareStatement(sql);
        spBuscar.setString(1, numero);
        ResultSet resultado = objConectarse.ejecutarSQL(spBuscar);
        
        while (resultado.next()){
            nuevoMonto = resultado.getDouble("monto_inicial_cuenta");
            
            nuevoMonto += deposito;
        }
        
        sql = "update cuenta set monto_inicial_cuenta = ? where numero_cuenta = ?";
        
        PreparedStatement spDepositar = objConectarse.abrirConexion().prepareStatement(sql);
        spDepositar.setDouble(1, nuevoMonto);
        spDepositar.setString(2, numero);       
        
        int valor = objConectarse.ejecutarSQLActualizable(spDepositar);
    }
    
    public boolean retirarMonto(String numero, double retiro) throws Exception{
        String sql = "select * from cuenta where numero_cuenta = ?";
        double nuevoMonto = 0;
        Conexion objConectarse = new Conexion();
        
        PreparedStatement spBuscar = objConectarse.abrirConexion().prepareStatement(sql);
        spBuscar.setString(1, numero);
        ResultSet resultado = objConectarse.ejecutarSQL(spBuscar);
        
        while (resultado.next()){
            nuevoMonto = resultado.getDouble("monto_inicial_cuenta");
            
            if(nuevoMonto >= retiro){
                nuevoMonto -= retiro;
            }else{
                return false;
            }
        }
        
        sql = "update cuenta set monto_inicial_cuenta = ? where numero_cuenta = ?";
        
        PreparedStatement spModificar = objConectarse.abrirConexion().prepareStatement(sql);
        spModificar.setDouble(1, nuevoMonto);
        spModificar.setString(2, numero);
        
        int valor = objConectarse.ejecutarSQLActualizable(spModificar); 
        
        return true;
    }
    
    /*
        -- REPORTES --
    */
    
    public ResultSet listarCuentas() throws Exception{
        String sql = "select * from cuenta";
        Conexion objConectarse = new Conexion();
        
        PreparedStatement sp = objConectarse.abrirConexion().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultado = objConectarse.ejecutarSQL(sp);
        
        return resultado;
    }
    
    public ResultSet listarCuentasPorAño(double año) throws Exception{
        String sql = "select * from cuenta where extract(year from fecha_apertura_cuenta) = ?";
        Conexion objConectarse = new Conexion();
        
        PreparedStatement spListar = objConectarse.abrirConexion().prepareStatement(sql);
        spListar.setDouble(1, año);              
        ResultSet resultado = objConectarse.ejecutarSQL(spListar);
        
        return resultado;
    }
    
    public ResultSet listarCuentasSegunDni(String dni) throws Exception{
        String sql = "select * from cuenta where dni_cliente_cuenta = ?";
        Conexion objConectarse = new Conexion();
        
        PreparedStatement spListar = objConectarse.abrirConexion().prepareStatement(sql);
        spListar.setString(1, dni);              
        ResultSet resultado = objConectarse.ejecutarSQL(spListar);
        
        return resultado;
    }
    
    public ResultSet listarCantidadPorMoneda() throws Exception{
        String sql = "select tipo_moneda_cuenta, count(*) as cantidad from cuenta group by tipo_moneda_cuenta";       
        
        Conexion objConectarse = new Conexion();
        PreparedStatement sp = objConectarse.abrirConexion().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultado = objConectarse.ejecutarSQL(sp);
        
        return resultado;
    }
    
    public ResultSet listarCuentasCerradas() throws Exception{
        String sql = "select * from cuenta where estado_cuenta = 'CERRADA'";
        Conexion objConectarse = new Conexion();
        
        PreparedStatement sp = objConectarse.abrirConexion().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultado = objConectarse.ejecutarSQL(sp);
        
        return resultado;
    }
}
