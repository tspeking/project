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
import cc.mrbird.web.domain.FinanceTrend;
import cc.mrbird.web.domain.FinanceTrendVO;
import cc.mrbird.web.service.FinanceService;

@Controller
public class FinanceController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private FinanceService financeService;
	
	@RequestMapping("finance/info")
    @RequiresPermissions("finance:list")
	public String getFinanceList(){
		
		return "finance/info";
	}
	
	@RequestMapping("finance/financeInfo")
    @ResponseBody
    public String getFinanceInfo(FinanceTrendVO vo) {
		logger.info("input param:{}",JSON.toJSONString(vo));
        return JSON.toJSONString(financeService.getFinanceInfo(vo));
    }
	
	@RequestMapping("macrography/list")
    @RequiresPermissions("macrography:list")
	public String getMacrographyList(){
		
		return "finance/macrography";
	}
	
	@RequestMapping("macrography/lists")
    @RequiresPermissions("macrography:list")
    @ResponseBody
    public Map<String, Object> macrographyList(QueryRequest request, FinanceTrendVO vo) {
        return super.selectByPageNumSize(request, () -> this.financeService.findFinanceTrend(vo, request));
    }
	
	@Log("新增宏观数据")
    @RequiresPermissions("macrography:add")
    @RequestMapping("macrography/add")
    @ResponseBody
    public ResponseBo addMacrography(FinanceTrend entity) {
        try {
        	entity.setCrateTime(new Date());
            this.financeService.addMacrography(entity);
            
            return ResponseBo.ok("新增宏观数据成功！");
        } catch (Exception e) {
        	logger.error("新增宏观数据失败", e);
            return ResponseBo.error("新增宏观失败，请联系网站管理员！");
        }
    }
	
	@Log("删除宏观数据")
    @RequiresPermissions("macrography:delete")
    @RequestMapping("macrography/delete")
    @ResponseBody
    public ResponseBo deleteMacrographys(String ids) {
        try {
            this.financeService.deleteMacrographys(ids);
            
            return ResponseBo.ok("删除宏观数据成功！");
        } catch (Exception e) {
        	logger.error("删除宏观数据失败", e);
            return ResponseBo.error("删除宏观数据失败，请联系网站管理员！");
        }
    }
	
	@RequestMapping("macrography/getMacrography")
    @ResponseBody
    public ResponseBo getMacrography(Long financeId) {
        try {
        	FinanceTrend entity = this.financeService.findById(financeId);
            
            return ResponseBo.ok(entity);
        } catch (Exception e) {
            logger.error("获取宏观数据失败", e);
            return ResponseBo.error("获取宏观数据失败，请联系网站管理员！");
        }
    }
	
	@Log("修改宏观数据")
    @RequiresPermissions("macrography:update")
    @RequestMapping("macrography/update")
    @ResponseBody
    public ResponseBo updateMacrography(FinanceTrend entity) {
        try {
            this.financeService.updateMacrography(entity);
            
            return ResponseBo.ok("修改宏观数据成功！");
        } catch (Exception e) {
            logger.error("修改宏观数据失败", e);
            return ResponseBo.error("修改宏观数据失败，请联系网站管理员！");
        }
    }
}
