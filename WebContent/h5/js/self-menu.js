$(function(){
    $("#moveright").click(function(){
        $("#menu").animate({"left":"-70%"},"slow");

        $(this).find("a").addClass("cantuse");
        $("#moveleft").find("a").removeClass("cantuse");
    });
    $("#moveleft").click(function(){
        $("#menu").animate({"left":"10%"},"slow");
        $(this).find("a").addClass("cantuse");;
        $("#moveright").find("a").removeClass("cantuse");
    });
});