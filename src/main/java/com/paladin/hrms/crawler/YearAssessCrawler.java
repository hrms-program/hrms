package com.paladin.hrms.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.framework.utils.StringUtil;
import com.paladin.hrms.crawler.pojo.OrgPersonnelYearAssessCrawler;


/** 年度考核  
 * @author 黄伟华
 * @version 2018年12月12日 下午3:08:27 
 */
public class YearAssessCrawler extends PersonnelHrmsCrawler{
    
    public List<OrgPersonnelYearAssessCrawler> crawl(String perId) throws IOException{
        
        List<OrgPersonnelYearAssessCrawler> year = new ArrayList<OrgPersonnelYearAssessCrawler>();
        
        Document d = getDocument("http://58.213.112.246/rlzy/medicalperson/exp?personid="+perId+"&exptype=ndkh_div", null,Method.POST,true);
        Elements tables = d.getElementsByClass("li-text");
        for (Element table : tables) {
            OrgPersonnelYearAssessCrawler crawler = new OrgPersonnelYearAssessCrawler();
            crawler.setId(table.getElementById("id").val());
            String checkresult=table.getElementById("checkresult").getElementsByAttribute("selected").text();
            crawler.setPersonnelId(perId);
            crawler.setYear(table.getElementById("annual").val());
            crawler.setWages(table.getElementById("lastyearwage").val());
            crawler.setBonus(table.getElementById("lastyearbonus").val());
            crawler.setOtherIncome(table.getElementById("otherincome").val());
            if(StringUtil.isNotEmpty(checkresult)){
                crawler.setAssessResult(ConstantsContainer.getTypeKey("assess-result", checkresult));
            }
            crawler.setTechnicalPost(table.getElementById("employ").val());
            crawler.setAssessIllustrate(table.getElementById("checkexplain").val());
            year.add(crawler);
        }
        return year;
    }
    
}
