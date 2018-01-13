package cn.krim.gp.core.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.krim.gp.core.model.MyEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="user")
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends MyEntity{
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
	@Override
	public  String getEntityId() {
		
		return getUserId();
	}
}
