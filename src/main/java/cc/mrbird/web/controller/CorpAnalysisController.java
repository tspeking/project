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

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.web.domain.CorpVO;
import cc.mrbird.web.domain.corp.Corp;
import cc.mrbird.web.service.CorpService;

@Controller
@RequestMapping("corp")
public class CorpAnalysisController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CorpService corpService;
	
	@RequestMapping("index")
    @RequiresPermissions("corp:list")
	public String getFinanceList(){
		logger.info("Entrying CorpAnalysisController ... ");
		return "corp/list";
	}
	
	@RequestMapping("list")
	@RequiresPermissions("corp:list")
    @ResponseBody
    public Map<String, Object> corpList(QueryRequest request, CorpVO vo) {
        return super.selectByPageNumSize(request, () -> this.corpService.findCorps(vo, request));
    }
	
	@Log("新增企业信息")
    @RequiresPermissions("corp:add")
    @RequestMapping("add")
    @ResponseBody
    public ResponseBo addCorp(Corp entity) {
        try {
        	entity.setCreateTime(new Date());
            this.corpService.addCorp(entity);
            //test commit
            return ResponseBo.ok("新增宏观数据成功！");
        } catch (Exception e) {
        	logger.error("新增宏观数据失败", e);
            return ResponseBo.error("新增宏观失败，请联系网站管理员！");
        }
    }
	
	@Log("删除企业信息")
    @RequiresPermissions("corp:delete")
    @RequestMapping("delete")
    @ResponseBody
    public ResponseBo deleteCorps(String ids) {
        try {
            this.corpService.deleteCorps(ids);
            
            return ResponseBo.ok("删除企业成功！");
        } catch (Exception e) {
        	logger.error("删除企业失败", e);
            return ResponseBo.error("删除企业失败，请联系网站管理员！");
        }
    }
	
	@RequestMapping("getCorp")
    @ResponseBody
    public ResponseBo getCorp(Long corpId) {
        try {
        	Corp entity = this.corpService.findById(corpId);
            
            return ResponseBo.ok(entity);
        } catch (Exception e) {
            logger.error("获取企业信息失败", e);
            return ResponseBo.error("获取企业信息失败，请联系网站管理员！");
        }
    }
	
	@Log("修改企业信息")
    @RequiresPermissions("corp:update")
    @RequestMapping("update")
    @ResponseBody
    public ResponseBo updateCorp(Corp entity) {
        try {
            this.corpService.updateCorp(entity);
            
            return ResponseBo.ok("修改企业信息成功！");
        } catch (Exception e) {
            logger.error("修改企业信息失败", e);
            return ResponseBo.error("修改企业信息失败，请联系网站管理员！");
        }
    }
}
