package com.paladin.hrms.service.complaint.dto;


/**   
* @author jisanjie
* @version 2018年10月23日 下午1:16:18 
*/
public class ComplaintIcExchangeDTO {
      
      private String  initiateName;
      
      private String  forExchangeName;
      
      private String  initiateIc;
         
      private String  forExchangeIc;
      
      private Integer dealStatus;
      
      private String id;

      public String getInitiateName() {
            return initiateName;
      }

      public void setInitiateName(String initiateName) {
            this.initiateName = initiateName;
      }

      public String getForExchangeName() {
            return forExchangeName;
      }

      public void setForExchangeName(String forExchangeName) {
            this.forExchangeName = forExchangeName;
      }

      public String getInitiateIc() {
            return initiateIc;
      }

      public void setInitiateIc(String initiateIc) {
            this.initiateIc = initiateIc;
      }

      public String getForExchangeIc() {
            return forExchangeIc;
      }

      public void setForExchangeIc(String forExchangeIc) {
            this.forExchangeIc = forExchangeIc;
      }

      public Integer getDealStatus() {
            return dealStatus;
      }

      public void setDealStatus(Integer dealStatus) {
            this.dealStatus = dealStatus;
      }

      public String getId() {
            return id;
      }

      public void setId(String id) {
            this.id = id;
      }
      
      
      
      
}
