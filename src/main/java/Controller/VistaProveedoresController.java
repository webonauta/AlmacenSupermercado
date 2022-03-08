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
public class VistaProveedoresController implements Initializable {

    @FXML
    private TextField txtFieldBuscarProveedor;
    @FXML
    private Button btnBuscarProveedor;
    @FXML
    private Button btnMenuProveedor;
    @FXML
    private TextField txtFieldEmpresaProveedor;
    @FXML
    private TextField txtFieldRazonSocialProveedor;
    @FXML
    private TextField txtFieldClaveProveedor;
    @FXML
    private TextField txtFieldDireccionProveedor;
    @FXML
    private TextField txtFieldRFCProveedor;
    @FXML
    private Button btnNuevoProovedor;
    @FXML
    private Button btnActualizarProveedor;
    @FXML
    private Button btnEliminarProveedor;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void txtClientes(MouseEvent event) {
        try {
            FXMLLoader loaderClient = new FXMLLoader(getClass().getResource("/view/panelClientes.fxml"));
            
            Parent rootClient = loaderClient.load();
            
            VistaProveedoresController controlClient = loaderClient.getController();
           
            Scene sceneClient = new Scene(rootClient);
           
            
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
