package cc.mrbird.web.dao;

import java.util.List;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.corp.CorpTrend;
import cc.mrbird.web.domain.corp.CorpTrendVO;
import cc.mrbird.web.domain.corp.QuotaSearchVo;

public interface CorpTrendMapper extends MyMapper<CorpTrend>{
	List<CorpTrend> findCorpTrends(CorpTrendVO vo);
	CorpTrend findCorpTrend(Long quotaId);
	List<CorpTrend> getSingleChartData(QuotaSearchVo vo);
}
