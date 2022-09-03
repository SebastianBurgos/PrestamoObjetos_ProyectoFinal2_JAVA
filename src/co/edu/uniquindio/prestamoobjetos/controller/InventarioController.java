package co.edu.uniquindio.prestamoobjetos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.prestamoobjetos.Aplicacion;
import co.edu.uniquindio.prestamoobjetos.model.Objeto;
import co.edu.uniquindio.prestamoobjetos.model.Prestamo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class InventarioController {
	Aplicacion aplicacion;

	ObservableList<Objeto> listaObjetosData = FXCollections.observableArrayList();

	ObservableList<Prestamo> listaPrestamosData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSalir;

    @FXML
    private TextArea txtObjetosDisponibles;

    @FXML
    private TextArea txtObjetosPrestados;

    @FXML
    private TextArea txtObjetosAgotados;

    @FXML
    void salirLoginAction(ActionEvent event) {
    	mostrarLogin();
    }

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		mostrarObjetosDisponibles();
		mostrarObjetosPrestados();
		mostrarObjetosAgotados();
	}

	private void mostrarLogin() {
		aplicacion.salirLogin();
	}

	void initialize() {

	}

	/**
	 * Metodo para mostrar los objetos disponibles
	 */
	private void mostrarObjetosDisponibles() {
		String objetosDisponibles = "";
		objetosDisponibles = aplicacion.obtenerObjetosDisponibles();
		txtObjetosDisponibles.setText(objetosDisponibles);
	}

	/**
	 * Metodo para mostrar los objetos prestados
	 */
	private void mostrarObjetosPrestados() {
		String objetosPrestados = "";
		objetosPrestados = aplicacion.obtenerObjetosPrestados();
		txtObjetosPrestados.setText(objetosPrestados);
	}

	/**
	 * Metodo para mostrar ls objetos agotados
	 */
	private void mostrarObjetosAgotados() {
		String objetosAgotados = "";
		objetosAgotados = aplicacion.obtenerObjetosAgotados();
		txtObjetosAgotados.setText(objetosAgotados);
	}

	public ObservableList<Objeto> getListaObjetosData() {
		listaObjetosData.addAll(aplicacion.obtenerObjetos());
		return listaObjetosData;
	}

	public ObservableList<Prestamo> getListaPrestamosData() {
		listaPrestamosData.addAll(aplicacion.obtenerPrestamos());
		return listaPrestamosData;
	}
}