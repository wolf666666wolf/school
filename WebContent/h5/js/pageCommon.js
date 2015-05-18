function alertMsg(dom,msg,left,bottomS,color,fontsize,bottomE){
    $msg = $("<div id='callBackMsg'>"+msg+"</div>").css({"position":"absolute","text-align":"center"}).css({"left":left,"bottom":bottomS,"color":color,"font-size":fontsize}).animate({"bottom":bottomE},1300);
    $(dom).append($msg);
    setTimeout(function(){
        $("#callBackMsg").remove();
    },1700);
}
/* $(function(){

    var w = $("body").width();
    if( $("body").attr("id")=="bind"){
        $("body").css({"font-size":w/500*15+"px"});
    }
    $("body").css({"font-size":w/500*10+"px"});
}); */