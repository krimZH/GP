package cn.krim.gp.core.functional;

import java.io.Serializable;

@FunctionalInterface
public interface GetId {
	Serializable getEntityId();
}
