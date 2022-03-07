/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class PanelClientesController implements Initializable {

    @FXML
    private Label txtClientes;
    @FXML
    private Label txtProveedores;
    @FXML
    private Label txtProductos;
    @FXML
    private TextField fieldBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnNuevo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void txtProveedores(MouseEvent event) {
        try {
            FXMLLoader loaderProv = new FXMLLoader(getClass().getResource("/view/panelProveedores.fxml"));
            
            Parent rootProv = loaderProv.load();
            
            VistaProveedoresController controlProv = loaderProv.getController();
           
            Scene sceneProv = new Scene(rootProv);
           
            
        } catch (Exception e) {
            
        }
    }
    
    private void textProductos(MouseEvent event) {
        try {
            FXMLLoader loaderProd = new FXMLLoader(getClass().getResource("/view/panelProductos.fxml"));
            
            Parent rootProd = loaderProd.load();
            
            VistaProveedoresController controlProd = loaderProd.getController();
            
            Scene sceneProd = new Scene(rootProd);
            
        } catch (Exception e) {
            
        }
    }
    
}
