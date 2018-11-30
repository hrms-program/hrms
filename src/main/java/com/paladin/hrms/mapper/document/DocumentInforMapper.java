package com.paladin.hrms.mapper.document;

import java.util.List;
import java.util.Map;

import com.paladin.framework.mybatis.CustomMapper;
import com.paladin.hrms.controller.document.pojo.DocumentInforVO;
import com.paladin.hrms.model.document.DocumentInfor;

public interface DocumentInforMapper extends CustomMapper<DocumentInfor>{
	
	List<DocumentInfor> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int remove(String id);

	void batchRemove(String[] ids);

	void saveInfo(DocumentInfor document);


	DocumentInfor queryDocumentByBusiId(String busiId);

	void deleteOldEntity(DocumentInfor oldEntity);	


	List<DocumentInforVO> listForExport(Map<String, Object> map);

	void batchInsert(List<DocumentInfor> subList);

	int countForExport(Map<String, Object> map);	

}
