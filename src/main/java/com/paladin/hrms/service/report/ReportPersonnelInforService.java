package com.paladin.hrms.service.report;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.excel.write.DefaultWriteRow;
import com.paladin.framework.excel.write.ExcelWriter;
import com.paladin.framework.excel.write.WriteRow;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportPersonnelInforQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.mapper.report.ReportPersonnelInforMapper;
import com.paladin.hrms.service.org.dto.OrgPersonnelVO;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.List;

@Service
public class ReportPersonnelInforService {

	@Autowired
	private ReportPersonnelInforMapper reportPersonnelInforMapper;

	@SuppressWarnings("unused")
	public Page<ReportPersonnelInforDTO> searchTableList(
			ReportPersonnelInforQuery query) {
		Page<OrgPersonnelVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		return  reportPersonnelInforMapper.searchTableList(query);
	}

    private  static  final WriteRow reportPersonnelInforRow = DefaultWriteRow.createWriteRow(ReportPersonnelInforDTO.class, null);

    public void export(ReportPersonnelInforQuery query, ServletOutputStream outputStream) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        try {
            ExcelWriter<ReportPersonnelInforDTO> writer = new ExcelWriter<>(workbook, reportPersonnelInforRow);
            writer.openNewSheet("人员");

            int offset = 0;
            int limit = 500;

            PageResult<ReportPersonnelInforDTO> pageResult = null;
            List<ReportPersonnelInforDTO> data = null;

            int i = 0;
            while (true) {
                query.setOffset(offset);
                query.setLimit(limit);

                pageResult = searchTableListExport(query) ;
                data = pageResult.getData();
                writer.write(data);
                offset += limit;
                if (offset >= pageResult.getTotal()) {
                    break;
                }else {
                    i++;
                    pageResult = null;
                    data = null;
                    writer.openNewSheet("人员-"+ i);
                }
            }
            writer.output(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("导出Excel失败", e);
        }
    }

    private PageResult<ReportPersonnelInforDTO> searchTableListExport(ReportPersonnelInforQuery query) {
        Page<ReportPersonnelInforDTO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionUtil.DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        reportPersonnelInforMapper.searchTableListExport(query, permission);
        return new PageResult<>(page);
    }
}
