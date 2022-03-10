/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;


import DAO.UsuariosDAO;
import Model.UsuarioDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class VistaLoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void validarUsuario(ActionEvent event) {
        UsuarioDTO usuario = null;
        UsuariosDAO u = new UsuariosDAO();
        
        usuario = u.validarUsuario(txtUsuario.getText(),txtPassword.getText());
        
        System.out.println("Te logeaste : " + usuario);
    }

}
