package inventario.sessions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import inventario.entities.Contador;
import inventario.entities.InvEstado;
import inventario.entities.InvInventario;
import inventario.entities.InvInventarioDet;
import inventario.entities.InvInventarioMov;
import inventario.entities.InvTipoMovimiento;
import inventario.util.objects.ObjInventario;
import inventario.util.objects.ObjMovimiento;
import santamaria.sessions.BusinessSBSL;
import santamaria.util.Utils;

/**
 * Session Bean implementation class InventarioSBSL
 */
@Stateless
public class InventarioSBSL extends BusinessSBSL implements InventarioSBSLLocal {

	public InventarioSBSL() {

	}

	public long generarNumero(String cntNombre) {
		Contador num = null;
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		try {
			etx.begin();
			String sql = "select * from santamaria.contador where cnt_nombre = '"
					+ cntNombre + "' for update";
			Query q = em.createNativeQuery(sql, Contador.class);
			num = (Contador) q.getSingleResult();
			if (num != null) {
				num.setCntValor(num.getCntValor().add(BigDecimal.ONE));
			}
			em.merge(num);
			em.flush();
			etx.commit();
		} catch (NoResultException e) {
			try {
				num = new Contador();
				Query q1 = em
						.createQuery("select max(c.cntId) from Contador c");
				num.setCntId(Long.valueOf(q1.getSingleResult().toString()) + 1);
				num.setCntNombre(cntNombre);
				num.setCntValor(BigDecimal.ONE);
				num.setCntDescripcion("CONTADOR DE " + cntNombre);
				em.persist(num);
				em.flush();
				etx.commit();
			} catch (Exception e1) {
				if (etx != null) {
					etx.rollback();
				}
				e1.printStackTrace();
			}
		} catch (Exception ex) {
			if (etx != null) {
				etx.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.clear();
			em.close();
		}
		return Long.parseLong(String.valueOf(num.getCntValor()));
	}

	public void crearNuevoInventario(ObjInventario objInventario) {
		if (objInventario.isValidCreate()) {
			EntityTransaction etx = null;
			Date hoy = Utils.getHoy();
			try {
				em = emf.createEntityManager();
				etx = em.getTransaction();
				etx.begin();
				for (InvInventario inv : objInventario.getInventarioList()) {
					InvInventarioDet invDet = new InvInventarioDet();
					invDet.setInvInventario(inv);
					invDet.setInvPlanta(objInventario.getPlanta());
					invDet.setFecCrea(hoy);
					invDet.setUsuCrea(objInventario.getUsuario().getUsuLogin());
					invDet.setInvdCantEntradas(BigDecimal.ZERO);
					invDet.setInvdCantReserva(BigDecimal.ZERO);
					invDet.setInvdCantSalidas(BigDecimal.ZERO);
					invDet.setInvdCatId(BigDecimal.ONE); // REVISAR CATALOGO DE
															// CUENTAS CONTABLES
					invDet.setInvdCostoActual(objInventario.getPlanta()
							.getPlanPrecioUnitario());
					invDet.setInvdCostoIni(objInventario.getPlanta()
							.getPlanPrecioUnitario());
					invDet.setInvdExistencia(BigDecimal.ZERO);
					invDet.setInvdExistenciaIni(BigDecimal.ZERO);
					invDet.setInvdMonto(BigDecimal.ZERO);
					invDet.setInvdMontoEntradas(BigDecimal.ZERO);
					invDet.setInvdMontoIni(BigDecimal.ZERO);
					invDet.setInvdMontoReserva(BigDecimal.ZERO);
					invDet.setInvdMontoSalidas(BigDecimal.ZERO);
					invDet.setInvdPrecioVenta(objInventario.getPlanta()
							.getPlanPrecio());
					invDet.setRegActivo(BigDecimal.ONE);
					em.persist(invDet);
				}
				em.flush();
				etx.commit();
			} catch (Exception e) {
				if (etx != null) {
					etx.rollback();
				}
				e.printStackTrace();
			} finally {
				em.clear();
				em.close();
			}
		}
	}

	public void sumarAlInventario(List<ObjMovimiento> listado,
			InvTipoMovimiento tipoMov, String correlativo, InvEstado estado,
			String observacion) {
		EntityTransaction etx = null;
		Date hoy = Utils.getHoy();
		try {
			em = emf.createEntityManager();
			etx = em.getTransaction();
			etx.begin();
			for (ObjMovimiento obj : listado) {
				InvInventarioMov mov = new InvInventarioMov();
				mov.setMovCodigo(correlativo);
				mov.setFecCrea(hoy);
				mov.setMovCantidad(obj.getCantidad());
				mov.setMovMonto(obj.getProducto().getInvdCostoActual()
						.multiply(BigDecimal.valueOf(obj.getCantidad())));
				mov.setRegActivo(BigDecimal.ONE);
				mov.setUsuCrea(obj.getUsuario().getUsuLogin());
				mov.setInvEstado(estado);
				mov.setInvInventarioDet(obj.getProducto());
				mov.setInvTipoMovimiento(tipoMov);
				mov.setMovObservacion(observacion);

				obj.getProducto().setFecModi(hoy);
				obj.getProducto().setUsuModi(obj.getUsuario().getUsuLogin());
				obj.getProducto().setInvdCantEntradas(
						obj.getProducto().getInvdCantEntradas()
								.add(BigDecimal.ONE));
				obj.getProducto().setInvdExistencia(
						obj.getProducto().getInvdExistencia()
								.add(BigDecimal.valueOf(obj.getCantidad())));
				obj.getProducto()
						.setInvdMonto(
								obj.getProducto().getInvdMonto()
										.add(mov.getMovMonto()));
				obj.getProducto().setInvdMontoEntradas(
						obj.getProducto().getInvdMontoEntradas()
								.add(mov.getMovMonto()));

				em.persist(mov);

				em.merge(obj.getProducto());
			}
			em.flush();
			etx.commit();
		} catch (Exception e) {
			if (etx != null) {
				etx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.clear();
			em.close();
		}
	}

	public void restarAlInventario(List<ObjMovimiento> listado,
			InvTipoMovimiento tipoMov, String correlativo, InvEstado estado,
			String observacion) {
		EntityTransaction etx = null;
		Date hoy = Utils.getHoy();
		try {
			em = emf.createEntityManager();
			etx = em.getTransaction();
			etx.begin();
			for (ObjMovimiento obj : listado) {
				InvInventarioMov mov = new InvInventarioMov();
				mov.setMovCodigo(correlativo);
				mov.setFecCrea(hoy);
				mov.setMovCantidad(obj.getCantidad());
				mov.setMovMonto(obj.getProducto().getInvdCostoActual()
						.multiply(BigDecimal.valueOf(obj.getCantidad())));
				mov.setRegActivo(BigDecimal.ONE);
				mov.setUsuCrea(obj.getUsuario().getUsuLogin());
				mov.setInvEstado(estado);
				mov.setInvInventarioDet(obj.getProducto());
				mov.setInvTipoMovimiento(tipoMov);
				mov.setMovObservacion(observacion);

				obj.getProducto().setFecModi(hoy);
				obj.getProducto().setUsuModi(obj.getUsuario().getUsuLogin());
				obj.getProducto().setInvdCantSalidas(
						obj.getProducto().getInvdCantSalidas()
								.add(BigDecimal.ONE));
				obj.getProducto()
						.setInvdExistencia(
								obj.getProducto()
										.getInvdExistencia()
										.subtract(
												BigDecimal.valueOf(obj
														.getCantidad())));
				obj.getProducto().setInvdMonto(
						obj.getProducto().getInvdMonto()
								.subtract(mov.getMovMonto()));
				obj.getProducto().setInvdMontoSalidas(
						obj.getProducto().getInvdMontoSalidas()
								.add(mov.getMovMonto()));

				em.persist(mov);

				em.merge(obj.getProducto());
			}

			em.flush();
			etx.commit();
		} catch (Exception e) {
			if (etx != null) {
				etx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.clear();
			em.close();
		}
	}

	public void sumarAjuste(List<ObjMovimiento> listado,
			InvTipoMovimiento tipoMov, String correlativo, InvEstado estado,
			String observacion) {
		EntityTransaction etx = null;
		Date hoy = Utils.getHoy();
		try {
			em = emf.createEntityManager();
			etx = em.getTransaction();
			etx.begin();
			for (ObjMovimiento obj : listado) {
				InvInventarioMov mov = new InvInventarioMov();
				mov.setMovCodigo(correlativo);
				mov.setFecCrea(hoy);
				mov.setMovCantidad(obj.getCantidad());
				mov.setMovMonto(obj.getProducto().getInvdCostoActual()
						.multiply(BigDecimal.valueOf(obj.getCantidad())));
				mov.setRegActivo(BigDecimal.ONE);
				mov.setUsuCrea(obj.getUsuario().getUsuLogin());
				mov.setInvEstado(estado);
				mov.setInvInventarioDet(obj.getProducto());
				mov.setInvTipoMovimiento(tipoMov);
				mov.setMovObservacion(observacion);

				obj.getProducto().setFecModi(hoy);
				obj.getProducto().setUsuModi(obj.getUsuario().getUsuLogin());
				obj.getProducto().setInvdCantEntradas(
						obj.getProducto().getInvdCantEntradas()
								.add(BigDecimal.ONE));
				obj.getProducto().setInvdExistencia(
						obj.getProducto().getInvdExistencia()
								.add(BigDecimal.valueOf(obj.getCantidad())));
				obj.getProducto()
						.setInvdMonto(
								obj.getProducto().getInvdMonto()
										.add(mov.getMovMonto()));
				obj.getProducto().setInvdMontoEntradas(
						obj.getProducto().getInvdMontoEntradas()
								.add(mov.getMovMonto()));

				em.persist(mov);

				em.merge(obj.getProducto());
			}
			em.flush();
			etx.commit();
		} catch (Exception e) {
			if (etx != null) {
				etx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.clear();
			em.close();
		}
	}

	public void restarAjuste(List<ObjMovimiento> listado,
			InvTipoMovimiento tipoMov, String correlativo, InvEstado estado,
			String observacion) {
		EntityTransaction etx = null;
		Date hoy = Utils.getHoy();
		try {
			em = emf.createEntityManager();
			etx = em.getTransaction();
			etx.begin();
			for (ObjMovimiento obj : listado) {
				InvInventarioMov mov = new InvInventarioMov();
				mov.setMovCodigo(correlativo);
				mov.setFecCrea(hoy);
				mov.setMovCantidad(obj.getCantidad());
				mov.setMovMonto(obj.getProducto().getInvdCostoActual()
						.multiply(BigDecimal.valueOf(obj.getCantidad())));
				mov.setRegActivo(BigDecimal.ONE);
				mov.setUsuCrea(obj.getUsuario().getUsuLogin());
				mov.setInvEstado(estado);
				mov.setInvInventarioDet(obj.getProducto());
				mov.setInvTipoMovimiento(tipoMov);
				mov.setMovObservacion(observacion);

				obj.getProducto().setFecModi(hoy);
				obj.getProducto().setUsuModi(obj.getUsuario().getUsuLogin());
				obj.getProducto().setInvdCantSalidas(
						obj.getProducto().getInvdCantSalidas()
								.add(BigDecimal.ONE));
				obj.getProducto()
						.setInvdExistencia(
								obj.getProducto()
										.getInvdExistencia()
										.subtract(
												BigDecimal.valueOf(obj
														.getCantidad())));
				obj.getProducto().setInvdMonto(
						obj.getProducto().getInvdMonto()
								.subtract(mov.getMovMonto()));
				obj.getProducto().setInvdMontoSalidas(
						obj.getProducto().getInvdMontoSalidas()
								.add(mov.getMovMonto()));

				em.persist(mov);

				em.merge(obj.getProducto());
			}

			em.flush();
			etx.commit();
		} catch (Exception e) {
			if (etx != null) {
				etx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.clear();
			em.close();
		}
	}
}
