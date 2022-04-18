/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ClienteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Alberto
 */
public class ClientesDAO {
    private Connection conexion;
    private PreparedStatement pstm;
    private String sql;
    private ResultSet rs;
    
    public void insertarCliente(ClienteDTO cliente){
        try {
            conexion = Conexion.getConnection();
            conexion.setAutoCommit(false);
            
            sql = "INSERT INTO clientes(nombre, apellido_paterno, apellido_materno, telefono, direccion) ";
            sql += " VALUES(?, ?, ?, ?, ?);";
            
            pstm=conexion.prepareStatement(sql);
            
            pstm.setString(1, cliente.getNombre());
            pstm.setString(2, cliente.getApellidoPaterno());
            pstm.setString(3, cliente.getApellidoMaterno());
            pstm.setString(4, cliente.getTelefono());
            pstm.setString(5, cliente.getDireccion());
            
            int resultado = pstm.executeUpdate();
            if(resultado > 0){
                conexion.commit();
                System.out.println("Cliente agregado correctamente");
            }else{
                throw new RuntimeException("No se pudo agregar los datos del cliente");
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
                if(conexion != null) conexion.rollback();
                if(pstm != null) pstm.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
             
            
        }
    }
    
     public ObservableList<ClienteDTO> getClientes() {
           ObservableList<ClienteDTO> lista = FXCollections.observableArrayList();
           ClienteDTO c; 
           
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
    
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql = "SELECT nombre,apellido_paterno,apellido_materno,telefono,direccion";
            sql += " FROM clientes; ";
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
           
            //devuelve las tuplas de la consulta sql en uno bjeto ResultSet 
            rs=pstm.executeQuery();
            
            while(rs.next()){
                
                c = new ClienteDTO();
                
                c.setNombre(rs.getString("nombre"));
                c.setApellidoPaterno(rs.getString("apellido_paterno"));
                c.setApellidoMaterno(rs.getString("apellido_materno"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                
               
                lista.add(c);
                
            }
            
            return lista;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }finally{
            try {
                
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
             
            
        }
        
    } 
}
