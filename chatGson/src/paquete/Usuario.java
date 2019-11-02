package paquete;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class Usuario {

	private PrintStream streamOut;
	private InputStream streamIn;
	private String nickname;
	private Socket cliente;

	public Usuario(Socket cliente, String nombre) throws IOException {
		this.streamOut = new PrintStream(cliente.getOutputStream());
		this.streamIn = cliente.getInputStream();
		this.cliente = cliente;
		this.nickname = nombre;
	}

	public PrintStream getStreamOut() {
		return streamOut;
	}

	public void setStreamOut(PrintStream streamOut) {
		this.streamOut = streamOut;
	}

	public InputStream getStreamIn() {
		return streamIn;
	}

	public void setStreamIn(InputStream streamIn) {
		this.streamIn = streamIn;
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public PrintStream getOutStream() {
		return this.streamOut;
	}

	public InputStream getInputStream() {
		return this.streamIn;
	}

	public String getNickname() {
		return this.nickname;
	}

	public String toString() {
		return this.getNickname();
	}

}
