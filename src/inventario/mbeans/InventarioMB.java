package inventario.mbeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import inventario.controller.InventarioController;
import inventario.entities.InvEstado;
import inventario.entities.InvInventario;
import inventario.entities.InvInventarioDet;
import inventario.entities.InvTipoMovimiento;
import inventario.util.objects.ObjMovimiento;

@ManagedBean
@ViewScoped
public class InventarioMB extends InventarioController {

	@ManagedProperty(value = "#{loginMB}")
	LoginMB loginMB = new LoginMB();

	private String inventario = null;
	private List<ObjMovimiento> prodSelectedList = new ArrayList<ObjMovimiento>();
	private ObjMovimiento prodSelected = new ObjMovimiento();
	private ObjMovimiento objMovimiento;
	private List<InvInventarioDet> inventarioDetList = new ArrayList<InvInventarioDet>();
	private List<InvInventarioDet> inventarioDetFilterList = new ArrayList<InvInventarioDet>();
	private String observacion = null;
	private int ajuste;

	public InventarioMB() {
		super();
	}

	public void clean() {
		inventario = null;
		prodSelectedList = new ArrayList<ObjMovimiento>();
		prodSelected = new ObjMovimiento();
		objMovimiento = new ObjMovimiento();
		inventarioDetList = new ArrayList<InvInventarioDet>();
		observacion = null;
		ajuste = 0;
	}

	@SuppressWarnings("unchecked")
	public List<String> autoCompleteInventario(String query) {
		List<String> inventarios = new ArrayList<String>();
		List<InvInventario> inventarioAll = new ArrayList<InvInventario>();
		inventarioAll = (List<InvInventario>) inventarioLocal.findByCondition(
				InvInventario.class, "o.regActivo = 1", "o.invNombre");
		for (int i = 0; i < inventarioAll.size(); i++) {
			InvInventario inventario = inventarioAll.get(i);
			if (inventario.getInvNombre().toLowerCase()
					.startsWith(query.toLowerCase())) {
				inventarios.add(inventario.getInvNombre());
			}
		}
		return inventarios;
	}

	public void onEliminarProducto() {
		boolean result = false;
		try {
			Iterator<ObjMovimiento> iter = prodSelectedList.iterator();
			while (iter.hasNext()) {
				ObjMovimiento obj = iter.next();
				if (objMovimiento.getProducto().getInvdId() == obj
						.getProducto().getInvdId()) {
					iter.remove();
					result = true;
				}
			}
			if (result) {
				objMovimiento = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addError(new FacesMessage(
					"Ocurrio un error al momento de eliminar el producto"));
		}
	}

	public void onAgregarProducto() {
		boolean result = false;
		try {
			if (prodSelected.getCantidad() > 0) {
				Iterator<ObjMovimiento> iter = prodSelectedList.iterator();
				while (iter.hasNext()) {
					ObjMovimiento obj = iter.next();
					if (prodSelected.getProducto().getInvdId() == obj
							.getProducto().getInvdId()) {
						result = true;
					}
				}
				if (result) {
					addWarn(new FacesMessage("Producto seleccionado ya fue agregado anteriormente"));
					prodSelected = new ObjMovimiento();
				} else {
					prodSelected.setUsuario(getLoginMB().getObjAppsSession()
							.getUsuario());
					prodSelectedList.add(prodSelected);
					prodSelected = new ObjMovimiento();
					getInventarioDetList();
				}
			} else {
				addWarn(new FacesMessage("Debe digitar la cantidad"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void onAgregarProductoSalida() {
		boolean result = false;
		try {
			if (prodSelected.getCantidad() > 0) {
				Iterator<ObjMovimiento> iter = prodSelectedList.iterator();
				while (iter.hasNext()) {
					ObjMovimiento obj = iter.next();
					if (prodSelected.getProducto().getInvdId() == obj
							.getProducto().getInvdId()) {
						result = true;
					}
				}
				if (result) {
					addWarn(new FacesMessage("Producto seleccionado ya fue agregado anteriormente"));
					prodSelected = new ObjMovimiento();
				} else {
					prodSelected.setUsuario(getLoginMB().getObjAppsSession()
							.getUsuario());
					prodSelectedList.add(prodSelected);
					prodSelected = new ObjMovimiento();
					getInventarioDetList();
				}
			} else {
				addWarn(new FacesMessage("Debe digitar la cantidad"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void onGuardarSuma() {
		try {
			if (prodSelectedList.size() > 0) {
				InvTipoMovimiento tipoMov = (InvTipoMovimiento) inventarioLocal
						.findByPk(InvTipoMovimiento.class, 5L);
				InvEstado estado = (InvEstado) inventarioLocal.findByPk(
						InvEstado.class, 3L);
				String correlativo = generarCorrMovimiento("ENT");
				inventarioLocal.sumarAlInventario(prodSelectedList, tipoMov,
						correlativo, estado, observacion);
				addMessage(new FacesMessage("Movimiento guardado con exito"));
				clean();
			} else {
				addWarn(new FacesMessage("Debe seleccionar uno o más productos"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addError(new FacesMessage(
					"Ocurrio un error al guardar el movimiento"));
		}
	}
	
	public void onGuardarResta() {
		try {
			if (prodSelectedList.size() > 0) {
				InvTipoMovimiento tipoMov = (InvTipoMovimiento) inventarioLocal
						.findByPk(InvTipoMovimiento.class, 6L);
				InvEstado estado = (InvEstado) inventarioLocal.findByPk(
						InvEstado.class, 3L);
				String correlativo = generarCorrMovimiento("SAL");
				inventarioLocal.restarAlInventario(prodSelectedList, tipoMov,
						correlativo, estado, observacion);
				addMessage(new FacesMessage("Movimiento guardado con exito"));
				clean();
			} else {
				addWarn(new FacesMessage("Debe seleccionar uno o más productos"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addError(new FacesMessage(
					"Ocurrio un error al guardar el movimiento"));
		}
	}
	
	public void onGuardarAjuste() {
		InvTipoMovimiento tipoMov = null;
		try {
			if (prodSelectedList.size() > 0) {
				if (ajuste == 1) {
					tipoMov = (InvTipoMovimiento) inventarioLocal
							.findByPk(InvTipoMovimiento.class, 7L);
				} else if (ajuste == 2) {
					tipoMov = (InvTipoMovimiento) inventarioLocal
							.findByPk(InvTipoMovimiento.class, 8L);
				}
				InvEstado estado = (InvEstado) inventarioLocal.findByPk(
						InvEstado.class, 3L);
				String correlativo = generarCorrMovimiento("AJU");
				if (ajuste == 1) {
					inventarioLocal.sumarAjuste(prodSelectedList, tipoMov, correlativo, estado, observacion);
				} else if (ajuste == 2) {
					inventarioLocal.restarAjuste(prodSelectedList, tipoMov, correlativo, estado, observacion);
				}
				addMessage(new FacesMessage("Movimiento guardado con exito"));
				clean();
			} else {
				addWarn(new FacesMessage("Debe seleccionar uno o más productos"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addError(new FacesMessage(
					"Ocurrio un error al guardar el movimiento"));
		}
	}
	
	public boolean validarSalida() {
		boolean result = true;
		return result;
	}

	public String getInventario() {
		return inventario;
	}

	public void setInventario(String inventario) {
		this.inventario = inventario;
	}

	public List<ObjMovimiento> getProdSelectedList() {
		return prodSelectedList;
	}

	public void setProdSelectedList(List<ObjMovimiento> prodSelectedList) {
		this.prodSelectedList = prodSelectedList;
	}

	public ObjMovimiento getProdSelected() {
		return prodSelected;
	}

	public void setProdSelected(ObjMovimiento prodSelected) {
		this.prodSelected = prodSelected;
	}

	public ObjMovimiento getObjMovimiento() {
		return objMovimiento;
	}

	public void setObjMovimiento(ObjMovimiento objMovimiento) {
		this.objMovimiento = objMovimiento;
	}

	@SuppressWarnings("unchecked")
	public List<InvInventarioDet> getInventarioDetList() throws Exception {
		// System.out.println("INVENTARIO " + inventario);
		if (inventario != null) {
			inventarioDetList = new ArrayList<InvInventarioDet>();
			inventarioDetList = (List<InvInventarioDet>) inventarioLocal
					.findByCondition(InvInventarioDet.class,
							"o.invInventario.invNombre = '" + inventario + "' and o.regActivo = 1",
							"o.invPlanta.planNombre");
			// System.out.println(inventarioDetList.size());
		} else {
			inventarioDetList = new ArrayList<InvInventarioDet>();
		}
		// inventarioDetList = (List<InvInventarioDet>)
		// inventarioLocal.findAll(InvInventarioDet.class);
		return inventarioDetList;
	}

	public void setInventarioDetList(List<InvInventarioDet> inventarioDetList) {
		this.inventarioDetList = inventarioDetList;
	}

	public List<InvInventarioDet> getInventarioDetFilterList() {
		return inventarioDetFilterList;
	}

	public void setInventarioDetFilterList(
			List<InvInventarioDet> inventarioDetFilterList) {
		this.inventarioDetFilterList = inventarioDetFilterList;
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getAjuste() {
		return ajuste;
	}

	public void setAjuste(int ajuste) {
		this.ajuste = ajuste;
	}
}
