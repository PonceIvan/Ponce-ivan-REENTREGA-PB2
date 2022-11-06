package ar.edu.unlam.pb2;

public class NoMonetaria extends Transaccion implements Monitoreable{

	public NoMonetaria(Dispositivo dispositivo, Cliente cliente, Banco banco) {
		super(dispositivo, cliente, banco);
	}
	
	//Monitoreable
		@Override
		public void monitorear() {
			this.getCliente().registrarDispositivo(this.getDispositivo());
			this.getCliente().registrarTransaccion(this);
		}

}
