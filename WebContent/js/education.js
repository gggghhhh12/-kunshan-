/**
 * 
 */
function myfunction()
		{
			var entertime=document.getElementById("entertime").value;
			var  gradtime=document.getElementById("gradtime").value;
			var edudutyvalue=document.getElementById("eduduty").value;
			var eduawardvalue=document.getElementById("eduaward").value;
			var eduprictisevalue=document.getElementById("eduprictise").value;
			if(entertime!='' && gradtime!='')
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
				return true;
			}
			else{
				alert("时间不能为空");
				return false;
			}
		}