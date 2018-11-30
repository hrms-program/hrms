$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		saveInfo();
	}
});


$(function(){
	
	window.scrollTo(0,0);
	
	$(window).scroll(function(){
	//获取滚动条的滑动距离
		var scroH = $(this).scrollTop();
		
		if(scroH != 0){
			$(".file-header").css({"position":"fixed"});
		}
		
		//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
		if(scroH > $("#baseinfo-content").offset().top){
			$(".file-header").css({"position":"fixed"});
		}else{
			$(".file-header").css({"position":"relative"});
		}
	})
	
	$('#anchor-point li').click(function(){
        var index = $(this).index();
        
        $(this).addClass('active').siblings().removeClass('active');

		if(index <= 3){
			$("html,body").animate({ scrollTop: $('.info-content').eq(index).offset().top - 80}, 500);
		}else{
			$("html,body").animate({ scrollTop: $('.background-info-box').eq((index-4)).offset().top -80}, 500);
		}
    });
	
	//下拉选赋值
	addDropDownList('/common/dict/queryListByDictType',{"type":"management_level"},$('#managementLevel'));//管理层次
	addDropDownList('/common/dict/queryListByDictType',{"type":"personnel_compilation"},$('#personnelCompilation'));//人事编制
	addDropDownList('/common/dict/queryListByDictType',{"type":"nation"},$('#nation'));//民族
	addDropDownList('/common/dict/queryListByDictType',{"type":"politics"},$('#party'));//党派
	addDropDownList('/common/dict/queryListByDictType',{"type":"person_type"},$('#personType'));//人员性质
	addDropDownList('/common/dict/queryListByDictType',{"type":"education_level"},$('#initialEducation'));//初始学历
	addDropDownList('/common/dict/queryListByDictType',{"type":"education_level"},$('#highestEducation'));//最高学历
	addDropDownList('/common/dict/queryListByDictType',{"type":"work_status"},$('#workStatus'));//在岗情况
	addDropDownList('/common/dict/queryListByDictType',{"type":"yes_no"},$('#isGeneral'));//是否全科医生
	addDropDownList('/system/sysDept/queryCodeAndNameForCombSelector',null,$('#deptCode'));//所属单位
	addDropDownList('/system/sysDept/queryCodeAndNameForCombSelector',null,$('#secondDeptCode'));//借调（入）单位
	
	addDropDownList('/common/dict/queryListByDictType',{"type":"job_level"},$('#position-level'));//职务层次
	addDropDownList('/common/dict/queryListByDictType',{"type":"management_level"},$('#manage-level'));//管理层次
	addDropDownList('/common/dict/queryListByDictType',{"type":"yes_no"},$('#whether-leader'));//是否领导干部
	addDropDownList('/common/dict/queryListByDictType',{"type":"yes_no"},$('#whether-team'));//是否班子成员
	addDropDownList('/common/dict/queryListByDictType',{"type":"yes_no"},$('#whether-reserveCadre'));//是否后备干部
	
	addDropDownList('/common/dict/queryListByDictType',{"type":"job_level"},$('#partyPosition-level'));//职务层次
	addDropDownList('/common/dict/queryListByDictType',{"type":"management_level"},$('#partyManage-level'));//管理层次
	addDropDownList('/common/dict/queryListByDictType',{"type":"yes_no"},$('#partyWhether-leader'));//是否领导干部
	addDropDownList('/common/dict/queryListByDictType',{"type":"yes_no"},$('#partyWhether-team'));//是否班子成员
	addDropDownList('/common/dict/queryListByDictType',{"type":"yes_no"},$('#partyWhether-reserveCadre'));//是否后备干部
	
});


//个人信息保存

function saveInfo(this_){
	
	var haveFile=new Array();
	$("#attach_table tbody th").each(function(index,domEL){
		if(domEL.id){
			haveFile.push(domEL.id);
		}
	});
	$("#have-file").val(haveFile.join(","));//以逗号拼接
	
	var haveFile2=new Array();
	$("#attach_table2 tbody th").each(function(index,domEL){
		if(domEL.id){
			haveFile2.push(domEL.id);
		}
	});
	$("#have-file2").val(haveFile2.join(","));//以逗号拼接
	
	
	var data=$('#saveInfo').serialize();
	var data1 = serializeNotNull(data);
	console.log(777);
	var btn=$(this_);
	btn.attr('disabled',true);
	$.ajax({
		url : '/document/saveInfo', //用于文件上传的服务器端请求地址
		type : "POST",
		dataType : 'JSON', //返回值类型 一般设置为json
		data : data1, //返回值类型 一般设置为json
		success : function(data) { //服务器成功响应处理函数
			if (data.status == 1) {
				btn.attr('disabled',true);
				layer.msg(data.msg)
			} else {
				layer.alert(data.msg);
				btn.attr('disabled',false);
			}
		},
		error : function(data) { //服务器响应失败处理函数
			btn.attr('disabled',false);
			layer.alert("网络异常，请退出登录后再重试！");
			btn.attr('disabled',false);
		}
	});
}


function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#saveInfo").validate({
		rules : {
			name : {
				required : true
			},
			idnumber : {
				required : true
			}
			
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			},
			idnumber : {
				required : icon + "请输入身份证号"
			}
		}
	})
}


function back(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
	parent.reLoad();
}


//上传文件，上传后新增一行(现任行政职务)
function ajaxFileUpload(this_){
	var name=this_.files[0].name;
	if(!limitFileSize(this_.files[0],'50MB')){
		layer.alert("上传文件大小不得超过50M！",{icon:2});
		return;
	}
	$.ajaxFileUpload({
        url : '/attachment/upload', //用于文件上传的服务器端请求地址
        secureuri : false, //是否需要安全协议，一般设置为false
        fileElementId : 'attachment', //文件上传域的ID
        dataType : 'JSON', //返回值类型 一般设置为json
        data : {'type':'AdministrativePost'}, //返回值类型 一般设置为json
        success : function(data) { //服务器成功响应处理函数
        	$("#attachment").val('');//第一次上传后如果删除，第二次不能再上传该文件的bug
            if(data.status==1){
            	
            	var tbodyLength=$("#tbody").find("tr").length;
            	var firstTr = $("#firstTr");
            	if(tbodyLength==1){
            		firstTr.remove();
            	}            	
            	
            	var trObj = $('<tr></tr>');   
                trObj.attr("id", new Date().getTime());  
                trObj.html( '<th>'+data.result.originalName+'</th><th id="'+data.result.id+'">'
                		+'<a class="remove" href="/attachment/download?id='+data.result.id+'">'
            			+'<i class="glyphicon glyphicon-save"></i>下载</a>'
            			+'<a class="remove" style="margin:0 0 0 10px" href="javascript:void(0);" onclick="delAttach(this);">'
            			+'<i class="glyphicon glyphicon-remove"></i>删除</a></th>');  
                $('#tbody').append(trObj); 
                
            }else if(data.status==0){
                layer.alert(data.msg,{icon:2});
            }
        },
        error : function(data) { //服务器响应失败处理函数
        	$("#attachment").val('');
        	layer.alert("网络异常，请退出登录后再重试！",{icon:5});
        }
    });
}

//删除
function delAttach(this_){
	var attachmentId=this_.parentNode.id;
	var trId = this_.parentNode.id;  
	$.ajax({
        url : '/attachment/remove', //用于文件上传的服务器端请求地址
        type: "POST",
        dataType : 'JSON', //返回值类型 一般设置为json
        data : {'id':attachmentId}, //返回值类型 一般设置为json
        success : function(data) { //服务器成功响应处理函数
            if(data.status==1){
            	var tbodyLength=$("#tbody").find("tr").length;
            	if(tbodyLength==1){
            		var tr = $("<tr id='firstTr'></tr>");
            		tr.html('<th colspan="2" id="thead">暂无附件</th>');
            		$("#tbody").append(tr);
            	}
            	
            	this_.parentNode.parentNode.remove(); 
            	
            }else if(data.status==0){
                layer.alert(data.msg,{icon:2});
            }
            
        },
        error : function(data) { //服务器响应失败处理函数
        	layer.alert("网络异常，请退出登录后再重试！",{icon:5});
        }
    });
	
}


//上传文件，上传后新增一行(现任党内职务)
function ajaxFileUpload2(this_){
	var name=this_.files[0].name;
	if(!limitFileSize(this_.files[0],'50MB')){
		layer.alert("上传文件大小不得超过50M！",{icon:2});
		return;
	}
	console.log(777);
	$.ajaxFileUpload({
        url : '/attachment/upload', //用于文件上传的服务器端请求地址
        secureuri : false, //是否需要安全协议，一般设置为false
        fileElementId : 'attachment2', //文件上传域的ID
        dataType : 'JSON', //返回值类型 一般设置为json
        data : {'type':'InnerPartyPost'}, //返回值类型 一般设置为json
        success : function(data) { //服务器成功响应处理函数
        	$("#attachment2").val('');//第一次上传后如果删除，第二次不能再上传该文件的bug
            if(data.status==1){
            	
            	var tbodyLength=$("#tbody2").find("tr").length;
            	var firstTr = $("#firstTr2");
            	if(tbodyLength==1){
            		firstTr.remove();
            	}            	
            	
            	var trObj = $('<tr></tr>');   
                trObj.attr("id", new Date().getTime());  
                trObj.html( '<th>'+data.result.originalName+'</th><th id="'+data.result.id+'">'
                		+'<a class="remove" href="/attachment/download?id='+data.result.id+'">'
            			+'<i class="glyphicon glyphicon-save"></i>下载</a>'
            			+'<a class="remove" style="margin:0 0 0 10px" href="javascript:void(0);" onclick="delAttach2(this);">'
            			+'<i class="glyphicon glyphicon-remove"></i>删除</a></th>');  
                $('#tbody2').append(trObj); 
                
            }else if(data.status==0){
                layer.alert(data.msg,{icon:2});
            }
        },
        error : function(data) { //服务器响应失败处理函数
        	$("#attachment2").val('');
        	layer.alert("网络异常，请退出登录后再重试！",{icon:5});
        }
    });
}

//删除
function delAttach2(this_){
	var attachmentId=this_.parentNode.id;
	var trId = this_.parentNode.id;  
	console.log(888);
	$.ajax({
        url : '/attachment/remove', //用于文件上传的服务器端请求地址
        type: "POST",
        dataType : 'JSON', //返回值类型 一般设置为json
        data : {'id':attachmentId}, //返回值类型 一般设置为json
        success : function(data) { //服务器成功响应处理函数
            if(data.status==1){
            	var tbodyLength=$("#tbody2").find("tr").length;
            	if(tbodyLength==1){
            		var tr = $("<tr id='firstTr2'></tr>");
            		tr.html('<th colspan="2" id="thead2">暂无附件</th>');
            		$("#tbody2").append(tr);
            	}
            	
            	this_.parentNode.parentNode.remove(); 
            	
            }else if(data.status==0){
                layer.alert(data.msg,{icon:2});
            }
            
        },
        error : function(data) { //服务器响应失败处理函数
        	layer.alert("网络异常，请退出登录后再重试！",{icon:5});
        }
    });
	
}