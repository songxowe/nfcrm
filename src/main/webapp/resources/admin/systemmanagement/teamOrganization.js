/**
 * author:王超超
 * day:2016-11-19
 * @deprecated 组织架构
 */
$(document).ready(function() { 
	$('#tt').tree({
	    onClick: function(node){
	        if(node.node_level=="2"){
	        	swal({title: "系统提示",text: "结构节点添加上限",type: "warning",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"
        		},
        		function(){
        			window.location="../admin/addTeamPanel.do";
        		});
	        }else{
	        	$("#node_name").val(node.text);
		        $("#node_name").attr("name",node.id);
		        $("#currentNode").val("");
		        $('#team_desc').val("");
		        window.nodeLevel = node.node_level;
	        }
	    }
	});
	
	
	$("#submitTeam").click(function(){
		
		//获取选择节点内容
		var nodes = $('#node_name').attr("name");
		var currentNode = $('#currentNode').val();
		var team_desc = $('#team_desc').val();
		
		if(nodes=="" || nodes==null){
			alert("父节点不能为空");
			return;
		}
		if(currentNode=="" || currentNode==null){
			alert("当前节点不能为空");
			return;
		}
		if(team_desc=="" || team_desc==null){
			alert("节点描述不能为空");
			return;
		}
		if(window.nodeLevel=="2"){
			alert("增加节点上限");
			return;
		}
		
		var data={
				team_name:currentNode,
				parent_function_id:nodes,
				team_desc:team_desc,
				node_level:window.nodeLevel
		};
		
		$.ajax({ 
            type: "POST",
            url: "/NfCrm/admin/submitTeamPanel.do",
            data:data,
            async: false,
            dataType: "json",
            success: function(data){
                    if(data.code=="000"){
                    	swal({title: "系统提示",text: "机构新增成功",type: "success",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"
                    		},
                    		function(){
                    			window.location="../admin/addTeamPanel.do";
                    		});
                    	
                    }
            },error:function(rec){
           	      console.info("网络异常,请稍后再试");
            }
        });
		
	});
	
	
}); 


		
	
 
