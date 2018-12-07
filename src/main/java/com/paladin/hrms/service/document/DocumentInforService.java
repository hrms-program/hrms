package com.paladin.hrms.service.document;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.UserSession;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.DateUtil;
import com.paladin.framework.utils.ExcelUtils;
import com.paladin.framework.utils.IdcardUtils;
import com.paladin.framework.utils.Query;
import com.paladin.framework.utils.SysContants;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.hrms.controller.document.pojo.DocumentInforVO;
import com.paladin.hrms.mapper.document.DocumentInforMapper;
import com.paladin.hrms.model.document.DocumentInfor;

@Service
public class DocumentInforService {
	@Autowired
	private DocumentWorkingExperienceService workingExperienceService;
	@Autowired
	private DocumentLearningExperienceService learningExperienceService;
	@Autowired
	private DocumentTrainingExperienceService trainingExperienceService;
	@Autowired
	private DocumentRewardSituationService rewardSituationService;
	@Autowired
	private DocumentAnnualCheckService annualCheckService;
	@Autowired
	private DocumentAdministrativePostService administrativePostService;
	@Autowired
	private DocumentInnerpartyPostService innerpartyPostService;
	@Autowired
	private DocumentProfessionalTitleService professionalTitleService;
	@Autowired
	private DocumentFamilyMemberService familyMemberService;
//	@Autowired
//	private DictService dictService;
	@Autowired
	private DocumentInforMapper documentInforMapper;
//	@Autowired
//	private DeptService deptService;

	public List<DocumentInfor> list(Map<String, Object> map) {

		return enhance(getList(map));
	}

	public List<DocumentInfor> getList(Map<String, Object> map) {

		return documentInforMapper.list(map);
	}

	/**
	 * 查询导出字段，包含现任行政职务和现任党内职务
	 * 
	 * @param map
	 * @return
	 */
	public List<DocumentInforVO> getListForExport(Map<String, Object> map) {

		return documentInforMapper.listForExport(map);
	}

	private List<DocumentInfor> enhance(List<DocumentInfor> list) {

		if (CollectionUtils.isEmpty(list)) {
			return list;
		}
		for (DocumentInfor document : list) {
			enhanceAllProperties(document);
		}
		return list;
	}

	/**
	 * 填充全部字段
	 * 
	 * @param document
	 * @return
	 */
	private DocumentInfor enhanceAllProperties(DocumentInfor document) {
		// document.setSexName(dictService.getName(DictStore.sex,document.getSex()));
//		if(StringUtils.isNotBlank(document.getDeptCode())){
//			document.setDeptCodeName(deptService.getNameByCode(document.getDeptCode()));
//		}
//		if (StringUtils.isNotBlank(document.getNation())) {// 民族
//			document.setNationName(dictService.getName(DictStore.nation,
//					document.getNation()));
//		}
//		if (StringUtils.isNotBlank(document.getParty())) {// 党派
//			document.setPartyName(dictService.getName(DictStore.politics,
//					document.getParty()));
//		}
//		if (StringUtils.isNotBlank(document.getPersonnelCompilation())) {// 人事编制
//			document.setPersonnelCompilationName(dictService.getName(
//					DictStore.personnel_compilation,
//					document.getPersonnelCompilation()));
//		}
//		if (StringUtils.isNotBlank(document.getPersonType())) {// 人员性质,取自数据字person_type（公务员，参公干部，参公工人，事业干部，事业工人）
//			document.setPersonTypeName(dictService.getName(
//					DictStore.person_type, document.getPersonType()));
//		}
//		if (StringUtils.isNotBlank(document.getJobLevel())) {// 现职务层次
//			document.setJobLevelName(dictService.getName(DictStore.job_level,
//					document.getJobLevel()));
//		}
//		if (StringUtils.isNotBlank(document.getManagementLevel())) {// 管理层次（市管干部、部管干部、委管干部、其他）
//			document.setManagementLevelName(dictService.getName(
//					DictStore.management_level, document.getManagementLevel()));
//		}
//		if (StringUtils.isNotBlank(document.getProfessionalSort())) {// 专业类别，来自数据字典professional_sort
//			document.setProfessionalSortName(dictService.getName(
//					DictStore.professional_sort, document.getProfessionalSort()));
//		}
//		if (StringUtils.isNotBlank(document.getWorkStatus())) {// 在岗情况（在岗，规培。。。）
//			document.setWorkStatusName(dictService.getName(
//					DictStore.work_status, document.getWorkStatus()));
//		}
//		if (StringUtils.isNotBlank(document.getInitialEducation())) {// 最初学历
//			document.setInitialEducationName(dictService.getName(
//					DictStore.education_level, document.getInitialEducation()));
//		}
//		if (StringUtils.isNotBlank(document.getHighestEducation())) {// 最高学历
//			document.setHighestEducationName(dictService.getName(
//					DictStore.education_level, document.getHighestEducation()));
//		}
//		// 以下是 “是否”的转换
//		if (StringUtils.isNotBlank(document.getIsGeneral())) {// 是否全科医生
//			document.setIsGeneralName(SwitchState.getMsg(document
//					.getIsGeneral()));
//		}
//		if (StringUtils.isNotBlank(document.getIsSecondment())) {// 是否借出
//			document.setIsSecondmentName(SwitchState.getMsg(document
//					.getIsSecondment()));
//		}
		return document;
	}

	public int count(Map<String, Object> map) {

		return documentInforMapper.count(map);
	}

	public int remove(String id) {

		return documentInforMapper.remove(id);
	}

	public void batchRemove(String[] ids) {

		documentInforMapper.batchRemove(ids);
	}

	public DocumentInforVO queryDocumentById(String id) {
		DocumentInforVO result = new DocumentInforVO();
		//先查org_personnel表基本信息
		try {
			BeanUtils.copyProperties(result, documentInforMapper.selectByPrimaryKey(id));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 查询相关经历
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("relationId", result.getIdentificationNo());//关联身份证号
		// 1.工作经历
		result.setWorkingExperienceList(workingExperienceService.list(params));
		// 2.教育经历
		result.setLearningExperienceList(learningExperienceService.list(params));
		// 3.职称信息
		result.setProfessionalTitleList(professionalTitleService.list(params));
		// 4.培训经历
		result.setTrainingExperienceList(trainingExperienceService.list(params));
		// 5.奖惩情况
		result.setRewardSituationList(rewardSituationService.list(params));
		// 6.年度考核
		result.setAnnualCheckList(annualCheckService.list(params));
		// 6.家庭成员
		result.setFamilyMemberList(familyMemberService.list(params));
		// 7.行政职务经历
		result.setAdministrativePostList(administrativePostService.list(params));
		// 8.最近的行政职务
		if (CollectionUtils.isNotEmpty(result.getAdministrativePostList())) {
			result.setCurrentAdministrativePost(result.getAdministrativePostList().get(0));
			
		}

		// 9.党内职务经历
		result.setInnerpartyPostList(innerpartyPostService.list(params));
		// 10.最近党内职务经历
		if (CollectionUtils.isNotEmpty(result.getInnerpartyPostList())) {
			result.setCurrentInnerpartyPost(result.getInnerpartyPostList().get(
					0));
		}

		return result;
	}

	// 基本信息保存
	@SuppressWarnings("unused")
	@Transactional
	public void saveInfo(DocumentInfor document) {
		// 基本信息，工作信息保存
		// 判断身份证号码是否已存在
		String user=UserSession.getCurrentUserSession().getAccount();
		String now=DateUtil.getCurrentTime();
		
		document.setId(UUIDUtil.createUUID());
		document.setBusiId(document.getId());
		document.setCreateAt(DateUtil.getCurrentTime());
		document.setUpdateAt(DateUtil.getCurrentTime());
		document.setCreateId(user);
		document.setUpdateId(document.getCreateId());
		document.setState(SysContants.ACTIVE);
		documentInforMapper.saveInfo(document);
	/*	// 现任行政职务保存
		DocumentAdministrativePost currentAdministrativePost = document
				.getCurrentAdministrativePost();
		currentAdministrativePost.setRelationId(document.getIdnumber());
		currentAdministrativePost.setId(UUIDUtil.createUUID());
		currentAdministrativePost.setBusiId(currentAdministrativePost.getId());
		currentAdministrativePost.setCreateAt(DateUtil.getCurrentTime());
		currentAdministrativePost.setCreateId(user);
		currentAdministrativePost.setUpdateAt(DateUtil.getCurrentTime());
		currentAdministrativePost.setUpdateId(user);
		currentAdministrativePost.setState(SysContants.ACTIVE);
		administrativePostService
				.saveCurrentAdministrativePost(currentAdministrativePost);

		// 现任党内职务保存
		DocumentInnerpartyPost currentInnerpartyPost = document
				.getCurrentInnerpartyPost();
		currentInnerpartyPost.setRelationId(document.getIdnumber());
		currentInnerpartyPost.setId(UUIDUtil.createUUID());
		currentInnerpartyPost.setBusiId(currentInnerpartyPost.getId());
		currentInnerpartyPost.setCreateAt(DateUtil.getCurrentTime());
		currentInnerpartyPost.setCreateId(user);
		currentInnerpartyPost.setUpdateAt(DateUtil.getCurrentTime());
		currentInnerpartyPost.setUpdateId(user);
		currentInnerpartyPost.setState(SysContants.ACTIVE);
		innerpartyPostService.saveCurrentInnerpartyPost(currentInnerpartyPost);*/
		
		
		
	}

	// 基本信息修改保存
	@Transactional
	public void saveOrUpdateBasicInfo(DocumentInfor document) {

		DocumentInfor oldEntity = documentInforMapper.queryDocumentByBusiId(document
				.getBusiId());
		if (oldEntity == null) {
			throw new BusinessException("数据库中查不到该记录！");
		}

		// document.setCreateAt(oldEntity.getCreateAt());
		// document.setCreateId(oldEntity.getCreateId());
		document.setUpdateAt(DateUtil.getCurrentTime());
		document.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		documentInforMapper.updateByPrimaryKeySelective(document);
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(document.getUpdateAt());
		oldEntity.setUpdateId(document.getUpdateId());
		oldEntity.setState(SysContants.DELETED);
		documentInforMapper.saveInfo(oldEntity);

	}

	/**
	 * 导出页面的查询列表
	 */
	@SuppressWarnings("unused")
	public PageResult<DocumentInforVO> listForExport(Query query) {
		Page<DocumentInforVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		List<DocumentInforVO> listForExport = getListForExport(query);
		return new PageResult<>(page);
	}

	/**
	 * 用户信息导入
	 */
	@SuppressWarnings({ "unused"})
	@Transactional
	public List<DocumentInfor> importExcel(MultipartFile file) throws Exception {
		Sheet sheet = ExcelUtils.getSheet(file);
		int rows = sheet.getLastRowNum();// 一共有多少行
		if (rows == 0) {
			throw new BusinessException("请填写数据");
		}
		List<String> idnumbers=new ArrayList<String>();
		List<DocumentInfor> userList=new ArrayList<DocumentInfor>();
		//部门表
//		List<DeptDO> deptDbList = deptService.list(new HashMap<String,Object>());
		Map<String,String> orgMap=new HashMap<String,String>();
//		if(CollectionUtils.isNotEmpty(deptDbList)){
//			for (DeptDO dept : deptDbList) {
//				orgMap.put(dept.getName(),dept.getDeptCode());//{部门名称，部门编码}
//			}
//		}
		//用户表
		List<DocumentInfor> docDbList = documentInforMapper.list(new HashMap<String,Object>());
		Map<String,DocumentInfor> userMap=new HashMap<String,DocumentInfor>();//{身份证号:用户}
		if(CollectionUtils.isNotEmpty(docDbList)){
			for (DocumentInfor doc : docDbList) {
				userMap.put(doc.getIdentificationNo(),doc);
			}
		}
		for(int i=1;i<rows+1;i++){
			Row row  = sheet.getRow(i);
			//行不为空
			if(row!=null){
				//读取cell
				DocumentInfor userInfo =new DocumentInfor();
				String deptCode = ExcelUtils.getCellValue(row.getCell(0));//所属单位
				String section = ExcelUtils.getCellValue(row.getCell(1));//所属部门/科室
				String name = ExcelUtils.getCellValue(row.getCell(2));//姓名
				String idnumber = ExcelUtils.getCellValue(row.getCell(3));//身份证号
				String personnelCompilation = ExcelUtils.getCellValue(row.getCell(4));//人事编制
				String personType = ExcelUtils.getCellValue(row.getCell(5));//人员性质(在编人员身份)
				String workStatus = ExcelUtils.getCellValue(row.getCell(6));//工作状态
				String workTime = ExcelUtils.getCellValue(row.getCell(7));//参加工作时间
				String orgTime = ExcelUtils.getCellValue(row.getCell(8));//服务本机构时间
				String isSecondment = ExcelUtils.getCellValue(row.getCell(9));//是否借出
				String secondDeptCode = ExcelUtils.getCellValue(row.getCell(10));//借出单位
				String secondSection= ExcelUtils.getCellValue(row.getCell(11));//调入科室
				String profession = ExcelUtils.getCellValue(row.getCell(12));//专业特长，有何特色
				if(!IdcardUtils.validateCard(idnumber)){
					throw new BusinessException("上传的Excel表格中身份证号非法，第"+i+"行"+idnumber+"！");
				}
				if(idnumbers.contains(idnumber)){
					throw new BusinessException("上传的Excel表格中身份证号重复，第"+i+"行"+idnumber+"！");
				}
				if(userMap.keySet().contains(idnumber)){
					throw new BusinessException("第"+i+"行：系统中已存在身份号为"+idnumber+"的用户，但真实姓名不符，请核对并修正！");
				}
				idnumbers.add(idnumber);
				if(!orgMap.values().contains(deptCode)){
					throw new BusinessException("系统中不存在该部门：第"+i+"行"+deptCode);
				}
				/*userInfo.setId(UUIDUtil.createUUID());
				userInfo.setBusiId(userInfo.getId());
				userInfo.setDeptCode(deptCode);
				userInfo.setSection(section);
				userInfo.setIdnumber(idnumber);
				userInfo.setName(name);
				userInfo.setSex(IdcardUtils.getGenderByIdCard(idnumber));
				userInfo.setBirthday(IdcardUtils.getBirthByIdCard(idnumber));
				userInfo.setPersonnelCompilation(personnelCompilation);
				userInfo.setPersonType(personType);
				userInfo.setWorkStatus(workStatus);
				userInfo.setWorkTime(StringUtils.isEmpty(workTime)?null:workTime);
				userInfo.setOrgTime(StringUtils.isEmpty(orgTime)?null:orgTime);
				userInfo.setIsSecondment(isSecondment);
				userInfo.setSecondDeptCode(secondDeptCode);
				userInfo.setSecondSection(secondSection);
				userInfo.setProfession(profession);
				userList.add(userInfo);*/
			}
		}
		this.batchInsert(userList);//批量插入
		return null;
	}

	/**
	 * 批量插入数据库
	 * @param userList
	 */
	private void batchInsert(List<DocumentInfor> userList) {
		int row=500;//每500条往数据据写一次
		int count=1;
		while(true){
			if((count-1)*row>userList.size()){
				break;
			}
			int endIndex=count*row;
			endIndex=endIndex<userList.size()?endIndex:userList.size();
			List<DocumentInfor> subList= userList.subList((count-1)*row,endIndex);
			if(CollectionUtils.isEmpty(subList)){
				break;
			}
			documentInforMapper.batchInsert(subList);
			count++;
		}
	}

	public int countForExport(Map<String, Object> map) {
		
		return documentInforMapper.countForExport(map);
	}

	//更新保存基本信息
	public void updateDocumentInfor(DocumentInfor documentInfor) {

		DocumentInfor oldEntity = documentInforMapper.queryDocumentByBusiId(documentInfor.getBusiId());
		if (oldEntity == null) {
			throw new BusinessException("数据库中查不到该记录！");
		}

		// document.setCreateAt(oldEntity.getCreateAt());
		// document.setCreateId(oldEntity.getCreateId());
		documentInfor.setUpdateAt(DateUtil.getCurrentTime());
		documentInfor.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		documentInforMapper.updateByPrimaryKeySelective(documentInfor);
		oldEntity.setId(UUIDUtil.createUUID());
		oldEntity.setUpdateAt(DateUtil.getCurrentTime());
		oldEntity.setUpdateId(UserSession.getCurrentUserSession().getAccount());
		oldEntity.setState(SysContants.DELETED);
		documentInforMapper.saveInfo(oldEntity);

	}

}
