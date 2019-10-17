package cc.mrbird.web.domain.corp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_corp")
public class Corp implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "CORP_ID")
	private Long corpId;
	
	@Column(name = "INDUSTRY")
	private String industry;
	
	@Column(name = "SUB_INDUSTRY")
	private String subIndustry;
	
	@Column(name = "CORP_NAME")
	private String corpName;
	
	@Column(name = "INDUSTRY_LOCATION")
	private String industryLocation;
	
	@Column(name = "IS_FLAGSHIP")
	private String isFlagship;
	
	@Column(name = "IS_PRICE_POWER")
	private String isPricePower;
	
	@Column(name = "BUSINESS_SCOPE")
	private String businessScope;
	
	@Column(name = "MARKET_SHARE")
	private String marketShare;
	
	@Column(name = "FOWARD_PRICE")
	private String fowardPrice;
	
	@Column(name = "IS_UNDERESTIMATE")
	private String isUnderestimate;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	@Column(name = "LAST_MODIFY_TIME")
	private Date lastModifyTime;
	
	@Column(name = "UNDERESTIMATE_REGION")
	private String underestimateRegion;
	
	@Column(name = "CENTRE_REGION")
	private String centreRegion;
	
	@Column(name = "PRIORITY_LEVEL")
	private int priorityLevel;
	
	@Column(name = "PREMIUM_PRICE")
	private String premiumPrice;

	public Long getCorpId() {
		return corpId;
	}

	public void setCorpId(Long corpId) {
		this.corpId = corpId;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSubIndustry() {
		return subIndustry;
	}

	public void setSubIndustry(String subIndustry) {
		this.subIndustry = subIndustry;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getIndustryLocation() {
		return industryLocation;
	}

	public void setIndustryLocation(String industryLocation) {
		this.industryLocation = industryLocation;
	}

	public String getIsFlagship() {
		return isFlagship;
	}

	public void setIsFlagship(String isFlagship) {
		this.isFlagship = isFlagship;
	}

	public String getIsPricePower() {
		return isPricePower;
	}

	public void setIsPricePower(String isPricePower) {
		this.isPricePower = isPricePower;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getMarketShare() {
		return marketShare;
	}

	public void setMarketShare(String marketShare) {
		this.marketShare = marketShare;
	}

	public String getFowardPrice() {
		return fowardPrice;
	}

	public void setFowardPrice(String fowardPrice) {
		this.fowardPrice = fowardPrice;
	}

	public String getIsUnderestimate() {
		return isUnderestimate;
	}

	public void setIsUnderestimate(String isUnderestimate) {
		this.isUnderestimate = isUnderestimate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getUnderestimateRegion() {
		return underestimateRegion;
	}

	public void setUnderestimateRegion(String underestimateRegion) {
		this.underestimateRegion = underestimateRegion;
	}

	public String getCentreRegion() {
		return centreRegion;
	}

	public void setCentreRegion(String centreRegion) {
		this.centreRegion = centreRegion;
	}

	public int getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(int priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public String getPremiumPrice() {
		return premiumPrice;
	}

	public void setPremiumPrice(String premiumPrice) {
		this.premiumPrice = premiumPrice;
	}
	
	
}
