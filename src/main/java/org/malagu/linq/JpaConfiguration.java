package org.malagu.linq;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.malagu.linq.initiator.JpaUtilAble;
import org.malagu.linq.initiator.JpaUtilInitiator;

/**
 * 模块配置类
 * @author Kevin Yang (mailto:muxiangqiu@gmail.com)
 * @since 2017年11月16日
 */
@Configuration
@ComponentScan
public class JpaConfiguration implements ApplicationContextAware {

	@Autowired
	List<JpaUtilInitiator> jpaUtilInitiators;
	
	@Autowired(required = false)
	List<JpaUtilAble> jpaUtilAbles;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		for (JpaUtilInitiator jpaUtilInitiator : jpaUtilInitiators) {
			jpaUtilInitiator.initialize(applicationContext);
		}
		if (jpaUtilAbles != null) {
			for (JpaUtilAble jpaUtilAble : jpaUtilAbles) {
				jpaUtilAble.afterPropertiesSet(applicationContext);
			}
		}
	}

}
