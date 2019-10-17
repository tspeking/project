package cc.mrbird.web.service;

import java.util.List;
import java.util.Map;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.web.domain.FinanceTrend;
import cc.mrbird.web.domain.FinanceTrendVO;

public interface FinanceService {

	/**
	 * 获取宏观信息
	 * @return
	 */
	Map<String, Object> getFinanceInfo(FinanceTrendVO vo);
	
	/**
	 * 获取宏观数据列表
	 * @param vo
	 * @param request
	 * @return
	 */
	List<FinanceTrend> findFinanceTrend(FinanceTrendVO vo, QueryRequest request);
	
	/**
	 * 添加宏观数据
	 * @param entity
	 */
	void addMacrography(FinanceTrend entity);
	
	/**
	 * 删除宏观数据
	 * @param ids
	 */
	void deleteMacrographys(String ids);
	
	/**
	 * 获取单条宏观数据
	 * @param financeId
	 * @return
	 */
	FinanceTrend findById(Long financeId);
	
	/**
	 * 修改宏观数据
	 * @param entity
	 */
	void updateMacrography(FinanceTrend entity);
}
