package inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_inventario_mov database table.
 * 
 */
@Entity
@Table(name="inv_inventario_mov", schema="santamaria")
@NamedQuery(name="InvInventarioMov.findAll", query="SELECT i FROM InvInventarioMov i")
public class InvInventarioMov implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="MOV_ID", schema="santamaria", table="contador", pkColumnName="cnt_nombre",valueColumnName="cnt_valor", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="MOV_ID")
	@Column(name="mov_id")
	private long movId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_crea")
	private Date fecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_modi")
	private Date fecModi;

	@Column(name="mov_cantidad")
	private long movCantidad;

	@Column(name="mov_codigo")
	private String movCodigo;

	@Column(name="mov_monto")
	private BigDecimal movMonto;

	@Column(name="reg_activo")
	private BigDecimal regActivo;

	@Column(name="usu_crea")
	private String usuCrea;

	@Column(name="usu_modi")
	private String usuModi;
	
	@Column(name="mov_observacion")
	private String movObservacion;

	//bi-directional many-to-one association to InvEstado
	@ManyToOne
	@JoinColumn(name="mov_est_id")
	private InvEstado invEstado;

	//bi-directional many-to-one association to InvInventarioDet
	@ManyToOne
	@JoinColumn(name="mov_invd_id")
	private InvInventarioDet invInventarioDet;

	//bi-directional many-to-one association to InvTipoMovimiento
	@ManyToOne
	@JoinColumn(name="mov_tip_id")
	private InvTipoMovimiento invTipoMovimiento;

	//bi-directional many-to-one association to InvMovimientoTracking
	@OneToMany(mappedBy="invInventarioMov")
	private List<InvMovimientoTracking> invMovimientoTrackings;

	public InvInventarioMov() {
	}

	public long getMovId() {
		return this.movId;
	}

	public void setMovId(long movId) {
		this.movId = movId;
	}

	public Date getFecCrea() {
		return this.fecCrea;
	}

	public void setFecCrea(Date fecCrea) {
		this.fecCrea = fecCrea;
	}

	public Date getFecModi() {
		return this.fecModi;
	}

	public void setFecModi(Date fecModi) {
		this.fecModi = fecModi;
	}

	public long getMovCantidad() {
		return this.movCantidad;
	}

	public void setMovCantidad(long movCantidad) {
		this.movCantidad = movCantidad;
	}

	public String getMovCodigo() {
		return this.movCodigo;
	}

	public void setMovCodigo(String movCodigo) {
		this.movCodigo = movCodigo;
	}

	public BigDecimal getMovMonto() {
		return this.movMonto;
	}

	public void setMovMonto(BigDecimal movMonto) {
		this.movMonto = movMonto;
	}

	public BigDecimal getRegActivo() {
		return this.regActivo;
	}

	public void setRegActivo(BigDecimal regActivo) {
		this.regActivo = regActivo;
	}

	public String getUsuCrea() {
		return this.usuCrea;
	}

	public void setUsuCrea(String usuCrea) {
		this.usuCrea = usuCrea;
	}

	public String getUsuModi() {
		return this.usuModi;
	}

	public void setUsuModi(String usuModi) {
		this.usuModi = usuModi;
	}

	public InvEstado getInvEstado() {
		return this.invEstado;
	}

	public void setInvEstado(InvEstado invEstado) {
		this.invEstado = invEstado;
	}

	public InvInventarioDet getInvInventarioDet() {
		return this.invInventarioDet;
	}

	public void setInvInventarioDet(InvInventarioDet invInventarioDet) {
		this.invInventarioDet = invInventarioDet;
	}

	public InvTipoMovimiento getInvTipoMovimiento() {
		return this.invTipoMovimiento;
	}

	public void setInvTipoMovimiento(InvTipoMovimiento invTipoMovimiento) {
		this.invTipoMovimiento = invTipoMovimiento;
	}

	public List<InvMovimientoTracking> getInvMovimientoTrackings() {
		return this.invMovimientoTrackings;
	}

	public void setInvMovimientoTrackings(List<InvMovimientoTracking> invMovimientoTrackings) {
		this.invMovimientoTrackings = invMovimientoTrackings;
	}

	public InvMovimientoTracking addInvMovimientoTracking(InvMovimientoTracking invMovimientoTracking) {
		getInvMovimientoTrackings().add(invMovimientoTracking);
		invMovimientoTracking.setInvInventarioMov(this);

		return invMovimientoTracking;
	}

	public InvMovimientoTracking removeInvMovimientoTracking(InvMovimientoTracking invMovimientoTracking) {
		getInvMovimientoTrackings().remove(invMovimientoTracking);
		invMovimientoTracking.setInvInventarioMov(null);

		return invMovimientoTracking;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (movId ^ (movId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvInventarioMov other = (InvInventarioMov) obj;
		if (movId != other.movId)
			return false;
		return true;
	}

	public String getMovObservacion() {
		return movObservacion;
	}

	public void setMovObservacion(String movObservacion) {
		this.movObservacion = movObservacion;
	}

}