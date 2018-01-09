package cn.krim.gp.front.users;

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
}
