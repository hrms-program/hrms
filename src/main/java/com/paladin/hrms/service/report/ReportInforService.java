package com.paladin.hrms.service.report;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.excel.write.DefaultWriteRow;
import com.paladin.framework.excel.write.ExcelWriter;
import com.paladin.framework.excel.write.WriteRow;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.report.confirm.pojo.AgencyReportInforExport;
import com.paladin.hrms.controller.report.pojo.ReportInforDTO;
import com.paladin.hrms.controller.report.pojo.ReportInforQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.report.ReportInforMapper;
import com.paladin.hrms.model.report.ReportPersonnelInfor;
import com.paladin.hrms.service.org.OrgPersonnelService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.Date;
import java.util.List;


@Service
public class ReportInforService {
	
	@Autowired
	private ReportInforMapper reportInforMapper; 
	
	@Autowired
	private OrgPersonnelService personnelService;

	public PageResult<ReportInforDTO> searchTableList(ReportInforQuery query) {
	    Page<ReportInforDTO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        reportInforMapper.searchTableList(query, permission);
        return new PageResult<>(page);
	}

	public PageResult<ReportInforDTO> searchOrgList(ReportInforQuery query) {
	    Page<ReportInforDTO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        reportInforMapper.searchOrgList(query, permission);
        return new PageResult<>(page);
	}

    private  static  final WriteRow reportInforRow = DefaultWriteRow.createWriteRow(ReportInforDTO.class, null);
    private  static  final WriteRow agencyReportInforRow = DefaultWriteRow.createWriteRow(AgencyReportInforExport.class, null);

    /**
     * 功能描述: <br>
     * 〈个案信息上报统计导出〉
     * @param query
     * @param output
     * @return:void
     */
    public void export(ReportInforQuery query, ServletOutputStream output) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        try {
            ExcelWriter<ReportInforDTO> writer = new ExcelWriter<>(workbook, reportInforRow);
            writer.openNewSheet("机构");

            int offset = 0;
            int limit = 500;

            PageResult<ReportInforDTO> pageResult = null;
            List<ReportInforDTO> data = null;

            int i = 0;
            while (true) {
                query.setOffset(offset);
                query.setLimit(limit);

                pageResult = searchTableList(query) ;
                data = pageResult.getData();
                writer.write(data);
                offset += limit;
                if (offset >= pageResult.getTotal()) {
                    break;
                }else {
                    i++;
                    pageResult = null;
                    data = null;
                    writer.openNewSheet("机构-"+ i);
                }
            }
            writer.output(output);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("导出Excel失败", e);
        }
    }

	public void reportPersonal(String personnelId) {
		//根据personnelId查询上报记录是否存在
		ReportPersonnelInfor oldEntity = reportInforMapper.findOldEntity(personnelId);
		if(oldEntity!=null){
			reportInforMapper.removeReportPersonnelInfor(personnelId);
		}

		ReportPersonnelInfor dto = new ReportPersonnelInfor();
		dto.setId(UUIDUtil.createUUID());
		dto.setPersonnelId(personnelId);
		dto.setName(personnelService.get(personnelId).getName());
		dto.setReportState(SysContants.ACTIVE);
		dto.setReportTime(new Date());
		dto.setReportUser(UserSession.getCurrentUserSession().getAccount());
		dto.setIsDelete(SysContants.IS_DELETE);
		reportInforMapper.insertReportPersonnelInfor(dto);
	}


    public void export2(ReportInforQuery query, ServletOutputStream outputStream) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        try {
            ExcelWriter<AgencyReportInforExport> writer = new ExcelWriter<>(workbook, agencyReportInforRow);
            writer.openNewSheet("机构");

            int offset = 0;
            int limit = 500;

            PageResult<AgencyReportInforExport> pageResult = null;
            List<AgencyReportInforExport> data = null;

            int i = 0;
            while (true) {
                query.setOffset(offset);
                query.setLimit(limit);

                pageResult = searchOrgListExport(query) ;
                data = pageResult.getData();
                writer.write(data);
                offset += limit;
                if (offset >= pageResult.getTotal()) {
                    break;
                }else {
                    i++;
                    pageResult = null;
                    data = null;
                    writer.openNewSheet("机构-"+ i);
                }
            }
            writer.output(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("导出Excel失败", e);
        }
    }

    private PageResult<AgencyReportInforExport> searchOrgListExport(ReportInforQuery query) {
        Page<AgencyReportInforExport> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        reportInforMapper.searchOrgListExport(query, permission);
        return new PageResult<>(page);
    }
}
