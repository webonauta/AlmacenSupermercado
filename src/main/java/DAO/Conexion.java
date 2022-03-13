package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Navarro Villa Emmanuel De Jesús
 */
public class Conexion {
    private static Connection con=null;
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String password="semillaLab@DBTest";
    private static String usuario="semillal_root";
    private static String bd="semillal_almacen";
    private static String url="jdbc:mysql://162.241.60.208/"+bd;


public static Connection getConnection(){

        try {  
             if(con==null){
                Runtime.getRuntime().addShutdownHook(new MiShDown());
                Class.forName(driver);
                con=DriverManager.getConnection(url,usuario,password);
             }
            } catch (ClassNotFoundException | SQLException ex) {
           
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
              }
    return con;
}
    
static class MiShDown extends Thread{

    public void run(){

        try {
            con.close();
            System.out.println("Conexion Cerrada");
        } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      }    
}
