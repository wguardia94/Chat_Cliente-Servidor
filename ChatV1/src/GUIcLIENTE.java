import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIcLIENTE extends JFrame {

	private JPanel contentPane;
	String serverAddress;
	Scanner in;
	PrintWriter out;
	JFrame frame = new JFrame("Chatter");
	JTextField textField;
	JTextArea messageArea;

	/**
	 * Create the frame.
	 */
	public GUIcLIENTE(String serverAddress) {
		this.serverAddress = serverAddress;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(5, 237, 424, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(5, 11, 377, 202);
		contentPane.add(textArea);
		
		
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
		
	}

	private String obtenerName() {
		return "Willian";
	}

	private void run() throws IOException {
		try {
			Socket socket = new Socket(serverAddress, 59001);
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);

			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line.startsWith("SUBMITNAME")) {
					out.println(obtenerName());
				} else if (line.startsWith("NAMEACCEPTED")) {
					this.frame.setTitle("Chatter - " + line.substring(13));
					textField.setEditable(true);
				} else if (line.startsWith("MESSAGE")) {
					messageArea.append(line.substring(8) + "\n");
				}
			}
		} finally {
			frame.setVisible(false);
			frame.dispose();
		}
	}

	public static void main(String[] args) throws IOException {

		GUIcLIENTE frame = new GUIcLIENTE("localhost");
		frame.setVisible(true);
		frame.run();
	}

}
