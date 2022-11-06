package ar.edu.unlam.pb2;

public class Monetaria extends Transaccion implements Monitoreable{

	public Monetaria(Dispositivo dispositivo, Cliente cliente, Banco banco) {
		super(dispositivo, cliente, banco);
	}
	
	//Monitoreable
		@Override
		public void monitorear() {
			this.getCliente().registrarDispositivo(this.getDispositivo());
			this.getCliente().registrarTransaccion(this);
		}

}
