package com.paladin.hrms.controller.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.ExportExcel;
import com.paladin.framework.utils.Query;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.document.pojo.DocumentInforVO;
import com.paladin.hrms.service.document.DocumentInforService;

@Controller
@RequestMapping("/hrms/documentExport")
public class DocumentExportController extends ControllerSupport {
	
	private static final ObjectMapper MAPPER=new ObjectMapper();

	@Autowired
	private DocumentInforService documentInforService;
	
    /**
     * 首页
     * 
     * @return
     */
    @RequestMapping("/index")
    @QueryInputMethod(queryClass = DocumentInforVO.class)
    public String index() {
        return "/hrms/document/document_export_index";
    }
	
    /**
     * 查询
     * 
     * @param query
     * @return
     */
    @RequestMapping("/listForExport")
    @ResponseBody
    @QueryOutputMethod(queryClass = DocumentInforVO.class, paramIndex = 0)
    public Object findAll(@RequestParam Map<String, Object> params) {
    	Query query=new Query(params);
        return CommonResponse.getSuccessResponse(documentInforService.listForExport(query));
    }
	
    
    /**
	 * Excel导出
	 */
	@RequestMapping("/export")
	public void exportExcel(@RequestParam Map<String, Object> queryParams,
			HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	List<String> headers=new ArrayList<String>();//表头
	    	List<String> atts=new ArrayList<String>();//字段
	    	Map<String,Object> parms=new HashMap<String,Object>();//查询条件
	    	for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
	    		String key=entry.getKey();
	    		if(key.startsWith(SysContants.C_PREFIX)){//导出字段的表头
	    			String[] split = StringUtils.split(key, SysContants.EN_UNDER_LINE);
	    			int index=Integer.parseInt(split[1]);//第三位是数字，表示，生成Excel时是第几列
	    			headers.add(index, queryParams.get(key).toString());
	    			atts.add(index,split[2]);
	    		}else{
	    			parms.put(key, queryParams.get(key));
	    		}
	    	}
	    	//查询数据
	    	List<DocumentInforVO> documentList = documentInforService.getListForExport(queryParams);
	        // 数据
	        List<Object[]> dataList= new ArrayList<Object[]>();
	        for (DocumentInforVO document : documentList) {
	        	List<String> obj=new ArrayList<String>();
	        	String asString = MAPPER.writeValueAsString(document);
	        	JSONObject parseObject = JSONObject.parseObject(asString);
				for (String att : atts) {
					String value=null;
					if(StringUtils.contains(att, SysContants.EN_DOT)){//处理对象里的对象
						String[] split =StringUtils.split(att,SysContants.EN_DOT);
						JSONObject jsonObject = parseObject.getJSONObject(split[0]);
						value=jsonObject.getString(split[1]);
					}else{
						value = parseObject.getString(att);
					}
					obj.add(value);
				}
				dataList.add(obj.toArray());
			}
	        ExportExcel.exportExcel(request, response, "档案导出", headers, dataList, "yyyy-MM-dd");
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
	}
    
}
