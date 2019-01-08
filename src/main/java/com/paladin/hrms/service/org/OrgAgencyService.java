package com.paladin.hrms.service.org;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.excel.write.DefaultWriteRow;
import com.paladin.framework.excel.write.ExcelWriter;
import com.paladin.framework.excel.write.WriteRow;
import com.paladin.hrms.controller.org.pojo.OrgAgencyQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.core.OrgDistrictContainer;
import com.paladin.hrms.mapper.org.OrgAgencyMapper;
import com.paladin.hrms.model.org.OrgAgency;
import com.paladin.hrms.service.org.dto.OrgAgencyAccountVO;
import com.paladin.hrms.service.org.dto.OrgAgencyExportQueryDTO;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;

@Service
public class OrgAgencyService extends ServiceSupport<OrgAgency> {
    @Autowired
    private OrgAgencyMapper orgAgencyMapper;

	public int save(OrgAgency agency) {
		if (!OrgDistrictContainer.checkDistrictData(agency)) {
			throw new BusinessException("没有有效的所属区域");
		}
		return super.save(agency);
	}

	public int update(OrgAgency agency) {
		if (!OrgDistrictContainer.checkDistrictData(agency)) {
			throw new BusinessException("没有有效的所属区域");
		}
		return super.update(agency);
	}

    /**
     * 功能描述: <br>
     * 〈查询机构账号〉
     * @param
     * @param offset
     * @param llimit
     * @return:com.paladin.framework.common.PageResult<com.paladin.hrms.service.org.dto.OrgAgencyAccountVO>
     */
	public PageResult<OrgAgencyAccountVO> findAgencyAccountPage(OffsetPage offsetPage) {
        Page<OrgAgencyAccountVO> page = PageHelper.offsetPage(offsetPage.getOffset(),offsetPage.getLimit());
        DataPermissionCondition permissionCondition = DataPermissionUtil.getPermissionCondition(null, null);
        orgAgencyMapper.findAccount(permissionCondition);
        return new PageResult<>(page);
    }
	
	public PageResult<OrgAgency>findOrgAgency(OrgAgencyQuery query){
	    Page<OrgAgency> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        orgAgencyMapper.findAll(query, permission);
        return new PageResult<>(page);
	}

    private PageResult<OrgAgencyExportQueryDTO> findExportAgency(OrgAgencyQuery query) {
        Page<OrgAgencyExportQueryDTO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        orgAgencyMapper.findExportAgency(query, permission);
        return new PageResult<>(page);
    }

    private  static  final WriteRow orgAgencyExportRow = DefaultWriteRow.createWriteRow(OrgAgencyExportQueryDTO.class, null);

    /**
     * 功能描述: <br>
     * 〈机构信息导出〉
     * @param query
     * @param output
     * @return:void
     */
    public void export(OrgAgencyQuery query, OutputStream output) {

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        try {
            ExcelWriter<OrgAgencyExportQueryDTO> writer = new ExcelWriter<>(workbook, orgAgencyExportRow);
            writer.openNewSheet("机构信息统计表");

            int offset = 0;
            int limit = 600;

            PageResult<OrgAgencyExportQueryDTO> pageResult = null;
            List<OrgAgencyExportQueryDTO> data = null;

            int i = 0;
            while (true) {
                query.setOffset(offset);
                query.setLimit(limit);

                pageResult = findExportAgency(query) ;
                data = pageResult.getData();
                writer.write(data);
                offset += limit;
                if (offset >= pageResult.getTotal()) {
                    break;
                }else {
                    i++;
                    pageResult = null;
                    data = null;
                    writer.openNewSheet("机构信息统计表-"+ i);
                }
            }
            writer.output(output);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("导出Excel失败", e);
        }
    }


}