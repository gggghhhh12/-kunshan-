/**
 * 
 */
		$(document).ready(function(){
			$(".jobintention dl dd .acity").click(function(){
				$(".city").show();
			});

			$(".city dl dd span").click(function(){
				var liveaddr = $(this).text();
				$(".jobintention #place").val(liveaddr);
				$(".city").hide();
			});

			$(".city .cancel").click(function(){
				$(".city").hide();
			});
			
			$(".jobintention dl dd .atrade").click(function(){
				$(".trade").show();
			});
			
			$(".trade dl dd span").click(function(){
				var trade = $(this).text();
				$(".jobintention #trade").val(trade);
				$(".trade").hide();
			});
			
			$(".trade .cancel").click(function(){
				$(".trade").hide();
			});
		});
		function myfunction()
		{
			var evaluationvalue=document.getElementById("evaluation").value;
			if(evaluationvalue.length>=500)
			{
				alert("个人评价字数超过限制");
				return false;
			}
			else
			{
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