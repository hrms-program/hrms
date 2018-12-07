package com.paladin.hrms.controller.syst;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.syst.pojo.SysAppDTO;
import com.paladin.hrms.controller.syst.pojo.SysAppQuery;
import com.paladin.hrms.controller.syst.pojo.SysAppVO;
import com.paladin.hrms.model.syst.SysApp;
import com.paladin.hrms.service.syst.SysAnnouncementService;
import com.paladin.hrms.service.syst.SysAppService;

/**   
 * @author 黄伟华
 * @version 2018年12月5日 上午9:34:17 
 */
@Controller
@RequestMapping("/hrms/system/app/")
public class SysAppController  extends ControllerSupport{
    
    @Autowired
    SysAppService sysAppService;
    
    @Autowired
    SysAnnouncementService sysAnnouncementService;
    
    @Autowired
    private SysAttachmentService attachmentService;
    
    /**
     * 首页
     * 
     * @return
     */
    @RequestMapping("/index")
    @QueryInputMethod(queryClass = SysAppQuery.class)
    public String index() {
        return "/hrms/syst/app_index";
    }
    
    /**
     * 查询
     * 
     * @param query
     * @return
     */
    @RequestMapping("/find")
    @ResponseBody
    @QueryOutputMethod(queryClass = SysAppQuery.class, paramIndex = 0)
    public Object findAll(SysAppQuery query) {
        return CommonResponse.getSuccessResponse(sysAppService.searchPage(query));
    }
    
    /**
     * 新增
     * 
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("object", new SysApp());
        return "/hrms/syst/app_edit";
    }
    
    @RequestMapping("/edit")
    public String toEdit(@RequestParam(required = false) String id, Model model) {
        model.addAttribute("id", id);
        return "/hrms/syst/app_edit";
    }
    
    /**
     * 编辑
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Object edit(@RequestParam String id, ModelAndView model) {
        SysApp sysApp = sysAppService.get(id);
        if (sysApp == null) {
            throw new BusinessException("编辑的内容不存在");
        }
        SysAppVO sysAppVO = beanIncompleteCopy(sysApp, new SysAppVO());
        return CommonResponse.getSuccessResponse(sysAppVO);
    }
    
    /**
     * 新增,修改
     * @param dto
     * @param bindingResult
     * @param attachmentFiles
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object addSave(@Valid SysAppDTO dto,BindingResult bindingResult,
        @RequestParam(required = false) MultipartFile[] attachmentFiles){
        if (bindingResult.hasErrors()) {
            return validErrorHandler(bindingResult);
        }
        List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(dto.getAttachmentId(), attachmentFiles);
        if (attachments != null && attachments.size() > 2) {
            return CommonResponse.getErrorResponse("数量不能超过2张");
        }
        dto.setAttachmentId(attachments.get(0).getId());
        String id = dto.getId();

        SysApp model = beanCopy(dto, (id == null || id.length() == 0) ? new SysApp() : sysAppService.get(id));
        if (sysAppService.saveOrUpdate(model) > 0) {
            return CommonResponse.getSuccessResponse(true);
        }

        return CommonResponse.getFailResponse();
    }

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public Object remove(@RequestParam String id) {
        return CommonResponse.getResponse(sysAppService.removeByPrimaryKey(id));
    }
    
}
