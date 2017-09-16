$(document).ready(function() { 
	
	//将后台传输的值插入select下拉框
	$.ajax({
		type: "POST",
		url: "/NfCrm/customer/selectOrganization.do",
		dataType : "json",
		success: function(data){
			var datamap=data.list;//得到json数据赋给datamap
			for(var i=0;i<datamap.length;i++){
				var son="<option value='" + datamap[i].team_id + "'>" + datamap[i].team_name + "</option>"
				 $("#teamId").append(son);
			}
					
		}
	});
	
	
	$("#search").click(function(){
		
		var data={
				user_group:$("#teamId").val(),
				user_name:$("#userName").val(),
				user_no:$("#userNo").val()
		};
		
		$.ajax({ 
            type: "POST",
            url: "/NfCrm/useradmin/queryUserInfo.do",
            data:data,
            async: false,
            dataType: "json",
            success: function(data){
                    if(data.code=="000"){
                    	swal({title: "系统提示",text: "角色信息变更成功",type: "success",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"
                    		},
                    		function(){
                    			window.location="../useradmin/queryUserInfo.do";
                    		});
                    	
                    }
            },error:function(rec){
           	      console.info("网络异常,请稍后再试");
            }
        });
		
	});
	
	
}); 


		
	
 
