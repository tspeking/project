package cc.mrbird.web.dao;

import java.util.List;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.FinanceTrend;
import cc.mrbird.web.domain.FinanceTrendVO;

public interface FinanceTrendMapper extends MyMapper<FinanceTrend>{
	
	List<FinanceTrend> findFinanceTrends(FinanceTrendVO vo);
	
	FinanceTrend findFinanceTrend(Long financeId);
}
