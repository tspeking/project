package cc.mrbird.web.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_finance_trend")
public class FinanceTrend implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "FINANCE_ID")
	private Long financeId;
	
	@Column(name = "QUOTA_NAME")
	private String quotaName;
	
	@Column(name = "QUOTA_VALUE")
	private String quotaValue;
	
	@Column(name = "CRATE_TIME")
	private Date crateTime;
	
	/**
	 * 1 融资余额 2 CPI 3 M2 4 社融 
	 * 5 GDP季度 6 乘用车同比 7 乘用车环比 8 沪深交易量
	 */
	@Column(name = "TYPE")
	private String type;

	public Long getFinanceId() {
		return financeId;
	}

	public void setFinanceId(Long financeId) {
		this.financeId = financeId;
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

	public Date getCrateTime() {
		return crateTime;
	}

	public void setCrateTime(Date crateTime) {
		this.crateTime = crateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
