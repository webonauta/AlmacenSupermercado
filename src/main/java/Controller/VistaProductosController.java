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
public class VistaProductosController implements Initializable {

    @FXML
    private TextField txtFieldBuscarProductos;
    @FXML
    private Button btnBuscarProductos;
    @FXML
    private Button btnMenuProductos;
    @FXML
    private TextField txtFieldNombreProducto;
    @FXML
    private TextField txtFieldPrecioUnitarioProducto;
    @FXML
    private TextField txtFieldCategoriaProducto;
    @FXML
    private TextField txtFieldCantidadProducto;
    @FXML
    private Button btnNuevoProductos;
    @FXML
    private Button btnActualizarProductos;
    @FXML
    private Button btnEliminarProductos;

   

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

    private void txtProveedores(MouseEvent event) {
        try {
            FXMLLoader loaderProv = new FXMLLoader(getClass().getResource("/view/panelProveedores.fxml"));
            
            Parent rootProv = loaderProv.load();
            
            VistaProveedoresController controlProv = loaderProv.getController();
           
            Scene sceneProv = new Scene(rootProv);
           
            
        } catch (Exception e) {
            
        }
    }

}
