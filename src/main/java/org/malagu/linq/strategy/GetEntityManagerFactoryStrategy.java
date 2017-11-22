package org.malagu.linq.strategy;

import javax.persistence.EntityManagerFactory;

/**
 * @author Kevin Yang (mailto:muxiangqiu@gmail.com)
 * @since 2017年11月16日
 */
public interface GetEntityManagerFactoryStrategy {
	
	EntityManagerFactory getEntityManagerFactory(Class<?> domainClass);

}
