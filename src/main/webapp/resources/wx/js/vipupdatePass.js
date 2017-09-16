/**
 * xiongjz2016/8/26修改密码
 */
$(document).ready(function() { 
$("#submit").click(function(){
    var old=$("#oldpassword").val();
    var new1=$("#newpassword").val();
    var new2=$("#old2password").val();
    if(old=="")
    	{
    	alert("请输入原密码");
    	return;
    	}
    if(new1=="")
    	{
    	alert("请输入新密码");
    	return;
    	
    	}
    if(new2=="")
    	{
    	 alert("请确认密码");
    	 return;
    	
    	}
    if(new1!=new2)
    	{
    	alert("新密码确认应该相同");
    	return;
    	
    	}
    if(old==new1)
    	{
    alert("新密码与原密码不能一样");
    return;
    	
    	}
    
	var data={
			oldPass:old,
			vipPass:new1	 
		};
		 $.ajax({
            type: "POST",
            url: "/dreamcardservice/weixin/updateVipPass.do",
            data:data,
            async: false,
            dataType: "json",
            success: function(data){
                      if(data.code=="000"){
                    	  alert("修改成功");
                   	   location.href="../index.html";
                      }else if(data.code=="001"){
                   	   alert("原密码错误，请重新输入");
                      } 
                      else{
                   	   alert("网络异常,请稍后再试");
                      }   
            },error:function(rec){
           	      console.info("网络异常,请稍后再试");
            }
        });
	});
});