package com.paladin.hrms.controller.document;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.model.document.DocumentAttachment;
import com.paladin.hrms.service.document.DocumentAttachmentService;


/**
 * author netmatch
 * date  2017/5/24 0024 下午 3:40.
 */
@Controller
@RequestMapping("/hrms/attachment")
public class DocumentAttachmentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${attachment.path}")
    private String webUploadPath;
    
    @Autowired
    private DocumentAttachmentService attachmentService;
    
    @RequestMapping("/index")
    public String index(){
        return "attachment/index";
    }

    //文件上传相关代码
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Object upload(@RequestParam("attachment") MultipartFile file,String type,String remarks,boolean createPDF) {
        if (file.isEmpty()) {
            return CommonResponse.getFailResponse("文件为空");
        }
        // 获取文件名
        String origFileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + origFileName);
        // 获取文件的后缀名
        String suffixName = origFileName.substring(origFileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 解决中文问题，liunx下中文路径，图片显示问题
        String uuid=UUIDUtil.createUUID();
        String fileName = uuid+ suffixName;
       
        // 检测是否存在目录
        String filePath = webUploadPath.replace("file:", "") + UserSession.getCurrentUserSession().getAccount() + File.separator
				+ type;
        String url=UserSession.getCurrentUserSession().getAccount() + File.separator+type+File.separator+fileName;
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File dest = new File(dir + File.separator +fileName);
        try {
            file.transferTo(dest);
            if(createPDF&&!suffixName.endsWith(".pdf")){//TODO 生成PDF文件
            	return CommonResponse.getFailResponse("只能上传PDF,请注意文件格式！");
            }
            //将附件信息插入数据库
            DocumentAttachment attach=new DocumentAttachment();
            attach.setAttachName(fileName);
            attach.setOriginalName(origFileName);
            attach.setUrl(url);
            attach.setSuffix(suffixName.replace(SysContants.EN_DOT, ""));
            attach.setType(type);
            attach.setRemarks(remarks);
            attach=attachmentService.saveAttachment(attach);
            return CommonResponse.getSuccessResponse("上传成功",attach);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return CommonResponse.getFailResponse("文件上传失败！");
        } catch (IOException e) {
            e.printStackTrace();
            return CommonResponse.getFailResponse("文件上传失败！");
        }
    }

    //文件下载相关代码
    @RequestMapping("/download")
    public String downloadFile(String id, HttpServletRequest request,HttpServletResponse response){
    	DocumentAttachment attach=attachmentService.queryAttachmentById(id);
        if (attach!=null&&attach.getAttachName() != null) {
            File file = new File(webUploadPath.replace("file:", ""), attach.getUrl());
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                String fileName=attach.getOriginalName();
                setDownLoadDisposition(request, response, fileName); // 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                OutputStream os = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (os != null) {
                        try {
                        	os.flush();
                        	os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

	private void setDownLoadDisposition(HttpServletRequest request,
			HttpServletResponse response, String fileName){
			if(request==null||response==null||StringUtils.isBlank(fileName)){
				throw new BusinessException("文件下载出错！");
			}
		    try {
		    	if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
				response.setHeader("Content-Disposition","attachment;"+ "filename="+ new   
						String(fileName.getBytes("GBK"),"ISO8859-1"));
		    	}else{//firefox、chrome、safari、opera  
		    		response.setHeader("Content-Disposition","attachment;"+  
		    				"filename="+ new String(fileName.getBytes("UTF8"), "ISO8859-1") );  
		    	}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
	}
   

    /**
     * 删除附件
     * @param id
     * @return
     */
    @RequestMapping(value="/remove", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteAttachment(String id){
    	if(StringUtils.isBlank(id)){
    		return CommonResponse.getFailResponse("id为空");
    	}
    	attachmentService.deleteAttachmentById(id);
    	return CommonResponse.getSuccessResponse("删除成功");
    }
    
}
