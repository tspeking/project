package cc.mrbird.web.dao;

import java.util.List;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.CorpVO;
import cc.mrbird.web.domain.corp.Corp;

public interface CorpMapper extends MyMapper<Corp>{
	List<Corp> findCorps(CorpVO vo);
	Corp findCorp(Long corpId);
}
