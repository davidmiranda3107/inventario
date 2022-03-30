package inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_estado database table.
 * 
 */
@Entity
@Table(name="inv_estado", schema="santamaria")
@NamedQuery(name="InvEstado.findAll", query="SELECT i FROM InvEstado i")
public class InvEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="INV_EST_ID", schema="santamaria", table="contador", pkColumnName="cnt_nombre",valueColumnName="cnt_valor", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="INV_EST_ID")
	@Column(name="est_id")
	private long estId;

	@Column(name="est_descripcion")
	private String estDescripcion;

	@Column(name="est_nombre")
	private String estNombre;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_crea")
	private Date fecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_modi")
	private Date fecModi;

	@Column(name="reg_activo")
	private BigDecimal regActivo;

	@Column(name="usu_crea")
	private String usuCrea;

	@Column(name="usu_modi")
	private String usuModi;

	//bi-directional many-to-one association to InvInventarioMov
	@OneToMany(mappedBy="invEstado")
	private List<InvInventarioMov> invInventarioMovs;

	//bi-directional many-to-one association to InvMovimientoTracking
	@OneToMany(mappedBy="invEstado")
	private List<InvMovimientoTracking> invMovimientoTrackings;

	public InvEstado() {
	}

	public long getEstId() {
		return this.estId;
	}

	public void setEstId(long estId) {
		this.estId = estId;
	}

	public String getEstDescripcion() {
		return this.estDescripcion;
	}

	public void setEstDescripcion(String estDescripcion) {
		this.estDescripcion = estDescripcion;
	}

	public String getEstNombre() {
		return this.estNombre;
	}

	public void setEstNombre(String estNombre) {
		this.estNombre = estNombre;
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

	public List<InvInventarioMov> getInvInventarioMovs() {
		return this.invInventarioMovs;
	}

	public void setInvInventarioMovs(List<InvInventarioMov> invInventarioMovs) {
		this.invInventarioMovs = invInventarioMovs;
	}

	public InvInventarioMov addInvInventarioMov(InvInventarioMov invInventarioMov) {
		getInvInventarioMovs().add(invInventarioMov);
		invInventarioMov.setInvEstado(this);

		return invInventarioMov;
	}

	public InvInventarioMov removeInvInventarioMov(InvInventarioMov invInventarioMov) {
		getInvInventarioMovs().remove(invInventarioMov);
		invInventarioMov.setInvEstado(null);

		return invInventarioMov;
	}

	public List<InvMovimientoTracking> getInvMovimientoTrackings() {
		return this.invMovimientoTrackings;
	}

	public void setInvMovimientoTrackings(List<InvMovimientoTracking> invMovimientoTrackings) {
		this.invMovimientoTrackings = invMovimientoTrackings;
	}

	public InvMovimientoTracking addInvMovimientoTracking(InvMovimientoTracking invMovimientoTracking) {
		getInvMovimientoTrackings().add(invMovimientoTracking);
		invMovimientoTracking.setInvEstado(this);

		return invMovimientoTracking;
	}

	public InvMovimientoTracking removeInvMovimientoTracking(InvMovimientoTracking invMovimientoTracking) {
		getInvMovimientoTrackings().remove(invMovimientoTracking);
		invMovimientoTracking.setInvEstado(null);

		return invMovimientoTracking;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (estId ^ (estId >>> 32));
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
		InvEstado other = (InvEstado) obj;
		if (estId != other.estId)
			return false;
		return true;
	}

}