package cc.mrbird.web.service;

import java.util.List;
import java.util.Map;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.web.domain.corp.CorpTrend;
import cc.mrbird.web.domain.corp.CorpTrendVO;
import cc.mrbird.web.domain.corp.QuotaSearchVo;

public interface CorpTrendService {
	/**
	 * 查询企业指标趋势信息列表
	 * @param vo
	 * @param request
	 * @return
	 */
	List<CorpTrend> findCorpTrends(CorpTrendVO vo, QueryRequest request);
	
	/**
	 * 新增企业指标趋势信息
	 * @param CorpTrend
	 */
	void addCorpTrend(CorpTrend corpTrend);
	
	/**
	 * 删除企业指标趋势信息
	 * @param ids
	 */
	void deleteCorpTrends(String ids);
	
	/**
	 * 获取单个企业指标趋势信息
	 * @param quotaId
	 * @return
	 */
	CorpTrend findById(Long quotaId);
	
	/**
	 * 修改企业信息
	 * @param entity
	 */
	void updateCorpTrend(CorpTrend entity);
	
	/**
	 * 获取单线图数据
	 * @param vo
	 * @return
	 */
	Map<String, Object> getSingleChartData(QuotaSearchVo vo);
}
