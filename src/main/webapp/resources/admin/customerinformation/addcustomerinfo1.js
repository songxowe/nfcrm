//弹出层js
function show(tag) {
		$("input[name='recommend_name']").val("");
		var light = document.getElementById(tag);
		var fade = document.getElementById('fade');
		light.style.display = 'block';
		fade.style.display = 'block';
		$("#Customertable tr").remove();
		
	//将Window open里的项目回显到页面
		if (tag=="light"){
			var pro=$("input[name='Cpro']");
			$("#testProId").html("");//每次点击进来先清空列表
			var html_select = "<option value='0'>请选择</option>";
			var str="";
			var str1="";
			var str2="";
			for (var i = 0; i < pro.length; i++) {
				var tmpObj = pro[i];
				if(tmpObj.checked){
					str += "Y"+"|"+tmpObj.value+"|"+tmpObj.id+"|";
					str1 += tmpObj.value;
					str2 += tmpObj.id;
					html_select += "<option value='"+tmpObj.id+"'>"+tmpObj.value+"</option>";
				}else{
					str += "N"+"|"+tmpObj.value+"|"+tmpObj.id+"|";
					html_select += "<option value='"+tmpObj.id+"'>"+tmpObj.value+"</option>";
				}
			}
			$("#submitParam").val(str);
			$("#PN").val(str1);
			$("#PI").val(str2);
			$("#testProId").append(html_select);
	        
		}
	}

//Check box设置为单选
$("input[name='Cpro']").live("click",function(){
	$("input[name='Cpro']").attr("checked",false);
	$(this).attr("checked",true);
});

//隐藏弹框
function hide(tag) {
		var light = document.getElementById(tag);
		var fade = document.getElementById('fade');
		light.style.display = 'none';
		fade.style.display = 'none';

}

//windows open 弹框  项目新增弹框 --------------------------------------------
function mainOpen(){
	  var iWidth=800;                          //弹出窗口的宽度; 
	  var iHeight=510;                         //弹出窗口的高度; 
	  var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
      //获得窗口的水平位置 
      var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
      var url="/NfCrm/clocustomer/redirectProPan.do";
	 window.open(url,"child",'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no'); 
}


$(document).ready(function(){
	
	//查询所有项目
	$("#showPro").click(function() {  
		mainOpen();
    }); 
//------------------------------------------------------------------------	
	
	
	//获得项目id
	$("#testProId").blur(function(){
		var State=$("#testProId").val();
		$("#pid").val(State);
	});
	
	
	//上传图片
	$("#up_button").click(function(){
		var photo=$(":input[name='file1']").val();
		var phostr=photo.substring(12);
		var formData = new FormData($("#upform")[0]);
		$.ajax({ 
            type: "POST",
            url: "/NfCrm/clocustomer/upload.do",
            data:formData,
            processData : false,
			contentType : false,
			datetype : "json",
            success: function(data){
            	swal({title: "系统提示",text: "上传成功！",type: "success",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"
        		},
        		function(){
        			var dat=JSON.parse(data);
        			var date=dat[0].filePath;
        			var str="../";
        			var pho=str+date;
        			$("#pho").attr('src',pho);
        		});
            },error:function(rec){
           	      console.info("网络异常,请稍后再试");
            }
        });
	});
	
	
   //添加客户跟进日志信息并进行查询--------------------------------------------------------
    $("#submit").click(function() {
    	var pro_id = $("input[name='pro_id']").val();
    	var follow_time = $("input[name='follow_time']").val();
    	var follow_desc = $("input[name='follow_desc']").val();
    	var repeat_follow = $("input[name='repeat_follow']").val();
    	var follow_scheme = $("input[name='follow_scheme']").val();

    	if(pro_id==""||follow_time==""||follow_desc==""||repeat_follow==""||follow_scheme==""){
    	   swal({title: "温馨提醒",text: "信息还未填写完整！",type: "warning",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"});
    	   }else{
    		   
    	var followname=$("#testProId").find("option:selected").text();
    	var data = {
    	    pro_id:$("input[name='pro_id']").val(),
    	    follow_pro:followname,
    	    follow_time : $("input[name='follow_time']").val(),
    	    follow_img : $("#pho").attr("src"),
    	    follow_desc : $("textarea[name='follow_desc']").val(),
    	    repeat_follow : $("input[name='repeat_follow']").val(),
    	    follow_scheme : $("textarea[name='follow_scheme']").val(),
    	};
    	$.ajax({
    		type : "POST",
    		url : "/NfCrm/clocustomer/addlog.do",
    		data : data,
    		async : true,
    		dataType : "json",
    		success : function(data) {
    			swal({title: "系统提示",text: "上传成功！",type: "success",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"},
        		function(){});
    			var logId=data.logid;
    			var data1={logId:logId};
    			
		      //将后台传输的值插入table
		      $.ajax({
		        type: "POST",
		        url: "/NfCrm/clocustomer/selectLog.do",
		    	dataType : "json",
		    	data : data1,
		        success: function(sundata){
		    	var datamap=sundata.list;//得到json数据赋给datamap
		 
		    	for(var i=0;i<datamap.length;i++){
		    		var record=datamap[i].phone_record;
		    		if(record==""||record==null){
		    			record="暂无";
		    		}
		    		var img=datamap[i].follow_img;
		    		if(img==""||img==null){
		    			img="无";
		    		}else{
		    			img="有";
		    		}
		        var son="<tr><td>"+datamap[i].follow_time+"</td><td>"+datamap[i].follow_pro+"</td><td>" 
		        +record+"</td><td>"+datamap[i].follow_desc+"</td><td>"+img+"</td><td>"
		        +datamap[i].repeat_follow+"</td><td>"+"<input type='button' id="
		        +logId+" name='logDetail' value='查看' onclick='LogDetail("+logId+")'>"+"</td></tr>";
		    	$("#mytable").append(son);
		    	    }
		    	}
		    });
    	},
	    		error : function(rec) {
	    			console.info("网络异常,请稍后再试");
	    		}
    	    });
         }	
    });
    
    
    
    var data3 ="";
    //查询跟进日志字数
    $.ajax({
		type : "POST",
		url : "/NfCrm/clocustomer/queryloginfo.do",
		data :data3,
		async : true,
		success : function(fff) {
		   $("#contextSize").val(fff.log);
		},
		error : function(rec) {
			console.info("网络异常,请稍后再试");
		}
	});
    
   
    
   //添加推荐客户名
       var UserList=0;
    $("#zcq").click(function() {
		var recommend_name=$("input[name='recommend_name']").val();
		if(recommend_name!=""){
		var newE="<tr><td name='Rn'>"+recommend_name+"</td></tr>";
		$("#Customertable").append(newE);
		   UserList++;
		}
		});
    
    
   
    //匹配推荐客户名
    $("#subfrom").click(function() {
		    	var Rm_name="";
		    	var Rn=$("td[ name='Rn']");
		    	for (var i = 0; i <Rn.length; i++) {
		    		Rm_name += Rn[i].innerHTML + "|";
		    	}
		    	var data = {
		    			Rn:Rm_name,		
		    	}; 
		    	var usercount=Number($("#Po_list").val());
		    	if(usercount!=UserList){
		    		if(usercount>UserList)
		    		alert("已选择"+UserList+"客户，还差"+(usercount-UserList)+"个！");
		    		else
		    		alert("已选择"+UserList+"客户，多了"+(UserList-usercount)+"个！");
		    		UserList=0;
		    			return false;
		    	}
		    	
		    	$.ajax({
		    		type : "POST",
		    		url : "/NfCrm/clocustomer/selectCustomerName.do",
		    		data : data,
		    		async : true,
		    		dataType : "json",
		    		success : function(data) {
						var list=0;
		    			var strHtml="";
		    			for (var key in data) {  
		    				for(var i in data[key]){  
		    				   // msg+=i+": "+Pin[key][i]+"\n";  
		    				    if(data[key][i]=="N"){
		    				    	strHtml+="<label>"+i+":未注册;</br></label>";
		    				    	list++;
		    				    }else{
		    				    	strHtml+="<label>"+i+":已注册;</br></label>";
		    				    }
		    				}  
		    			}  
		    			$("#Customertable").html("");
		    			$("#Po_list").val($("#Po_list").val()==""?"":((Number($("#Po_list").val())-list)<0?0:(Number($("#Po_list").val())-list)));
		    			$("#showMsg").append(strHtml);
		    		},
		    		error : function(rec) {
		    			console.info("网络异常,请稍后再试");
		    		}
		    	});
		   });
});

//输入框校验
function submitData(){
	var customerFrom = document.getElementById('customerFrom');
	var IsSub=true;
	var AlertValue="";
	if($("#CustomerName").val()==""){
		IsSub=false;
		AlertValue="客户名称";
	}else if($("#CustomerSource").val()==""){
		IsSub=false;
		AlertValue="客户来源";
	}else if($("#Region").val()==""){
		IsSub=false;
		AlertValue="所在区域";
	}else if($("#CustomerLevel").val()==""){
		IsSub=false;
		AlertValue="客户等级";
	}else if($("#UnitAddress").val()==""){
		IsSub=false;
		AlertValue="公司地址";
	}else if($("#PN").val()==""){
		IsSub=false;
		AlertValue="主推项目";
	}if(IsSub){
		   customerFrom.submit();
	}else{
	     if(IsSub=true){
	    	 alert("请将"+AlertValue+"填写完整！！"); 
	    	 return;
	      }
	   }
	}


function checkPhone(){ 
    var phone = $("input[name='phone']").val();
    if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){ 
        alert("手机号码有误，请重填");  
        $("input[name='weixin']").val("");
        return false; 
    } else{
    	return true;
    }
}


function checkQQ(){ 
    var qq = $("input[name='qq']").val();
    if(!(/^[1-9]\d{0,10}$/.test(qq))){ 
        alert("qq号码有误，请重填");  
        $("input[name='qq']").val("");
        return false; 
    } else{
    	return true;
    }
}


function checkWX(){ 
    var wx = $("input[name='weixin']").val();
    if(!(/^[a-zA-Z\d_]{5,}$/.test(wx))){ 
        alert("微信号码有误，请重填"); 
        $("input[name='weixin']").val();
        return false; 
    } else{
    	return true;
    }
}

function checkdesc(){ 
	var maxChars = $("#contextSize").val();
    var desc = $("textarea[name='follow_desc']").val();
    if(desc.length<maxChars){ 
        alert("跟进备注字数不够，请重填"); 
        $("textarea[name='follow_desc']").val("");
        return false; 
    } else{
    	return true;
    }
}

function checkscheme(){ 
	var maxChars = $("#contextSize").val();
    var scheme = $("textarea[name='follow_scheme']").val();
    if(scheme.length<maxChars){ 
        alert("下次跟进方案字数不够，请重填"); 
        $("textarea[name='follow_scheme']").val("");
        return false; 
    } else{
    	return true;
    }
}
