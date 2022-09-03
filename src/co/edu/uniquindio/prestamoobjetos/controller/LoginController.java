package co.edu.uniquindio.prestamoobjetos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.prestamoobjetos.Aplicacion;
import co.edu.uniquindio.prestamoobjetos.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
	Aplicacion aplicacion = new Aplicacion();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombreUsuario;

    @FXML
    private TextField txtContrase�a;

    @FXML
    private MenuItem optJefeInventario;

    @FXML
    private SplitMenuButton optTipoUsuario;

    @FXML
    private MenuItem optAdministrador;

    @FXML
    private MenuItem optPrestador;

    @FXML
    private Button btnIngresar;

    @FXML
    void seleccionarAdministrador(ActionEvent event) {
    	optTipoUsuario.setText("Administrador");
    }

    @FXML
    void seleccionarJefeInventario(ActionEvent event) {
    	optTipoUsuario.setText("Jefe Inventario");
    }

    @FXML
    void seleccionarPrestador(ActionEvent event) {
    	optTipoUsuario.setText("Prestador");
    }

    @FXML
    void ingresar(ActionEvent event) {
    	verificarIngreso();
    }

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * Metodo para verificar el ingreso a las 3 interfaces
	 */
	private void verificarIngreso() {
		String nombreUsuario = txtNombreUsuario.getText();
		String contrase�a = txtContrase�a.getText();
		String tipoUsuario = optTipoUsuario.getText();

		if(validarDatos(nombreUsuario, contrase�a, tipoUsuario)==1){
			aplicacion.ingresoVentanaPrincipal();
		}else{
			if(validarDatos(nombreUsuario, contrase�a, tipoUsuario)==2){
				aplicacion.ingresoInventario();
			}else{
				if(validarDatos(nombreUsuario, contrase�a, tipoUsuario)==3){
					aplicacion.ingresoPrestador();
				}else{
					mostrarMensaje("Notificaci�n ingreso", "Datos invalidos", "El nombre de usuario o la contrase�a son incorrectas", AlertType.WARNING);
				}
			}
		}
	}

	/**
	 * Metodo para validar los datos ingresados en login
	 * @param nombreUsuario
	 * @param contrase�a
	 * @param tipoUsuario
	 * @return
	 */
	private int validarDatos(String nombreUsuario, String contrase�a, String tipoUsuario) {
		if(nombreUsuario.equals("Administrador") && contrase�a.equals("12345") && tipoUsuario.equals("Administrador")){
			return 1;
		}else{
			if(nombreUsuario.equals("Inventario") && contrase�a.equals("54321") && tipoUsuario.equals("Jefe Inventario")){
				return 2;
			}else{
				if(nombreUsuario.equals("Prestador") && contrase�a.equals("123") && tipoUsuario.equals("Prestador")){
					return 3;
				}else{
					if(nombreUsuario == null || contrase�a == null){
						return 0;
					}
				}
			}
		}
		return 0;
	}

	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}
}
