function msg_show(tip,i){
    layer.msg(tip, {time: 3000, icon:i});
}

function open_show(url){
    layer.open({
        type: 2,
        title: false,
        area: ['500px', '330px'],
        shade: 0,
        closeBtn: 1,
        shadeClose: true,
        content:url
    });
}

$(".droplist").hover(function(){
    $(this).children("div").show();
    $(this).addClass('hover');
},function(){
    $(this).children("div").hide();
    $(this).removeClass('hover');
});

$(".free_phone,.pop_up").click(function(){
    open_show($(this).attr('request-url'));
});

$(".request-url").click(function(){
    var url = $(this).attr('request-url');
    var house_id = $(this).attr('data-bid');
    //请求ajax
    $.ajax({
        type: "post",
        url : url,
        dataType:'json',
        data:{house_id:house_id},
        success: function(json){
            if(json.status==1){
                msg_show(json.msg,1);
            }else{
                msg_show(json.msg,2)
            }
        }
    });
});

function pops(type){
    $('#msg-tip').css('display', 'none');
    if(type=='yy'){
        $(".hc_title strong").html('预约看房');
    }else if(type='jj'){
        $(".hc_title strong").html('降价通知');
        $(".hc_but").val("确 定");
    }
    $("#type").val(type);
    //捕获页
    layer.open({
        type: 1,
        area: ['546x', '380px'], //宽高
        shade: false,
        title: false, //不显示标题
        content: $('.hc_div'),
        cancel: function(index){
            layer.close(index);
            this.content.hide();
        }
    });
}

/**
 * 问答增加
 * @param url
 * @returns {boolean}
 */
function ask_add(url){
    var house_id = $("#house_id").val();
    var title = $('#askTitle').val();
    var info = $('#askInfo').val();
    var cate_id = $('#askCateId').val();
    var name = $('#askName').val();
    var phone = $('#askPhone').val();
    var answer_user_id = $("#answer_user_id").val();
    if(title=='' || title=='请输入标题'){
        msg_show('问答标题不能为空',2);
        return false;
    }
    if(info=='' || info=='请在这输入问题详细内容描述'){
        msg_show('请在输入问题详细内容描述',2);
        return false;
    }
    if(!cate_id || cate_id==0){
        msg_show('请在选择问答分类',2);
        return false;
    }
    if(answer_user_id){
        if(!name || !phone){
            msg_show('请输入您的联系信息',2);
            return false;
        }
    }
    //请求ajax
    $.ajax({
        type: "post",
        url : url,
        dataType:'json',
        data:{house_id:house_id,title:title,info:info,cate_id:cate_id,name:name,phone:phone,answer_user_id:answer_user_id},
        success: function(json){
            if(json.status==1){
                msg_show(json.msg,1);
            }else{
                msg_show(json.msg,2)
            }
        }
    });
}
/**
 * 问题回答
 * @param url
 * @returns {boolean}
 */
function answer_add(url){
    var ask_id = $("#ask_id").val();
    var info = $('#textarea').val();

    if(info=='' || info=='请在这输入您要回答的内容'){
        msg_show('请输入你要回答的内容',2);
        return false;
    }
    if(!ask_id){
        msg_show('没有找到对应的问答内容',2);
        return false;
    }
    //请求ajax
    $.ajax({
        type: "post",
        url : url,
        dataType:'json',
        data:{ask_id:ask_id,info:info},
        success: function(json){
            if(json.status==1){
                msg_show(json.msg,1);
            }else{
                msg_show(json.msg,2)
            }
        }
    });
}
/**
 * 顶起跟踩下
 * @param url
 * @returns {boolean}
 */
function top_step(url,t){
    var id = t.parent().attr("data-bid");
    var type = t.attr('data-boxtype');
    if(id==''){
        msg_show('找不到对应的ID信息',2);
        return false;
    }
    if(type==''){
        msg_show('没有找到对应操作指令',2);
        return false;
    }
    //请求ajax
    $.ajax({
        type: "post",
        url : url,
        dataType:'json',
        data:{id:id,type:type},
        success: function(json){
            if(json.status==1){
                t.html(json.v);
                msg_show(json.msg,1);
            }else{
                msg_show(json.msg,2)
            }
        }
    });
}

/**
 * 关注会员
 * @param url
 * @returns {boolean}
 */
function follow_user(url,id){
    //请求ajax
    $.ajax({
        type: "post",
        url : url,
        dataType:'json',
        data:{id:id},
        success: function(json){
            if(json.status==1){
                msg_show(json.msg,1);
            }else{
                msg_show(json.msg,2)
            }
        }
    });
}
/**
 * @param url
 * @returns {boolean}
 */
function msg_add(url){
    var type = $("#type").val();
    var house_id = $("#house_id").val();
    var name = $('input[name=name]').val();
    var phone = $('input[name=phone]').val();
    var remarks = $('input[name=remarks]').val();

    if(!house_id){
        $('#msg-tip').text('不是有效的楼盘信息');
        $('#msg-tip').css('display', 'block');
        return false;
    }

    if (name.length < 2 || name.length > 16 || name == '姓名'){
        $('#msg-tip').text('用户名填写错误');
        $('#msg-tip').css('display', 'block');
        return false;
    }

    if (!/^[1]\d{10}$/.test(phone)){
        $('#msg-tip').text('手机号填写错误');
        $('#msg-tip').css('display', 'block');
        return false;
    }
    if(remarks=='备注'){
        var remarks = '';
    }
    //请求ajax
    $.ajax({
        type: "post",
        url : url,
        dataType:'json',
        data:{house_id:house_id,phone:phone,type:type,name:name,remarks:remarks},
        success: function(json){
            if(json.status==1){
                msg_show(json.msg,1);
                window.location.reload();
            }else{
                msg_show(json.msg,2)
            }
        }
    });
}

/**
 * 提交点评
 * @param url
 */
function comm_add(url){
    var house_id = $("#house_id").val();
    var info = $("#info").val();
    //星级
    var price = $("input[name='price']").val();
    var area = $("input[name='area']").val();
    var traffic = $("input[name='traffic']").val();
    var mating = $("input[name='mating']").val();
    var environment = $("input[name='environment']").val();
    if(!price){
        msg_show('请对楼盘价格打星',5);
        return false;
    }
    if(!area){
        msg_show('请对楼盘地段打星',5);
        return false;
    }
    if(!traffic){
        msg_show('请对楼盘交通打星',5);
        return false;
    }
    if(!mating){
        msg_show('请对楼盘配套打星',5);
        return false;
    }
    if(!environment){
        msg_show('请对楼盘环境打星',5);
        return false;
    }
    if(house_id==''){
        msg_show('点评的楼盘ID不存在',5);
        return false;
    }
    if(info==''){
        msg_show('请填写点评内容',5);
        return false;
    }
    //请求ajax
    $.ajax({
        type: "post",
        url : url,
        dataType:'json',
        data:{house_id:house_id,info:info,price:price,area:area,mating:mating,environment:environment,traffic:traffic},
        success: function(json){
            if(json.status==1){

                msg_show(json.msg,1);
            }else{
                msg_show(json.msg,2)
            }
        }
    });
}

//顶部搜索
$("#top_search a").click(function(){
    $("#top_search a").removeClass('selected');
    $(this).addClass('selected');
    var type = $(this).attr('data-did');
    $("#search_type").val(type);
    if(type=='House'){
        var str = '请输入您要搜索的楼盘关键词';
    }else if(type=='Used'){
        var str = '请输入您要搜索的房源关键词';
    }else if(type=='Estate'){
        var str = '请输入您要搜索的小区关键词';
    }else if(type=='News'){
        var str = '请输入您要搜索的文章关键词';
    }else if(type=='Ask'){
        var str = '请输入您要搜索的问答关键词';
    }else if(type=='Rental'){
        var str = '请输入您要搜索的房源关键词';
    }
    $("#search_key").val(str);
    $("#top_c").val(type);
});
//点击搜索
$("#search_but").click(function(){
    var type = $("#top_c").val();
    var key = $("#search_key").val();
    $("#top_k").val(key);
    if(type=='House'){
        var str = '请输入您要搜索的楼盘关键词';
        if(key==str || key==''){
            msg_show(str,2);
            return false;
        }
    }else if(type=='Used'){
        var str = '请输入您要搜索的房源关键词';
        if(key==str || key==''){
            msg_show(str,2);
            return false;
        }
    }else if(type=='Estate'){
        var str = '请输入您要搜索的小区关键词';
        if(key==str || key==''){
            msg_show(str,2);
            return false;
        }
    }else if(type=='News'){
        var str = '请输入您要搜索的文章关键词';
        if(key==str || key==''){
            msg_show(str,2);
            return false;
        }
    }else if(type=='Ask'){
        var str = '请输入您要搜索的问答关键词';
        if(key==str || key==''){
            msg_show(str,2);
            return false;
        }
    }else if(type=='Rental'){
        var str = '请输入您要搜索的房源关键词';
        if(key==str || key==''){
            msg_show(str,2);
            return false;
        }
    }
    $("#topForm").submit();
});

function top_key(){
    var msg = $("#search_key").val();
    if($("#search_key").val() == "") {
        $("#search_key").val(msg);
    }else{
        $("#search_key").val("");
    }
    $("#search_key").focus(function () {
        if ($(this).val() == msg) {
            $(this).val("");
        }
    });
    $("#search_key").blur(function () {
        if ($(this).val() == "") {
            $(this).val(msg);
        }
    });
}

//显示导航
$("#web_nav_map").click(function(){
   $("#web_nav").toggle();
});

// 文本框文本域提示文字的自动显示与隐藏
/**
 * 使用很简单，方法是：fangCmsText，最简单是使用类似下面代码：
 $(".fangcms").fangCmsText();
 参数
 三个可控参数，一是默认的失去焦点的文字颜色，一个是文本框获得焦点时的文字颜色，还有一个就是切换显示的class，参见下面的实例：
 $("#test").fangCmsText({
        blurColor: "green",
        focusColor: "red",
        chgClass: "change"
    });
 */
(function($){
    $.fn.fangCmsText = function(options){
        options = options || {};
        var defaults = {
            blurColor: "#999",
            focusColor: "#333",
            auto: true,
            chgClass: ""
        };
        var settings = $.extend(defaults,options);
        $(this).each(function(){
            if(defaults.auto){
                $(this).css("color",settings.blurColor);
            }
            var v = $.trim($(this).val());
            if(v){
                $(this).focus(function(){
                    if($.trim($(this).val()) === v){
                        $(this).val("");
                    }
                    $(this).css("color",settings.focusColor);
                    if(settings.chgClass){
                        $(this).toggleClass(settings.chgClass);
                    }
                }).blur(function(){
                        if($.trim($(this).val()) === ""){
                            $(this).val(v);
                        }
                        $(this).css("color",settings.blurColor);
                        if(settings.chgClass){
                            $(this).toggleClass(settings.chgClass);
                        }
                    });
            }
        });
    };
})(jQuery);

