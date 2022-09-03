package co.edu.uniquindio.prestamoobjetos.model;

import javax.swing.JOptionPane;

public class DetallePrestamo {

	private Objeto objeto;
	private int unidadesPrestadas;
	private double subTotal;

	public DetallePrestamo(int unidadesPrestadas, double subTotal) {
		super();
		this.unidadesPrestadas = unidadesPrestadas;
		this.subTotal = subTotal;
	}

	public DetallePrestamo() {
		// TODO Auto-generated constructor stub
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public int getUnidadesPrestadas() {
		return unidadesPrestadas;
	}

	public void setUnidadesPrestadas(int unidadesPrestadas) {
		this.unidadesPrestadas = unidadesPrestadas;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		return "DetallePrestamo [objeto=" + objeto + ", unidadesPrestadas=" + unidadesPrestadas + ", subTotal="
				+ subTotal + "]";
	}

	public void crearDetallePrestamo(Objeto objetoEncontrado, int unidadesPrestadas, int diasSolicitados) {
		this.unidadesPrestadas = unidadesPrestadas;
		if(objetoEncontrado != null && unidadesPrestadas <= objetoEncontrado.getUnidadesDisponibles()){
			objeto = objetoEncontrado;
			subTotal = objetoEncontrado.getPrecioAlquiler()*diasSolicitados*unidadesPrestadas;
			objeto.disminuirExistencias(unidadesPrestadas);
			if(objeto.getUnidadesDisponibles() == 0){
				objeto.setEstado("No disponible");
			}
		}else{
			JOptionPane.showMessageDialog(null, "No hay suficientes unidades para prestar");
		}

	}

	public void aumentarExistencias(int unidadesAumentar) {
		objeto.aumentarExistencias(unidadesAumentar);
	}

	public void disminuirExistencias(int unidadesDisminuir) {
		objeto.disminuirExistencias(unidadesDisminuir);
	}

	public double calcularValorDiasAdicionales(int unidadesPrestadas, int diasAdicionales) {
		double valorDiasAdicionales = 0.0;
		double subTotalDiasAdicionales = 0.0;

		subTotalDiasAdicionales = objeto.getPrecioAlquiler()*diasAdicionales*unidadesPrestadas;
		valorDiasAdicionales = subTotalDiasAdicionales*1.7;

		return valorDiasAdicionales;
	}

	public boolean verificarObjetoPrestado(String codigoObjeto) {
		if(objeto.getCodigo().equals(codigoObjeto)){
			return true;
		}else{
			return false;
		}
	}

	public void actualizarDetallePrestamo(String estadoActual, String estado, int unidadesPrestadas) {
		setUnidadesPrestadas(unidadesPrestadas);
		if(estadoActual.equals("Entregado") && estado.equals("Pendiente")){
			disminuirExistencias(unidadesPrestadas);
		}
		if(estadoActual.equals("Pendiente") && estado.equals("Entregado")){
			aumentarExistencias(unidadesPrestadas);
		}
	}


}
