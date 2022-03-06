/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dao.LoginConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField passField;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button btnLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginBottonOnAction(ActionEvent event){
        if(txtUsuario.getText().isBlank() == false && passField.getText().isBlank() == false){
            //loginMessageLabel.setText("Error al iniciar sesion");
            validateLogin();
        }else{
            loginMessageLabel.setText("Ingresa usuario y contraseña");
        }
    }
    
    public void validateLogin(){
        LoginConnection connectNow = new LoginConnection();
        Connection connectDB = connectNow.getConnection();
        
        String verifyLogin = "SELECT count(1) FROM usuarios WHERE id_usuario = '"+ txtUsuario.getText() +"' AND password = '" + passField.getText() +"'";
        
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            
            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Bienvenido");
                }else{
                    loginMessageLabel.setText("Datos incorrectos, intente de nuevo.");
                }
            }
        } catch (Exception e) {
        }
    }
    
}
