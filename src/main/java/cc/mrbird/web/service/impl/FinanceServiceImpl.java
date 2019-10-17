package cc.mrbird.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.FinanceTrendMapper;
import cc.mrbird.web.domain.FinanceTrend;
import cc.mrbird.web.domain.FinanceTrendVO;
import cc.mrbird.web.service.FinanceService;

@Service("financeService")
public class FinanceServiceImpl extends BaseService<FinanceTrend> implements FinanceService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FinanceTrendMapper financeTrendMapper;

	@Override
	public Map<String, Object> getFinanceInfo(FinanceTrendVO vo) {
		Map<String, Object> data = new HashMap<>();
		List<String> categories = new ArrayList<>();
		List<Double> seriesData = new ArrayList<>();
		
		List<FinanceTrend> list = financeTrendMapper.findFinanceTrends(vo);
		log.info(JSON.toJSONString(list));
		if (null != list && list.size() > 0) {
			for (FinanceTrend entity: list) {
				categories.add(entity.getQuotaName());
				seriesData.add(Double.parseDouble(entity.getQuotaValue()));
			}
		}
		
		data.put("categories", categories);
		data.put("seriesData", seriesData);
		
		return data;
	}

	@Override
	public List<FinanceTrend> findFinanceTrend(FinanceTrendVO vo, QueryRequest request) {
		List<FinanceTrend> list = financeTrendMapper.findFinanceTrends(vo);
		
		return list;
	}

	@Override
	@Transactional
	public void addMacrography(FinanceTrend entity) {
		this.save(entity);
	}

	@Override
	@Transactional
	public void deleteMacrographys(String ids) {
		List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "financeId", FinanceTrend.class);
	}

	@Override
	@Transactional
	public void updateMacrography(FinanceTrend entity) {
		
		this.updateNotNull(entity);
	}

	@Override
	public FinanceTrend findById(Long financeId) {
		
		return financeTrendMapper.findFinanceTrend(financeId);
	}

}
