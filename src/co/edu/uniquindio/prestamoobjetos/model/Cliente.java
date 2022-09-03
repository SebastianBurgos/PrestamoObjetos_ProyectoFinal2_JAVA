package co.edu.uniquindio.prestamoobjetos.model;

public class Cliente {

	private String numeroDocumento;
	private TipoDocumento tipoDocumento;
	private String nombre;
	private String telefonoResidencia;
	private String telefonoCelular;
	private String direccion;
	private String ciudadResidencia;
	private String departamento;
	private String pais;
	private String profesion;
	private String email;

	/**
	 * Metodo constructor de la clase Cliente
	 * @param nombre
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @param direccion
	 * @param ciudadResidencia
	 * @param departamento
	 * @param pais
	 * @param profesion
	 * @param email
	 * @param telefonoCelular
	 * @param telefonoResidencia
	 */
	public Cliente(String nombre, TipoDocumento tipoDocumento, String numeroDocumento, String direccion, String ciudadResidencia,
			String departamento, String pais, String profesion, String email, String telefonoCelular, String telefonoResidencia) {
		super();
		this.numeroDocumento = numeroDocumento;
		this.tipoDocumento = tipoDocumento;
		this.nombre = nombre;
		this.telefonoResidencia = telefonoResidencia;
		this.telefonoCelular = telefonoCelular;
		this.direccion = direccion;
		this.ciudadResidencia = ciudadResidencia;
		this.departamento = departamento;
		this.pais = pais;
		this.profesion = profesion;
		this.email = email;
	}

	/**
	 * Metodo constructor solo para setear uno por uno los datos
	 */
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}


	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTelefonoResidencia() {
		return telefonoResidencia;
	}


	public void setTelefonoResidencia(String telefonoResidencia) {
		this.telefonoResidencia = telefonoResidencia;
	}


	public String getTelefonoCelular() {
		return telefonoCelular;
	}


	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getCiudadResidencia() {
		return ciudadResidencia;
	}


	public void setCiudadResidencia(String ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}


	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getProfesion() {
		return profesion;
	}


	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Cliente [numeroDocumento=" + numeroDocumento + ", tipoDocumento=" + tipoDocumento
				+ ", nombre=" + nombre + ", telefonoResidencia=" + telefonoResidencia + ", telefonoCelular="
				+ telefonoCelular + ", direccion=" + direccion + ", ciudadResidencia=" + ciudadResidencia
				+ ", departamento=" + departamento + ", pais=" + pais + ", profesion=" + profesion + ", email=" + email
				+ "]";
	}

}
