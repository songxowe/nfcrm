$(document).ready(function() { 
	$("#updateOperate").click(function(){
		
		//获取选择节点内容
		var nodes = $('#tt').tree('getChecked');
		var role_id = $('#role_id').val();
		var nodeIds = '';
		for (var i = 0; i < nodes.length; i++) {
			if((i+1)==nodes.length){
				nodeIds += nodes[i].functionOperateIds;
			}else{
				nodeIds += nodes[i].functionOperateIds+",";
			}
		}
		var data = {
				nodeIds:nodeIds,
				role_id:role_id
		}
		
		$.ajax({ 
            type: "POST",
            url: "/NfCrm/admin/submitOperateButton.do",
            data:data,
            async: false,
            dataType: "json",
            success: function(data){
                    if(data.code=="000"){
                    	swal({title: "系统提示",text: "操作按钮变更成功",type: "success",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"
                    		},
                    		function(){
                    			window.location="../admin/queryAllPermList.do";
                    		});
                    	
                    }
            },error:function(rec){
           	      console.info("网络异常,请稍后再试");
            }
        });
		
	});
}); 


		
	
 
