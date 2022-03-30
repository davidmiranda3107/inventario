package inventario.sessions;

import java.util.List;

import inventario.entities.InvEstado;
import inventario.entities.InvTipoMovimiento;
import inventario.util.objects.ObjInventario;
import inventario.util.objects.ObjMovimiento;

import javax.ejb.Local;

import santamaria.sessions.BusinessSBSLLocal;

@Local
public interface InventarioSBSLLocal extends BusinessSBSLLocal {

	public long generarNumero(String cntNombre);
	public void crearNuevoInventario(ObjInventario objInventario);
	public void sumarAlInventario(List<ObjMovimiento> listado, InvTipoMovimiento tipoMov, String correlativo, InvEstado estado, String observacion);
	public void restarAlInventario(List<ObjMovimiento> listado, InvTipoMovimiento tipoMov, String correlativo, InvEstado estado, String observacion);
	public void sumarAjuste(List<ObjMovimiento> listado, InvTipoMovimiento tipoMov, String correlativo, InvEstado estado, String observacion);
	public void restarAjuste(List<ObjMovimiento> listado, InvTipoMovimiento tipoMov, String correlativo, InvEstado estado, String observacion);
}
