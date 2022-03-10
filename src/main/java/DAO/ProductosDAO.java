package DAO;

import Model.ProductoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 *
 * @author Navarro Villa Emmanuel De Jesús
 */
public class ProductosDAO {
    private Connection conexion;
    private PreparedStatement pstm;
    private String sql;
    
    public void insertarProducto(ProductoDTO producto){
        
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            //desactivo autoCommit
            conexion.setAutoCommit(false);
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql="INSERT INTO productos(clave,nombre,descripcion,categoria,fecha_alta,cantidad,precio_unitario,precio_venta) ";
            sql+=" VALUES(?,?,?,?,?,?,?,?);";
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            //seteamos(actualizamos) los valores de la sentencia  sql parametrizada(?,?,?...etc)
            //pasandole en orden los parametros de un EstudianteDTO de acuerdo a la tabla que vamos insertar
            pstm.setString(1, producto.getClave());
            pstm.setString(2, producto.getNombre());
            pstm.setString(3, producto.getDescripcion());
            pstm.setString(4, producto.getCategoria());
            pstm.setString(5,producto.getFechaAlta());
            pstm.setInt(6, producto.getCantidad());
            pstm.setFloat(7, producto.getPrecioUnitario());
            pstm.setFloat(8, producto.getPrecioVenta());
           
            
            
            //executeUpdate() devuelve el número de filas afectadas
            int resultado=pstm.executeUpdate();
            if (resultado>0) {
                //// todo OK entonces commiteo la operacion
                conexion.commit();
                System.out.println("Producto insertado correctamente");
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
}
