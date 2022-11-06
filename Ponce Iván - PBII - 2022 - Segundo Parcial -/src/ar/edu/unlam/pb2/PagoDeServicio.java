package ar.edu.unlam.pb2;

public class PagoDeServicio extends Monetaria implements Monitoreable, Alertable{

	private String numeroDeTransaccion;
	private String servicio;
	private double monto;
	

	public PagoDeServicio(Dispositivo dispositivo,Cliente cliente, Banco banco, String nroDeTransaccion, String servicio, double monto) {
		super(dispositivo,cliente, banco); 
		this.numeroDeTransaccion = nroDeTransaccion;
		this.servicio = servicio;
		this.monto = monto;
		this.getBanco().validarScore(this);
	}
	
	@Override //InterfazAlertable
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
	
	
	
	public String getNroDeTransaccion() {
		return numeroDeTransaccion;
	}



	public void setNroDeTransaccion(String nroTx) {
		this.numeroDeTransaccion = nroTx;
	}



	public String getServicio() {
		return servicio;
	}



	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public double getMonto() {
		return monto;
	}

    public void setMonto(double monto) {
		this.monto = monto;
	}




	
	
	

}
