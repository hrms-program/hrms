
$(function() {
	
	load();
});
function selectLoad() {
	var html = "";
	$.ajax({
		url : '/common/dict/type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].type + '">' + data[i].description + '</option>'
			}
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			//点击事件
			$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
function load() {
	selectLoad();
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url :"/document/list", // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				search : true, // 是否显示搜索框
				showColumns : true, // 是否显示内容下拉框（选择显示的列）
				showExport : true, //是否显示导出按钮
	            exportDataType : ["selected"], //basic'导出当前页, 'all'导出全部, 'selected'导出选中项
	            exportTypes:['excel'],  //导出文件类型，默认的类型有default: ['json', 'xml', 'csv', 'txt', 'sql', 'excel']
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						type : $('#searchName').val(),
					};
				},
				columns : [
					{
						checkbox : true
					},
					{
						field : 'name',
						title : '姓名'
					},
					{
						field : 'sexName',
						title : '性别',
						width : '150px',
						visible:false
					},
					{
						field : 'idnumber',
						title : '身份证号',
						width : '200px'
					},
					{
						field : 'documentCode',
						title : '档案编码',
						width : '200px'
					},
					{
						field : 'personTypeName',
						title : '人员性质'
					},
					{
						field : 'partyName',
						title : '党派'
					},
					{
						field : 'workTime',
						title : '参加工作时间'
					},
					{
						field : 'managementLevel',
						title : '管理层次'
					},
					{
						field : 'deptCode',
						title : '单位名称',
						visible:false
					},
					{
						field : 'section',
						title : '所属科室',
						visible:false
					},
					{
						field : 'birthday',
						title : '生日',
						visible:false
					},
					{
						field : 'nation',
						title : '民族',
						visible:false
					},
					{
						field : 'nativePlace',
						title : '籍贯',
						visible:false
					},
					{
						field : 'birthPlace',
						title : '出生地',
						visible:false
					},
					{
						field : 'address',
						title : '家庭住址',
						visible:false
					},
					{
						field : 'address',
						title : '家庭住址',
						visible:false
					},
					{
						field : 'personnelCompilation',
						title : '人事编制',
						visible:false
					},
					{
						field : 'compilation_dept',
						title : '本人编制所在单位',
						visible:false
					},
					
					{
						field : 'isSecondment',
						title : '是否借出',
						visible:false
					},
					{
						field : 'secondDeptName',
						title : '借调单位',
						visible:false
					},
					{
						field : 'secondSection',
						title : '借调科室',
						visible:false
					},
					{
						field : 'isLeader',
						title : '是否领导职务',
						visible:false
					},
					{
						field : 'isMember',
						title : '是否领导班子成员',
						visible:false
					},
					{
						field : 'isReserveCadres',
						title : '是否后备干部',
						visible:false
					},
					{
						field : 'retiredDate',
						title : '退线时间',
						visible:false
					},
					{
						field : 'initialEducation',
						title : '初始学历',
						visible:false
					},
					{
						field : 'highestEducation',
						title : '最高学历',
						visible:false
					},
					{
						field : 'governmentTitle',
						title : '党政职务',
						visible:false
					},
					
					{
						field : 'partyTime',
						title : '入党派时间',
						visible:false
					},
					
					{
						field : 'orgTime',
						title : '服务本机构时间',
						visible:false
					},
					{
						field : 'jobLevel',
						title : '现职务层次',
						visible:false
					},
					
					{
						field : 'health',
						title : '健康状况',
						visible:false
					},
					{
						field : 'profession',
						title : '熟悉专业，有何特色',
						visible:false
					},
					{
						field : 'professionalSort',
						title : '专业类别',
						visible:false
					},
					{
						field : 'professionLevel',
						title : '专业级别',
						visible:false
					},
					{
						field : 'healthTechnicalTitle',
						title : '卫计类专业技术职称',
						visible:false
					},
					{
						field : 'nonhealthTechnicalTitle',
						title : '非卫计类专业技术职称',
						visible:false
					},
					{
						field : 'technicalPosition',
						title : '个人最高级别专业技术职务',
						visible:false
					},
					{
						field : 'technicalTime',
						title : '获得最高专业技术职务时间',
						visible:false
					},
					{
						field : 'hirepositionTime',
						title : '聘用最高级别专业技术职务时间',
						visible:false
					},
					{
						field : 'isGeneral',
						title : '是否全科医生',
						visible:false
					},
					{
						field : 'workStatus',
						title : '在岗情况',
						visible:false
					},
					{
						field : 'salaryLevel',
						title : '执行工资标准',
						visible:false
					},
					{
						field : 'salaryGrade',
						title : '执行工资档次',
						visible:false
					},
					{
						field : 'workYear',
						title : '退休时工龄',
						visible:false
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="查看" onclick="check(\''
							+ row.id
							+ '\')"><i class="fa fa-book"></i></a> ';
							
							return e + d +f;
						}
					} ]
			});
}
function reLoad() {
	var opt = {
		query : {
			name : $('#name').val(),
			idnumber : $('#idnumber').val(),
			party : $('#party').val(),
			sex : $('#sex').val(),
			documentCode : $('#documentCode').val(),
			section : $('#section').val(),
			nation : $('#nation').val(),
			telph : $('#telph').val(),
		}
	}
	$('#exampleTable').bootstrapTable('refresh', opt);
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '100%', '100%' ],
		content :'/document/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '100%', '100%' ],
		content : '/document/edit?id=' + id
	});
}

function check(id) {
	layer.open({
		type : 2,
		title : '查看',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		/*area : [ '800px', '520px' ],*/
		area : [ '100%', '100%' ],
		content : '/document/check/' + id
	});
}

function infor(id) {
	layer.open({
		type : 2,
		title : '信息统计',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		/*area : [ '800px', '520px' ],*/
		area : [ '100%', '100%' ],
		content : '/document/infor/' + id
	});
}

function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : "/document/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}

function addD(type,description) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add/'+type+'/'+description // iframe的url
	});
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url :'/document/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}

function uploadFile(this_){
	console.log(111);
    $.ajaxFileUpload({
        url : '/document/importExcel', //用于文件上传的服务器端请求地址
        secureuri : false, //是否需要安全协议，一般设置为false
        fileElementId : 'attachment', //文件上传域的ID
        data:{},
        dataType : 'JSON', //返回值类型 一般设置为json
        success : function(r) { //服务器成功响应处理函数
            if(r.code==0){
            	layer.alert("导入成功！",{icon:1});
                $("#attachment").val('');
            }else{
                $("#attachment").val('');//第一次上传后如果删除，第二次不能再上传该文件的bug
                layer.alert(r.msg ,{icon:2});
            }
        },
        error : function(data) { //服务器响应失败处理函数
            $("#attachment").val('');
            layer.alert("网络异常，请退出登录后再重试！",{icon:5});
        }
    });
}