package inventario.controller;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import inventario.sessions.InventarioSBSLLocal;
import santamaria.controller.ReportController;
import santamaria.controller.SuperController;

public class InventarioController extends SuperController {

	@EJB(name = "ejb/InventarioSBSL")
	protected InventarioSBSLLocal inventarioLocal;

	public static final String SYSTEM_NAME = "INVENTARIO";
	protected static final String SYSTEM_RESOURCE_BUNDLE = "msg";

	// area de permisos del sistema
	private boolean perGeneral;
	
	public InventarioController() {
		super();
		setJdbcName("jdbc/santamaria");
		setFrmtRepo(ReportController.REPORT_TYPE_PDF);
		setRptURL("WEB-INF/reportes/");
		setSendLogo(true);
		setDestination(REPORT_PDF_DESTINATION_ATTACHMENT);
	}
	
	@PostConstruct
	public void init() {
		configPermisos();
	}
	
	public void configPermisos() {
		
	}
	
	public String generarNumero(String contador) {
		long correlativo = inventarioLocal.generarNumero(contador);
		String numero = String.format("%02d", correlativo);
		return numero;
	}
	
	public String generarCorrPlanta(String contador) {
		long correlativo = inventarioLocal.generarNumero(contador);
		String numero = String.format("%04d", correlativo);
		return numero;
	}
	
	public String generarCorrMovimiento(String contador) {
		Calendar cal = Calendar.getInstance();
		long annio = cal.get(Calendar.YEAR);
		long correlativo = inventarioLocal.generarNumero(contador + annio);
		String numero = contador + annio + "-" + String.format("%06d", correlativo);
		return numero;
	}

	public boolean isPerGeneral() {
		return perGeneral;
	}

	public void setPerGeneral(boolean perGeneral) {
		this.perGeneral = perGeneral;
	}
}
