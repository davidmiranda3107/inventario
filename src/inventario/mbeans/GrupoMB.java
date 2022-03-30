package inventario.mbeans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import santamaria.util.Utils;

import inventario.controller.InventarioController;
import inventario.entities.InvGrupo;

@ManagedBean
@ViewScoped
public class GrupoMB extends InventarioController {

	@ManagedProperty(value = "#{loginMB}")
	LoginMB loginMB = new LoginMB();

	private InvGrupo invGrupo = new InvGrupo();
	private InvGrupo invGrupoSelected;
	private List<InvGrupo> invGrupoList = null;
	private boolean editar = false;
	private boolean lista = true;
	private boolean nuevo = false;

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public GrupoMB() {
		super();
	}

	public void clean() {
		invGrupo = new InvGrupo();
		invGrupoSelected = null;
		editar = false;
		nuevo = false;
	}

	@SuppressWarnings("unchecked")
	public void fillGrupos() {
		invGrupoList = new ArrayList<InvGrupo>();
		invGrupoList = (List<InvGrupo>) inventarioLocal.findByCondition(invGrupo.getClass(), "o.regActivo = 1",
				"o.grpCodigo");
	}

	public void onGuardar() {
		try {
			if (validarNuevoGrupo()) {
				invGrupo.setFecCrea(Utils.getHoy());
				invGrupo.setUsuCrea(getLoginMB().getObjAppsSession().getUsuario().getUsuLogin());
				invGrupo.setRegActivo(BigDecimal.ONE);
				invGrupo.setGrpCodigo(generarNumero("GRP_ID"));
				invGrupo.setGrpId(Long.valueOf(invGrupo.getGrpCodigo()));
				inventarioLocal.insert(invGrupo);
				addInfo(new FacesMessage("Grupo guardado con exito"));
				invGrupoList = null;
				getInvGrupoList();
				clean();
			} else {
				addWarn(new FacesMessage("Grupo ya existe"));
				invGrupo = new InvGrupo();
				RequestContext.getCurrentInstance().execute("PF('grupoDialog').show()");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addError(new FacesMessage("Ocurrio un error al momento de guardar el grupo"));
		}
	}

	public boolean validarNuevoGrupo() {
		boolean resultado = true;
		int x = inventarioLocal.yaExiste("santamaria.inv_grupo",
				"grp_nombre = '" + invGrupo.getGrpNombre() + "' and reg_activo = 1");
		if (x > 0) {
			resultado = false;
		}
		return resultado;
	}
	
	public void onEditar() {
		try {
			invGrupo.setFecModi(Utils.getHoy());
			invGrupo.setUsuModi(getLoginMB().getObjAppsSession().getUsuario().getUsuLogin());
			inventarioLocal.update(invGrupo);
			addInfo(new FacesMessage("Grupo guardado con exito"));
			invGrupoList = null;
			getInvGrupoList();
			clean();
		} catch (Exception ex) {
			ex.printStackTrace();
			addError(new FacesMessage("Ocurrio un error al momento de guardar el grupo"));
		}
	}
	
	public void onEliminar() {
		try {
			invGrupo.setFecModi(Utils.getHoy());
			invGrupo.setUsuModi(getLoginMB().getObjAppsSession().getUsuario().getUsuLogin());
			invGrupo.setRegActivo(BigDecimal.ZERO);
			inventarioLocal.update(invGrupo);
			addInfo(new FacesMessage("Grupo eliminado con exito"));
			invGrupoList = null;
			getInvGrupoList();
			clean();
		} catch (Exception ex) {
			ex.printStackTrace();
			addError(new FacesMessage("Ocurrio un error al momento de eliminar el grupo"));
		}
	}

	public InvGrupo getInvGrupo() {
		return invGrupo;
	}

	public void setInvGrupo(InvGrupo invGrupo) {
		this.invGrupo = invGrupo;
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

	public InvGrupo getInvGrupoSelected() {
		return invGrupoSelected;
	}

	public void setInvGrupoSelected(InvGrupo invGrupoSelected) {
		this.invGrupoSelected = invGrupoSelected;
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

	public List<InvGrupo> getInvGrupoList() {
		if (invGrupoList == null) {
			fillGrupos();
		}
		return invGrupoList;
	}

	public void setInvGrupoList(List<InvGrupo> invGrupoList) {
		this.invGrupoList = invGrupoList;
	}
}
