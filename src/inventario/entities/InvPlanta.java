package inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_planta database table.
 * 
 */
@Entity
@Table(name="inv_planta", schema="santamaria")
@NamedQuery(name="InvPlanta.findAll", query="SELECT i FROM InvPlanta i")
public class InvPlanta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="PLAN_ID", schema="santamaria", table="contador", pkColumnName="cnt_nombre",valueColumnName="cnt_valor", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="PLAN_ID")
	@Column(name="plan_id")
	private long planId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_crea")
	private Date fecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_modi")
	private Date fecModi;

	@Column(name="plan_codigo")
	private String planCodigo;

	@Column(name="plan_descripcion")
	private String planDescripcion;

	@Column(name="plan_nombre")
	private String planNombre;
	
	@Column(name="plan_precio_uni")
	private BigDecimal planPrecioUnitario;
	
	@Column(name="plan_precio_iva")
	private BigDecimal planPrecioIva;
	
	@Column(name="plan_precio")
	private BigDecimal planPrecio;
	
	@Column(name="plan_origen")
	private String planOrigen;

	@Column(name="reg_activo")
	private BigDecimal regActivo;

	@Column(name="usu_crea")
	private String usuCrea;

	@Column(name="usu_modi")
	private String usuModi;

	//bi-directional many-to-one association to InvCierreDet
	@OneToMany(mappedBy="invPlanta")
	private List<InvCierreDet> invCierreDets;

	//bi-directional many-to-one association to InvInventarioDet
	@OneToMany(mappedBy="invPlanta")
	private List<InvInventarioDet> invInventarioDets;

	//bi-directional many-to-one association to InvGrupo
	@ManyToOne
	@JoinColumn(name="plan_grp_id")
	private InvGrupo invGrupo;

	public InvPlanta() {
	}

	public long getPlanId() {
		return this.planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
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

	public String getPlanCodigo() {
		return this.planCodigo;
	}

	public void setPlanCodigo(String planCodigo) {
		this.planCodigo = planCodigo;
	}

	public String getPlanDescripcion() {
		return this.planDescripcion;
	}

	public void setPlanDescripcion(String planDescripcion) {
		this.planDescripcion = planDescripcion;
	}

	public String getPlanNombre() {
		return this.planNombre;
	}

	public void setPlanNombre(String planNombre) {
		this.planNombre = planNombre;
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

	public List<InvCierreDet> getInvCierreDets() {
		return this.invCierreDets;
	}

	public void setInvCierreDets(List<InvCierreDet> invCierreDets) {
		this.invCierreDets = invCierreDets;
	}

	public InvCierreDet addInvCierreDet(InvCierreDet invCierreDet) {
		getInvCierreDets().add(invCierreDet);
		invCierreDet.setInvPlanta(this);

		return invCierreDet;
	}

	public InvCierreDet removeInvCierreDet(InvCierreDet invCierreDet) {
		getInvCierreDets().remove(invCierreDet);
		invCierreDet.setInvPlanta(null);

		return invCierreDet;
	}

	public List<InvInventarioDet> getInvInventarioDets() {
		return this.invInventarioDets;
	}

	public void setInvInventarioDets(List<InvInventarioDet> invInventarioDets) {
		this.invInventarioDets = invInventarioDets;
	}

	public InvInventarioDet addInvInventarioDet(InvInventarioDet invInventarioDet) {
		getInvInventarioDets().add(invInventarioDet);
		invInventarioDet.setInvPlanta(this);

		return invInventarioDet;
	}

	public InvInventarioDet removeInvInventarioDet(InvInventarioDet invInventarioDet) {
		getInvInventarioDets().remove(invInventarioDet);
		invInventarioDet.setInvPlanta(null);

		return invInventarioDet;
	}

	public InvGrupo getInvGrupo() {
		return this.invGrupo;
	}

	public void setInvGrupo(InvGrupo invGrupo) {
		this.invGrupo = invGrupo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (planId ^ (planId >>> 32));
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
		InvPlanta other = (InvPlanta) obj;
		if (planId != other.planId)
			return false;
		return true;
	}

	public BigDecimal getPlanPrecioUnitario() {
		return planPrecioUnitario;
	}

	public void setPlanPrecioUnitario(BigDecimal planPrecioUnitario) {
		this.planPrecioUnitario = planPrecioUnitario;
	}

	public BigDecimal getPlanPrecioIva() {
		return planPrecioIva;
	}

	public void setPlanPrecioIva(BigDecimal planPrecioIva) {
		this.planPrecioIva = planPrecioIva;
	}

	public BigDecimal getPlanPrecio() {
		return planPrecio;
	}

	public void setPlanPrecio(BigDecimal planPrecio) {
		this.planPrecio = planPrecio;
	}

	public String getPlanOrigen() {
		return planOrigen;
	}

	public void setPlanOrigen(String planOrigen) {
		this.planOrigen = planOrigen;
	}

}