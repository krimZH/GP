package cn.krim.gp.front.config;

import java.util.Map;

import com.google.common.collect.Maps;

public class BeanMap {
	public static final Map<String, String> CORE_BEAN_ADDR = Maps.newHashMap();
	public static final Map<String, String> FRONT_BEAN_ADDR = Maps.newHashMap();
	static{
		CORE_BEAN_ADDR.put("User", "cn.krim.gp.core.model.users.User");
		CORE_BEAN_ADDR.put("Common", "cn.krim.gp.core.model.questions.CommonQuestion");
		CORE_BEAN_ADDR.put("Picture", "cn.krim.gp.core.model.questions.PictureQuestion");
		CORE_BEAN_ADDR.put("TestPaper", "cn.krim.gp.core.model.questions.TestPaper");
	}
	static{
		FRONT_BEAN_ADDR.put("User", "cn.krim.gp.front.model.users.User");
		FRONT_BEAN_ADDR.put("Common", "cn.krim.gp.front.model.questions.CommonQuestion");
		FRONT_BEAN_ADDR.put("Picture", "cn.krim.gp.front.model.questions.PictureQuestion");
		FRONT_BEAN_ADDR.put("TestPaper", "cn.krim.gp.front.model.questions.TestPaper");
	}
}
