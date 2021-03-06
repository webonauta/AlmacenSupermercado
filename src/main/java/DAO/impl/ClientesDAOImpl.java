package DAO.impl;

import DAO.ClientesDAO;
import DAO.Conexion;
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
 * @author Ingrid Casales 
 */
public class ClientesDAOImpl implements ClientesDAO{
    private Connection conexion;
    private PreparedStatement pstm;
    private String sql;
    private ResultSet rs;
    
    @Override
    public void insertarCliente(ClienteDTO cliente){
        try {
            conexion = Conexion.getConnection();
            conexion.setAutoCommit(false);
            
            sql = "INSERT INTO clientes(nombre, apellido_paterno, apellido_materno, telefono, direccion) ";
            sql += " VALUES(?, ?, ?, ?, ?);";
            
            pstm = conexion.prepareStatement(sql);
            
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
    
    @Override
    public void eliminarCliente(ClienteDTO cliente){
        
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            //desactivo autoCommit
            conexion.setAutoCommit(false);
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql="DELETE FROM clientes ";
            sql+=" WHERE nombre = ? AND apellido_paterno = ? AND apellido_materno = ?;";
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            //seteamos(actualizamos) los valores de la sentencia  sql parametrizada(?,?,?...etc)
            pstm.setString(1, cliente.getNombre());
            pstm.setString(2, cliente.getApellidoPaterno());
            pstm.setString(3, cliente.getApellidoMaterno());
            
            //executeUpdate() devuelve el n??mero de filas afectadas
            int resultado=pstm.executeUpdate();
            if (resultado>0) {
                //// todo OK entonces commiteo la operacion
                conexion.commit();
                System.out.println("Cliente Borrado correctamente");
            } else {
                throw new RuntimeException("Cliente no borrado correctamente");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
                //rollback por las dudas
                if(conexion!=null) conexion.rollback();
                if(pstm!=null) pstm.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
             
            
        }
    
    }
    
    @Override
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

    @Override
    public int getIdCliente(String paterno, String materno, String nombre) {
       int id_usuario = 0;
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql = "SELECT id_cliente";
            sql += " FROM clientes ";
            sql += " WHERE nombre = ? AND apellido_paterno = ? AND apellido_materno = ?; ";
            
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            pstm.setString(1 , nombre);
            pstm.setString(2 , paterno);
            pstm.setString(3 , materno);
            
            //devuelve las tuplas de la consulta sql en uno objeto ResultSet 
            rs=pstm.executeQuery();
            
            while(rs.next()){
              
                id_usuario = Integer.parseInt(rs.getString(1));
                
            }
            
            return id_usuario;
            
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
    

    @Override
    public void actualizarCliente(ClienteDTO cliente , int id) {
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            //desactivo autoCommit
            conexion.setAutoCommit(false);
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql="UPDATE clientes ";
            sql+=" SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, telefono = ?, direccion = ? ";
            sql+=" WHERE  id_cliente = ? ;";
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            //seteamos(actualizamos) los valores de la sentencia  sql parametrizada(?,?,?...etc)
            //pasandole en orden los parametros de un EstudianteDTO de acuerdo a la tabla que vamos insertar
            pstm.setString(1, cliente.getNombre());
            pstm.setString(2, cliente.getApellidoPaterno());
            pstm.setString(3, cliente.getApellidoPaterno());
            pstm.setString(4, cliente.getTelefono());
            pstm.setString(5, cliente.getDireccion());
            pstm.setInt(6, id);
            
            //executeUpdate() devuelve el n??mero de filas afectadas
            int resultado=pstm.executeUpdate();
            if (resultado>0) {
                //// todo OK entonces commiteo la operacion
                conexion.commit();
                System.out.println("Cliente actualizado correctamente");
            } else {
                throw new RuntimeException("Cliente no actualizado correctamente");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
                //rollback por las dudas
                if(conexion!=null) conexion.rollback();
                if(pstm!=null) pstm.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
             
            
        }
    }
}
