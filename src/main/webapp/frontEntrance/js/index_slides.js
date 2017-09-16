/* banner 滚动 */
function sliderBanner(options) {
    // slider-main li的高度
    var liHgt = $('.home-slider .slider-main li:first').height();
    // slider-item 最后一个item 里的ul定位
    $('.home-slider .slider-item .item:last ul').css({
        'left' : $('.home-slider .slider-item .item').width() - $('.home-slider .slider-item .item ul').outerWidth()
    });
    $(window).resize(function(){
        // slider-main li的高度
        liHgt = $('.home-slider .slider-main li:first').height();
        // slider-item 最后一个item 里的ul定位
        $('.home-slider .slider-item .item:last ul').css({
            'left' : $('.home-slider .slider-item .item').width() - $('.home-slider .slider-item .item ul').outerWidth()
        });
    });
    // slider-item 浮动弹出子选项
    $('.home-slider .slider-item .item').hover(function(){
        $(this).children('ul').stop(true,true).slideDown(100);
    },function(){
        $(this).children('ul').slideUp(100);
    });
    // 图片轮播
    // 初始值
    var defaults = {
        'imgNum' : $('.home-slider .slider-main li').size(),  // 图片数量
        'timeInterval' : 4000,                                // 时间间隔
        'colorArr': [
            '#C6202B',  // 第 1 张图片的背景颜色
            '#31813D',  // 第 2 张图片的背景颜色
            '#F1981B',  // 第 3 张图片的背景颜色
            '#008CEE',  // 第 4 张图片的背景颜色
            '#902789',  // 第 5 张图片的背景颜色
            '#4B4B4B'   // 第 6 张图片的背景颜色
        ]
    };
    var opts = $.extend({},defaults,options);
    // 默认给第一个li加active
    $('.home-slider .slider-main li:first, .home-slider .slider-item .item:first').addClass('active');
    // 给第一个li 设置opacity: 1
    $('.home-slider .slider-main li:first').css('opacity', 1);
    // 给 6 张图加背景颜色
    for(var i=0; i<$('.home-slider .slider-main li').size(); i++) {
        $('.home-slider .slider-main li').eq(i).css('background', opts.colorArr[i]);
    }
    var curIndex = 0;
    // 切换内容函数
    function switchImg(_index) {
        var curActive = $('.home-slider .slider-main li').eq(_index);
        if($('.home-slider .slider-main li:last').hasClass('active')) {
            $('.home-slider .slider-main li:first').addClass('active').animate({'opacity': 1}, 500).siblings().removeClass('active').css('opacity', 0.5);
        }
        curActive.next().addClass('active').siblings().removeClass('active');
        curIndex = _index + 1;
        $('.home-slider .slider-main li').eq(curIndex).animate({'opacity': 1}, 500).siblings().css('opacity', 0.5);
        if(curIndex == opts.imgNum) {
            curIndex = 0;
            $('.home-slider .slider-item .item').eq(curIndex).addClass('active').siblings().removeClass('active');
        }
        $('.home-slider .slider-main').css('top', -(curIndex * liHgt));
        $('.home-slider .slider-item .item').eq(curIndex).addClass('active').siblings().removeClass('active');
    }
    // 自动切换效果
    var _start = setInterval(function(){
        switchImg(curIndex);
    }, opts.timeInterval);
    // 鼠标浮动控制点 切换内容
    $('.home-slider .slider-item .item').mouseover(function(){
        clearInterval(_start);
        curIndex = $(this).index();
        $(this).addClass('active').siblings().removeClass('active');
        $('.home-slider .slider-main').css('top', -(curIndex * liHgt));
        $('.home-slider .slider-main li').eq(curIndex).animate({'opacity': 1}, 500).siblings().css('opacity', 0.5);
    }).mouseout(function(){
            _start = setInterval(function(){
                switchImg(curIndex);
            }, opts.timeInterval);
        });
    // 鼠标浮动li 暂停滚动
    $('.home-slider .slider-main li').mouseover(function(){
        clearInterval(_start);
    }).mouseout(function(){
            _start = setInterval(function(){
                switchImg(curIndex);
            }, opts.timeInterval);
        });
}