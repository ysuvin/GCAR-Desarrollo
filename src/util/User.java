package util;

import java.io.Serializable;

public class User implements Serializable {
	 private String rut;
	 private String pass;
	 private String nombre1;
	 private String nombre2;
	 private String paterno;
	 private String materno;
	 private String mail;
	 private String anyo;
	// private String vezCursando;
	 private String edad;
	 private int tipo;
	 private boolean is_hashed;
	 
	public User(){
		 
	 }
	
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getAnyo() {
		return anyo;
	}

	public void setAnyo(String anyo) {
		this.anyo = anyo;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public boolean isIs_hashed() {
		return is_hashed;
	}

	public void setIs_hashed(boolean is_hashed) {
		this.is_hashed = is_hashed;
	}
	
}