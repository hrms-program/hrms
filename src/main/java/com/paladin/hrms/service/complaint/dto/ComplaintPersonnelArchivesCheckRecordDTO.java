package com.paladin.hrms.service.complaint.dto;

/**   
* @author jisanjie
* @version 2018年11月3日 下午1:07:23 
*/
public class ComplaintPersonnelArchivesCheckRecordDTO{
      
      private String id;
      
      private String checkContent;
      
      private Integer status;
      
      private String checkOpinion;

      public String getId() {
            return id;
      }

      public void setId(String id) {
            this.id = id;
      }

      public String getCheckContent() {
            return checkContent;
      }

      public void setCheckContent(String checkContent) {
            this.checkContent = checkContent;
      }

      public Integer getStatus() {
            return status;
      }

      public void setStatus(Integer status) {
            this.status = status;
      }

      public String getCheckOpinion() {
            return checkOpinion;
      }

      public void setCheckOpinion(String checkOpinion) {
            this.checkOpinion = checkOpinion;
      }
      
      
      
}
