package com.paladin.hrms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.utils.qrcode.QRCodeException;
import com.paladin.framework.utils.qrcode.QRCodeUtil;
import com.paladin.hrms.model.syst.SysApp;
import com.paladin.hrms.service.syst.SysAppService;

/**   
 * @author 黄伟华
 * @version 2018年12月6日 下午12:40:05 
 */
@Controller
@RequestMapping(value = "/app")
public class DownloadAppController{
    
    @Autowired
    SysAppService sysAppService;
    
    @Autowired
    SysAttachmentService sysAttachmentService;
    
    
    /**
     * 获取app下载url
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/obtain/url")
    public Object obtainUrl(HttpServletRequest request, HttpServletResponse response){
        
        SysApp app = sysAppService.sysAppById();
        
        if (app == null){return null;}
        SysAttachment attachment = sysAttachmentService.get(app.getAttachmentId());
        
        String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/file/" + attachment.getPelativePath() + "";
        try{
            QRCodeUtil.createQRCode(basePath, response.getOutputStream());// 生成二维码
        }
        catch (QRCodeException | IOException e){
        }
        return null;
    }
}
