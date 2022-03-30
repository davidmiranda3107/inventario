package inventario.mbeans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import santamaria.util.Utils;

import inventario.controller.InventarioController;
import inventario.entities.InvGrupo;
import inventario.entities.InvInventario;
import inventario.entities.InvPlanta;
import inventario.util.objects.ObjInventario;

@ManagedBean
@ViewScoped
public class PlantaMB extends InventarioController {

	@ManagedProperty(value = "#{loginMB}")
	LoginMB loginMB = new LoginMB();

	private InvPlanta invPlanta = new InvPlanta();
	private InvPlanta invPlantaSelected;
	private List<InvPlanta> invPlantaList = null;
	private boolean editar = false;
	private boolean lista = true;
	private boolean nuevo = false;
	private List<InvGrupo> grupoList = null;

	public void clean() {
		invPlanta = new InvPlanta();
		invPlantaSelected = null;
		editar = false;
		nuevo = false;
	}

	@SuppressWarnings("unchecked")
	public void fillPlantas() {
		invPlantaList = new ArrayList<InvPlanta>();
		invPlantaList = (List<InvPlanta>) inventarioLocal.findByCondition(
				invPlanta.getClass(), "o.regActivo = 1", "o.planCodigo");
	}
	
	@SuppressWarnings("unchecked")
	public void fillGrupos() {
		grupoList = new ArrayList<InvGrupo>();
		grupoList = (List<InvGrupo>) inventarioLocal.findByCondition(InvGrupo.class, "o.regActivo = 1",
				"o.grpCodigo");
	}
	
	public void test() {
		//System.out.println("[GRUPO SELECCIONADO]" + invPlanta.getInvGrupo().getGrpNombre());
		invPlanta = new InvPlanta();
	}

	public boolean validarNuevaPlanta() {
		boolean resultado = true;
		int x = inventarioLocal.yaExiste(
				"santamaria.inv_planta",
				"plan_grp_id = " + invPlanta.getInvGrupo().getGrpId()
						+ " and reg_activo = 1 and plan_nombre = '"
						+ invPlanta.getPlanNombre() + "' and plan_origen = '" + invPlanta.getPlanOrigen() + "'");
		if (x > 0) {
			resultado = false;
		}
		return resultado;
	}
	
	public void actualizaPrecio() {
		if(invPlanta.getPlanPrecioUnitario() != null) {
			if(invPlanta.getPlanPrecioUnitario().compareTo(BigDecimal.ZERO) == 1) {
				BigDecimal iva = BigDecimal.ZERO;
				iva = invPlanta.getPlanPrecioUnitario().multiply(new BigDecimal("13"));
				iva = iva.divide(new BigDecimal("100"), 6,
						RoundingMode.HALF_UP);
				invPlanta.setPlanPrecioIva(iva);
				invPlanta.setPlanPrecio(invPlanta.getPlanPrecioUnitario().add(invPlanta.getPlanPrecioIva()));
			} else {
				addWarn(new FacesMessage("El precio unitario no es válido"));
			}
		} else {
			addWarn(new FacesMessage("Debe digitar precio unitario"));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onGuardar() {
		try {
			if (validarNuevaPlanta()) {
				//Lista de inventarios disponibles
				List<InvInventario> inventarios = new ArrayList<InvInventario>();
				inventarios = (List<InvInventario>) inventarioLocal.findAll(InvInventario.class);
				
				invPlanta.setFecCrea(Utils.getHoy());
				invPlanta.setUsuCrea(getLoginMB().getObjAppsSession().getUsuario().getUsuLogin());
				invPlanta.setRegActivo(BigDecimal.ONE);
				invPlanta.setPlanCodigo(invPlanta.getInvGrupo().getGrpCodigo() + generarCorrPlanta("PLAN_"+invPlanta.getInvGrupo().getGrpCodigo())+invPlanta.getPlanOrigen());
				inventarioLocal.insert(invPlanta);
				ObjInventario objInventario = new ObjInventario(invPlanta, inventarios, getLoginMB().getObjAppsSession().getUsuario());
				inventarioLocal.crearNuevoInventario(objInventario);
				addInfo(new FacesMessage("Planta guardada con exito"));
				invPlantaList = null;
				getInvPlantaList();
				clean();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addError(new FacesMessage("Ocurrio un error al momento de guardar la planta"));
		}
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

	public InvPlanta getInvPlanta() {
		return invPlanta;
	}

	public void setInvPlanta(InvPlanta invPlanta) {
		this.invPlanta = invPlanta;
	}

	public InvPlanta getInvPlantaSelected() {
		return invPlantaSelected;
	}

	public void setInvPlantaSelected(InvPlanta invPlantaSelected) {
		this.invPlantaSelected = invPlantaSelected;
	}

	public List<InvPlanta> getInvPlantaList() {
		return invPlantaList;
	}

	public void setInvPlantaList(List<InvPlanta> invPlantaList) {
		this.invPlantaList = invPlantaList;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public boolean isLista() {
		return lista;
	}

	public void setLista(boolean lista) {
		this.lista = lista;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public List<InvGrupo> getGrupoList() {
		if (grupoList == null) {
			fillGrupos();
		}
		return grupoList;
	}

	public void setGrupoList(List<InvGrupo> grupoList) {
		this.grupoList = grupoList;
	}

}
