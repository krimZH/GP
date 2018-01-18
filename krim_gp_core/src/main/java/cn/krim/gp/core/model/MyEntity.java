package cn.krim.gp.core.model;

import java.io.Serializable;

import cn.krim.gp.core.EntityBaseMthod.SimpleInterface;


public  class MyEntity implements SimpleInterface {
	
	public MyEntity(){
		
	}

	@Override
	public Serializable getEntityId() {
		return null;
	}

	@Override
	public void setEntityId(Serializable id) {
		
	}
}
