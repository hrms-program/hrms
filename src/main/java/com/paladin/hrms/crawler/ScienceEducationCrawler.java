package com.paladin.hrms.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.hrms.crawler.pojo.OrgPersonnelScienceEducationCrawler;

/** 科教信息  
 * @author 黄伟华
 * @version 2018年12月17日 上午10:22:08 
 */
public class ScienceEducationCrawler extends PersonnelHrmsCrawler{
    
    public List<OrgPersonnelScienceEducationCrawler> crawl(String perId) throws IOException{
        
        List<OrgPersonnelScienceEducationCrawler> scienceEducation = new ArrayList<OrgPersonnelScienceEducationCrawler>();
        
        Document d = getDocument("http://58.213.112.246/rlzy/medicalperson/exp?personid="+perId+"&exptype=kycg_div", null,Method.POST,true);
        Elements tables = d.getElementsByClass("li-text");
        
        int scienttype=0;
        
        for (Element table : tables) {
            OrgPersonnelScienceEducationCrawler education = new OrgPersonnelScienceEducationCrawler();
            String id=table.getElementById("id").val();
            
            Object list =getJson("http://58.213.112.246/rlzy/accessory/detail.json?id="+id+"&type=scient", null,Method.POST,true);
            String aa=JSON.toJSONString(list);
            JSONObject object = JSONObject.parseObject(aa);
            JSONArray array = object.getJSONArray("accList");
            List<String> url = new ArrayList<>();
            for(int j = 0; j < array.size(); j++){
                JSONObject o = array.getJSONObject(j);
                url.add("58.213.112.246/rlzy/accessory/download?id="+o.get("id"));
            }
            education.setId(id);
            education.setPersonnelId(perId);
            String rewardsCategory=table.getElementById("scienttype"+scienttype).getElementsByAttribute("selected").text();
            education.setScienceEducationType(ConstantsContainer.getTypeKey("science-education-type", rewardsCategory));
            education.setName(table.getElementById("sname").val());
            education.setElement(table.getElementById("factor").val());
            education.setTopicNumber(table.getElementById("pnumber").val());
            education.setTopicSource(table.getElementById("itemsource").val());
            education.setYear(table.getElementById("annual").val());
            education.setMoney(table.getElementById("money").val());
            education.setPrizeIssuingOrgan(table.getElementById("certificateorgan").val());
            education.setPrizeGrade(table.getElementById("awardlevel").val());
            education.setAwardProject(table.getElementById("foritem").val());
            education.setAwardTime(toDate(table.getElementById("getawardtime").val()));
            education.setAwardTopic(table.getElementById("jxRelatedId").val());
            education.setPaperMagazine(table.getElementById("magazine").val());
            education.setSciInfluenceFactor(table.getElementById("sicimpactfactor").val());
            education.setPaperFirstAuthor(table.getElementById("firstauthor").val());
            education.setPaperCommunicationAuthor(table.getElementById("communicationauthor").val());
            education.setPaperReleaseTime(toDate(table.getElementById("reporttimeString").val()));
            String dissertationtype=table.getElementById("dissertationtype").getElementsByAttribute("selected").text();
            education.setPaperType(ConstantsContainer.getTypeKey("paper-type", dissertationtype));
            education.setPaperRelationTopic(table.getElementById("lwRelatedId").val());
            education.setDept(table.getElementById("setdepartment").val());
            education.setEndTime(toDate(table.getElementById("finishtimeString").val()));
            education.setOneselfRanking(table.getElementById("ranking").val());
            education.setStatus(ConstantsContainer.getTypeKey("personal-info-status", table.getElementsByTag("font").text()));          
            education.setUrl(url);
           
            scienceEducation.add(education);
            scienttype++;
        }
        return scienceEducation;
    }
 
}
