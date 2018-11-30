package com.paladin.hrms.controller.complaint.pojo;


import com.paladin.framework.common.OffsetPage;

/**   
* @author jisanjie
* @version 2018年10月23日 上午10:18:09 
*/
public class ComplaintIcExchangeQuery extends OffsetPage {
      
      private String  ic;

      private Integer dealStatus;
      
      private String  name;
      
      
      
      public String getName() {
            return name;
      }

      public String getIc() {
            return ic;
      }

      public void setIc(String ic) {
            this.ic = ic;
      }

      public void setName(String name) {
            this.name = name;
      }

      public Integer getDealStatus() {
            return dealStatus;
      }

      public void setDealStatus(Integer dealStatus) {
            this.dealStatus = dealStatus;
      }

      
      
}
