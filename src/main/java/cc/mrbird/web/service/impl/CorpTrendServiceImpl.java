package cc.mrbird.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import cc.mrbird.common.util.quota.Quota;
import cc.mrbird.web.dao.CorpTrendMapper;
import cc.mrbird.web.domain.FinanceTrend;
import cc.mrbird.web.domain.corp.Corp;
import cc.mrbird.web.domain.corp.CorpTrend;
import cc.mrbird.web.domain.corp.CorpTrendVO;
import cc.mrbird.web.domain.corp.QuotaSearchVo;
import cc.mrbird.web.service.CorpTrendService;

@Service("corpTrendService")
public class CorpTrendServiceImpl extends BaseService<CorpTrend> implements CorpTrendService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CorpTrendMapper corpTrendMapper;
	
	@Override
	public List<CorpTrend> findCorpTrends(CorpTrendVO vo, QueryRequest request) {
		List<CorpTrend> list = corpTrendMapper.findCorpTrends(vo);
		
		if(list != null && list.size() > 0) {
			for(CorpTrend entity:list){
				entity.setQuotaName(Quota.getQuotaName(entity.getQuotaName()));
			}
		}
		
		return list;
	}

	@Override
	@Transactional
	public void addCorpTrend(CorpTrend corpTrend) {
		this.save(corpTrend);

	}

	@Override
	@Transactional
	public void deleteCorpTrends(String ids) {
		List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "quotaId", CorpTrend.class);

	}

	@Override
	public CorpTrend findById(Long quotaId) {
		CorpTrend entity = corpTrendMapper.findCorpTrend(quotaId);
		return entity;
	}

	@Override
	public void updateCorpTrend(CorpTrend entity) {
		entity.setLastModifyTime(new Date());
		this.updateNotNull(entity);
	}

	@Override
	public Map<String, Object> getSingleChartData(QuotaSearchVo vo) {
		Map<String, Object> data = new HashMap<>();
		List<String> categories = new ArrayList<>();
		List<Double> seriesData = new ArrayList<>();
		
		List<CorpTrend> list = corpTrendMapper.getSingleChartData(vo);
		log.info(JSON.toJSONString(list));
		if (null != list && list.size() > 0) {
			for (CorpTrend entity: list) {
				categories.add(entity.getQuotaTime());
				seriesData.add(Double.parseDouble(entity.getQuotaValue()));
			}
		}
		
		data.put("categories", categories);
		data.put("seriesData", seriesData);
		
		return data;
	}

}
