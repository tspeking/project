package cc.mrbird.web.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.CorpMapper;
import cc.mrbird.web.domain.CorpVO;
import cc.mrbird.web.domain.corp.Corp;
import cc.mrbird.web.service.CorpService;

@Service("corpService")
public class CorpServiceImpl extends BaseService<Corp> implements CorpService {
	
	@Autowired
	private CorpMapper corpMapper;
	
	@Override
	public List<Corp> findCorps(CorpVO vo, QueryRequest request) {
		List<Corp> list = corpMapper.findCorps(vo);
		
		return list;
	}

	@Override
	@Transactional
	public void addCorp(Corp corp) {
		this.save(corp);
	}

	@Override
	@Transactional
	public void deleteCorps(String ids) {
		List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "corpId", Corp.class);
	}

	@Override
	public Corp findById(Long corpId) {
		
		return corpMapper.findCorp(corpId);
	}

	@Override
	public void updateCorp(Corp entity) {
		entity.setLastModifyTime(new Date());
		this.updateNotNull(entity);
	}

}
