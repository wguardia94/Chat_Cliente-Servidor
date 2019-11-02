package paquete;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.google.gson.Gson;

import paqueteclases.Mensaje;
import paqueteclases.Paquete;
import paqueteclases.UsuarioAB;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ClientGui extends Thread {
	public JTextField text_input = new JTextField();
	JTextArea textArea;
	private Thread read;
	private String ipServidor;
	private int PUERTO;
	private String nombre;

	private BufferedReader input;
	private PrintWriter output;
	private Socket servidor;
	private JFrame jframe_chat;
	JList listUsuarios;
	DefaultListModel modelo = new DefaultListModel();

	public ClientGui(String ipServidor, int puerto) {
		this.ipServidor = "localhost";
		this.PUERTO = 3000;
		this.nombre = "Nombre";

		jframe_chat = new JFrame("CHAT MULTIUSUARIO");
		jframe_chat.setSize(693, 500);
		jframe_chat.setResizable(false);
		jframe_chat.setLocationRelativeTo(null);
		jframe_chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/** Input de los mensajes **/
		text_input.setBounds(10, 377, 475, 35);
		text_input.setMargin(new Insets(6, 6, 6, 6));

		/** Boton de enviar **/
		JButton btn_enviar = new JButton("Enviar");

		btn_enviar.setBounds(20, 423, 100, 29);
		jframe_chat.getContentPane().add(btn_enviar);

		jframe_chat.getContentPane().add(text_input);

		/** Boton para desconectarse **/
		JButton btn_desconectar = new JButton("Desconectar");
		jframe_chat.getContentPane().add(btn_desconectar);
		btn_desconectar.setBounds(524, 377, 130, 35);

		/** Evento para enviar los msj apretando enter **/
		text_input.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					sendMessage();
			}
		});

		/** onClick en boton enviar **/
		btn_enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				sendMessage();
			}
		});

		jframe_chat.getContentPane().setLayout(null);

		JLabel lblConectados = new JLabel("Conectados");
		lblConectados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConectados.setBounds(545, 16, 109, 19);
		jframe_chat.getContentPane().add(lblConectados);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 465, 355);
		jframe_chat.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Dialog", Font.PLAIN, 17));
		textArea.setEditable(false);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(508, 41, 156, 325);
		jframe_chat.getContentPane().add(scrollPane_1);

		listUsuarios = new JList();
		scrollPane_1.setViewportView(listUsuarios);

		jframe_chat.setVisible(true);

		/** onClickdesconectar **/
		btn_desconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				read.interrupt();
				agregarAreaChat("Coneccion cerrada");
				agregarAreaChat("Sin conexion");
				output.println("Cerro sesion");
				output.close();
			}
		});

		iniciar();

	}

	/**
	 * Metodo para enviar mensajes
	 *
	 * @param ini
	 **/
	public void sendMessage() {

		String msj = text_input.getText().trim();
		if (msj.equals("")) {
			return;
		}
		Mensaje miMensaje = new Mensaje('B', nombre, "", msj);
		Paquete miPaquete = new Paquete(miMensaje.getClass().getName(), miMensaje);

		Gson json = new Gson();

		String jPaquete = json.toJson(miPaquete);

		output.println(jPaquete);

		text_input.setText("");
	}

	public static void main(String[] args) throws Exception {
		new ClientGui("localhost", 3000);
	}

	public void iniciar() {

		try {
			nombre = JOptionPane.showInputDialog("Ingrese Nick");
			servidor = new Socket(ipServidor, PUERTO);
			agregarAreaChat("Conectado a " + servidor.getRemoteSocketAddress());
			input = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
			/***/
			agregarAreaChat(nombre + " Conectado ");
			/***/
			output = new PrintWriter(servidor.getOutputStream(), true);

			/**********************************************************************
			 * Manda el nombre al servidor * Crea un nuevo hilo para escuchar lo que le
			 * manda el servidor
			 *********************************************************************/
			read = new Read();
			read.start();

			/***/
			altaUser();

			/***/
		} catch (Exception ex) {
			agregarAreaChat("No se pudo conectar con el servidor");
			JOptionPane.showMessageDialog(jframe_chat, ex.getMessage());
		}

	}

	public void altaUser() {

		UsuarioAB userA = new UsuarioAB(nombre, 'A');
		Paquete miPaquete = new Paquete(userA.getClass().getName(), userA);

		Gson json = new Gson();

		String jPaquete = json.toJson(miPaquete);
		System.out.println("altaUser");
		output.println(jPaquete);
	}

	private void agregarAreaChat(String msg) {
		textArea.append("\n" + msg);
	}

	/** Clase para leer lo que envia el servidor **/
	class Read extends Thread {
		public void run() {
			String jPaquete;
			Gson json;
			while (!Thread.currentThread().isInterrupted()) {
				try {
					jPaquete = input.readLine();

					json = new Gson();

					Paquete miPaq = json.fromJson(jPaquete, Paquete.class);
System.out.print(miPaq.getTipo());
					switch (miPaq.getTipo()) {
					case "paqueteclases.UsuarioAB":
						UsuarioAB user = (UsuarioAB) miPaq.getAltaBaja();
						if (user.getTipo() == 'A') {
							agregarAreaChat(user.getNick() + "----se ah unido-----");
							modelo.removeAllElements();
						for(Usuario usr:user.getUsersActuales()) {
							System.out.println(usr.getNickname());
							modelo.addElement(usr.getNickname());
						}							
							listUsuarios.setModel(modelo);
						} else {
							agregarAreaChat(user.getNick() + "----ah dejado el chat-----");
							modelo.removeElement(user.getNick());
							listUsuarios.setModel(modelo);
						}
						break;

					case "paqueteclases.Mensaje":
						Mensaje mensaje = (Mensaje) miPaq.getMensaje();
						agregarAreaChat(mensaje.getNickOrigen() + "--Dice: " + mensaje.getMensaje());
						break;
					}

				} catch (IOException ex) {
					System.err.println("Error " + ex.toString());
				}
			}
		}
	}
}
