	package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Denunciable{
	
	private String nombre;
	private String cuit;
	private List<Dispositivo> dispositivos;
	private List<Transaccion> transacciones;
	private double saldo;
	private int score;
	
	
	public Cliente() {}
	
	public Cliente( String cuit, String nombre, double saldo) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.saldo = saldo;
		this.dispositivos = new ArrayList<Dispositivo>();
		this.transacciones = new ArrayList<Transaccion>();
		this.score = 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void registrarTransaccion(Transaccion transaccion) {
		if(this.transacciones==null) {
			this.transacciones = new ArrayList<Transaccion>();
		}
		this.transacciones.add(transaccion);
	}
	
	public boolean estaLaTransaccionVacia() {
		return this.transacciones.isEmpty();
	}
	
	public int cantidadDeTransacciones() {
		return this.transacciones.size();
	}
	
	public Transaccion ultimaTransaccion() {
		int tamano = cantidadDeTransacciones();
		return this.transacciones.get(tamano -1);
		
	}
	
	public void registrarDispositivo(Dispositivo dispositivo) {
		if(this.dispositivos==null) {
			
		}
		this.dispositivos.add(dispositivo);
	}
	
	public boolean dispositivosVacia() {
		return this.dispositivos.isEmpty();	}
	
	public boolean encontrarDispositivo(Denunciable denunciable) {
		for (Dispositivo dispositivo : dispositivos) {
			if(dispositivo.equals(denunciable)){
				return true;
			}
		}
		return false;
	}
	
	

}
