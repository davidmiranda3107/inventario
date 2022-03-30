package inventario.mbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import inventario.controller.InventarioController;
import inventario.entities.InvGrupo;
import inventario.entities.InvInventario;
import inventario.entities.InvInventarioDet;
import inventario.entities.InvInventarioMov;
import inventario.entities.InvPlanta;
import inventario.entities.InvTipoMovimiento;

@ManagedBean
@ViewScoped
public class ConsultaMB extends InventarioController {

	private List<InvInventario> inventarioFilterList = null;
	private List<InvGrupo> grupoFilterList = null;
	private List<InvPlanta> productoFilterList = null;
	private String inventario;
	private String grupo;
	private String producto;
	private List<InvInventarioDet> productoList;
	private boolean mostrarTabla = false;
	private String codigo;
	private InvInventarioDet prodSelected;
	private String movimiento;
	private Date fecDesde;
	private Date fecHasta;
	private List<InvInventarioMov> movimientoList;
	private InvInventarioMov movSelected;

	public ConsultaMB() {
		super();
	}

	public void clean() {
		productoList = null;
		producto = null;
		grupo = null;
		inventario = null;
		mostrarTabla = false;
		movimiento = null;
		fecDesde = null;
		fecHasta = null;
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

	@SuppressWarnings("unchecked")
	public List<String> autoCompleteGrupo(String query) {
		List<String> grupos = new ArrayList<String>();
		List<InvGrupo> gruposAll = new ArrayList<InvGrupo>();
		gruposAll = (List<InvGrupo>) inventarioLocal.findByCondition(
				InvGrupo.class, "o.regActivo = 1", "o.grpNombre");
		for (int i = 0; i < gruposAll.size(); i++) {
			InvGrupo grupo = gruposAll.get(i);
			if (grupo.getGrpNombre().toLowerCase()
					.startsWith(query.toLowerCase())) {
				grupos.add(grupo.getGrpNombre());
			}
		}
		return grupos;
	}

	@SuppressWarnings("unchecked")
	public List<String> autoCompleteProducto(String query) {
		List<String> productos = new ArrayList<String>();
		List<InvPlanta> productosAll = new ArrayList<InvPlanta>();
		productosAll = (List<InvPlanta>) inventarioLocal.findByCondition(
				InvPlanta.class, "o.regActivo = 1", "o.planNombre");
		for (int i = 0; i < productosAll.size(); i++) {
			InvPlanta producto = productosAll.get(i);
			if (producto.getPlanNombre().toLowerCase()
					.startsWith(query.toLowerCase())) {
				productos.add(producto.getPlanCodigo() + " - "
						+ producto.getPlanNombre());
			}
			if (producto.getPlanCodigo().toLowerCase()
					.startsWith(query.toLowerCase())) {
				productos.add(producto.getPlanCodigo() + " - "
						+ producto.getPlanNombre());
			}
		}
		return productos;
	}

	@SuppressWarnings("unchecked")
	public List<String> autoCompleteMov(String query) {
		List<String> movimientos = new ArrayList<String>();
		List<InvTipoMovimiento> movimientosAll = new ArrayList<InvTipoMovimiento>();
		movimientosAll = (List<InvTipoMovimiento>) inventarioLocal
				.findByCondition(InvTipoMovimiento.class, "o.regActivo = 1",
						"o.tipNombre");
		for (int i = 0; i < movimientosAll.size(); i++) {
			InvTipoMovimiento tipo = movimientosAll.get(i);
			if (tipo.getTipNombre().toLowerCase()
					.startsWith(query.toLowerCase())) {
				movimientos.add(tipo.getTipNombre());
			}
		}
		return movimientos;
	}

	@SuppressWarnings("unchecked")
	public void buscarProductoxInventario() {
		String query = null;
		boolean seguir = false;
		mostrarTabla = true;
		if (inventario != null) {
			seguir = true;
			query = "o.invInventario.invNombre = '" + inventario + "'";
		}
		if (grupo != null) {
			if (seguir) {
				query = query + " and o.invPlanta.invGrupo.grpNombre = '"
						+ grupo + "'";
			} else {
				seguir = true;
				query = "o.invPlanta.invGrupo.grpNombre = '" + grupo + "'";
			}
		}
		if (producto != null) {
			if (seguir) {
				query = query + " and o.invPlanta.planCodigo = '"
						+ producto.substring(0, 7) + "'";
			} else {
				seguir = true;
				query = "o.invPlanta.planCodigo = '" + producto.substring(0, 7)
						+ "'";
			}
		}
		if (seguir) {
			query = query + " and o.regActivo = 1";
			// System.out.println("[QUERY] " + query);
			productoList = new ArrayList<InvInventarioDet>();
			productoList = (List<InvInventarioDet>) inventarioLocal
					.findByCondition(InvInventarioDet.class, query,
							"o.invPlanta.planNombre");
		} else {
			query = null;
			productoList = new ArrayList<InvInventarioDet>();
		}
	}

	@SuppressWarnings("unchecked")
	public void buscarProducto() {
		String query = null;
		boolean seguir = false;
		mostrarTabla = true;
		if (inventario != null) {
			seguir = true;
			query = "o.invInventario.invNombre = '" + inventario + "'";
		}
		if (grupo != null) {
			if (seguir) {
				query = query + " and o.invPlanta.invGrupo.grpNombre = '"
						+ grupo + "'";
			} else {
				seguir = true;
				query = "o.invPlanta.invGrupo.grpNombre = '" + grupo + "'";
			}
		}
		if (producto != null) {
			if (seguir) {
				query = query + " and o.invPlanta.planNombre = '" + producto
						+ "'";
			} else {
				seguir = true;
				query = "o.invPlanta.planNombre = '" + producto + "'";
			}
		}
		if (seguir) {
			query = query + " and o.regActivo = 1";
			productoList = new ArrayList<InvInventarioDet>();
			productoList = (List<InvInventarioDet>) inventarioLocal
					.findByCondition(InvInventarioDet.class, query,
							"o.invPlanta.planNombre");
		} else {
			query = null;
			productoList = new ArrayList<InvInventarioDet>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void buscarMovimiento() {
		String query = null;
		boolean seguir = false;
		mostrarTabla = true;
		if (inventario != null) {
			seguir = true;
			query = "o.invInventarioDet.invInventario.invNombre = '" + inventario + "'";
		}
		if (grupo != null) {
			if (seguir) {
				query = query + " and o.invInventarioDet.invPlanta.invGrupo.grpNombre = '"
						+ grupo + "'";
			} else {
				seguir = true;
				query = "o.invInventarioDet.invPlanta.invGrupo.grpNombre = '" + grupo + "'";
			}
		}
		if (producto != null) {
			if (seguir) {
				query = query + " and o.invInventarioDet.invPlanta.planNombre = '" + producto
						+ "'";
			} else {
				seguir = true;
				query = "o.invInventarioDet.invPlanta.planNombre = '" + producto + "'";
			}
		}
		if (movimiento != null) {
			if (seguir) {
				query = query + " and o.invTipoMovimiento.tipNombre = '" + movimiento + "'";
			} else {
				seguir = true;
				query = "o.invTipoMovimiento.tipNombre = '" + movimiento + "'";
			}
		}
		if (fecDesde != null & fecHasta != null) {
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
			if (seguir) {
				query = query + "and o.fecCrea between '" + dt.format(fecDesde) + "' and '" + dt.format(fecHasta) + "'";
			} else {
				seguir = true;
				query = "o.fecCrea between '" +  dt.format(fecDesde) + "' and '" + dt.format(fecHasta) + "'";
			}
		}
		if (seguir) {
			query = query + " and o.regActivo = 1";
			//System.out.println("[query] "+ query);
			movimientoList = new ArrayList<InvInventarioMov>();
			movimientoList = (List<InvInventarioMov>) inventarioLocal.findByCondition(InvInventarioMov.class, query, "o.movId");
		} else {
			query = null;
			movimientoList = new ArrayList<InvInventarioMov>();
		}
	}

	public List<InvInventario> getInventarioFilterList() {
		return inventarioFilterList;
	}

	public void setInventarioFilterList(List<InvInventario> inventarioFilterList) {
		this.inventarioFilterList = inventarioFilterList;
	}

	public List<InvGrupo> getGrupoFilterList() {
		return grupoFilterList;
	}

	public void setGrupoFilterList(List<InvGrupo> grupoFilterList) {
		this.grupoFilterList = grupoFilterList;
	}

	public List<InvPlanta> getProductoFilterList() {
		return productoFilterList;
	}

	public void setProductoFilterList(List<InvPlanta> productoFilterList) {
		this.productoFilterList = productoFilterList;
	}

	public String getInventario() {
		return inventario;
	}

	public void setInventario(String inventario) {
		this.inventario = inventario;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public List<InvInventarioDet> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<InvInventarioDet> productoList) {
		this.productoList = productoList;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public InvInventarioDet getProdSelected() {
		return prodSelected;
	}

	public void setProdSelected(InvInventarioDet prodSelected) {
		this.prodSelected = prodSelected;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public Date getFecDesde() {
		return fecDesde;
	}

	public void setFecDesde(Date fecDesde) {
		this.fecDesde = fecDesde;
	}

	public Date getFecHasta() {
		return fecHasta;
	}

	public void setFecHasta(Date fecHasta) {
		this.fecHasta = fecHasta;
	}

	public List<InvInventarioMov> getMovimientoList() {
		return movimientoList;
	}

	public void setMovimientoList(List<InvInventarioMov> movimientoList) {
		this.movimientoList = movimientoList;
	}

	public InvInventarioMov getMovSelected() {
		return movSelected;
	}

	public void setMovSelected(InvInventarioMov movSelected) {
		this.movSelected = movSelected;
	}

}
