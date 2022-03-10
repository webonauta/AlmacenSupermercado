/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import DAO.ProductosDAO;
import Model.ProductoDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class VistaAgregarProductosController implements Initializable {

    @FXML
    private Label lblClave;
    @FXML
    private TextField txtClave;
    @FXML
    private Label lblNombre;
    @FXML
    private TextField txtNombre;
    @FXML
    private Label lblDescripcion;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Label lblCategoria;
    @FXML
    private TextField txtCategoria;
    @FXML
    private Label lblFechaAlta;
    @FXML
    private DatePicker txtDate;
    @FXML
    private Label lblCantidad;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Label lblPrecioUnitario;
    @FXML
    private TextField txtPrecioUnitario;
    @FXML
    private Label lblPrecioVenta;
    @FXML
    private TextField txtPrecioVenta;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void agregarProducto(ActionEvent event) {
        ProductosDAO p = new ProductosDAO();
        ProductoDTO producto = new ProductoDTO(txtClave.getText(),txtNombre.getText(),txtDescripcion.getText(),
                txtCategoria.getText(),"2021-02-13",Integer.parseInt(txtCantidad.getText()),
                Float.valueOf(txtPrecioUnitario.getText()),Float.valueOf(txtPrecioVenta.getText()));
        p.insertarProducto(producto);
        //System.out.println("Registro de producto");
    }

    @FXML
    private void regresarMenu(ActionEvent event) {
    }
    
}
