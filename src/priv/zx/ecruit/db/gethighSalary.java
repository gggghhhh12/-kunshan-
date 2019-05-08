package priv.zx.ecruit.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class gethighSalary {

	public int getHighJobSalary( String jobsalary2){
		String reg = "[\u4e00-\u9fa5]";
		int highSalary;
    	Pattern pat = Pattern.compile(reg);  
    	Matcher mat=pat.matcher(jobsalary2); 
    	String result = mat.replaceAll("");
    	System.out.println(result);
    	if(result.contains("-")){
    		String a[]=result.split("-");
    		System.out.println(a[0]);
    		highSalary=Integer.valueOf(a[1]);
    		
    	}else{
    		
    		highSalary=Integer.valueOf(result);
    	}
    	return highSalary;
		
		
		
	}
}
