package paquete;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import paqueteclases.Paquete;
import paqueteclases.UsuarioAB;

public class Servidor {

	private int puerto;
	private ArrayList<Usuario> usuarios;
	private ServerSocket server;

	public static void main(String[] args) throws IOException {
		new Servidor(3000).run();
	}

	public Servidor(int puerto) {
		this.puerto = puerto;

		this.usuarios = new ArrayList<Usuario>();
	}

	@SuppressWarnings("resource")
	public void run() throws IOException {
		server = new ServerSocket(puerto) {
			protected void finalize() throws IOException {
				this.close();
			}
		};

		System.out.println("Corriendo en puerto: 3000");

		while (true) {
			/** se acepta un nuevo cliente **/
			Socket cliente = server.accept();

			Scanner scanner = new Scanner(cliente.getInputStream());
			Gson json = new Gson();
			String jPaquete = scanner.nextLine();

			Paquete miPaq = json.fromJson(jPaquete, Paquete.class);
			String nickname = miPaq.getAltaBaja().getNick();
			reenvioListaUsuarios(jPaquete);
			System.out.println(
					"Nuevo cliente conectado : " + nickname + " Host: " + cliente.getInetAddress().getHostAddress());

			/**
			 * se crea el usuario nuevo, se lo agrega a la lista de usuarios y se envia un
			 * mensaje de informando que entro un nuevo usuario
			 **/
			Usuario nuevo_usuario = new Usuario(cliente, nickname);
			this.usuarios.add(nuevo_usuario);

			new Thread(new Enviar(this, nuevo_usuario)).start();
		}
	}

	public void eliminarUsuario(Usuario usuario, String paquete) {
		this.usuarios.remove(usuario);
		
		reenvioListaUsuarios(paquete);
	}

	/** enviar mensajes a todos los usuarios **/
	public void reenvioDeMensajes(String jPaquete) {

		for (Usuario usuario : this.usuarios) {
			usuario.getOutStream().println(jPaquete);
		}
	}

	/** enviar la lista de usuarios conectados **/
	public void reenvioListaUsuarios(String paquete) {
		Gson json = new Gson();
		Paquete miPaq = json.fromJson(paquete, Paquete.class);
		miPaq.getAltaBaja().setUsersActuales(usuarios);
		String jPaquete = json.toJson(miPaq);
		for (Usuario usuario : this.usuarios) {
			usuario.getOutStream().println(jPaquete);
		}
	}

	/** enviar mensaje a un usuario **/
	public void enviarMensaje(String jPaquete) {
		/*
		 * for (Usuario cliente : this.usuarios) { if
		 * (cliente.getNickname().equals(receptor) && cliente != emisor) {
		 * emisor.getOutStream().println(emisor.toString() + " -> " + cliente.toString()
		 * + ": " + msj); cliente.getOutStream().println("(Privado)" + emisor.toString()
		 * + ": " + msj); } }
		 */
	}
}
