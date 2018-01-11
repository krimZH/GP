package cn.krim.gp.front.users;

import java.time.Clock;

import lombok.Data;


@Data
public class User {

	private String userId;

	private String password;

	private Integer status;

	private Integer scoreId;

	private String className;

	private Long createTime;

	private Long updateTime;

	
	public String getIdentify() {
		// User identify with userId , password and hashCode
		return this.userId+this.password+this.hashCode();
	}
	
	public User(){
		this.createTime=Clock.systemUTC().millis();
	}
}
