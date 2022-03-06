/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
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
public class PanelProductosController implements Initializable {

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

    @FXML
    private void txtClientes(MouseEvent event) {
        try {
            FXMLLoader loaderClient = new FXMLLoader(getClass().getResource("/view/panelClientes.fxml"));
            
            Parent rootClient = loaderClient.load();
            
            PanelProveedoresController controlClient = loaderClient.getController();
           
            Scene sceneClient = new Scene(rootClient);
           
            
        } catch (Exception e) {
            
        }
    }

    @FXML
    private void txtProveedores(MouseEvent event) {
        try {
            FXMLLoader loaderProv = new FXMLLoader(getClass().getResource("/view/panelProveedores.fxml"));
            
            Parent rootProv = loaderProv.load();
            
            PanelProveedoresController controlProv = loaderProv.getController();
           
            Scene sceneProv = new Scene(rootProv);
           
            
        } catch (Exception e) {
            
        }
    }

}
