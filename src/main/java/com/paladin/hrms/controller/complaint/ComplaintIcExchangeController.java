package com.paladin.hrms.controller.complaint;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.hrms.controller.complaint.pojo.ComplaintIcExchangeDetailDTO;
import com.paladin.hrms.controller.complaint.pojo.ComplaintIcExchangeQuery;
import com.paladin.hrms.model.complaint.ComplaintIcExchange;
import com.paladin.hrms.model.complaint.ComplaintIcExchangeDetail;
import com.paladin.hrms.model.org.OrgPersonnel;
import com.paladin.hrms.service.complaint.ComplaintIcExchangeDetailService;
import com.paladin.hrms.service.complaint.ComplaintIcExchangeService;


/**   
* 身份证置换管理
* 
* @author jisanjie
* @version 2018年10月23日 上午10:09:27 
*/
@Controller
@RequestMapping("/hrms/complaint/ic/exchange")
public class ComplaintIcExchangeController extends ControllerSupport {
      
     @Autowired
     private ComplaintIcExchangeService complaintIcExchangeService;
     
     @Autowired
     private ComplaintIcExchangeDetailService complaintIcExchangeDetailService;
     

      
      /**
       * 跳首页
       * 
       */
      @RequestMapping("/index")
      @QueryInputMethod(queryClass = ComplaintIcExchangeQuery.class)
      public String index() {
          return "/hrms/complaint/ic_exchange_index";
      }
      
      /**
       * 查询
       * 
       */
      @RequestMapping("/find")
      @ResponseBody
      @QueryOutputMethod(queryClass = ComplaintIcExchangeQuery.class, paramIndex = 0)
      public Object findAll(ComplaintIcExchangeQuery query) {
          return CommonResponse.getSuccessResponse(complaintIcExchangeService.searchTableList(query));
      }
      
      /**
       * 跳往新增页
       * 
       */
      @RequestMapping("/to/add")
      public String toAdd(Model model,ComplaintIcExchange complaintIcExchange) {
          model.addAttribute("bOperate",1);//新增，操作为1：审查
          return "/hrms/complaint/ic_exchange_edit";
      }
      
      /**
       * 验证身份证
       * 
       */
      @ResponseBody
      @RequestMapping("/validate")
      public Object validate(String idcard) {
               return CommonResponse.getSuccessResponse(complaintIcExchangeService.validate(idcard));
            }
         
      /**
       * 保存置换信息
       * 
       */
      @RequestMapping("/save")
      public  String save(ComplaintIcExchange complaintIcExchange){
            List<OrgPersonnel>  listInit = complaintIcExchangeService.validate(complaintIcExchange.getInitiateIc());
            List<OrgPersonnel>  listFor = complaintIcExchangeService.validate(complaintIcExchange.getForExchangeIc());
            complaintIcExchange.setInitiateId(listInit.get(0).getId());
            complaintIcExchange.setForExchangeId((listFor.get(0).getId()));
            complaintIcExchange.setDealStatus(1);//1:已申请
            complaintIcExchangeService.save(complaintIcExchange);
            return "/hrms/complaint/ic_exchange_index";
      }
      
      
      
      /**
       * 跳往审查页
       * 
       */
      @RequestMapping("/to/check")
      public String toCheck(ComplaintIcExchange complaintIcExchange,Model model){
            List<OrgPersonnel>  listInit = complaintIcExchangeService.validate(complaintIcExchange.getInitiateIc());
            List<OrgPersonnel>  listFor = complaintIcExchangeService.validate(complaintIcExchange.getForExchangeIc());
            model.addAttribute("bid",complaintIcExchange.getId());
            model.addAttribute("initiatePersonnel",listInit.get(0));
            model.addAttribute("forExchangePersonnel",listFor.get(0));
            return "/hrms/complaint/ic_exchange_check";
      }
      
      /**
       * 审查/驳回
       * 
       */
      @RequestMapping("/achieve/check")
      public String achieveCheck(ComplaintIcExchangeDetailDTO detailDTO){
            ComplaintIcExchange complaintIcExchange=complaintIcExchangeService.get(detailDTO.getId());
            ComplaintIcExchangeDetail detail=new ComplaintIcExchangeDetail();
            if (1 == detailDTO.getDealResult()) {//通过
                  beanIncompleteCopy(detailDTO, detail);
                  complaintIcExchange.setDealStatus(ComplaintIcExchange.SUCCESSFUL_DEAL);
                  complaintIcExchangeDetailService.save(detail);
            }else if (0 == detailDTO.getDealResult()) {//驳回
                  beanIncompleteCopy(detailDTO, detail);
                  complaintIcExchange.setDealStatus(ComplaintIcExchange.EXCEPTIONAL_DEAL);
                  complaintIcExchangeDetailService.save(detail);
            }
            complaintIcExchangeService.update(complaintIcExchange);
            return "/hrms/complaint/ic_exchange_index"; 
      }
      
      /**
       * 查看
       * 
       */
      @RequestMapping("/view")
      public String view(ComplaintIcExchange complaintIcExchange,Model model){
            List<OrgPersonnel>  listInit = complaintIcExchangeService.validate(complaintIcExchange.getInitiateIc());
            List<OrgPersonnel>  listFor = complaintIcExchangeService.validate(complaintIcExchange.getForExchangeIc());
            ComplaintIcExchangeDetail complaintIcExchangeDetail = complaintIcExchangeDetailService.get(complaintIcExchange.getId());
            model.addAttribute("initiatePersonnel",listInit.get(0));
            model.addAttribute("forExchangePersonnel",listFor.get(0));
            model.addAttribute("complaintIcExchangeDetail",complaintIcExchangeDetail);
            return "/hrms/complaint/ic_exchange_detail";
      }
      
      @RequestMapping("/record")
      public String record(ComplaintIcExchange complaintIcExchange,Model model){
            List<OrgPersonnel>  listInit = complaintIcExchangeService.validate(complaintIcExchange.getInitiateIc());
            List<OrgPersonnel>  listFor = complaintIcExchangeService.validate(complaintIcExchange.getForExchangeIc());
            ComplaintIcExchangeDetail complaintIcExchangeDetail = complaintIcExchangeDetailService.get(complaintIcExchange.getId());
            model.addAttribute("initiatePersonnel",listInit.get(0));
            model.addAttribute("forExchangePersonnel",listFor.get(0));
            model.addAttribute("complaintIcExchangeDetail",complaintIcExchangeDetail);
            return "/hrms/complaint/ic_exchange_record";
      } 
           
       
}
