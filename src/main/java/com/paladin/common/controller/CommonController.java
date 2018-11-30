package com.paladin.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.framework.web.response.CommonResponse;

@Controller
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private SysAttachmentService attachmentService;

	@RequestMapping("/constant")
	@ResponseBody
	public Object enumConstants(@RequestParam("code") String[] code) {
		return CommonResponse.getSuccessResponse(ConstantsContainer.getTypeChildren(code));
	}

	@RequestMapping("/attachment/{id}")
	@ResponseBody
	public Object getAttachment(@PathVariable("id") String id) {
		return CommonResponse.getSuccessResponse(attachmentService.get(id));
	}
	
	
	@RequestMapping("/attachment")
	@ResponseBody
	public Object getAttachments(@RequestParam("id[]") String[] ids) {
		return CommonResponse.getSuccessResponse(attachmentService.getAttachment(ids));
	}

	@RequestMapping("/upload/files")
	@ResponseBody
	public Object uploadFiles(@RequestParam("files") MultipartFile[] files, @RequestParam(value  = "filenames", required = false) String[] names) {
		SysAttachment[] result = new SysAttachment[files.length];
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String filename = (names != null && names.length > i) ? names[i] : null;
			result[i] = attachmentService.createAttachment(file, filename);
		}
		return CommonResponse.getSuccessResponse(result);
	}

	@RequestMapping("/upload/images")
	@ResponseBody
	public Object uploadImages(@RequestParam("imageStr")String imageStr, @RequestParam(value = "imageName", required = false) String imageName) {
		if(imageStr==null||imageStr.length()==0){
			return CommonResponse.getErrorResponse("上传图片的图片为空");
		}
		SysAttachment result = new SysAttachment();
		result = attachmentService.createAttachment(imageStr,imageName);
		return CommonResponse.getSuccessResponse(result);
	}
	
	@RequestMapping("/upload/file")
	@ResponseBody
	public Object uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(name = "filename", required = false) String name) {
		return CommonResponse.getSuccessResponse(attachmentService.createAttachment(file, name));
	}
	
	/*@RequestMapping("/remove")
	public Object remove(@RequestParam("id") String id) {
		return CommonResponse.getResponse(attachmentService.removeByPrimaryKey(id));
	}*/
}
