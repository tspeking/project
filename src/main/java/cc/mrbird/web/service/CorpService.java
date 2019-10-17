package cc.mrbird.web.service;

import java.util.List;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.web.domain.CorpVO;
import cc.mrbird.web.domain.corp.Corp;

public interface CorpService {
	
	/**
	 * 查询企业信息列表
	 * @param vo
	 * @param request
	 * @return
	 */
	List<Corp> findCorps(CorpVO vo, QueryRequest request);
	
	/**
	 * 新增企业信息
	 * @param corp
	 */
	void addCorp(Corp corp);
	
	/**
	 * 删除企业
	 * @param ids
	 */
	void deleteCorps(String ids);
	
	/**
	 * 获取单个企业信息
	 * @param corpId
	 * @return
	 */
	Corp findById(Long corpId);
	
	/**
	 * 修改企业信息
	 * @param entity
	 */
	void updateCorp(Corp entity);
}
