		var salary = "不限";
		var nature = "不限";
		var diploma = "不限";
		var scale = "不限";
		$(document).ready(function(){
			var msg=$("#msg").val();
			if(msg!='null'&&msg!="undefined"&&msg!=""){
				alert(msg);
			}
			var userlevel=$("body input[name='userlevel']").val();
			$("#salary dd").click(function(){
				$(".resultcontext").empty();
				$(this).css("background-color","#0066CC");
				$(this).siblings().css("background-color","#FFFFFF");
				salary = $(this).text();
				$.get("servlet/stuSelectServlet2",{"salary":salary,"nature":nature,"diploma":diploma,"scale":scale},
					function(data,statusText){
						var result = data.result;
						var strResult = "";
						
						for(var i=0;i<result.length;i++){
							var epname=result[i].epName;
							if(userlevel=="0"){
								epname="xxx公司";
							}
							strResult += "<td class=\"t1\"><a href=\"servlet/StuJobDetail?EPusername=" + 
													result[i].epUsername + "&jobname="+result[i].jobName+"\" target=\"_blank\">" + result[i].jobName + "</a></td>" +
													"<td  class=\"t2\">" + epname + "</td >" +
													"<td  class=\"t3\">" + result[i].epAddr + "</td >" +
													"<td  class=\"t4\">" + result[i].salary + "</td >" + "<hr>";
						}
						$(".resultcontext").html(strResult);
					},"json");
				
			});
			
			$("#nature dd").click(function(){
				$(".resultcontext").empty();
				$(this).css("background-color","#0066CC");
				$(this).siblings().css("background-color","#FFFFFF");
				nature = $(this).text();
				$.get("servlet/stuSelectServlet2",{"salary":salary,"nature":nature,"diploma":diploma,"scale":scale},
					function(data,statusText){
						var result = data.result;
						var strResult = "";
						
						for(var i=0;i<result.length;i++){
							var epname=result[i].epName;
							if(userlevel=="0"){
								epname="xxx公司";
							}
							strResult += "<td  class=\"t1\"><a href=\"servlet/StuJobDetail?EPusername=" + 
													result[i].epUsername + "&jobname="+result[i].jobName+"\" target=\"_blank\">" + result[i].jobName + "</a></td >" +
													"<td class=\"t2\">" + epname + "</td>" +
													"<td class=\"t3\">" + result[i].epAddr + "</td>" +
													"<td class=\"t4\">" + result[i].salary + "</td>" + "<hr>";
						}
						$(".resultcontext").html(strResult);
					},"json");
			});
			
			$("#diploma dd").click(function(){
				$(".resultcontext").empty();
				$(this).css("background-color","#0066CC");
				$(this).siblings().css("background-color","#FFFFFF");
				diploma = $(this).text();
				$.get("servlet/stuSelectServlet2",{"salary":salary,"nature":nature,"diploma":diploma,"scale":scale},
					function(data,statusText){
						var result = data.result;
						var strResult = "";
						
						for(var i=0;i<result.length;i++){
							var epname=result[i].epName;
							if(userlevel=="0"){
								epname="xxx公司";
							}
							strResult += "<td class=\"t1\"><a href=\"servlet/StuJobDetail?EPusername=" + 
													result[i].epUsername + "&jobname="+result[i].jobName+"\" target=\"_blank\">" + result[i].jobName + "</a></td>" +
													"<td class=\"t2\">" + epname + "</td>" +
													"<td class=\"t3\">" + result[i].epAddr + "</td>" +
													"<td class=\"t4\">" + result[i].salary + "</td>" + "<hr>";
						}
						$(".resultcontext").html(strResult);
					},"json");
			});
			
			$("#scale dd").click(function(){
				$(".resultcontext").empty();
				$(this).css("background-color","#0066CC");
				$(this).siblings().css("background-color","#FFFFFF");
				scale = $(this).text();
				$.get("servlet/stuSelectServlet2",{"salary":salary,"nature":nature,"diploma":diploma,"scale":scale},
					function(data,statusText){
						var result = data.result;
						var strResult = "";
						
						for(var i=0;i<result.length;i++){
							var epname=result[i].epName;
							if(userlevel=="0"){
								epname="xxx公司";
							}
							strResult += "<td class=\"t1\"><a href=\"servlet/StuJobDetail?EPusername=" + 
													result[i].epUsername + "&jobname="+result[i].jobName+"\" target=\"_blank\">" + result[i].jobName + "</a></td>" +
													"<td class=\"t2\">" + epname + "</td>" +
													"<td class=\"t3\">" + result[i].epAddr + "</td>" +
													"<td class=\"t4\">" + result[i].salary + "</td>" + "<hr>";
						}
						$(".resultcontext").html(strResult);
					},"json");
			});
			
			$(".select .aplace").click(function(){
				$(".city").show();
			});

			$(".city dl dd td").click(function(){
				var city = $(this).text();
				$(".select #address").val(city);
				$(".city").hide();
			});
			
			$(".city .cancel").click(function(){
				$(".city").hide();
			});
			
			$(".select .ajobkind").click(function(){
				$(".jobkind").show();
			});

			$(".jobkind dl dd td").click(function(){
				var jobkind = $(this).text();
				$(".select #jobkind").val(jobkind);
				$(".jobkind").hide();
			});

			$(".jobkind .cancel").click(function(){
				$(".jobkind").hide();
			});
			
			$(".select .atrade").click(function(){
				$(".trade").show();
			});

			$(".trade dl dd td").click(function(){
				var trade = $(this).text();
				$(".select #trade").val(trade);
				$(".trade").hide();
			});

			$(".trade .cancel").click(function(){
				$(".trade").hide();
			});
			goPage(1,3);
		});