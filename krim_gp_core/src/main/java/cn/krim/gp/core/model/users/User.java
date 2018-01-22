package cn.krim.gp.core.model.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import cn.krim.gp.core.model.MyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="user")
@Data
@EqualsAndHashCode(callSuper=false)
@DynamicUpdate
public class User extends MyEntity{
	@Id @Column(name="user_id")
	private String userId;
	@Column(name="password")
	private String password;
	@Column(name="real_name")
	private String realName;
	@Column(name="status")
	private Integer status;
	@Column(name="score_id")
	private Integer scoreId;
	@Column(name="class_name")
	private String className;
	@Column(name="mail")
	private String mail;
	@Column(name="create_time")
	private Long createTime;
	@Column(name="update_time")
	private Long updateTime;
	@Override
	public  String getEntityId() {
		
		return getUserId();
	}
	@Override
	public void setEntityId(Serializable id) {
		setUserId((String) id);
	}
	
}
