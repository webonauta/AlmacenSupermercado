package DAO.impl;

import DAO.*;
import Model.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Navarro Villa Emmanuel De Jesús
 */
public class UsuariosDAOImpl {
    private Connection conexion;
    private PreparedStatement pstm;
    private ResultSet rs;
    private String sql;
    
    public UsuarioDTO validarUsuario(String id_usuario,String password) {
            UsuarioDTO usuario = null;
        
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql = "SELECT id_usuario, nombre, paterno,materno ";
            sql += "FROM usuarios  ";
            sql += "WHERE id_usuario = ?  AND password = ?;";
            
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            
            pstm.setString(1, id_usuario);
            pstm.setString(2, password);
            
            
            
            //devuelve las tuplas de la consulta sql en uno bjeto ResultSet 
            rs=pstm.executeQuery();
            
            while(rs.next()){
                
                usuario = new UsuarioDTO();
                usuario.setIdUsuario(rs.getString(1));
                usuario.setNombre(rs.getString(2));
                usuario.setPaterno(rs.getString(3));
                usuario.setMaterno(rs.getString(4));
                
            }
            
            return usuario;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
                
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
             
            
        }
        
    } 
}
