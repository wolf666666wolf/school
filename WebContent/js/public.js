/** 
 *Description: the stylesheet
 *@Author: bradenhan
 *@email: bradenhan@126.com
 *@date: 2014-09-17 23:24:30
 *@ersion 1.0
 */
$(document).ready(function() {
	//底部滚动
	$("#footerPartners").jCarouselLite({
		btnPrev: "#prev",
		btnNext: "#next",
		auto: 3000, //图片停留时间
		scroll: 5, //每次滚动覆盖的图片个数
		speed: 1000, //设置速度，0是不动。其次就是数字越大 ，移动越慢。
		vertical: false, //横向（true），竖向（false）
		visible: 5, //显示的数量
		circular: true //是否循环
	});
	//导航 
	
	$('.nav-box .main-nav').mouseenter(function() {
		$('.nav-box .main-nav > li').mouseenter(function() {
			$(this).addClass('current').siblings('li').removeClass('current');
		});
	}).mouseleave(function() {
		$('.nav-box .main-nav li').removeClass('current');
	});
});