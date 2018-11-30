package com.paladin.hrms.thymeleaf;

import java.util.List;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paladin.hrms.core.HrmsUserSession;
import com.paladin.hrms.core.OrgDistrictContainer;
import com.paladin.hrms.core.OrgDistrictContainer.District;

public class DistrictTagProcessor extends AbstractElementTagProcessor {

	private static final String TAG_NAME = "agency";// 标签名
	private static final int PRECEDENCE = 10000;// 优先级

	public DistrictTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, // 此处理器将仅应用于HTML模式
				dialectPrefix, // 要应用于名称的匹配前缀
				TAG_NAME, // 标签名称：匹配此名称的特定标签
				true, // 将标签前缀应用于标签名称
				null, // 无属性名称：将通过标签名称匹配
				false, // 没有要应用于属性名称的前缀
				PRECEDENCE); // 优先(内部方言自己的优先)
	}

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag, final IElementTagStructureHandler structureHandler) {
		String html = null;
		List<District> districts = null;
		String id = null;

		final String type = tag.getAttributeValue("type");

		if ("all".equals(type)) {
			districts = OrgDistrictContainer.getRootDistrict();
			id = "tonto_district_all_value";
		} else {
			districts = HrmsUserSession.getCurrentUserSession().getManageDistrict();
			id = "tonto_district_owned_value";
		}

		if (districts != null && districts.size() > 0) {
			try {
				html = objectMapper.writeValueAsString(districts);
			} catch (JsonProcessingException e) {
			}
		}

		html = "<div id='" + id + "' style='display:none'>" + html + "</div>";
		structureHandler.replaceWith(html, false);
	}
}
