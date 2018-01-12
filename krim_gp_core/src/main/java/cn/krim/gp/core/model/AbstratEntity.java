package cn.krim.gp.core.model;

import java.io.Serializable;

import cn.krim.gp.core.functional.GetId;


public  class AbstratEntity implements GetId {
	
	public AbstratEntity(){
		
	}

	@Override
	public Serializable getEntityId() {
		return null;
	}
}
