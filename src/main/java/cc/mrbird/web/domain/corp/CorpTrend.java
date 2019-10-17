package cc.mrbird.web.domain.corp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_corp_quota_trend")
public class CorpTrend implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "quota_id")
	private Long quotaId;
	
	@Column(name = "corp_name")
	private String corpName;
	
	@Column(name = "quota_name")
	private String quotaName;
	
	@Column(name = "quota_value")
	private String quotaValue;
	
	@Column(name = "quota_type")
	private String quotaType;
	
	@Column(name = "quota_time")
	private String quotaTime;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "last_modify_time")
	private Date lastModifyTime;

	public Long getQuotaId() {
		return quotaId;
	}

	public void setQuotaId(Long quotaId) {
		this.quotaId = quotaId;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getQuotaName() {
		return quotaName;
	}

	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	public String getQuotaValue() {
		return quotaValue;
	}

	public void setQuotaValue(String quotaValue) {
		this.quotaValue = quotaValue;
	}

	public String getQuotaType() {
		return quotaType;
	}

	public void setQuotaType(String quotaType) {
		this.quotaType = quotaType;
	}

	public String getQuotaTime() {
		return quotaTime;
	}

	public void setQuotaTime(String quotaTime) {
		this.quotaTime = quotaTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
}
