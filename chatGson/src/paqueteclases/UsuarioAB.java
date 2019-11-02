package paqueteclases;

import java.util.ArrayList;

import paquete.Usuario;

public class UsuarioAB {
ArrayList<Usuario> usersActuales=new ArrayList<Usuario>();
String nick;
char tipo;
public String getNick() {
	return nick;
}
public ArrayList<Usuario> getUsersActuales() {
	return usersActuales;
}
public void setUsersActuales(ArrayList<Usuario> usersActuales) {
	this.usersActuales = usersActuales;
}
public void setNick(String nick) {
	this.nick = nick;
}
public char getTipo() {
	return tipo;
}
public void setTipo(char tipo) {
	this.tipo = tipo;
}
public UsuarioAB(String nick, char tipo) {
	super();
	this.nick = nick;
	this.tipo = tipo;
}




}
