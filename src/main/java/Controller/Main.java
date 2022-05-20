package controller;

import DAO.Conexion;
import java.io.IOException;
import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Alberto
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {//este metodo inicia la aplicacion, primaryStage es la ventana principal(Jframe)
        
        try{
            FXMLLoader loader = new FXMLLoader();//cargo la vista
            loader.setLocation(Main.class.getResource("/View/VistaLogin.fxml"));
            //cargo la ventana
            Pane ventana = (Pane) loader.load();//es un contenedor Panel que puede tener más componentes
            Scene scene = new Scene(ventana);// es la zona donde está la interacion de los componentes
            //seteo la scene y la muestro
            primaryStage.setTitle("Nenis Market");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            Connection con = null;
            con = Conexion.getConnection();
            
            if (con != null) {
                System.out.println("Conexion establecida");
            }
            
            
        }catch(IOException e){
            System.out.println("E: "+e.getMessage());
        }
    
    }

    
        
        
    public static void main(String[] args) {
        launch(args);
    }
    
}
