package inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the inv_movimiento_tracking database table.
 * 
 */
@Entity
@Table(name="inv_movimiento_tracking", schema="santamaria")
@NamedQuery(name="InvMovimientoTracking.findAll", query="SELECT i FROM InvMovimientoTracking i")
public class InvMovimientoTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="MOVT_ID", schema="santamaria", table="contador", pkColumnName="cnt_nombre",valueColumnName="cnt_valor", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="MOVT_ID")
	@Column(name="movt_id")
	private long movtId;

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

	//bi-directional many-to-one association to InvEstado
	@ManyToOne
	@JoinColumn(name="movt_est_id")
	private InvEstado invEstado;

	//bi-directional many-to-one association to InvInventarioMov
	@ManyToOne
	@JoinColumn(name="movt_mov_id")
	private InvInventarioMov invInventarioMov;

	public InvMovimientoTracking() {
	}

	public long getMovtId() {
		return this.movtId;
	}

	public void setMovtId(long movtId) {
		this.movtId = movtId;
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

	public InvEstado getInvEstado() {
		return this.invEstado;
	}

	public void setInvEstado(InvEstado invEstado) {
		this.invEstado = invEstado;
	}

	public InvInventarioMov getInvInventarioMov() {
		return this.invInventarioMov;
	}

	public void setInvInventarioMov(InvInventarioMov invInventarioMov) {
		this.invInventarioMov = invInventarioMov;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (movtId ^ (movtId >>> 32));
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
		InvMovimientoTracking other = (InvMovimientoTracking) obj;
		if (movtId != other.movtId)
			return false;
		return true;
	}

}