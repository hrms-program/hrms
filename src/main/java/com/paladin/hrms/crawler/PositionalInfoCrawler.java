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
import com.paladin.hrms.crawler.pojo.OrgPersonnelPositionalInfoCrawler;

/**   
 * @author 黄伟华
 * @version 2018年12月17日 下午1:26:03 
 */
public class PositionalInfoCrawler extends PersonnelHrmsCrawler{
        
    
    public List<OrgPersonnelPositionalInfoCrawler> crawl(String perId) throws IOException{
        
        List<OrgPersonnelPositionalInfoCrawler> info = new ArrayList<OrgPersonnelPositionalInfoCrawler>();
        
        Document d = getDocument("http://58.213.112.246/rlzy/medicalperson/exp?personid="+perId+"&exptype=zcxx_div", null,Method.POST,true);
        Elements tables = d.getElementsByClass("li-text");
        for (Element table : tables) {
            
            OrgPersonnelPositionalInfoCrawler positional = new OrgPersonnelPositionalInfoCrawler();
            String id=table.getElementById("id").val();
            
            Object list = getJson("http://58.213.112.246/rlzy/accessory/detail.json?id="+id+"&type=title", null,Method.POST,true);
            String aa=JSON.toJSONString(list);
            JSONObject object = JSONObject.parseObject(aa);
            JSONArray array = object.getJSONArray("accList");
            
            List<String> url = new ArrayList<>();            
            for(int j = 0; j < array.size(); j++){
                JSONObject o = array.getJSONObject(j);
                url.add("58.213.112.246/rlzy/accessory/download?id="+o.get("id"));
            }
            
            positional.setId(id);
            positional.setPersonnelId(perId);
            positional.setPositionalTitleName(table.getElementById("titlename").val());
            String titletype=table.getElementById("titletype").getElementsByAttribute("selected").text();
            positional.setPositionalTitleType(ConstantsContainer.getTypeKey("positional-type", titletype));
            String titlelevel=table.getElementById("titlelevel").getElementsByAttribute("selected").text();
            positional.setPositionalLevelLevel(ConstantsContainer.getTypeKey("positional-level-level", titlelevel));
            positional.setPositionalTitleTime(toDate(table.getElementById("titletimeString").val()));
            positional.setStatus(ConstantsContainer.getTypeKey("personal-info-status", table.getElementsByTag("font").text()));
            positional.setUrl(url);
            
            info.add(positional);
        }
        return info;
    }
   
}
