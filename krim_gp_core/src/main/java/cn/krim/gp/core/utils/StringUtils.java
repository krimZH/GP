package cn.krim.gp.core.utils;

public class StringUtils {
	
	public boolean isEmpty(String str){
		if(str==null||"".equals(str)) return false;
		if(str.trim().length()==0) return false;
		return true;
	}
}
