import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {

	public static void main(String[] args) {

		Persona p = new Persona("pedro");
		Mensaje m = new Mensaje("Hola mundo");
		ArrayList<Persona> arrayP = new ArrayList<Persona>();

		arrayP.add(new Persona("Jose"));
		arrayP.add(new Persona("Andres"));

		Paquete mp = new Paquete(p.getClass().getName(), p);

		Gson json = new Gson();

		String j = json.toJson(mp);

		Paquete mp2 = json.fromJson(j, Paquete.class);

		switch (mp2.tipo) {
		case "Persona":
			Persona p4 = (Persona) mp2.getInfoPersona();
			System.out.println("Nombre persona " + p4.getNombre());
			break;

		case "Mensaje":
			Mensaje m2 = (Mensaje) mp2.getInfoMensaje();
			System.out.println("Mensaje " + m2.getMensaje());
			break;
		case "ArrayPersona":
			ArrayList<Persona> arrPersonas2 = (ArrayList<Persona>) mp2.getInfoArrayPersona();
			for (Persona pFor : arrPersonas2) {
				System.out.println("Persona de array " + pFor.getNombre());

			}
			break;
		}

////////////////////////////////////////////////////
		mp = new Paquete(m.getClass().getName(), m);

		j = json.toJson(mp);

		mp2 = json.fromJson(j, Paquete.class);

		switch (mp2.tipo) {
		case "Persona":
			Persona p4 = (Persona) mp2.getInfoPersona();
			System.out.println("Nombre persona " + p4.getNombre());
			break;

		case "Mensaje":
			Mensaje m2 = (Mensaje) mp2.getInfoMensaje();
			System.out.println("Mensaje " + m2.getMensaje());
			break;

		case "ArrayPersona":
			ArrayList<Persona> arrPersonas2 = (ArrayList<Persona>) mp2.getInfoArrayPersona();
			for (Persona pFor : arrPersonas2) {
				System.out.println("Persona de array " + pFor.getNombre());

			}
			break;
		}

		///////////////////////////////////////////

		mp = new Paquete("ArrayPersona", arrayP);

		j = json.toJson(mp);
		mp2 = json.fromJson(j, Paquete.class);

		switch (mp2.tipo) {
		case "Persona":
			Persona p4 = (Persona) mp2.getInfoPersona();
			System.out.println("Nombre persona " + p4.getNombre());
			break;

		case "Mensaje":
			Mensaje m2 = (Mensaje) mp2.getInfoMensaje();
			System.out.println("Mensaje " + m2.getMensaje());
			break;

		case "ArrayPersona":
			ArrayList<Persona> arrPersonas2 = (ArrayList<Persona>) mp2.getInfoArrayPersona();
			for (Persona pFor : arrPersonas2) {
				System.out.println("Persona de array " + pFor.getNombre());

			}
			break;

		}

	}

}
