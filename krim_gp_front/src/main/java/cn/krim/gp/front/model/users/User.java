package cn.krim.gp.front.model.users;

import lombok.Data;


@Data
public class User {
	
	private String userId;
	
	private String realName;
	
	private String password;
	
	private Integer status;
	
	private Integer scoreId;
	
	private String className;
	
	private String mail;
	
	private Long createTime;
	
	private Long updateTime;

}
