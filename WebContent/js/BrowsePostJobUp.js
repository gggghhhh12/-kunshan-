/**
 * 
 */
$(document).ready(function(){
	
			$(".button input").click(function(){
				var jobname=encodeURI(encodeURI($(this).attr("name")));
				var flag=$(this).attr("id");
				if(flag=="up"){
					alert(jobname+"up");
					$.get("servlet/EPModifyStatusServlet?status=0&jobname="+jobname,function(data,statusText){
						var callback = data.callback;
						$("."+jobname).text(callback);
					},"json");
				}
				else{
					alert(jobname+"down");
					$.get("servlet/EPModifyStatusServlet?status=1&jobname="+jobname,function(data,statusText){
						var callback = data.callback;
						$("."+jobname).text(callback);
					},"json");
				}
			});
			
			$(".button input").click(function(){
 			var jobname=$(this).attr("name");
		alert(jobname);
 			$.get("servlet/EPModifyStatusServlet",{"status":1,"jobname":jobname},function(data,statusText){
					var callback = data.callback;
					$(".callback").text(callback);
				},"json");
 			});
		});