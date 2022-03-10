/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ClienteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 *
 * @author Alberto
 */
public class ClientesDAO {
    private Connection conexion;
    private PreparedStatement pstm;
    private String sql;
    
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
}
