package com.paladin.hrms.service.org;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.excel.write.ExcelWriter;
import com.paladin.framework.excel.write.ValueFormator;
import com.paladin.framework.excel.write.WriteColumn;
import com.paladin.framework.excel.write.WriteRow;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.org.OrgPersonnelMapper;
import com.paladin.hrms.service.org.dto.OrgPersonnelExportQueryDTO;
import com.paladin.hrms.service.org.dto.OrgPersonnelExportSimpleVO;
import com.paladin.hrms.service.util.ConvertDeptConstant;
import com.paladin.hrms.service.util.ConvertTechQualificationConstant;

@Service
public class OrgPersonnelExportService {

	@Autowired
	private OrgPersonnelMapper personnelMapper;

	public PageResult<OrgPersonnelExportSimpleVO> findSimpleExportPersonnel(OrgPersonnelExportQueryDTO query) {
		Page<OrgPersonnelExportSimpleVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		personnelMapper.findSimpleExportPersonnel(query, permission);
		return new PageResult<>(page);
	}

	/**
	 * 分页查找导出的人员信息（带最高学历等教育信息）
	 * 
	 * @param query
	 * @return
	 */
	public PageResult<Map<String, Object>> findExportPersonnel(OrgPersonnelExportQueryDTO query) {
		Page<Map<String, Object>> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		personnelMapper.findExportPersonnel(query, permission);
		return new PageResult<>(page);
	}

	/**
	 * 分页查找导出的人员信息（不带最高学历等教育信息）
	 * 
	 * @param query
	 * @return
	 */
	public PageResult<Map<String, Object>> findExportPersonnelWithoutEducation(OrgPersonnelExportQueryDTO query) {
		Page<Map<String, Object>> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		personnelMapper.findExportPersonnelWithoutEducation(query, permission);
		return new PageResult<>(page);
	}

	/**
	 * 导出Excel
	 * 
	 * @param columnNames
	 * @param query
	 * @param output
	 */
	public void export(String[] columnNames, OrgPersonnelExportQueryDTO query, OutputStream output) {

		if (columnNames == null || columnNames.length == 0) {
			return;
		}
		// 判断是否有教育学历内容
		boolean hasEducation = false;

		List<WriteColumn> columns = new ArrayList<>(columnNames.length);
		for (String name : columnNames) {
			ExportColumn column = columnMap.get(name);
			if (column != null) {
				columns.add(column);
				if (educationColumns.contains(column.key)) {
					hasEducation = true;
				}
			}
		}

		int i = 0;
		for (WriteColumn column : columns) {
			column.setCellIndex(i++);
			column.resetCellStyle();
		}

		ExportRow row = new ExportRow();
		row.setColumns(columns);

		SXSSFWorkbook workbook = new SXSSFWorkbook();
		try {
			ExcelWriter<Map<String, Object>> writer = new ExcelWriter<>(workbook, row);
			writer.openNewSheet("人员信息");

			int offset = 0;
			int limit = 500;

			PageResult<Map<String, Object>> pageResult = null;
			List<Map<String, Object>> data = null;

			while (true) {
				query.setOffset(offset);
				query.setLimit(limit);
				// 带学历内容会影响查询效率，所以这里判断区分两种情况
				pageResult = hasEducation ? findExportPersonnel(query) : findExportPersonnelWithoutEducation(query);
				data = pageResult.getData();
				writer.write(data);
				offset += limit;

				if (offset >= pageResult.getTotal()) {
					break;
				}

				pageResult = null;
				data = null;
			}
			writer.output(output);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("导出Excel失败", e);
		}
	}

	private final static Map<String, ExportColumn> columnMap;
	private final static Set<String> educationColumns;

	static {
		columnMap = new HashMap<>();
		educationColumns = new HashSet<>();

		List<ExportColumn> columns = new ArrayList<>();
		columns.add(new ExportColumn("name", "姓名", 8));
		columns.add(new ExportColumn("sex", "性别", "sex", 6));
		columns.add(new ExportColumn("identificationType", "证件类型", "identification-type", 10));
		columns.add(new ExportColumn("identificationNo", "证件号码", 20));
		columns.add(new ExportColumn("nation", "民族", "nation", 12));
		columns.add(new ExportColumn("birthday", "出生日期", 12));
		columns.add(new ExportColumn("startWorkTime", "参加工作日期", 12));
		columns.add(new ExportColumn("cellphone", "手机号", 12));
		columns.add(new ExportColumn("officePhone", "办公室电话", 15));
		columns.add(new ExportColumn("nationality", "国籍", "nationality", 10));
		columns.add(new ExportColumn("joinPartyTime", "入党/团时间", 12));
		columns.add(new ExportColumn("nativePlace", "籍贯", 30));

		columns.add(new ExportColumn("agencyName", "所属机构", 25));
		columns.add(new ExportColumn("dept", "所在科室代码", 10));
		columns.add(new ExportColumn("dept", "所在科室名称", new ConvertDeptConstant(), 20));
		columns.add(new ExportColumn("deptName", "实际科室名称", 20));
		columns.add(new ExportColumn("major", "从事专业", "major", 20));
		columns.add(new ExportColumn("technicalQualification", "专业技术资格(评)", new ConvertTechQualificationConstant(), 30));
		columns.add(new ExportColumn("techPost", "专业技术职务(聘)", "tech-post", 10));
		columns.add(new ExportColumn("gainDate", "专业技术资格取得时间", 12));
		columns.add(new ExportColumn("duty", "行政/业务管理职务", "duty", 20));
		columns.add(new ExportColumn("formation", "编制情况", "formation", 15));
		columns.add(new ExportColumn("inorout", "年内人员流动情况", "inorout", 30));
		columns.add(new ExportColumn("inoroutDate", "调入/调出时间", 12));

		columns.add(new ExportColumn("personnelType", "人员类型", "personnel-type", 10));
		columns.add(new ExportColumn("docCertCode", "医师/卫生监督员执业证书编码", 25));
		columns.add(new ExportColumn("practiceCategory", "医师执业类别", "practice-category-type", 15));
		columns.add(new ExportColumn("practiceScope", "医师执业范围", 20));
		columns.add(new ExportColumn("expertise", "专科特长", 20));
		columns.add(new ExportColumn("isMultisite", "是否多地点执业医师", "boolean", 5));
		columns.add(new ExportColumn("secondCategory", "第2执业单位的机构类别", "practice-category", 20));
		columns.add(new ExportColumn("thirdCategory", "第3执业单位的机构类别", "practice-category", 20));
		columns.add(new ExportColumn("docTrainCert", "全科医生取得培训合格证书情况", 20));
		columns.add(new ExportColumn("isDispatched", "是否由乡镇卫生院或社区卫生服务机构派驻村卫生室工作", "boolean", 10));
		columns.add(new ExportColumn("vdocCertCode", "乡村医生执业证书编号", 25));
		columns.add(new ExportColumn("nurseCertCode", "护士执业证书编号", 25));
		columns.add(new ExportColumn("workYears", "从事乡村医生工作年限", 8));
		columns.add(new ExportColumn("isOnjobTrain", "高中及以下学历乡村医生是否为在职培训合格者", "boolean", 10));

		columns.add(new ExportColumn("topEducation", "最高学历", "education", 12));
		columns.add(new ExportColumn("topAcademicDegree", "学位", "academic-degree", 12));
		columns.add(new ExportColumn("topEducationMajor", "所学专业", "major-type", 20));

		educationColumns.add("topEducation");
		educationColumns.add("topAcademicDegree");
		educationColumns.add("topEducationMajor");

		for (ExportColumn column : columns) {
			columnMap.put(column.key, column);
		}
	}

	private static class ExportColumn extends WriteColumn {

		private String key;

		private ExportColumn(String key, String name, int width) {
			this.key = key;
			setName(name);
			setWidth(width);
			setAlignment(CellStyle.ALIGN_CENTER);
		}

		private ExportColumn(String key, String name, String enumType, int width) {
			this.key = key;
			setName(name);
			setEnumType(enumType);
			setWidth(width);
			setAlignment(CellStyle.ALIGN_CENTER);
		}

		private ExportColumn(String key, String name, ValueFormator formator, int width) {
			this.key = key;
			setName(name);
			setValueFormator(formator);
			setWidth(width);
			setAlignment(CellStyle.ALIGN_CENTER);
		}

		private ThreadLocal<SimpleDateFormat> formatLocal = new ThreadLocal<SimpleDateFormat>() {
			public SimpleDateFormat initialValue() {
				return new SimpleDateFormat("yyyy-MM-dd");
			}
		};

		public void setCellValue(Cell cell, Object value) {
			if (value != null && value instanceof Date) {
				cell.setCellValue(formatLocal.get().format((Date) value));
			} else {
				super.setCellValue(cell, value);
			}
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Object peelData(Object data) {
			if (data instanceof Map) {
				return ((Map) data).get(key);
			}
			return null;
		}

		@Override
		public String getEnumName(Object value) {
			if (value == null)
				return "";
			return ConstantsContainer.getTypeValue(getEnumType(), (Integer) value);
		}

		private ThreadLocal<Integer> cellIndexLocal = new ThreadLocal<>();

		public int getCellIndex() {
			return cellIndexLocal.get();
		}

		public void setCellIndex(int cellIndex) {
			cellIndexLocal.set(cellIndex);
		}
	}

	private static class ExportRow extends WriteRow {
		@Override
		public Object peelData(Object data) {
			return data;
		}
	}

}
