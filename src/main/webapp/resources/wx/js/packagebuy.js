$(document).ready(function() { 
	var ckcode=window.location.search.split("=")[1];
	var ckdate=window.location.search.split("=")[2];
		   $("#ckcode").html(ckcode);
		   $("#ckdate").html(ckdate);
		   $(".showsuccess").show();
		   drawcode(ckcode);
 }); 
function drawcode(code){
	var qrcode = new QRCode(document.getElementById("ckcodeimg"), {
        width : 125,//设置宽高
        height : 125
    });
    qrcode.makeCode(code);
}
 
		
	
 
