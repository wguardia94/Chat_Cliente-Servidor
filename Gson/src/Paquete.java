import java.util.ArrayList;

public class Paquete {

	
	String tipo;
	Persona infoPersona;
	Mensaje infoMensaje;
	ArrayList<Persona> infoArrayPersona=new ArrayList<Persona>();
	
	public Paquete(String tipo, Persona info) {
		super();
		this.tipo = tipo;
		this.infoPersona = info;
	}
	
	
	public Paquete(String tipo, Mensaje info) {
		super();
		this.tipo = tipo;
		this.infoMensaje = info;
	}
	
	
	public Persona getInfoPersona() {
		return infoPersona;
	}


	public void setInfoPersona(Persona infoPersona) {
		this.infoPersona = infoPersona;
	}


	public Mensaje getInfoMensaje() {
		return infoMensaje;
	}


	public void setInfoMensaje(Mensaje infoMensaje) {
		this.infoMensaje = infoMensaje;
	}


	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public ArrayList<Persona> getInfoArrayPersona() {
		return infoArrayPersona;
	}


	public void setInfoArrayPersona(ArrayList<Persona> infoArrayPersona) {
		this.infoArrayPersona = infoArrayPersona;
	}


	public Paquete(String tipo, ArrayList<Persona> infoArrayPersona) {
		super();
		this.tipo = tipo;
		this.infoArrayPersona = infoArrayPersona;
	}

	
	
	
	
	
	
}
