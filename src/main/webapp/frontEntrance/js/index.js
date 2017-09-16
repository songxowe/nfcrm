$(".tabsList li a").click(function(){
    var parent_id = $(this).parent().parent().attr('id');
    $("#"+parent_id+" a").removeClass('selected');
    $(this).addClass('selected');
    var bid = $(this).attr('data-bid');
    $("."+parent_id).hide();
    $("#"+bid).show();
});
//模版类型
function getTpl(model,json,id){
    $("#"+id).empty();
    var str = '';
    if(model=='house'){
        $(json.list).each(function(i,v){
            str +="<li> <span class='group_buy_l_img'><div class='buy_t1'>";
            str +="<h1><a target='_blank' href="+ v.url+">"+v.title+"</a></h1>";
            str +="</div><a target='_blank' href="+ v.url+"><img src="+v.img+" width='200' height='150' /></a></span>";
            str +="<h2><span class='g_b_r'><span class='ccc01'>["+ v.add1+"]</span></span>"+v.average_price+"元/㎡</h2> <p>"+ v.discount+"</p> </li>";
        });
    }

    if(model=='used'){
        $(json.list).each(function(i,v){
            str +="<li> <span class='group_buy_l_img'>";
            str +="<div class='buy_t1'><h1><a target='_blank' href="+ v.url+">"+ v.type_area+"-"+v.area+"㎡</a></h1></div>";
            str +=" <a target='_blank' href="+ v.url+"><img src="+v.img+" width='200' height='150' /></a></span>";
            str +="<h2><span class='g_b_r'>总价:<span class='red01'>"+ v.total_price+"</span>万</span>"+v.average_price+"元/㎡</h2>";
            str +="<p>"+ v.title +"</p></li>";
        });
    }

    if(model=='rental'){
        $(json.list).each(function(i,v){
            str +="<li> <span class='group_buy_l_img'>";
            str +="<div class='buy_t1'><h1><a target='_blank' href="+ v.url+">"+ v.type_area+"-"+v.area+"㎡</a></h1></div>";
            str +=" <a target='_blank' href="+ v.url+"><img src="+v.img+" width='200' height='150' /></a></span>";
            str +="<h2><span class='g_b_r'><span class='ccc01'>["+ v.data.estate+"]</span></span>"+v.price+"元/月</h2>";
            str +="<p>"+ v.title +"</p></li>";
        });
    }

    if(model=='estate'){
        $(json.list).each(function(i,v){
            str +="<li> <span class='group_buy_l_img'>";
            str +="<div class='buy_t1'><h1><a target='_blank' href="+ v.url+">"+ v.title+"</a></h1></div>";
            str +=" <a target='_blank' href="+ v.url+"><img src="+v.img+" width='200' height='150' /></a></span>";
            str +="<h2 class='estate_h2'>二手房(<span>"+ v.data.sell_count+"</span>)套&nbsp;&nbsp;&nbsp;出租房(<span>"+ v.data.lease_count+"</span>)套</h2>";
            str +="<p><span class='ccc02'>"+ v.address +"</span></p></li>";
        });
    }

    $("#"+id).append(str);
}