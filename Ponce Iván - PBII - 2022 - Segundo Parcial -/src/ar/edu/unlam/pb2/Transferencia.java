package ar.edu.unlam.pb2;

public class Transferencia extends Monetaria implements Rechazable, Alertable{
	
	String numeroTransaccion;
	String cuentaDestino;
	double monto;
	
	public Transferencia(Dispositivo dispositivo,Cliente cliente, Banco banco, String numeroTransaccion, String cuentaDestino, double monto) {
		super(dispositivo, cliente,banco);
		this.numeroTransaccion = numeroTransaccion;
		this.cuentaDestino = cuentaDestino;
		this.monto = monto;
		this.getBanco().validarScore(this);
	}

	public Transferencia(Dispositivo dispositivo, Cliente cliente, Banco banco) {
		super(dispositivo, cliente, banco);
	}
	@Override 		// interfaz Rechazable
	public Boolean monitorear(int i) throws FraudeException {
		confirmarSiFueFraude();
		marcarComoCasoSospechoso();
		monitorear();
				
		return false;
	}


			@Override //interfaz alertable
			public void marcarComoCasoSospechoso() {
				if(this.getCliente().getScore()>60 && this.getCliente().getScore()<79) {
					this.getBanco().ingresarAnalizable(this);
					System.out.println("El caso esta siendo analizado.");
				}
				System.out.println("Transaccion Aprobada");
				monitorear();
			}
			
			@Override//interfaz alertable
			public void confirmarSiFueFraude() {
				try {
					if(this.getCliente().getScore()>=80) {
						throw new FraudeException("La transaccion fue fraudulenta !!!");
					}
				}catch (Exception e) {
					this.getBanco().ingresarFraude(this.getCliente());
					System.out.println("Verificar la lista de fraudes");
					e.printStackTrace();
				}
			}
	
	
				

}
