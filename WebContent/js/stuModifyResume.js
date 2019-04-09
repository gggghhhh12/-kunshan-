/**
 * 
 */
	$(document).ready(function(){
			$(".basicinfo dl dd .acity").click(function(){
				$(".city").show();
			});

			$(".city dl dd span").click(function(){
				var liveaddr = $(this).text();
				$(".basicinfo #liveaddr").val(liveaddr);
				$(".city").hide();
			});

			$(".city .cancel").click(function(){
				$(".city").hide();
			});

			$(".basicinfo dl dd .acity1").click(function(){
				$(".city1").show();
			});

			$(".city1 dl dd span").click(function(){
				var liveaddr = $(this).text();
				$(".basicinfo #residence").val(liveaddr);
				$(".city1").hide();
			});

			$(".city1 .cancel").click(function(){
				$(".city1").hide();
			});
			
			$(".jobintention dl dd .acity2").click(function(){
				$(".city2").show();
			});

			$(".city2 dl dd span").click(function(){
				var place = $(this).text();
				$(".jobintention #place").val(place);
				$(".city2").hide();
			});

			$(".city2 .cancel").click(function(){
				$(".city2").hide();
			});
			
			$(".jobintention dl dd .atrade1").click(function(){
				$(".trade1").show();
			});
			
			$(".trade1 dl dd span").click(function(){
				var trade = $(this).text();
				$(".jobintention #trade").val(trade);
				$(".trade1").hide();
			});
			
			$(".trade1 .cancel").click(function(){
				$(".trade1").hide();
			});
		});
	function myfunction()
	{
		var entertime1=document.getElementById("entertime").value;
		var  gradtime1=document.getElementById("gradtime").value;
		var edudutyvalue=document.getElementById("eduduty").value;
		var eduawardvalue=document.getElementById("eduaward").value;
		var eduprictisevalue=document.getElementById("eduprictise").value;
		var evaluationvalue=document.getElementById("evaluation").value;
		var birthday=document.getElementById("birthday").value;
		if(entertime1!='' && gradtime!=''&&birthday!='')
		{
			
			if( edudutyvalue.length>500){
				alert("校内职务字数超过最大限制字数");
				return false;
			}
			else if(eduawardvalue.length>500){
				alert("在校奖励超过最大限制字数");
				return false;
			}
			else if(eduprictisevalue.length>500){
				alert("社会实践超过最大限制字数");
				return false;
			}
			else if(evaluationvalue.length>500){
				alert("个人评价超过最大限制字数");
				return false;
			}
			else{
				var r=confirm("确定保存？");
				if(r==true){
					alert("保存成功");
					return true;
				}
				else{
					alert("取消保存");
					return false;
				}
				
			}
		}
		else{
			alert("时间不能为空");
			return false;
		}
	}