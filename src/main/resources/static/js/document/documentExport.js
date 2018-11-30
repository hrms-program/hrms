$(function() {
	$('input[name="condition"]').click(function(){
		console.log($(this).val())
		if($(this).get(0).checked){
			$('#dataGrid').bootstrapTable('showColumn',$(this).val());
		}else{
			$('#dataGrid').bootstrapTable('hideColumn',$(this).val());
		}
		
	});
	
	$('#downloadExcel').click(function(){
		var query =$('#queryForm').serializeJsonObject();
		$('#dataGrid thead th').each(function(idx){
			var col=$(this).attr('data-field');
			query['C_'+idx+'_'+col]=$(this).text();
		})
		console.log(query);
		var rows=$('#exampleTable').bootstrapTable('getOptions').totalRows;//导出行数
		layer.msg('数据导出完成后，会自动打开。导出过程较慢，请稍候！', {icon: 16, shade: 0.01})
		setTimeout(function(){
		  layer.closeAll();
		}, 3*rows);
		postDownLoadFile({
            url:'/hrms/documentExport/export',
            data:query,
            method:'post'
	    });
	})
});


function postDownLoadFile(options) {
    var config = $.extend(true, { method: 'post' }, options);
    var $iframe = $('<iframe id="down-file-iframe" />');
    var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
    $form.attr('action', config.url);
    for (var key in config.data) {
        $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
    }
    $iframe.append($form);
    $(document.body).append($iframe);
    $form[0].submit();
    $iframe.remove();
}


function selectAll(){
	var baseInfo=$(this).parent().next().find('input[name="condition"]');
	baseInfo.each(function(){
		$(this).attr('checked','checked');
		$('#dataGrid').bootstrapTable('showColumn',$(this).val());
	})
	
}