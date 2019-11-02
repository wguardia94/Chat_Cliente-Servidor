package paqueteclases;

public class Mensaje {

	
	char tipo;
	String nickOrigen;
	String nickDestino;
	String mensaje;
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public Mensaje(char tipo, String nickOrigen,String nickDestino, String mensaje) {
		super();
		this.tipo = tipo;
		this.nickOrigen = nickOrigen;
		this.nickDestino = nickOrigen;
		this.mensaje = mensaje;
	}

	
	
	public String getNickOrigen() {
		return nickOrigen;
	}
	public void setNickOrigen(String nickOrigen) {
		this.nickOrigen = nickOrigen;
	}
	public String getNickDestino() {
		return nickDestino;
	}
	public void setNickDestino(String nickDestino) {
		this.nickDestino = nickDestino;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
