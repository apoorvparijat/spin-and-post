var c = 0;
$(document).ready(function(){
    $(".ui-draggable").draggable({cancel:".ui-draggable .msg"});
    jQuery(document).keyup(function(e) {
           if (e.keyCode == 27) {jQuery('.light-box').fadeOut('fast');  }   // esc
           clearInterval(c);
    });
    var i = 0;
    if(sets == null)
        sets = 0;
    htmlStr = "";
    
    do{
        
        htmlStr += '<div class="btn btn'+i+'" onclick="showSet('+i+')">'+i+'</div>';
        i++;
    }while(i < sets)
    $("#navButtons").html(htmlStr);
$(".btn0").addClass("activeSet");
});

function sendAjax(){
    formData = $("#article-form").serialize();
    $(".modal-box").center();
    $(".bar").css("background-color","#060");
    $(".modal-box,.light-box").show();
    $.post("AjaxRequestHandler",formData,function(data){
        $(".modal-box .msg").html(data);
        c = setInterval(function(){$.post("AjaxRequestHandler",{'ajaxAction':'GetProgress','actionToTrack':'PostToNetwork'},function(data){
                jsonData = jQuery.parseJSON(data);
                if(jsonData.status != "completed"){
                    $('.modal-box .msg').html(jsonData.msg);
                    width = (jsonData.progress*295)/100;
                    $(".bar").css("width",width);
                    if(jsonData.status == "error"){
                        $(".bar").css("background-color","#C03");
                        clearInterval(c);
                    }
                }else{
                    
                    $('.modal-box .msg').html(jsonData.msg);
                    width = (jsonData.progress*295)/100;
                    $(".bar").css("width",width);
                    clearInterval(c);
                }
                
            });},700);
        
    });
}


function showSet(id){
    $(".set").hide();
    $(".btn").removeClass("activeSet");
    $(".btn"+id).addClass("activeSet");
    $(".set"+id).show();
}

(function($){
  $.fn.center = function(){
  return this.each(function(){
                   var windowHeight = $(window).height();
                   var windowWidth = $(window).width();
                   var elem = $(this);
                   var elemWidth = $(this).width();
                   var leftVal = (windowWidth - elemWidth)/2 + window.pageXOffset;
                   var elemHeight = $(this).height();
                   var topVal = (windowHeight - elemHeight)/2 +window.pageYOffset;
                   //alert(windowHeight + "/2 -" + elemHeight +"/2 =" + (windowHeight-elemHeight)/2);
                   topVal = topVal < 0 ? 20 : topVal;
                   leftVal = leftVal < 0 ? 20 : leftVal;
                   $(this).css("top",(topVal-200));
                   $(this).css("left",leftVal);
                   });
  }
  })(jQuery);



function showZoomPopup(elem,x,y){
    //set the final width and height. the element will zoom at the center
    var finalWidth = 300;
    var finalHeight = 360;
    var oldTop = parseInt(elem.css("top"),10);
    var oldLeft = parseInt(elem.css("left"),10);
    var currentHeight = elem.height();
    var currentWidth = elem.width();
    var newTop = oldTop - (finalHeight - currentHeight)/2;
    var newLeft = oldLeft - (finalWidth-currentWidth)/2;
    //var radSqr = (newTop - y)^2 + (newLeft-x)^2;
    //var rad = Math.sqrt(radSqr);
    elem.animate({top:newTop,left:newLeft,rotate: 0,boxShadow: '0 0 100px 5px #e9e9e9',width:'300px',height:'360px',fontSize : '13.0px',opacity: '1'} ,600,'easeOutExpo');
    //elem.effect("scale",{percent:200,direction:"both"},1000);
    
}


function sendToWordpress(id){
    $("#popup"+id).hide();
    $("#submitted"+id).hide();
    $("#loader"+id).show();
    tags = $("#tags").val();
    category = $("#category").val();
    title = $('#title'+id).html();
    article = $('#article'+id).html();
    root = $("#root"+id).val();
    if(root[root.length-1] != '/'){
        root += "/";
    }
    username = $("#username"+id).val();
    password = $("#password"+id).val();
    jsonData = {"title" : title,"article" : article,"username":username,"password":password,"root":root,"tags": tags,"category": category};
    $.post("PostToWordpress",jsonData,function(data){
        $("#loader"+id).fadeOut("fast");
        $("#submitted"+id).html(data).fadeIn("slow");
        
    });
}

function fillDetails(id){

    $("#popup"+id).fadeIn("fast");
}

function hide(id){
    $("#popup"+id).fadeOut("fast");
}

function hideAll()
{
    $(".zoom-popup").fadeOut("fast");
}
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};