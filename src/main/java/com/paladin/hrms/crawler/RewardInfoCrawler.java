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
import com.paladin.hrms.crawler.pojo.OrgPersonnelRewardInfoCrawler;

/**奖励信息   
 * @author 黄伟华
 * @version 2018年12月14日 下午5:21:52 
 */
public class RewardInfoCrawler extends PersonnelHrmsCrawler{
    
    
    public List<OrgPersonnelRewardInfoCrawler> crawl(String perId) throws IOException{
        
        List<OrgPersonnelRewardInfoCrawler> info = new ArrayList<OrgPersonnelRewardInfoCrawler>();
        
        Document d = getDocument("http://58.213.112.246/rlzy/medicalperson/exp?personid="+perId+"&exptype=jcjl_div", null,Method.POST,true);
        Elements tables = d.getElementsByClass("li-text");
        int awardid=0;
        for (Element table : tables) {
            
            OrgPersonnelRewardInfoCrawler reward = new OrgPersonnelRewardInfoCrawler();
            String id=table.getElementById("awardid"+awardid+"").val();
            
            Object list = getJson("http://58.213.112.246/rlzy/accessory/detail.json?id="+id+"&type=award", null,Method.POST,true);
            String aa=JSON.toJSONString(list);
            JSONObject object = JSONObject.parseObject(aa);
            JSONArray array = object.getJSONArray("accList");
            
            List<String> url = new ArrayList<>();
            for(int j = 0; j < array.size(); j++){
                JSONObject o = array.getJSONObject(j);
                url.add("58.213.112.246/rlzy/accessory/download?id="+o.get("id"));
            }
            reward.setId(id);
            reward.setPersonnelId(perId);
            reward.setRewardProject(table.getElementById("rewardsitem").val());
            reward.setRewardLevel(table.getElementById("grade").val());
            
            String rewardsCategory=table.getElementById("rewardsCategory").getElementsByAttribute("selected").text();
            reward.setRewardType(ConstantsContainer.getTypeKey("reward-type", rewardsCategory));
            reward.setRewardTime(toDate(table.getElementById("rewardstimeString").val()));
            reward.setAwardPrizeDept(table.getElementById("department").val());
            
            String iscancel=table.getElementById("rewardsCategory").getElementsByAttribute("selected").text();
            reward.setIsRevoke(ConstantsContainer.getTypeKey("boolean", iscancel));
            reward.setRewardMoney(table.getElementById("money").val());
            reward.setRewardReason(table.getElementById("reson").val());
            reward.setRewardIllustrate(table.getElementById("content").val());
            reward.setStatus(ConstantsContainer.getTypeKey("personal-info-status", table.getElementsByTag("font").text()));            
            reward.setUrl(url);
            info.add(reward);
            awardid++;
        }
        return info;
    }
    
}
