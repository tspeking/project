package cc.mrbird.web.controller;

import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.web.domain.corp.CorpTrend;
import cc.mrbird.web.domain.corp.CorpTrendVO;
import cc.mrbird.web.domain.corp.QuotaSearchVo;
import cc.mrbird.web.service.CorpTrendService;

@Controller
@RequestMapping("quota")
public class CorpTrendController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CorpTrendService corpTrendService;
	
	@RequestMapping("index")
    @RequiresPermissions("quota:index")
	public String getQuotaList(){
		logger.info("Entrying CorpTrendController ... ");
		return "quota/list";
	}
	
	@RequestMapping("list")
	@RequiresPermissions("quota:index")
    @ResponseBody
    public Map<String, Object> corpTrendList(QueryRequest request, CorpTrendVO vo) {
        return super.selectByPageNumSize(request, () -> this.corpTrendService.findCorpTrends(vo, request));
    }
	
	@Log("新增企业指标信息")
    @RequiresPermissions("quota:add")
    @RequestMapping("add")
    @ResponseBody
    public ResponseBo addCorpTrend(CorpTrend entity) {
        try {
        	entity.setCreateTime(new Date());
        	entity.setLastModifyTime(new Date());
            this.corpTrendService.addCorpTrend(entity);
            
            return ResponseBo.ok("新增指标数据成功！");
        } catch (Exception e) {
        	logger.error("新增指标数据失败", e);
            return ResponseBo.error("新增指标失败，请联系网站管理员！");
        }
    }
	
	@Log("删除企业指标信息")
    @RequiresPermissions("quota:delete")
    @RequestMapping("delete")
    @ResponseBody
    public ResponseBo deleteCorpTrends(String ids) {
        try {
            this.corpTrendService.deleteCorpTrends(ids);
            
            return ResponseBo.ok("删除企业指标成功！");
        } catch (Exception e) {
        	logger.error("删除企业指标失败", e);
            return ResponseBo.error("删除企业指标失败，请联系网站管理员！");
        }
    }
	
	@RequestMapping("getCorpTrend")
    @ResponseBody
    public ResponseBo getCorpTrend(Long quotaId) {
        try {
        	CorpTrend entity = this.corpTrendService.findById(quotaId);
            
            return ResponseBo.ok(entity);
        } catch (Exception e) {
            logger.error("获取企业指标信息失败", e);
            return ResponseBo.error("获取企业指标信息失败，请联系网站管理员！");
        }
    }
	
	@Log("修改企业指标信息")
    @RequiresPermissions("quota:update")
    @RequestMapping("update")
    @ResponseBody
    public ResponseBo updateCorpTrend(CorpTrend entity) {
        try {
            this.corpTrendService.updateCorpTrend(entity);
            
            return ResponseBo.ok("修改企业指标信息成功！");
        } catch (Exception e) {
            logger.error("修改企业指标信息失败", e);
            return ResponseBo.error("修改企业指标信息失败，请联系网站管理员！");
        }
    }
	
	@RequestMapping("trend")
    @RequiresPermissions("quota:trend")
	public String getQuotaTrend(){
		logger.info("Entrying getQuotaTrend ... ");
		return "quota/charts";
	}
	
	@RequestMapping("getSingleChartData")
    @ResponseBody
    public String getSingleChartData(QuotaSearchVo vo) {
		logger.info("input param:{}",JSON.toJSONString(vo));
        return JSON.toJSONString(corpTrendService.getSingleChartData(vo));
    }
}
