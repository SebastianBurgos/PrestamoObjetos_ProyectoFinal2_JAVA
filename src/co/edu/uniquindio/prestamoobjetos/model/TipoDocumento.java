package co.edu.uniquindio.prestamoobjetos.model;

public enum TipoDocumento {
	CEDULA(0), PASAPORTE(1), CEDULAEXTRANJERIA(2);

	private int numTipoDocumento;

	private TipoDocumento(int numTipoDocumento){
		this.numTipoDocumento = numTipoDocumento;
	}

	public int getNumTipoDocumento() {
		return numTipoDocumento;
	}

}
