package com.paladin.hrms.service.analysis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.excel.write.DefaultWriteRow;
import com.paladin.framework.excel.write.ExcelWriter;
import com.paladin.framework.excel.write.WriteRow;
import com.paladin.hrms.core.DataPermissionUtil;
import com.paladin.hrms.core.DataPermissionUtil.DataPermissionCondition;
import com.paladin.hrms.mapper.analysis.AnalysisAgencyDataScoreMapper;
import com.paladin.hrms.model.analysis.AnalysisAgencyDataScore;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyDataScoreQueryDTO;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyDataScoreVO;
import com.paladin.hrms.service.analysis.dto.AnalysisAgencyExport;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;

@Service
public class AnalysisAgencyDataScoreService extends ServiceSupport<AnalysisAgencyDataScore>{
	
	@Autowired
	private AnalysisAgencyDataScoreMapper analysisAgencyDataScoreMapper;
	
	public PageResult<AnalysisAgencyDataScoreVO> findAgencyScore(AnalysisAgencyDataScoreQueryDTO query) {
		Page<AnalysisAgencyDataScoreVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		String agencyId = query.getAgencyId();
		String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
		DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
		analysisAgencyDataScoreMapper.findAgencyScore(query, permission);
		return new PageResult<>(page);
	}

    private PageResult<AnalysisAgencyExport> findAgencyData(AnalysisAgencyDataScoreQueryDTO query) {
        Page<AnalysisAgencyExport> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        String agencyId = query.getAgencyId();
        String[] agencyIds = (agencyId == null || agencyId.length() == 0) ? null : new String[] { agencyId };
        DataPermissionCondition permission = DataPermissionUtil.getPermissionCondition(null, agencyIds);
        analysisAgencyDataScoreMapper.findAgencyScoreExport(query, permission);
        return new PageResult<>(page);
    }

    private  static  final WriteRow analysisAgencyRow = DefaultWriteRow.createWriteRow(AnalysisAgencyExport.class, null);

    public void export(AnalysisAgencyDataScoreQueryDTO query, OutputStream output) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        try {
            ExcelWriter<AnalysisAgencyExport> writer = new ExcelWriter<>(workbook, analysisAgencyRow);
            writer.openNewSheet("机构");

            int offset = 0;
            int limit = 500;

            PageResult<AnalysisAgencyExport> pageResult = null;
            List<AnalysisAgencyExport> data = null;

            int i = 0;
            while (true) {
                query.setOffset(offset);
                query.setLimit(limit);

                pageResult = findAgencyData(query) ;
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

}