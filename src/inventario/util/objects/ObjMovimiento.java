package inventario.util.objects;

import santamaria.entities.AdmUsuario;
import inventario.entities.InvInventarioDet;

public class ObjMovimiento {

	private InvInventarioDet producto;
	private long cantidad;
	private AdmUsuario usuario;
	
	public ObjMovimiento() {
		
	}
	
	public ObjMovimiento(InvInventarioDet producto, long cantidad, AdmUsuario usuario) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.usuario = usuario;
	}

	public InvInventarioDet getProducto() {
		return producto;
	}

	public void setProducto(InvInventarioDet producto) {
		this.producto = producto;
	}

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	public AdmUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(AdmUsuario usuario) {
		this.usuario = usuario;
	}
	
}
