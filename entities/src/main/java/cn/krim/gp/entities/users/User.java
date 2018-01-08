package cn.krim.gp.entities.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	@Id
	private String userId;
	@Column
	private String password;
	@Column
	private Integer status;
	@Column
	private Integer scoreId;
	@Column
	private String className;
	@Column
	private Long createTime;
	@Column
	private Long updateTime;
}
