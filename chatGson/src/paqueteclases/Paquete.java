package paqueteclases;

public class Paquete {

	String tipo;
	Mensaje mensaje;
	UsuarioAB altaBaja;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public UsuarioAB getAltaBaja() {
		return altaBaja;
	}
	public void setAltaBaja(UsuarioAB altaBaja) {
		this.altaBaja = altaBaja;
	}
	public Paquete(String tipo, Mensaje mensaje) {
		super();
		this.tipo = tipo;
		this.mensaje = mensaje;
	}
	public Paquete(String tipo, UsuarioAB altaBaja) {
		super();
		this.tipo = tipo;
		this.altaBaja = altaBaja;
	}
	
	
}
