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
			
			/*$("form").submit(function(e){
				if(document.getElementById("birthday").value==" "||document.getElementById("birthday").value==null)
    			{
    				alert("日期不能为空");
    				return false;    				
    			}
				return true
			});*/	
		});

//function valid(){
//    //获取name为123对应的input输入框中的值
//    var val = $("input[name=123]").val();
//    if(val == '' || $.trim(val) == ''){
//        //如果val为空或者空格，将错误消息显示在对应span
//        $("#errorMsg").html('不能为空');
//        //让span显示出来
//        $("#errorMsg").show();
//    }
//}
		function myfunction()
		{
			var birthday=document.getElementById("birthday").value;
			if(birthday=='')
			{
				alert("时间不能为空");
				return false;    				
			}
			return true;
		}