
package DAO;

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
 */
public class ProveedoresDAO {
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
}