
package DAO.impl;

import DAO.Conexion;
import DAO.ProveedoresDAO;
import Model.ProveedorDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Navarro Villa Emmanuel de Jesús
 * @author Ingrid Casales
 */
public class ProveedoresDAOImpl implements ProveedoresDAO{
    private Connection conexion;
    private PreparedStatement pstm;
    private String sql;
    private CallableStatement stmt;
    private ResultSet rs;
    
    
    public void insertarProveedor(ProveedorDTO proveedor){
        
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            //desactivo autoCommit
            conexion.setAutoCommit(false);
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql="INSERT INTO proveedores(nombre,paterno,materno,telefono,direccion,empresa,rfc) ";
            sql+=" VALUES(?,?,?,?,?,?,?);";
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            //seteamos(actualizamos) los valores de la sentencia  sql parametrizada(?,?,?...etc)
            //pasandole en orden los parametros de un EstudianteDTO de acuerdo a la tabla que vamos insertar
            pstm.setString(1, proveedor.getNombre());
            pstm.setString(2, proveedor.getPaterno());
            pstm.setString(3, proveedor.getMaterno());
            pstm.setString(4, proveedor.getTelefono());
            pstm.setString(5, proveedor.getDireccion());
            pstm.setString(6, proveedor.getEmpresa());
            pstm.setString(7, proveedor.getRfc());
           
            //executeUpdate() devuelve el número de filas afectadas
            int resultado=pstm.executeUpdate();
            if (resultado>0) {
                //// todo OK entonces commiteo la operacion
                conexion.commit();
                System.out.println("Proveedor insertado correctamente");
            } else {
                throw new RuntimeException("Producto no insertado correctamente");
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
    
     public void eliminarProveedor(ProveedorDTO proveedor){
        
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            //desactivo autoCommit
            conexion.setAutoCommit(false);
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql="DELETE FROM proveedores ";
            sql+=" WHERE rfc = ?;";
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            //seteamos(actualizamos) los valores de la sentencia  sql parametrizada(?,?,?...etc)
            pstm.setString(1, proveedor.getRfc());
           
            
            //executeUpdate() devuelve el número de filas afectadas
            int resultado=pstm.executeUpdate();
            if (resultado>0) {
                //// todo OK entonces commiteo la operacion
                conexion.commit();
                System.out.println("Proveedor Borrado correctamente");
            } else {
                throw new RuntimeException("Proveedor no borrado correctamente");
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
    
     public ObservableList<ProveedorDTO> getProveedores() {
           ObservableList<ProveedorDTO> lista = FXCollections.observableArrayList();
           ProveedorDTO p; 
           
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
    
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql = "SELECT nombre,paterno,materno,telefono,direccion,empresa,rfc";
            sql += " FROM proveedores; ";
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
           
            //devuelve las tuplas de la consulta sql en uno bjeto ResultSet 
            rs=pstm.executeQuery();
            
            while(rs.next()){
                
                p = new ProveedorDTO();
                
                p.setNombre(rs.getString("nombre"));
                p.setPaterno(rs.getString("paterno"));
                p.setMaterno(rs.getString("materno"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setEmpresa(rs.getString("empresa"));
                p.setRfc(rs.getString("rfc"));
          
               
                lista.add(p);
                
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
    public void actualizarProveedor(ProveedorDTO proveedor, int id) {
         try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            //desactivo autoCommit
            conexion.setAutoCommit(false);
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql="UPDATE proveedores ";
            sql+=" SET nombre = ?, paterno = ?, materno = ?, telefono = ?, direccion = ?, empresa = ?, rfc = ? ";
            sql+=" WHERE  id_proveedor = ? ;";
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            //seteamos(actualizamos) los valores de la sentencia  sql parametrizada(?,?,?...etc)
            //pasandole en orden los parametros de un EstudianteDTO de acuerdo a la tabla que vamos insertar
            pstm.setString(1, proveedor.getNombre());
            pstm.setString(2, proveedor.getPaterno());
            pstm.setString(3, proveedor.getMaterno());
            pstm.setString(4, proveedor.getTelefono());
            pstm.setString(5, proveedor.getDireccion());
            pstm.setString(6, proveedor.getEmpresa());
            pstm.setString(7, proveedor.getRfc());
            pstm.setInt(8,id);
                       
            
            
            //executeUpdate() devuelve el número de filas afectadas
            int resultado=pstm.executeUpdate();
            if (resultado>0) {
                //// todo OK entonces commiteo la operacion
                conexion.commit();
                System.out.println("Proveedor actualizado correctamente");
            } else {
                throw new RuntimeException("Proveedor no actualizado correctamente");
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
    public int getIdProveedor(String rfc) {
            int id_proveedor = 0;
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql = "SELECT id_proveedor";
            sql += " FROM proveedores ";
            sql += " WHERE rfc = ?; ";
            
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            pstm.setString(1, rfc);
            
            //devuelve las tuplas de la consulta sql en uno objeto ResultSet 
            rs=pstm.executeQuery();
            
            while(rs.next()){
              
                id_proveedor = Integer.parseInt(rs.getString(1));
                
            }
            
            return id_proveedor;
            
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
