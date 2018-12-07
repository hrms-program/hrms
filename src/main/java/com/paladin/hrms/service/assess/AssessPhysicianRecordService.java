package com.paladin.hrms.service.assess;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.excel.write.DefaultWriteRow;
import com.paladin.framework.excel.write.ExcelWriter;
import com.paladin.framework.excel.write.WriteRow;
import com.paladin.hrms.controller.assess.pojo.PhysicianAssessQuery;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.assess.AssessPhysicianRecordMapper;
import com.paladin.hrms.model.assess.AssessPhysicianRecord;
import com.paladin.hrms.service.assess.dto.AssessPhysicianRecordExport;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;

@Service
public class AssessPhysicianRecordService extends ServiceSupport<AssessPhysicianRecord>{
    
    @Autowired
    AssessPhysicianRecordMapper assessPhysicianRecordMapper;
    
    public PageResult<AssessPhysicianRecord> findAssessPhysicianRecord(PhysicianAssessQuery query) {
        Page<AssessPhysicianRecord> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        assessPhysicianRecordMapper.findAssessPhysicianRecord(query, permission);
        return new PageResult<>(page);
    } 

    public int getAssessCycle(String identificationNo,String assessCycle){
        List<AssessPhysicianRecord> titles = searchAll(new GeneralCriteriaBuilder.Condition[] {
            new GeneralCriteriaBuilder.Condition(AssessPhysicianRecord.COLUMN_IDENTIFICATION_NO, QueryType.EQUAL, identificationNo),
            new GeneralCriteriaBuilder.Condition(AssessPhysicianRecord.COLUMN_ASSESS_CYCLE, QueryType.EQUAL, assessCycle)});
         return titles.size();
    }

    public PageResult<List<AssessPhysicianRecordExport>> findExportPersonnel(PhysicianAssessQuery query) {
        Page<List<AssessPhysicianRecordExport>> page  =  PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        assessPhysicianRecordMapper.findExportPersonnelByQuery(query,permission);
        return  new PageResult<>(page);
    }


private  static  final  WriteRow assessPhysicianRecordRow = DefaultWriteRow.createWriteRow(AssessPhysicianRecordExport.class, null);
    /**
     * 导出Excel
     *
     * @param query
     * @param output
     */
    public void export( PhysicianAssessQuery query, OutputStream output) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        try {
            ExcelWriter<List<AssessPhysicianRecordExport>> writer = new ExcelWriter<>(workbook, assessPhysicianRecordRow);
            writer.openNewSheet("医师定期考核统计表");

            int offset = 0;
            int limit = 500;

            PageResult<List<AssessPhysicianRecordExport>> pageResult = null;
            List<List<AssessPhysicianRecordExport>> data = null;

            int i = 0;
            while (true) {
                query.setOffset(offset);
                query.setLimit(limit);

                pageResult = findExportPersonnel(query) ;
                data = pageResult.getData();
                writer.write(data);
                offset += limit;
                if (offset >= pageResult.getTotal()) {
                    break;
                }else {
                    i++;
                    pageResult = null;
                    data = null;
                    writer.openNewSheet("医师定期考核统计表-"+ i);
                }
            }
            writer.output(output);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("导出Excel失败", e);
        }
    }
}