package inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_cierre database table.
 * 
 */
@Entity
@Table(name="inv_cierre", schema="santamaria")
@NamedQuery(name="InvCierre.findAll", query="SELECT i FROM InvCierre i")
public class InvCierre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="CIERRE_ID", schema="santamaria", table="contador", pkColumnName="cnt_nombre",valueColumnName="cnt_valor", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="CIERRE_ID")
	@Column(name="cierre_id")
	private long cierreId;

	@Column(name="cierre_anio")
	private BigDecimal cierreAnio;

	@Column(name="cierre_mes")
	private BigDecimal cierreMes;

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

	//bi-directional many-to-one association to InvInventario
	@ManyToOne
	@JoinColumn(name="cierre_inv_id")
	private InvInventario invInventario;

	//bi-directional many-to-one association to InvCierreDet
	@OneToMany(mappedBy="invCierre")
	private List<InvCierreDet> invCierreDets;

	public InvCierre() {
	}

	public long getCierreId() {
		return this.cierreId;
	}

	public void setCierreId(long cierreId) {
		this.cierreId = cierreId;
	}

	public BigDecimal getCierreAnio() {
		return this.cierreAnio;
	}

	public void setCierreAnio(BigDecimal cierreAnio) {
		this.cierreAnio = cierreAnio;
	}

	public BigDecimal getCierreMes() {
		return this.cierreMes;
	}

	public void setCierreMes(BigDecimal cierreMes) {
		this.cierreMes = cierreMes;
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

	public InvInventario getInvInventario() {
		return this.invInventario;
	}

	public void setInvInventario(InvInventario invInventario) {
		this.invInventario = invInventario;
	}

	public List<InvCierreDet> getInvCierreDets() {
		return this.invCierreDets;
	}

	public void setInvCierreDets(List<InvCierreDet> invCierreDets) {
		this.invCierreDets = invCierreDets;
	}

	public InvCierreDet addInvCierreDet(InvCierreDet invCierreDet) {
		getInvCierreDets().add(invCierreDet);
		invCierreDet.setInvCierre(this);

		return invCierreDet;
	}

	public InvCierreDet removeInvCierreDet(InvCierreDet invCierreDet) {
		getInvCierreDets().remove(invCierreDet);
		invCierreDet.setInvCierre(null);

		return invCierreDet;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cierreId ^ (cierreId >>> 32));
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
		InvCierre other = (InvCierre) obj;
		if (cierreId != other.cierreId)
			return false;
		return true;
	}

}