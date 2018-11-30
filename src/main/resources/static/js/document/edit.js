$().ready(function() {
	validateRule();
	calcAge()
});

$.validator.setDefaults({
	submitHandler : function(form) {
		console.log(88888);
		console.log(this);
		update(form);
	}
});

function calcAge(){
    var birthday = $("#birthday").val();
    if(!birthday){
    	return;
    }
    //获取年龄
    var myDate = new Date();
    var month = myDate.getMonth() + 1;
    var day = myDate.getDate();
    var age = myDate.getFullYear() - birthday.substring(0, 4) - 1;
    if(birthday.substring(5, 7) < month || birthday.substring(5, 7) == month && birthday.substring(8, 10) <= day) {
        age++;
    }
    $("#age").val(age);
}


function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#documentInfo").validate({
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
	});
	$("#documentWork").validate({
		rules : {
			deptCode : {
				required : true
			},
			workStatus : {
				required : true
			}
		},
		messages : {
			deptCode : {
				required : icon + "请选择单位名称"
			},
			workStatus : {
				required : icon + "请选择在岗情况"
			}
		}
	});
	$("#administrative-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				unitName : {
					required : true
				}
			},
			messages : {
				unitName : {
					required : icon + "请输入单位名称"
				}
			}
		});
	});
	
	$("#party-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				unitName : {
					required : true
				},
				manageLevel : {
					required : true
				}
			},
			messages : {
				unitName : {
					required : icon + "请输入单位名称"
				},
				manageLevel : {
					required : icon + "请输入管理层次"
				}
			}
		});
	});
	
	$("#work-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				unit : {
					required : true
				},
				position : {
					required : true
				}
			},
			messages : {
				unit : {
					required : icon + "请输入单位名称"
				},
				position : {
					required : icon + "请输入管理层次"
				}
			}
		});
	});
	
	$("#edu-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				school : {
					required : true
				},
				major : {
					required : true
				}
			},
			messages : {
				school : {
					required : icon + "请输入学校名称"
				},
				major : {
					required : icon + "请输入专业名称"
				}
			}
		});
	});
	
	$("#jobTit-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				type : {
					required : true
				},
				level : {
					required : true
				}
			},
			messages : {
				type : {
					required : icon + "请输入职称类型"
				},
				level : {
					required : icon + "请输入职称级别"
				}
			}
		});
	});
	
	$("#train-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				type : {
					required : true
				},
				unit : {
					required : true
				}
			},
			messages : {
				type : {
					required : icon + "请输入培训类型"
				},
				unit : {
					required : icon + "请输入培训单位"
				}
			}
		});
	});
	
	$("#reward-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				project : {
					required : true
				},
				rewardLevel : {
					required : true
				}
			},
			messages : {
				project : {
					required : icon + "请输入奖励项目"
				},
				rewardLevel : {
					required : icon + "请输入奖励级别"
				}
			}
		});
	});
	
	
	$("#punish-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				project : {
					required : true
				},
				punishLevel : {
					required : true
				}
			},
			messages : {
				project : {
					required : icon + "请输入处罚名目"
				},
				punishLevel : {
					required : icon + "请输入处罚级别"
				}
			}
		});
	});
	$("#certificate-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				year : {
					required : true
				},
				checkState : {
					required : true
				}
			},
			messages : {
				year : {
					required : icon + "请输入年度"
				},
				checkState : {
					required : icon + "请输入考核结果"
				}
			}
		});
	});
	
	$("#home-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				title : {
					required : true
				},
				idnumber : {
					required : true
				}
			},
			messages : {
				title : {
					required : icon + "请输入称谓"
				},
				idnumber : {
					required : icon + "请输入身份证号码"
				}
			}
		});
	});
	
	$("#jobPositions-experience .form_add").each(function(){
		$(this).validate({
			rules : {
				firName : {
					required : true
				}
			},
			messages : {
				firName : {
					required : icon + "请输职务名称"
				}
			}
		});
	});

}
$(function(){
	console.log(999);
	if($("#initialEducation").val()==0){
		$("#initialEducationDesc").attr("readonly",false);
		$("#initialEducationDesc").css({'background':'#fff','color':'#000'});
	}else{
		$("#initialEducationDesc").attr("readonly",true);
		$("#initialEducationDesc").css({'background':'#ddd','color':'#808080'});
		$("#initialEducationDesc").val("");
	}
	
	if($("#technicalPosition").val()==51){
		$("#technicalPositionDesc").attr("readonly",false);
		$("#technicalPositionDesc").css({'background':'#fff','color':'#000'});
	}else{
		$("#technicalPositionDesc").attr("readonly",true);
		$("#technicalPositionDesc").css({'background':'#ddd','color':'#808080'});
		$("#technicalPositionDesc").val("");
	}

	if($("#jobTitle").val() == 51){
		$("#nameDesc").attr("readonly",false);
		$("#nameDesc").css('color','#000');
	}else{
		$("#nameDesc").attr("readonly",true);
		$("#nameDesc").css('color','rgb(93, 93, 93)');		
		$("#nameDesc").val("");
	}


	console.log(1111111111111);
	
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
	
	$('#party-experience .form-wrap .cancel-btn').click(function(){
		$('#party-experience .form-wrap').hide();
	});

	$("#inpstart").trigger("input"); 
	
	//保存事件
	/*$('a.save-btn').click(function(){
		console.log(1000);
		var haveFile=new Array();
	
		
		var form=$(this).parents('form')[0];
		
		//查找form下的attach_table tbody th
		$(form).find("#attach_table tbody th").each(function(index,domEL){
			if(domEL.id){
				haveFile.push(domEL.id);
			}  
		});
		$(form).find("#have-file").val(haveFile.join(","));//以逗号拼接
		
		var data=$(form).serialize();
		var data1 = serializeNotNull(data);
		console.log(data1);
		var btn=$(this);
		$.ajax({
	        url : form.action+'/add', //用于文件上传的服务器端请求地址
	        type: "POST",
	        dataType : 'JSON', //返回值类型 一般设置为json
	        data : data1, //返回值类型 一般设置为json
	        success : function(data) { //服务器成功响应处理函数
	            if(data.status==1){
	            	btn.attr('disabled',true);
	            	layer.alert(data.msg,{icon:1});
	            	//保存成功后刷新页面
	            	location.reload();
	            }else if(data.status==0){
	            	btn.attr('disabled',false);
	                layer.alert(data.msg,{icon:2});
	            }
	        },
	        error : function(data) { //服务器响应失败处理函数
	        	btn.attr('disabled',false);
	        	layer.alert("网络异常，请退出登录后再重试！",{icon:5});
	        }
	    });
	});*/
	
	$('input.upload-btn').click(function(){
		$(this).next().click();
	})
	
	
	/*$('a.btn-danger').click(function(){
		
	});*/
	
	//下拉选赋值
	/*addDropDownList('/common/dict/queryListByDictType',{"type":"sex"},$('#sex'));//性别
	addDropDownList('/common/dict/queryListByDictType',{"type":"management_level"},$('#managementLevel'));//管理层次
	addDropDownList('/common/dict/queryListByDictType',{"type":"personnel_compilation"},$('#personnelCompilation'));//人事编制
	addDropDownList('/common/dict/queryListByDictType',{"type":"nation"},$('#nation'));//民族
	addDropDownList('/common/dict/queryListByDictType',{"type":"politics"},$('#party'));//党派
	addDropDownList('/common/dict/queryListByDictType',{"type":"person_type"},$('#personType'));//人员性质
	addDropDownList('/common/dict/queryListByDictType',{"type":"education_level"},$('#initialEducation'));//初始学历
	addDropDownList('/common/dict/queryListByDictType',{"type":"education_level"},$('#highestEducation'));//最高学历
	addDropDownList('/common/dict/queryListByDictType',{"type":"work_status"},$('#workStatus'));//在岗情况
	addDropDownList('/common/dict/queryListByDictType',{"type":"salary_level"},$('#salaryLevel'));//执行工资标准
	addDropDownList('/common/dict/queryListByDictType',{"type":"professional_title_level"},$('#professionalTitleLevel'));//职称级别
	addDropDownList('/common/dict/queryListByDictType',{"type":"professional_title_level"},$('#jobLevel'));//职称级别
	addDropDownList('/common/dict/queryListByDictType',{"type":"professional_title_type"},$('#professionalTitleType'));//职称类型
	addDropDownList('/common/dict/queryListByDictType',{"type":"professional_title_type"},$('#jobType'));//职称类型
	addDropDownList('/system/sysDept/queryCodeAndNameForCombSelector',null,$('#deptCode'));//所属单位
	addDropDownList('/system/sysDept/queryCodeAndNameForCombSelector',null,$('#secondDeptCode'));//借调（入）单位
	
	addDropDownList('/common/dict/queryListByDictType',{"type":"technical_position"},$('#technicalPosition'));//专业技术职务

	addDropDownList('/common/dict/queryListByDictType',{"type":"professional_sort"},$('#professionalSort'));//专业技术类型
	addDropDownList('/common/dict/queryListByDictType',{"type":"profession_level"},$('#professionLevel'));//专业技术级别

	//是否
	$('select.yes_no').each(function(){
		addDropDownList('/common/dict/queryListByDictType',{"type":"yes_no"},$(this));//职务层次
	})
	$('select[name="positionLevel"]').each(function(){
		addDropDownList('/common/dict/queryListByDictType',{"type":"job_level"},$(this));//职务层次
	})
	$('select[name="manageLevel"]').each(function(){
		addDropDownList('/common/dict/queryListByDictType',{"type":"management_level"},$(this));//职务层次
	})
	$('select[name="educationLevel"]').each(function(){
		addDropDownList('/common/dict/queryListByDictType',{"type":"education_level"},$(this));//职务层次
	})
	$('select[name="educationDegree"]').each(function(){
		addDropDownList('/common/dict/queryListByDictType',{"type":"education_degree"},$(this));//职务层次
	})
	$('select[name="name"]').each(function(){
		addDropDownList('/common/dict/queryListByDictType',{"type":"technical_position"},$(this));//专业技术职务
	})*/

	$('.eCon .action-btns .btn').hide(); // 页面加载完成后使按钮全部消失
	$('.eCon .input-text').attr('disabled',true); // 页面加载完成后有内容的input框不可编辑
	$('.eCon .upload-btn').attr('disabled',true);	
	$('.eCon .input-text').css('color','#5d5d5d');
		

	// 工作经历
	let hoverIndex = 0;
	$('.workExperCon').hover(function(){
		hoverIndex = $(this).index()-2;
		allActions('workExperCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','work-experience',hoverIndex);
	},function(){
		allNone('workExperCon','editBtn','delBtn','addBtn',hoverIndex);
	});

	// 并行职务经历
	let hoverIndex1 = 0;
	$('.parellelPostExperCon').hover(function(){
		hoverIndex1 = $(this).index()-2;
		allActions('parellelPostExperCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','parellelPost-experience',hoverIndex1);
	},function(){
		allNone('parellelPostExperCon','editBtn','delBtn','addBtn',hoverIndex1);
	});
	
	// 教育经历
	let hoverIndex2 = 0;
	$('.learnExperCon').hover(function(){
		hoverIndex2 = $(this).index()-2;
		allActions('learnExperCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','edu-experience',hoverIndex2);
	},function(){
		allNone('learnExperCon','editBtn','delBtn','addBtn',hoverIndex2);
	});


	// 职称信息
	let hoverIndex3 = 0;
	$('.jobExperCon').hover(function(){
		hoverIndex3 = $(this).index()-2;
		allActions('jobExperCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','jobTit-experience',hoverIndex3);
	},function(){
		allNone('jobExperCon','editBtn','delBtn','addBtn',hoverIndex3);
	});


	// 培训信息
	let hoverIndex4 = 0;
	$('.trainExperCon').hover(function(){
		hoverIndex4 = $(this).index()-2;
		allActions('trainExperCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','train-experience',hoverIndex4);
	},function(){
		allNone('trainExperCon','editBtn','delBtn','addBtn',hoverIndex4);
	});


	// 奖惩情况
	let hoverIndex5 = 0;
	$('.rewardCon').hover(function(){
		hoverIndex5 = $(this).index()-2;
		allActions('rewardCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','reward-experience',hoverIndex5);
	},function(){
		allNone('rewardCon','editBtn','delBtn','addBtn',hoverIndex5);
	});
	
	// 处罚情况
	let hoverIndex6 = 0;
	$('.punishCon').hover(function(){
		hoverIndex6 = $(this).index()-2;
		allActions('punishCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','punish-experience',hoverIndex6);
	},function(){
		allNone('punishCon','editBtn','delBtn','addBtn',hoverIndex6);
	});


	// 年度考核
	let hoverIndex8 = 0;
	$('.annualCon').hover(function(){
		hoverIndex8 = $(this).index()-2;
		allActions('annualCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','certificate-experience',hoverIndex8);
	},function(){
		allNone('annualCon','editBtn','delBtn','addBtn',hoverIndex8);
	});


	// 家庭成员
	let hoverIndex9 = 0;
	$('.memberCon').hover(function(){
		hoverIndex9 = $(this).index()-2;
		allActions('memberCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','home-experience',hoverIndex9);
	},function(){
		allNone('memberCon','editBtn','delBtn','addBtn',hoverIndex9);
	});

	// 党内职务经历
	let hoverIndex10 = 0;
	$('.partyExperCon').hover(function(){
		hoverIndex10 = $(this).index()-2;
		allActions('partyExperCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','party-experience',hoverIndex10);
	},function(){
		allNone('partyExperCon','editBtn','delBtn','addBtn',hoverIndex10);
	});
	
	// 行政职务经历
	let hoverIndex11 = 0;
	$('.administrativeExperCon').hover(function(){
		hoverIndex11 = $(this).index()-2;
		allActions('administrativeExperCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','administrative-experience',hoverIndex11);
	},function(){
		allNone('administrativeExperCon','editBtn','delBtn','addBtn',hoverIndex11);
	});
	
	// 行政职务经历
	let hoverIndex12 = 0;
	$('.jobPositionsExperCon').hover(function(){
		hoverIndex12 = $(this).index()-2;
		allActions('jobPositionsExperCon','cancel-btn','save-btn','editBtn','delBtn','addBtn','jobPositions-experience',hoverIndex12);
	},function(){
		allNone('jobPositionsExperCon','editBtn','delBtn','addBtn',hoverIndex12);
	});
	
	
});


function update(this_){
	var haveFile=new Array();
	var form=this_;
	
	//查找form下的attach_table tbody th
	$(form).find("#attach_table tbody th").each(function(index,domEL){
		if(domEL.id){
			haveFile.push(domEL.id);
		}  
	});
	$(form).find("#have-file").val(haveFile.join(","));//以逗号拼接
	var data=$(form).serialize();
	var data1 = serializeNotNull(data);
	$.ajax({
        url : form.action+'/add', //用于文件上传的服务器端请求地址
        type: "POST",
        dataType : 'JSON', //返回值类型 一般设置为json
        data : data1, //返回值类型 一般设置为json
        success : function(data) { //服务器成功响应处理函数
            if(data.status==1){
            	layer.alert(data.msg);
            	//保存成功后刷新页面
            	location.reload();
            }else if(data.status==0){
                layer.alert(data.msg,{icon:2});
            }
        },
        error : function(data) { //服务器响应失败处理函数
        	layer.alert("网络异常，请退出登录后再重试！",{icon:5});
        }
    });
}


// 鼠标离开后按钮显示情况
function allNone(fClass,editBtn,delBtn,addBtn,index){
	$('.'+ fClass +' '+'.'+ editBtn).eq(index).hide();
	$('.'+ fClass +' '+'.'+ delBtn).eq(index).hide();
	$('.'+ fClass +' '+'.'+ addBtn).eq(index).hide();
}


function allActions(fClass,cancelBtn,saveBtn,editBtn,delBtn,addBtn,exper,index){


	// hover时按钮显示情况
	if($('.'+ fClass +' '+'.'+ cancelBtn).eq(index).css('display') == 'none'){
		$('.'+ fClass +' '+'.'+ editBtn).eq(index).show();
		$('.'+ fClass +' '+'.'+ delBtn).eq(index).show();
		$('.'+ fClass +' '+'.'+ addBtn).eq(index).show();
	}else{
		$('.'+ fClass +' '+'.'+ editBtn).eq(index).hide();
		$('.'+ fClass +' '+'.'+ delBtn).eq(index).hide();
		$('.'+ fClass +' '+'.'+ addBtn).eq(index).hide();
	}	

	// 点击编辑
	$('.'+ fClass +' '+'.'+ editBtn).eq(index).click(function(){
		$('.'+ fClass +' '+'.'+ cancelBtn).eq(index).show();
		$('.'+ fClass +' '+'.'+ saveBtn).eq(index).show();
		$('.'+ fClass +' '+'.'+ editBtn).eq(index).hide();
		$('.'+ fClass +' '+'.'+ delBtn).eq(index).hide();
		$('.'+ fClass +' '+'.'+ addBtn).eq(index).hide();
		$('.'+ fClass).eq(index).find('.input-text').attr('disabled',false);
		$('.'+ fClass + ' ' + '.upload-btn').attr('disabled',false);
		$('.'+ fClass).eq(index).find('.input-text').css('color','#000');

		if(fClass == 'jobExperCon'){
			if($('.' + fClass + ' ' + "#jobTitle").eq(index).val() == 51){
				$('.' + fClass + ' ' + "#nameDesc").eq(index).attr("readonly",false);
				$('.' + fClass + ' ' + "#nameDesc").eq(index).css('color','#000');
			}else{
				$('.' + fClass + ' ' + "#nameDesc").eq(index).attr("readonly",true);
				$('.' + fClass + ' ' + "#nameDesc").eq(index).css('color','rgb(93, 93, 93)');
			}
		}
	});

	// 点击取消
	$('.'+ fClass +' '+'.'+ cancelBtn).eq(index).click(function(){
		$('.'+ fClass +' '+'.'+ cancelBtn).eq(index).hide();
		$('.'+ fClass +' '+'.'+ saveBtn).eq(index).hide();
		$('.'+ fClass +' '+'.'+ editBtn).eq(index).show();
		$('.'+ fClass +' '+'.'+ delBtn).eq(index).show();
		$('.'+ fClass +' '+'.'+ addBtn).eq(index).show();
		$('.'+ fClass).eq(index).find('.input-text').attr('disabled',true);
		$('.'+ fClass + ' ' + '.upload-btn').attr('disabled',true);
		$('.'+ fClass).eq(index).find('.input-text').css('color','#5d5d5d');
	});

	// 新增
	$('.'+ fClass +' '+'.'+ addBtn).eq(index).click(function(){
		$('#'+ exper +' '+ '.form-wrap').show();

		if(exper == 'jobTit-experience'){
			if($("#jobTitle1").val() == 51){
				$("#nameDesc1").attr("readonly",false);
				$("#nameDesc1").css('color','#000');
			}else{
				$("#nameDesc1").attr("readonly",true);
				$("#nameDesc1").css('color','rgb(93, 93, 93)');
			}
		}		
	});

	// 删除
	$('.'+ fClass +' '+'.'+ delBtn).eq(index).click(function(){
		var form=$(this).parents('form')[0];
		var this_=this;
		console.log(11111)
		layer.confirm('确认要删除吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
    		var data=$(form).serialize();
    		var data1 = serializeNotNull(data);
    		$.ajax({
    	        url : form.action+'/delete', //用于文件上传的服务器端请求地址
    	        type: "POST",
    	        dataType : 'JSON', //返回值类型 一般设置为json
    	        data : data1, //返回值类型 一般设置为json
    	        success : function(data) { //服务器成功响应处理函数
    	            if(data.status==1){
    	            	if($(this_).parents('.'+fClass).siblings().length==3){
    	            		$(this_).parents('.'+fClass).prev().show();
    	            	}
    	            	$(this_).parents('.'+fClass).hide();
    	            	layer.alert(data.msg,{icon:1});
    	            }else if(data.status==0){
    	                layer.alert(data.msg,{icon:2});
    	            }
    	        },
    	        error : function(data) { //服务器响应失败处理函数
    	        	layer.alert("网络异常，请退出登录后再重试！",{icon:5});
    	        }
    	    });
        }); 
	});
}




//附件上传（行政职务编辑）
function ajaxFileUpload(this_,type){
	var form=$(this_).parents('form')[0];
	console.log(form);
	console.log(222);
	var fileElementId=$(this_).attr('id');
	var busiId=$(form).find('#busiId').val();
	var name=this_.files[0].name;
	if(!limitFileSize(this_.files[0],'50MB')){
		layer.alert("上传文件大小不得超过50M！",{icon:2});
		return;
	}
	$.ajaxFileUpload({
        url : '/hrms/attachment/upload', //用于文件上传的服务器端请求地址
        secureuri : false, //是否需要安全协议，一般设置为false
        fileElementId :fileElementId , //文件上传域的ID
        dataType : 'JSON', //返回值类型 一般设置为json
        data : {'type':type,'remarks':busiId}, //返回值类型 一般设置为json
        success : function(data) { //服务器成功响应处理函数
        	console.log(888);
        	//$("#"+"attachment_"+type).val('');//第一次上传后如果删除，第二次不能再上传该文件的bug
        	/*var reg = /<pre.+?>(.+)<\/pre>/g;  
        	var result = data.match(reg);  
        	data = RegExp.$1; 
        	console.log(data);
        	$(this_).val('');*/
            if(JSON.parse(data).status==1){
            	
            	var tbodyLength=$(form).find("#tbody").find("tr").length;
            	var firstTr = $(form).find("#firstTr");
            	if(tbodyLength==1){
            		firstTr.remove();
            	}            	
            	
            	var trObj = $('<tr></tr>');   
                trObj.attr("id", new Date().getTime());  
                trObj.html( '<th>'+data.result.originalName+'</th><th id="'+data.result.id+'">'
                		+'<a class="remove" href="/hrms/attachment/download?id='+data.result.id+'">'
            			+'<i class="glyphicon glyphicon-save"></i>下载</a>'
            			+'<a class="remove" style="margin:0 0 0 10px" href="javascript:void(0);" onclick="delAttach(this);">'
            			+'<i class="glyphicon glyphicon-remove"></i>删除</a></th>');  
                $(form).find("#tbody").append(trObj); 
                
            }else{
                layer.alert(data.msg,{icon:2});
            }
        },
        error : function(data) { //服务器响应失败处理函数
        	//$("#"+'attachment_'+type).val('');
        	console.log(777);
        	$(this_).val('');
        	layer.alert("网络异常，请退出登录后再重试！",{icon:5});
        }
    });
}


//删除
function delAttach(this_){
	var form=$(this_).parents('form')[0];
	console.log(form)
	
	var attachmentId=this_.parentNode.id;
	var trId = this_.parentNode.id; 
	$.ajax({
        url : '/hrms/attachment/remove', //用于文件上传的服务器端请求地址
        type: "POST",
        dataType : 'JSON', //返回值类型 一般设置为json
        data : {'id':attachmentId}, //返回值类型 一般设置为json
        success : function(data) { //服务器成功响应处理函数
        	console.log(999);
            if(data.status==1){
            	var tbodyLength=$(form).find("#tbody").find("tr").length;
            	if(tbodyLength==1){
            		var tr = $("<tr id='firstTr'></tr>");
            		tr.html('<th colspan="2" id="thead">暂无附件</th>');
            		$(form).find("#tbody").append(tr);
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

function getInfo() {
    //获取输入身份证号码
    var pId = $("#idnumber").val();
    if(!pId){
    	return;
    }
    var error=checkIdcard(pId);
    if(error){
    	$("#age").val('');
    	$("#birthday").val('');
    	$("#sex").val('');
    	layer.alert(error);
    	
    	return;
    }
    if(pId.length == 15 || pId.length == 18) {//15或18位身份证号
    	var Ai = pId.length == 18 ? pId.substring(0, 17) : pId.slice(0, 6) + "19" + pId.slice(6, 16);
        if(!/^\d+$/.test(Ai)) {
            layer.alert("身份证除最后一位外，必须为数字！",{icon:2});
            return
        }
        var yyyy = Ai.slice(6, 10), mm = Ai.slice(10, 12) - 1, dd = Ai.slice(12, 14);
        var d = new Date(yyyy, mm, dd), now = new Date();
        var year = d.getFullYear(), mon = d.getMonth(), day = d.getDate();
        if(year != yyyy || mon != mm || day != dd || d > now || year < 1800) {
        	layer.alert("身份证格式错误！",{icon:2})
            return
        }
        var ic = String(Ai);
        //获取出生日期
        var birth = ic.substring(6, 10) + "-" + ic.substring(10, 12) + "-" + ic.substring(12, 14);
        $("#birthday").val(birth);
        //获取性别
        var gender = ic.slice(14, 17) % 2 ? "1" : "2"; // 1代表男性，2代表女性
        $("#sex").val(gender);
        //获取年龄
        var myDate = new Date();
        var month = myDate.getMonth() + 1;
        var day = myDate.getDate();
        var age = myDate.getFullYear() - ic.substring(6, 10) - 1;
        if(ic.substring(10, 12) < month || ic.substring(10, 12) == month && ic.substring(12, 14) <= day) {
            age++;
        }
        $("#age").val(age);
        
        //获取退线时间
        console.log(777);
        if(gender==1){//男
        	var retiredDate = parseInt(ic.substring(6, 10)) +55+ "-" + ic.substring(10, 12) + "-" + ic.substring(12, 14);
        	$("#retiredDate").val(retiredDate);
        }else{//女
        	var retiredDate = parseInt(ic.substring(6, 10))+52+ "-" + ic.substring(10, 12) + "-" + ic.substring(12, 14);
        	$("#retiredDate").val(retiredDate);
        }
    }else{
    	layer.alert("身份证号不合规范！");
    }
}

function getWorkYear(value){
	console.log(888);
	if($("#workTime").val!=""&&$("#workTime").val!=null){
		 var pId = $("#identificationNo").val();
	     if(pId.length == 15 || pId.length == 18) {
	    	 var Ai = pId.length == 18 ? pId.substring(0, 17) : pId.slice(0, 6) + "19" + pId.slice(6, 16);
	         if(!/^\d+$/.test(Ai)) {
	             layer.alert("身份证除最后一位外，必须为数字！",{icon:2});
	             return
	         }
	         var yyyy = Ai.slice(6, 10), mm = Ai.slice(10, 12) - 1, dd = Ai.slice(12, 14);
	         var d = new Date(yyyy, mm, dd), now = new Date();
	         var year = d.getFullYear(), mon = d.getMonth(), day = d.getDate();
	         if(year != yyyy || mon != mm || day != dd || d > now || year < 1800) {
	         	layer.alert("身份证格式错误！",{icon:2})
	             return
	         }
	         var ic = String(Ai);
	    	 var gender = ic.slice(14, 17) % 2 ? "1" : "2"; // 1代表男性，2代表女性
	    	 if(gender==1){
	    		 //获取退休时的时间（到月份）
	    		 var retire = (parseInt(ic.substring(6, 10)) +60)+ "-" + ic.substring(10, 12);
	    		 //拆分成月份
	    		 retire = retire.split('-');
	    		 retire = parseInt(retire[0]) * 12 + parseInt(retire[1]);//得到月数
	    		 
	    		// var workTime = $("#workTime").val();
	    		 workTime = value.split('-');
	    		 workTime = parseInt(workTime[0]) * 12 + parseInt(workTime[1]);//得到月数
	    		 
	    		  var year = parseInt((retire-workTime)/12);
	    		  var month = (retire-workTime)%12
	    		  $("#workYear").val(year+"年"+month+"个月");
	    		 
	    	 }else{
	    		 var retire = (parseInt(ic.substring(6, 10)) +55)+ "-" + ic.substring(10, 12);
	    		 //拆分成月份
	    		 retire = retire.split('-');
	    		 retire = parseInt(retire[0]) * 12 + parseInt(retire[1]);//得到月数
	    		 
	    		 //var workTime = $("#workTime").val();
	    		 workTime = value.split('-');
	    		 workTime = parseInt(workTime[0]) * 12 + parseInt(workTime[1]);//得到月数
	    		 
	    		  var year = parseInt((retire-workTime)/12);
	    		  var month = (retire-workTime)%12
	    		  $("#workYear").val(year+"年"+month+"个月");
	    	 }
	     }
	}
}

function getpositionYear(value){
	console.log(999);
	if($("#position-firstServingTime22").val!=""&&$("#position-firstServingTime22").val!=null){
		 
	    		 /*var retire = (parseInt(ic.substring(6, 10)) +55)+ "-" + ic.substring(10, 12);
	    		 //拆分成月份
	    		 retire = retire.split('-');
	    		 retire = parseInt(retire[0]) * 12 + parseInt(retire[1]);//得到月数*/	  
		         var myDate = new Date();
		         var nowYear = myDate.getFullYear();//获取当前时间年份
		         var nowMonth = myDate.getMonth();
		         var lengthYear = parseInt(nowYear)*12+parseInt(nowMonth)+1;
	    		 firstServingTime = value.split('-');
	    		 firstServingTime = parseInt(firstServingTime[0]) * 12 + parseInt(firstServingTime[1]);//得到月数
	    		 
	    		  var year = parseInt((lengthYear-firstServingTime)/12);
	    		  var month = (lengthYear-firstServingTime)%12
	    		  $("#position-servingTerm").val(year+"年"+month+"个月");
	    	 
	     
	}
	
}

function getInnerPositionYear(value){
	console.log(999);
	if($("#partyPosition-firstServingTime11").val!=""&&$("#partyPosition-firstServingTime11").val!=null){
		 
	    		 /*var retire = (parseInt(ic.substring(6, 10)) +55)+ "-" + ic.substring(10, 12);
	    		 //拆分成月份
	    		 retire = retire.split('-');
	    		 retire = parseInt(retire[0]) * 12 + parseInt(retire[1]);//得到月数*/	  
		         var myDate = new Date();
		         var nowYear = myDate.getFullYear();//获取当前时间年份
		         var nowMonth = myDate.getMonth();
		         var lengthYear = parseInt(nowYear)*12+parseInt(nowMonth)+1;
	    		 firstServingTime = value.split('-');
	    		 firstServingTime = parseInt(firstServingTime[0]) * 12 + parseInt(firstServingTime[1]);//得到月数
	    		 
	    		  var year = parseInt((lengthYear-firstServingTime)/12);
	    		  var month = (lengthYear-firstServingTime)%12
	    		  $("#partyServingTime").val(year+"年"+month+"个月");
	    	 
	     
	}
	
}
function getFamilyMemberAge(this_){
	//获取输入身份证号码
    var pId = $(this_).val();
    if(!pId){
    	return;
    }
    var error=checkIdcard(pId);
    if(error){
    	 $(this_).parent().next().find('input').val('');
    	layer.alert(error);
    	return;
    }
    if(pId.length == 15 || pId.length == 18) {//15或18位身份证号
    	var Ai = pId.length == 18 ? pId.substring(0, 17) : pId.slice(0, 6) + "19" + pId.slice(6, 16);
        if(!/^\d+$/.test(Ai)) {
            layer.alert("身份证除最后一位外，必须为数字！",{icon:2});
            return
        }
        var yyyy = Ai.slice(6, 10), mm = Ai.slice(10, 12) - 1, dd = Ai.slice(12, 14);
        var d = new Date(yyyy, mm, dd), now = new Date();
        var year = d.getFullYear(), mon = d.getMonth(), day = d.getDate();
        if(year != yyyy || mon != mm || day != dd || d > now || year < 1800) {
        	layer.alert("身份证格式错误！",{icon:2})
            return
        }
        var ic = String(Ai);
        //获取年龄
        var myDate = new Date();
        var month = myDate.getMonth() + 1;
        var day = myDate.getDate();
        var age = myDate.getFullYear() - ic.substring(6, 10) - 1;
        if(ic.substring(10, 12) < month || ic.substring(10, 12) == month && ic.substring(12, 14) <= day) {
            age++;
        }
        $(this_).parent().next().find('input').val(age);
    }else{
    	layer.alert("身份证号不合规范");
    }
	
}

$("#initialEducation").change(function(){ 
	if($("#initialEducation").val()==0){
		$("#initialEducationDesc").attr('readonly',false);
		$("#initialEducationDesc").css({'background':'#fff','color':'#000'});
	}else{
		$("#initialEducationDesc").attr('readonly',true);
		$("#initialEducationDesc").css({'background':'#ddd','color':'#808080'});
		$("#initialEducationDesc").val("");
	}
}) 
	
$("#technicalPosition").change(function(){ 	
	if($("#technicalPosition").val()==51){
		$("#technicalPositionDesc").attr("readonly",false);
		$("#technicalPositionDesc").css({'background':'#fff','color':'#000'});
	}else{
		$("#technicalPositionDesc").attr("readonly",true);
		$("#technicalPositionDesc").css({'background':'#ddd','color':'#808080'});
		$("#technicalPositionDesc").val("");
	}
})

$("#jobTitle").change(function(){ 
	if($("#jobTitle").val()==51){
		$("#nameDesc").attr("readonly",false);
		$("#nameDesc").css('color','#000');
	}else{
		$("#nameDesc").attr("readonly",true);
		$("#nameDesc").css('color','rgb(93, 93, 93)');
		$("#nameDesc").val("");
	}
})

$("#jobTitle1").change(function(){ 
	if($("#jobTitle1").val()==51){
		$("#nameDesc1").attr("readonly",false);
		$("#nameDesc1").css('color','#000');
	}else{
		$("#nameDesc1").attr("readonly",true);
		$("#nameDesc1").css('color','rgb(93, 93, 93)');
		$("#nameDesc1").val("");
	}
})
	
function checkIdcard(idcard) {
    var Errors = new Array(
    false,
    "身份证号码位数不对!",
    "身份证号码出生日期超出范围或含有非法字符!",
    "身份证号码校验错误!",
    "身份证地区非法!"
    );
    var area = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外" }

    var idcard, Y, JYM;
    var S, M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
    //地区检验   
    if (area[parseInt(idcard.substr(0, 2))] == null) return Errors[4];
    //身份号码位数及格式检验   
    switch (idcard.length) {
        //15位身份号码检测  
        case 15:
            if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性   
            } else {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性   
            }
            if (ereg.test(idcard)) return Errors[0];
            else return Errors[2];
            break;
        //18位身份号码检测  
        case 18:
            if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式   
            } else {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式   
            }
            if (ereg.test(idcard)) {//测试出生日期的合法性   
                //计算校验位   
                S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
                + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
                + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
                + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
                + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
                + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
                + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
                + parseInt(idcard_array[7]) * 1
                + parseInt(idcard_array[8]) * 6
                + parseInt(idcard_array[9]) * 3;
                Y = S % 11;
                M = "F";
                JYM = "10X98765432";
                M = JYM.substr(Y, 1);//判断校验位   
                if (M == idcard_array[17]) return Errors[0];//检测ID的校验位   
                else return Errors[3];
            }
            else return Errors[2];
            break;
        default:
            return Errors[1];
            break;
    }

}

