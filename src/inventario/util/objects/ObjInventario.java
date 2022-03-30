package inventario.util.objects;

import java.util.List;

import santamaria.entities.AdmUsuario;

import inventario.entities.InvInventario;
import inventario.entities.InvPlanta;

public class ObjInventario {

	private InvPlanta planta;
	private List<InvInventario> inventarioList;
	private AdmUsuario usuario;

	public ObjInventario() {

	}

	public ObjInventario(InvPlanta planta, List<InvInventario> inventarioList,
			AdmUsuario usuario) {
		super();
		this.planta = planta;
		this.inventarioList = inventarioList;
		this.usuario = usuario;
	}

	public boolean isValidCreate() {
		boolean result = false;
		boolean valNull = planta != null && inventarioList != null
				&& inventarioList.size() >= 1 && usuario != null;
		if (valNull) {
			result = true;
		}
		return result;
	}

	public InvPlanta getPlanta() {
		return planta;
	}

	public void setPlanta(InvPlanta planta) {
		this.planta = planta;
	}

	public List<InvInventario> getInventarioList() {
		return inventarioList;
	}

	public void setInventarioList(List<InvInventario> inventarioList) {
		this.inventarioList = inventarioList;
	}

	public AdmUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(AdmUsuario usuario) {
		this.usuario = usuario;
	}
	
}
