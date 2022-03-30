package inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_tipo_movimiento database table.
 * 
 */
@Entity
@Table(name="inv_tipo_movimiento", schema="santamaria")
@NamedQuery(name="InvTipoMovimiento.findAll", query="SELECT i FROM InvTipoMovimiento i")
public class InvTipoMovimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="TIP_ID", schema="santamaria", table="contador", pkColumnName="cnt_nombre",valueColumnName="cnt_valor", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TIP_ID")
	@Column(name="tip_id")
	private long tipId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_crea")
	private Date fecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_modi")
	private Date fecModi;

	@Column(name="reg_activo")
	private BigDecimal regActivo;

	@Column(name="tip_costo")
	private Boolean tipCosto;

	@Column(name="tip_descripcion")
	private String tipDescripcion;

	@Column(name="tip_entrada")
	private Boolean tipEntrada;

	@Column(name="tip_nombre")
	private String tipNombre;

	@Column(name="tip_salida")
	private Boolean tipSalida;

	@Column(name="usu_crea")
	private String usuCrea;

	@Column(name="usu_modi")
	private String usuModi;

	//bi-directional many-to-one association to InvInventarioMov
	@OneToMany(mappedBy="invTipoMovimiento")
	private List<InvInventarioMov> invInventarioMovs;

	public InvTipoMovimiento() {
	}

	public long getTipId() {
		return this.tipId;
	}

	public void setTipId(long tipId) {
		this.tipId = tipId;
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

	public Boolean getTipCosto() {
		return this.tipCosto;
	}

	public void setTipCosto(Boolean tipCosto) {
		this.tipCosto = tipCosto;
	}

	public String getTipDescripcion() {
		return this.tipDescripcion;
	}

	public void setTipDescripcion(String tipDescripcion) {
		this.tipDescripcion = tipDescripcion;
	}

	public Boolean getTipEntrada() {
		return this.tipEntrada;
	}

	public void setTipEntrada(Boolean tipEntrada) {
		this.tipEntrada = tipEntrada;
	}

	public String getTipNombre() {
		return this.tipNombre;
	}

	public void setTipNombre(String tipNombre) {
		this.tipNombre = tipNombre;
	}

	public Boolean getTipSalida() {
		return this.tipSalida;
	}

	public void setTipSalida(Boolean tipSalida) {
		this.tipSalida = tipSalida;
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
		invInventarioMov.setInvTipoMovimiento(this);

		return invInventarioMov;
	}

	public InvInventarioMov removeInvInventarioMov(InvInventarioMov invInventarioMov) {
		getInvInventarioMovs().remove(invInventarioMov);
		invInventarioMov.setInvTipoMovimiento(null);

		return invInventarioMov;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (tipId ^ (tipId >>> 32));
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
		InvTipoMovimiento other = (InvTipoMovimiento) obj;
		if (tipId != other.tipId)
			return false;
		return true;
	}

}