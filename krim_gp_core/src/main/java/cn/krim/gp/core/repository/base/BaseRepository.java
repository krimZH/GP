package cn.krim.gp.core.repository.base;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * define some common methods
 * @author krim
 * @since 2018/01/13
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {
	@Deprecated
	int updateEntity(Map<Object, Object> fieldMap, Class<?> entity) throws Exception;
}
