package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.AssertionFailedError;

public class TestCases {
	

	@Test
	public void queSePuedaCrearUnCliente()  {
 
		String nombre = "Fulanito";
		String cuit = "1465465";
		Double saldo = 10000.0;
		Cliente cliente = new Cliente(cuit, nombre,saldo);
		assertNotNull(cliente);
	    assertTrue(cuit.equals(cliente.getCuit()) );
	 
	}
	
	@Test
	public void queSePuedaCrearUnDispositivo() {

 
		String sistemaOperativo = "Linux";
		String ip = "192.168.1.20";
	    String imei = "120984";
	    String localidad = "g.catan" ;
		String biometrico = "FOTOdeLaCARA.JPEG";
		Dispositivo dispositivo = new Compu(sistemaOperativo,localidad, ip);
		Dispositivo dispositivo2 = new DispositivoMovil(imei,biometrico);
		assertNotNull(dispositivo);
		assertNotNull(dispositivo2);

	}
	
	@Test
	public void queSePuedaMonitorearUnaExtraccion() {
	
		String sistemaOperativo = "Linux";
		String ip = "192.168.1.20";
       String localidad = "g.catan" ;
		String biometrico = "FOTOdeLaCARA.JPEG";
		Dispositivo dispositivo = new Compu(sistemaOperativo,localidad, ip);
		
		Banco banco = new Banco();
		String nombre = "Fulanito";
		String cuit = "1465465";
		Double saldo = 10000.0;
		Cliente cliente = new Cliente(cuit, nombre,saldo);
		
		Monitoreable extraccion = new Extraccion(dispositivo, cliente,banco,"123",100.2);
		extraccion.monitorear();
		assertEquals(cliente.getCuit(),"1465465" );
		
		
	}
	
	
	@Test
	public void queSePuedaMonitorearUnaTransferencia() {
	 
		Dispositivo dispositivo = new Compu("Linux", "casanova", "192.168.1.6");
		Cliente cliente = new Cliente("20456842455","FULANITO",400);
		Banco banco = new Banco();
		Monitoreable transferencia = new Transferencia(dispositivo, cliente, banco,"123", "51515",200.2);
		transferencia.monitorear();
        assertEquals("20456842455", cliente.getCuit() );
	}
	
	@Test	public void queSePuedaMonitorearUnPagoConQR() {
		
		Cliente cliente = new Cliente("Alfonso Pratguy","20384241752",2000);
		Dispositivo dispositivo = new Compu("Mac", "San justo", "192.168.1.6");
		Banco banco = new Banco();
		Monitoreable pagoConQr = new PagoConQR(dispositivo, cliente,banco, "123", 1234);
		pagoConQr.monitorear();
	    assertEquals(1, cliente.cantidadDeTransacciones() );
	}
	
	@Test
	public void queSePuedaMonitorearUnPagoDeServicio() {
		Banco banco = new Banco();
		Dispositivo dispositivo = new Compu("Mac", "192.168.1.1", "Ramos Mejia");
		Cliente cliente = new Cliente("Mario Pergolini","2065614285",4000);
		Monitoreable pagoDeServicio = new PagoDeServicio(dispositivo, cliente,banco,"123","Edenor",200.2);
		pagoDeServicio.monitorear();
		assertEquals(1, cliente.cantidadDeTransacciones() );
	}
	
	@Test
	public void queSePuedaMonitorearUnAltaDeUsuario() {
		Dispositivo dispositivo = new Compu("windows", "192.168.1.1", "Ciudad Evita");
		Cliente cliente = new Cliente("Pablo osmar","20456842455",7000);
		Banco banco = new Banco();
		String usuario= "usuario" ;
		String contraseña = "contraseña" ;
		Monitoreable altaUsuario = new AltaUsuario(dispositivo, cliente,banco,usuario, contraseña);
		altaUsuario.monitorear();
        assertEquals(1, cliente.cantidadDeTransacciones());
	}
	
	@Test
	public void queSePuedaMonitorearUnCambioDeContraseña() {
		
		Dispositivo dispositivo = new Compu("windows", "192.4.5.6", "Ituzaingo");
		Cliente cliente = new Cliente("Jimena","20456842455",6000);
		Banco banco = new Banco();
		Monitoreable cambio = new CambioContraseña(dispositivo, cliente,banco,"jorge","jorge123","jor123");
		cambio.monitorear();
	
		assertEquals(1, cliente.cantidadDeTransacciones());
	}
	
	@Test
	public void queElScoreDeUnaTransaccionRechazableSinAntecedentesDeCero() throws FraudeException {
		Dispositivo dispositivo = new Compu("windows", "150.2.3.1", "Ituzaingo");
		Banco banco = new Banco();
		Cliente cliente = new Cliente("Favio","20456842455",4000);
		Rechazable transferencia = new Transferencia(dispositivo, cliente, banco,"1234","3214",200);
		transferencia.monitorear(1);
		assertEquals(0, cliente.getScore());
		}
	
	@Test
	public void queUnaTransaccionAlertablePuedaSerMarcadaComoFraudulenta(){
		Dispositivo dispositivo = new Compu("windows", "34.4.1.1", "Moron");
		Banco banco = new Banco();
		Cliente cliente = new Cliente("Ximena","20456842455",4000);
		Monitoreable cambio = new CambioContraseña(dispositivo, cliente, banco,"luciana","usuario","contraseña");
		cambio.monitorear();
		Dispositivo dispositivo2 = new DispositivoMovil("1234", "foto de cara. JPEG");
		Alertable transferencia = new Transferencia(dispositivo2, cliente, banco, "124","321",4000);
		transferencia.confirmarSiFueFraude();
		assertFalse(banco.fraudeVacio());
	}
	
	@Test
	public void queElScoreDeUnaTransaccionRechazableConNuevoDispositivoDe20Puntos() throws FraudeException {
		Dispositivo dispositivo = new Compu("Linux", "192.168.1.1", "Ituzaingo");
		Banco banco = new Banco();
		Cliente cliente = new Cliente("jorge","20456842455",4000);
		Monitoreable altaUsuario = new AltaUsuario(dispositivo, cliente, banco,"jorge","1234");
		altaUsuario.monitorear();
		Dispositivo dispositivo2 = new DispositivoMovil("1234", "foto de cara. JPEG");
		Rechazable extraccion = new Extraccion(dispositivo2, cliente, banco, "123", 1231);
		extraccion.monitorear(1);
		assertEquals(20, cliente.getScore());
	}
	
	@Test
	public void QueUnaTransaccionDeMasDe60PuntosYMenosDeOchentaSeaAlertadaPeroAprobada() throws FraudeException{
	
		Dispositivo dispositivo = new Compu("Linux", "192.168.1.1", "ezeiza");
		Banco banco = new Banco();
		Cliente cliente = new Cliente("javier","204196842452",4000);
		cliente.setScore(5);
		Monitoreable cambio = new CambioContraseña(dispositivo, cliente, banco,"brian","usuario","contraseña");
		cambio.monitorear();
		Rechazable transferencia = new Transferencia(dispositivo, cliente, banco, "124","321",4000);
		transferencia.monitorear(1);
		assertEquals(65, cliente.getScore());		
		assertTrue(banco.fraudeVacio());
	}
	
	@Test
	public void queUnaTransaccionDeMasDe80PuntosLanceLaExcepcionFraudeException() throws FraudeException{
		Banco banco = new Banco();
		Dispositivo dispositivo = new Compu("windows", "192.150.4.2", "Ituzaingo");
		Cliente cliente = new Cliente("Ponce","20456842455",4000);
		cliente.setScore(5);
		Monitoreable cambio = new CambioContraseña(dispositivo, cliente, banco,"jorge","123","321");
		cambio.monitorear();
		Dispositivo dispositivo2 = new DispositivoMovil("352523", "foto de cara. JPEG");
		Alertable transferencia = new Transferencia(dispositivo2, cliente, banco, "124","321",4000);
		transferencia.confirmarSiFueFraude();
		assertEquals(85, cliente.getScore());		
		assertFalse(banco.fraudeVacio());
	}
	 

}
