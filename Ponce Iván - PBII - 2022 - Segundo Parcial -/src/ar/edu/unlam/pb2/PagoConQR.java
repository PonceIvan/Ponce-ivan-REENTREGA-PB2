package ar.edu.unlam.pb2;

public class PagoConQR extends Monetaria implements Monitoreable, Alertable, Rechazable{
	
	private String numeroTransaccion;
	private double monto;
	
	//Constructores

	public PagoConQR(Dispositivo dispositivo, Cliente cliente, Banco banco, String numeroTransaccion, double monto) {
		super(dispositivo,cliente, banco); 
		this.numeroTransaccion = numeroTransaccion;
		this.monto = monto;
		this.getBanco().validarScore(this);
	}

	public PagoConQR(Dispositivo dispositivo, Cliente cliente, Banco banco) {
		super(dispositivo,cliente, banco); 
	}
	
	//Getters and Setters

	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	

	//Alertable

	@Override
	public void marcarComoCasoSospechoso() {
		if(this.getCliente().getScore()>60 && this.getCliente().getScore()<79) {
			this.getBanco().ingresarAnalizable(this);
			System.out.println("El caso se estara analizando.");
		}
		System.out.println("Transaccion Aprobada");
		monitorear();
	}
	
	@Override
	public void confirmarSiFueFraude() {
		try {
			if(this.getCliente().getScore()>=80) {
				throw new FraudeException("La transaccion resulto fraudulenta");
			}
		}catch (Exception e) {
			this.getBanco().ingresarFraude(this.getDispositivo());
			System.out.println("Chequear que la lista de fraudes tenga algo");
			e.printStackTrace();
		}
	}
	
		//Rechazable
		@Override
		public Boolean monitorear(int i) throws FraudeException {
			confirmarSiFueFraude();
			marcarComoCasoSospechoso();
			monitorear();
					
			return false;
		}
	

	
	
		
	

}
