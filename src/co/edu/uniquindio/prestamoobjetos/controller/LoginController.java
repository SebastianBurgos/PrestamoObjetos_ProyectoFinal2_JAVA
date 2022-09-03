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
    private TextField txtContraseña;

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
		String contraseña = txtContraseña.getText();
		String tipoUsuario = optTipoUsuario.getText();

		if(validarDatos(nombreUsuario, contraseña, tipoUsuario)==1){
			aplicacion.ingresoVentanaPrincipal();
		}else{
			if(validarDatos(nombreUsuario, contraseña, tipoUsuario)==2){
				aplicacion.ingresoInventario();
			}else{
				if(validarDatos(nombreUsuario, contraseña, tipoUsuario)==3){
					aplicacion.ingresoPrestador();
				}else{
					mostrarMensaje("Notificación ingreso", "Datos invalidos", "El nombre de usuario o la contraseña son incorrectas", AlertType.WARNING);
				}
			}
		}
	}

	/**
	 * Metodo para validar los datos ingresados en login
	 * @param nombreUsuario
	 * @param contraseña
	 * @param tipoUsuario
	 * @return
	 */
	private int validarDatos(String nombreUsuario, String contraseña, String tipoUsuario) {
		if(nombreUsuario.equals("Administrador") && contraseña.equals("12345") && tipoUsuario.equals("Administrador")){
			return 1;
		}else{
			if(nombreUsuario.equals("Inventario") && contraseña.equals("54321") && tipoUsuario.equals("Jefe Inventario")){
				return 2;
			}else{
				if(nombreUsuario.equals("Prestador") && contraseña.equals("123") && tipoUsuario.equals("Prestador")){
					return 3;
				}else{
					if(nombreUsuario == null || contraseña == null){
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
