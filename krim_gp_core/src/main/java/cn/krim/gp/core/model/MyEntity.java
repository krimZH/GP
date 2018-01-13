package cn.krim.gp.core.model;

import java.io.Serializable;

import cn.krim.gp.core.functional.GetId;


public  class MyEntity implements GetId {
	
	public MyEntity(){
		
	}

	@Override
	public Serializable getEntityId() {
		return null;
	}
}
