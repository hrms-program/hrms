package com.paladin.hrms.controller.org.pojo;
/**  
* 人员认领参数传递类
*  
* @author jisanjie
* @version 2018年11月26日 下午1:10:38 
*/
public class PersonnelClaimParamVO {
      
    private  String userId;
    private  String identificationNo;
    private  String userName;
    private  String agencyId;
    private  String goalAgencyName;
    
    
    public String getUserId() {
          return userId;
    }
    public void setUserId(String userId) {
          this.userId = userId;
    }
    public String getIdentificationNo() {
          return identificationNo;
    }
    public void setIdentificationNo(String identificationNo) {
          this.identificationNo = identificationNo;
    }
    public String getUserName() {
          return userName;
    }
    public void setUserName(String userName) {
          this.userName = userName;
    }
    public String getAgencyId() {
          return agencyId;
    }
    public void setAgencyId(String agencyId) {
          this.agencyId = agencyId;
    }
    public String getGoalAgencyName() {
          return goalAgencyName;
    }
    public void setGoalAgencyName(String goalAgencyName) {
          this.goalAgencyName = goalAgencyName;
    }
      
    
    
      
}
