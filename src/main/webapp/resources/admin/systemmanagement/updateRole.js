$(document).ready(function() { 
	$("#updateRole").click(function(){
		
		//获取选择节点内容
		var nodes = $('#tt').tree('getChecked');
		var nodeTmp = '';
		for(var i=0; i<nodes.length; i++){
			if (nodeTmp != '') nodeTmp += ',';
			nodeTmp += nodes[i].id;
		}
		
		var data={
				function_ids:nodeTmp,
				role_name:$("#role_name").val(),
				function_desc:$("#function_desc").val(),
				status:$('#wrap input[name="zhuangtai"]:checked').val(),
				role_id:$("#role_id").val()
		};
		
		$.ajax({ 
            type: "POST",
            url: "/NfCrm/admin/submitUpdateRole.do",
            data:data,
            async: false,
            dataType: "json",
            success: function(data){
                    if(data.code=="000"){
                    	swal({title: "系统提示",text: "角色信息变更成功",type: "success",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"
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


		
	
 
