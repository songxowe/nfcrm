var i = 0;
function addoneline() {
	i++;
	var oneline = "<div style='width:50%'><select name='photo"
			+ i
			+ "type' class='input w50'><option value=''>请选择图片</option><option value='indoor'>室内图</option><option value='show'>展示图</option><option value='tile'>平铺图</option><option value='envir'>环境图</option></select></div><div><a style='float: left;margin-top: 10px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a><input type='file' class='input w50' value='' style='width:12%;' name='photo"
			+ i
			+ "' /></div><div><a style='float: left;margin-top: 10px;'>&nbsp;&nbsp;&nbsp;&nbsp;备注：</a><input type='text' class='input w50' name='photo"
			+ i
			+ "remark' style='width:18%;float:left;' /></div><input type='hidden' name='projectid'/><br><br><br><br>";

	$("#extra").append(oneline);
}
/** --------------点击添加环境配置 ------------------* */
function addfacilities() {
	var faciliti="<input type='text' id='facilities' name='confi' class='input' style='width:120px;margin-top:-10px; float:left;' /><a style='float:left'>&nbsp;&nbsp</a>";
	$("#facilities").append(faciliti);
}
function addoperate() {
	var operate="<input type='text' id='operate' name='confi' class='input' style='width:120px;margin-top:-10px; float:left;' /><a style='float:left'>&nbsp;&nbsp</a>";
	$("#operate").append(operate);
}
function addworkstep() {
	var workstep="<input type='text' id='workstep' name='confi' class='input' style='width:120px;margin-top:-10px; float:left;' /><a style='float:left'>&nbsp;&nbsp</a>";
	$("#workstep").append(workstep);
}
function findtype(){
	if($("#protype option:selected").val()==0){
		swal({title: "提示",text: "请选择所附属的公司项目。",confirmButtonColor: "#87cefa",confirmButtonText: "确定"
		},
		function(){
			$("#parent").show();
			$("#parenttype option:first").prop("selected", 'selected');
			$("#parenttype option:selected").attr("disabled", true);
			$("input[name='area_min']").attr("disabled", true);
			$("input[name='area_max']").attr("disabled", true);
			$("input[name='price_min']").attr("disabled", true);
			$("input[name='price_max']").attr("disabled", true);
			$("input[name='pro_name']").attr("disabled", true);
			$("#emlop option:selected").attr("disabled", true);
			$("input[name='spread_type']").attr("disabled", true);
			$("input[name='region']").attr("disabled", true);
			$("input[name='actual_price']").attr("disabled", true);
			$("input[name='actual_area']").attr("disabled", true);
			$("input[name='p_type']").attr("disabled", true);
			$("input[name='profession']").attr("disabled", true);
			$("input[name='features']").attr("disabled", true);
			$("input[name='address']").attr("disabled", true);
			$("input[name='floor']").attr("disabled", true);
			$("input[name='property_price']").attr("disabled", true);
			$("input[name='decoration']").attr("disabled", true);
			$("input[name='food']").attr("disabled", true);
			$("select[name='food']").attr("disabled", true);
			$("select[name='confi']").attr("disabled", true);
		});
	}else if($("#protype option:selected").val()==1){
			$("#parent").hide();
			$("button[name='addconfigbtn']").show();
			$("input[name='pro_name']").val("");
			$("#emlop").val("");
			$("input[name='area_min']").val("");
			$("input[name='area_max']").val("");
			$("input[name='price_min']").val("");
			$("input[name='price_max']").val("");
			$("input[name='spread_type']").val("");
			$("input[name='region']").val("");
			$("input[name='actual_price']").val("");
			$("input[name='actual_area']").val("");
			$("input[name='p_type']").val("");
			$("input[name='profession']").val("");
			$("input[name='features']").val("");
			$("input[name='address']").val("");
			$("input[name='floor']").val("");
			$("input[name='property_price']").val("");
			$("input[name='decoration']").val("");
			$("input[name='food']").val("");
			$("input[name='phone']").val("");
			$("input[name='tel']").val("");
			$("input[name='confi']").remove();
			$("img[name='photo']").remove();
			
			$("input[button='addconfigbtn']").show();
			$("input[name='food']").attr("disabled", false);
			$("select[name='food']").attr("disabled", false);
			$("input[name='area_min']").attr("disabled", false);
			$("input[name='area_max']").attr("disabled", false);
			$("input[name='price_min']").attr("disabled", false);
			$("input[name='price_max']").attr("disabled", false);
			$("input[name='pro_name']").attr("disabled", false);
			$("#emlop option:selected").attr("disabled", false);
			$("input[name='spread_type']").attr("disabled", false);
			$("input[name='region']").attr("disabled", false);
			$("input[name='actual_price']").attr("disabled", false);
			$("input[name='actual_area']").attr("disabled", false);
			$("input[name='p_type']").attr("disabled", false);
			$("input[name='profession']").attr("disabled", false);
			$("input[name='features']").attr("disabled", false);
			$("input[name='address']").attr("disabled", false);
			$("input[name='floor']").attr("disabled", false);
			$("input[name='property_price']").attr("disabled", false);
			$("input[name='decoration']").attr("disabled", false);			
			$("input[name='tel']").attr("disabled", false);
			$("input[name='phone']").attr("disabled", false);
			$("#protype option:selected").attr("disabled", false);
	}
}
function finddetail(){
		var parent_type= $("#parenttype option:selected").val();
		window.location="/NfCrm/proj/rebackdata.do?pro_id="+parent_type;
	}
/** +++++++++++++++++++++++++++++++++结束++++++++++++++++++++++++++ */

/**
 * 动态加载模板
 */
$(document).ready(
		function() {
			$.ajax({
				type : "GET",
				url : "/NfCrm/template/selectTemplate2.do",
				async : true,
				dataType : "json",
				success : function(data) {
					var num = data.mylist;
					for (var i = 0; i < num.length; i++) {
						var options = "<option value='" + num[i].t_id + "'>"
								+ num[i].template_name + "</option>";
						$("#emlop").append(options);
					}
				},
				error : function(rec) {
					console.info("网络异常,请稍后再试");
				}
			});
		/*	查公司项目*/
			$.ajax({
				type : "POST",
				url : "/NfCrm/proj/queryprotype.do",
				async : true,
				dataType : "json",
				success : function(ptype) {
					var protype = ptype.protypelist;
					for (var i = 0; i < protype.length; i++) {
						var options = "<option value='" + protype[i].pro_id + "'>"
								+ protype[i].pro_name + "</option>";
						$("#parenttype").append(options);
					}
				},
				error : function(rec) {
					console.info("网络异常,请稍后再试");
				}
			});
			
			if($("#protype option:selected").val()=='please'){
				$("input[name='area_min']").attr("disabled", true);
				$("input[name='area_max']").attr("disabled", true);
				$("input[name='price_min']").attr("disabled", true);
				$("input[name='price_max']").attr("disabled", true);
				$("input[name='pro_name']").attr("disabled", true);
				$("#emlop option:selected").attr("disabled", true);
				$("input[name='spread_type']").attr("disabled", true);
				$("input[name='region']").attr("disabled", true);
				$("input[name='actual_price']").attr("disabled", true);
				$("input[name='actual_area']").attr("disabled", true);
				$("input[name='p_type']").attr("disabled", true);
				$("input[name='profession']").attr("disabled", true);
				$("input[name='features']").attr("disabled", true);
				$("input[name='address']").attr("disabled", true);
				$("input[name='floor']").attr("disabled", true);
				$("input[name='property_price']").attr("disabled", true);
				$("input[name='decoration']").attr("disabled", true);
				$("select[name='food']").attr("disabled", true);
				$("input[name='food']").attr("disabled", true);
				$("input[name='tel']").attr("disabled", true);
				$("input[name='phone']").attr("disabled", true);
				$("#protype option:selected").attr("disabled", true);
				$("input[name='confi']").attr("disabled", true);
			}
			if($("input[name='pro_name']").val()==""){
				swal("请选择项目类型");
			}else{
				$('#protype option:eq(1)').attr('selected','selected');
				$("#parent").show();
				/*var pro_name=$("input[name='pro_name']").val();
				$("#parenttype option").each(function(){  
			        if($(this).text() == pro_name){  
			            $(this).attr("selected","selected");  
			        }  
			    });*/
				$("#parenttype option:first").prop("selected", 'selected');
				var price=$("input[name='price_range']").val();
				var priceRange=price.split("~");
				for(var i=0;i<priceRange.length;i++){
					if(i%2==0){
						$("input[name='price_min']").val(priceRange[0]);
					}else{
						$("input[name='price_max']").val(priceRange[1]);
					}
				}
				$("button[name='addconfigbtn']").hide();
				var price=$("input[name='area_range']").val();
				var priceRange=price.split("~");
				for(var i=0;i<priceRange.length;i++){
					if(i%2==0){
						$("input[name='area_min']").val(priceRange[0]);
					}else{
						$("input[name='area_max']").val(priceRange[1]);
					}
				}
				$("input[name='tel']").attr("disabled", false);
				$("input[name='phone']").attr("disabled", false);
			}
			
	/**
	 * 提交新增项目的内容
	 */
	$("#submit").click(function() {
			var str = "";
			var items = $("input[name='confi']");
			for (var i = 0; i < items.length; i++) {
				str += items[i].value + ","
						+ items[i].id + "|";
			}
			var areamin=$("input[name='area_min']").val();
			var areamax=$("input[name='area_max']").val();
			var area=areamin+'~'+areamax;
			var pricemin=$("input[name='price_min']").val();
			var pricemax=$("input[name='price_max']").val();
			var price_range=pricemin+'~'+pricemax;
			var pro_name=$("input[name='pro_name']").val();
			var pro_type= $("#protype option:selected").val();
			var pro_template=$("#emlop option:selected").text();
			var pro_template_id=$("#emlop option:selected").val();
			var spread_type=$("input[name='spread_type']").val();
			var region=$("input[name='region']").val();
			var actual_price=$("input[name='actual_price']").val();
			var actual_area=$("input[name='actual_area']").val();
			var p_type=$("input[name='p_type']").val();
			var profession= $("input[name='profession']").val();
			var features=$("input[name='features']").val();
			var address=$("input[name='address']").val();
			var floor=$("input[name='floor']").val();
			var property_price=$("input[name='property_price']").val();
			var property=property_price+"元/平米/月";
			var decoration=$("input[name='decoration']").val();
			var food=$("input[name='food']").val();
			var phone=$("input[name='phone']").val();
			var tel=$("input[name='tel']").val();
			if(str==""||areamin==""||areamax==""||pro_name==""||pricemin==""||pricemax==""||pro_type==""||pro_template==""||spread_type==""||region==""||actual_price==""||actual_area==""||
					p_type==""||profession==""||features==""||address==""||floor==""||property_price==""||decoration==""||food==""||phone==""||tel==""){
				swal({title: "温馨提醒",text: "信息还未填写完整！",type: "warning",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"
        		},
        		function(){
        			
        		});
			}else{
			var date = {
				confi : str,
				pro_name :pro_name,
				pro_type :pro_type,
				pro_template :pro_template ,
				pro_template_id:pro_template_id,
				spread_type :spread_type ,
				region :region ,
				price_range : price_range,
				actual_price :actual_price,
				area_range : area,
				actual_area :actual_area,
				p_type : p_type,
				profession :profession,
				features :features,
				address : address,
				floor : floor,
				property_price : property,
				decoration : decoration,
				food :food ,
				phone :phone,
				tel : tel
			};

		$.ajax({
			type : "POST",
			url : "/NfCrm/proj/addproMsg.do",
			data : date,
			async : true,
			dataType : "json",
			success : function(rsp) {
				$("input[name='projectid']").val(rsp.pro_id);
					var formData = new FormData($("#upform")[0]);
					var url = "/NfCrm/proj/upload.do";
					$.ajax({
						url : url,
						type : 'POST',
						data : formData,
						processData : false,
						contentType : false,
						datetype : "json",
						success : function(responseStr) {
							swal({title: "系统提示",text: "新增项目成功！",type: "success",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"
			        		},
			        		function(){
			        			window.location = "/NfCrm/proj/selectallpro.do";
			        		});
						},
						error : function(
								responseStr) {
							alert("失败:"+ JSON.stringify(responseStr));// 将json对象转成json字符串。
						}
					});
			},
			error : function(rec) {
				console.info("网络异常,请稍后再试");
			}
		});
			}
		});

	
});