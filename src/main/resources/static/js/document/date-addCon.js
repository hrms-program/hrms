$(document).ready(function(){
	
	// 添加职务职岗并行经历
	$(".add-jobPositionsCon").click(function(){
		$("#jobPositions-experience .form-wrap").show();
		$("#jobPositions-experience .exp_none_div").css('height','20rem');
	});
	$("#jobPositions_form_add .cancel-btn").click(function(){
		$("#jobPositions-experience .form-wrap").hide();
		$("#jobPositions-experience .exp_none_div").css('height','20rem');
	});
	
	
	// 添加行政职务
	$(".add-administrativeCon").click(function(){
		$("#administrative-experience .form-wrap").show();
		$("#administrative-experience .exp_none_div").css('height','14rem');
	});
	$("#administrative_form_add .cancel-btn").click(function(){
		$("#administrative-experience .form-wrap").hide();
		$("#administrative-experience .exp_none_div").css('height','14rem');
	});
	
	// 添加党内职务
	$(".add-partyCon").click(function(){
		$("#party-experience .form-wrap").show();
		$("#party-experience .exp_none_div").css('height','14rem');
	});
	$("#party_form_add .cancel-btn").click(function(){
		$("#party-experience .form-wrap").hide();
		$("#party-experience .exp_none_div").css('height','14rem');
	});
	
	// 添加工作经历
	$(".add-parellelPostCon").click(function(){
		$("#parellelPost-experience .form-wrap").show();
		$("#parellelPost-experience .exp_none_div").css('height','14rem');
	});
	$("#parellelPost_form_add .cancel-btn").click(function(){
		$("#parellelPost-experience .form-wrap").hide();
		$("#parellelPost-experience .exp_none_div").css('height','14rem');
	});
	
	// 添加工作经历
	$(".add-workCon").click(function(){
		$("#work-experience .form-wrap").show();
		$("#work-experience .exp_none_div").css('height','14rem');
	});
	$("#gzjl_form_add .cancel-btn").click(function(){
		$("#work-experience .form-wrap").hide();
		$("#work-experience .exp_none_div").css('height','14rem');
	});
	
	// 添加教育经历
	$(".add-eduCon").click(function(){
		$("#edu-experience .form-wrap").show();
		$("#edu-experience .exp_none_div").css('height','14rem');
	});
	$("#edu_form_add .cancel-btn").click(function(){
		$("#edu-experience .form-wrap").hide();
		$("#edu-experience .exp_none_div").css('height','14rem');
	});
	
	// 添加职称信息
	$(".add-jobtitCon").click(function(){
		$("#jobTit-experience .form-wrap").show();
		$("#jobTit-experience .exp_none_div").css('height','14rem');
	});
	$("#tit_form_add .cancel-btn").click(function(){
		$("#jobTit-experience .form-wrap").hide();
		$("#jobTit-experience .exp_none_div").css('height','14rem');
	});
	
	// 添加培训经历
	$(".add-trainCon").click(function(){
		$("#train-experience .form-wrap").show();
		$("#train-experience .exp_none_div").css('height','14rem');
	});
	$("#train_form_add .cancel-btn").click(function(){
		$("#train-experience .form-wrap").hide();
		$("#train-experience .exp_none_div").css('height','14rem');
	});
	
	// 添加奖惩情况
	$(".add-rewardCon").click(function(){
		$("#reward-experience .form-wrap").show();
		$("#reward-experience .exp_none_div").css('height','14rem');
	});
	$("#reward_form_add .cancel-btn").click(function(){
		$("#reward-experience .form-wrap").hide();
		$("#reward-experience .exp_none_div").css('height','14rem');
	});
	
	// 添加处罚情况
	$(".add-punishCon").click(function(){
		$("#punish-experience .form-wrap").show();
		$("#punish-experience .exp_none_div").css('height','14rem');
	});
	$("#punish_form_add .cancel-btn").click(function(){
		$("#punish-experience .form-wrap").hide();
		$("#punish-experience .exp_none_div").css('height','14rem');
	});
	
	// 添加年度考核
	$(".add-certificateCon").click(function(){
		$("#certificate-experience .form-wrap").show();
		$("#certificate-experience .exp_none_div").css('height','14rem');
	});
	$("#certificate_form_add .cancel-btn").click(function(){
		$("#certificate-experience .form-wrap").hide();
		$("#certificate-experience .exp_none_div").css('height','14rem');
	});
	
	// 添加家庭成员信息
	$(".add-homeCon").click(function(){
		$("#home-experience .form-wrap").show();
		$("#home-experience .exp_none_div").css('height','17rem');
	});
	$("#home_form_add .cancel-btn").click(function(){
		$("#home-experience .form-wrap").hide();
		$("#home-experience .exp_none_div").css('height','17rem');
	});
	
	
	  //一个页面同时生成多个时间控件
	  lay('.render-date').on('click', function(e){
		  laydate.render({
		    elem: this,
		    show:true,
		    closeStop: this,
		    format: 'yyyy-MM-dd'
		  });
		}); 
	  
	  lay('.render-year-month').on('click', function(e){
		  laydate.render({
		    elem: this,
		    show:true,
		    closeStop: this,
		    type: 'month',
		    format: 'yyyy-MM'
		  });
		}); 
	  
	  
		// 年度
	  lay('.render-year').on('click',function(e){
		  laydate.render({
			  elem: this,
			  show:true,
			  closeStop: this,
			  type: 'year',
			  format: 'yyyy'
		  });
	  });
	  
	  lay('.render-year-month11').on('click', function(e){
		  laydate.render({
			  elem: '#workTime',
			  show:true,
			  closeStop: this,
			  type: 'month',
			  format: 'yyyy-MM',
			  done: function(value){
				  console.log(1111);
				  getWorkYear(value);
				  
			  }
		});
	  });
	  
	  lay('.render-year-month22').on('click', function(e){
		  laydate.render({
			  elem: '#position-firstServingTime22',
			  show:true,
			  closeStop: this,
			  type: 'month',
			  format: 'yyyy-MM',
			  done: function(value){
				  console.log(1111);
				  getpositionYear(value);
				  
			  }
		});
	  });
	  
	  lay('.render-year-month33').on('click', function(e){
		  laydate.render({
			  elem: '#position-firstServingTime11',
			  show:true,
			  closeStop: this,
			  type: 'month',
			  format: 'yyyy-MM',
			  done: function(value){
				  console.log(1111);
				  getInnerPositionYear(value);
				  
			  }
		});
	  })
	  
});


