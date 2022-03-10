/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
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
    public void start(Stage primaryStage) throws IOException {
        
        try{
            FXMLLoader loader = new FXMLLoader();//cardo la vista
            loader.setLocation(Main.class.getResource("/View/VistaLogin.fxml"));
            //cargo la ventana
            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene(ventana);
            //seteo ka scene y la muestro
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
