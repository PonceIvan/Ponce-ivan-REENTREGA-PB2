package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	
	private List<Denunciable> fraudes;
	private List<Alertable> analizar;
	
	public Banco() {
		this.fraudes = new ArrayList<Denunciable>();
	}
	
	public void ingresarFraude(Denunciable denunciable) {
		if(this.fraudes==null) {
			this.fraudes = new ArrayList<Denunciable>();
		}
		this.fraudes.add(denunciable);
	}
	
	public boolean fraudeVacio() {
		return this.fraudes.isEmpty();
	}
	
	public void ingresarAnalizable(Alertable alertable) {
		if(this.analizar==null) {
			this.analizar = new ArrayList<Alertable>();
		}
		this.analizar.add(alertable);
	}
	
	public void validarScore(Transaccion tx){
		Cliente cliente = tx.getCliente();
		int score = cliente.getScore();
		if(cuitEnFraude(cliente)) {
			score = score + 80;			
		} 
		if(ipImeiFraude(cliente)) {
			score = score + 80;
		}
		if(ultimaTxCambioContraseña(tx)) {
			score = score + 20;
		}
		if(transferenciaIgualASaldo(tx)) {
			score = score + 40;
		}
		if(dispositivoNuevo(tx)) {
			score = score + 20;
		}		
		cliente.setScore(score);
		System.out.println("El monitoreo fue existoso, El score de la transaccion fue de " + score);
		
	}
	
	

	

	private boolean cuitEnFraude(Cliente cliente) {
		for (Denunciable denunciable : fraudes) {
			if(denunciable instanceof Cliente) {
				if(((Cliente)denunciable).getCuit().equals(cliente.getCuit())) {
					return true;
				}
			}			
		}
	return false;	
	}
	
	private boolean ipImeiFraude(Cliente cliente) {
		for (Denunciable denunciable : fraudes) {
			if(cliente.encontrarDispositivo(denunciable)) {
				return true;
			}
			
		}
		return false;
	}
	
	private boolean ultimaTxCambioContraseña(Transaccion transaccion) {
		Cliente cliente = transaccion.getCliente();
		if(!cliente.estaLaTransaccionVacia()) {
			Transaccion lastTx = cliente.ultimaTransaccion();
			if(lastTx instanceof CambioContraseña && transaccion!=null) {
				return true;
			}
		}
		
		return false;
		
	}
	
private boolean transferenciaIgualASaldo(Transaccion transaccion) {
		Cliente cliente = transaccion.getCliente();
		if(transaccion instanceof Transferencia && transaccion instanceof Rechazable) {
			Transferencia tf = (Transferencia) transaccion;
			if(tf.monto == cliente.getSaldo()) {
				return true;
			}
		}
		
		return false;
	}

	private boolean dispositivoNuevo(Transaccion tx) {
		Cliente cliente = tx.getCliente();
		if(cliente.dispositivosVacia()) {
			return false;
		}
		return !cliente.encontrarDispositivo(tx.getDispositivo());
	}
	
	
	
}
