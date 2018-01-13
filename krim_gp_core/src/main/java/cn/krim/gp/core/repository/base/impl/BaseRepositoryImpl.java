package cn.krim.gp.core.repository.base.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import cn.krim.gp.core.repository.base.BaseRepository;

public class BaseRepositoryImpl<T,ID extends Serializable>  extends SimpleJpaRepository<T, Serializable>  implements BaseRepository<T, Serializable> {
	
	/**
	 * 持久化上下文
	 */
	@SuppressWarnings("unused")
	private EntityManager entityManager;
	
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager=em;
	}



}
