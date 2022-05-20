/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import DAO.ClientesDAO;
import Model.ClienteDTO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alberto
 */
public class VistaClientesController implements Initializable {

    @FXML
    private TextField txtdBuscarCliente;
    @FXML
    private Button btnBuscarCliente;
    @FXML
    private Button btnActualizarCliente;
    @FXML
    private Button btnEliminarCliente;
    @FXML
    private Button btnAgregarCliente;
    @FXML
    private Button btnMenu;
    @FXML
    private TableView<ClienteDTO> tblClientes;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colPaterno;
    @FXML
    private TableColumn colMaterno;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colDireccion;
    
    private ClienteDTO clienteSeleccionado = null;
    private ClientesDAO c;
    private ObservableList<ClienteDTO> listaClientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        this.colMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        this.colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        this.colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        
        c = new ClientesDAO();
        listaClientes = c.getClientes();
        tblClientes.setItems(listaClientes);
        
        FilteredList<ClienteDTO> filteredData = new FilteredList<>(listaClientes, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		 txtdBuscarCliente.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(productoSeleccionado -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (productoSeleccionado.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (productoSeleccionado.getApellidoPaterno().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (productoSeleccionado.getApellidoMaterno().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<ClienteDTO> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tblClientes.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tblClientes.setItems(sortedData);
        
    }    
    

    @FXML
    private void actualizarCliente(ActionEvent event) {
    }

    @FXML
    private void eliminarCliente(ActionEvent event) {
        ClientesDAO p = new ClientesDAO();
        if(clienteSeleccionado != null){
            
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Eliminar cliente seleccionado?", ButtonType.YES, ButtonType.NO);

              // clicking X also means no
              ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

              if (!ButtonType.NO.equals(result)) {
                  p.eliminarCliente(clienteSeleccionado);
                  listaClientes.remove(clienteSeleccionado);
                  tblClientes.refresh();
                }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("Debes seleccionar cliente");
            alert.show();
        }
    }

    @FXML
    private void agregarCliente(ActionEvent event) throws IOException {
         lanzarSiguienteVentana("VistaAgregarClientes.fxml");
    }

    @FXML
    private void regresarMenu(ActionEvent event) throws IOException {
         lanzarSiguienteVentana("VistaMenu.fxml");
    }
    
    
    @FXML
    private void seleccionarCliente(MouseEvent event) {
        clienteSeleccionado = this.tblClientes.getSelectionModel().getSelectedItem();
        System.out.println(clienteSeleccionado);
    }
    
    private void lanzarSiguienteVentana(String vista) throws IOException{
            FXMLLoader loader = new FXMLLoader();//cardo la vista
            loader.setLocation(Main.class.getResource("/View/"+vista));
            //cargo la ventana
            AnchorPane ventana = (AnchorPane) loader.load();
            Scene scene = new Scene(ventana);//panel
            //seteo ka scene y la muestro
            Stage primaryStage = new Stage();//jframe
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
            ((Stage) (btnAgregarCliente.getScene().getWindow())).close();
    }

    
    
}
