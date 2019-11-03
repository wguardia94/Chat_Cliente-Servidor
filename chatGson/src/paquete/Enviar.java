package paquete;

import java.util.Scanner;

import com.google.gson.Gson;

import paqueteclases.Mensaje;
import paqueteclases.Paquete;

public class Enviar implements Runnable {

	private Servidor servidor;
	private Usuario usuario;

	public Enviar(Servidor servidor, Usuario usuario) {
		this.servidor = servidor;
		this.usuario = usuario;
		// this.servidor.reenvioListaUsuarios();
	}

	public void run() {
		String jPaquete = "";

		Scanner scanner = new Scanner(this.usuario.getInputStream());
		while (scanner.hasNextLine()) {
			jPaquete = scanner.nextLine();
			Gson json = new Gson();

			Paquete miPaq = json.fromJson(jPaquete, Paquete.class);

			switch (miPaq.getTipo()) {
			case "paqueteclases.Mensaje":
				Mensaje msj = (Mensaje) miPaq.getMensaje();
				if (msj.getTipo() == 'B') {

					servidor.reenvioDeMensajes(jPaquete);
				} else {
					servidor.enviarMensaje(jPaquete);
				}

				break;

			case "UsuarioAB":

				break;

			}
		}
		servidor.eliminarUsuario(usuario, jPaquete);
		scanner.close();
	}
}
