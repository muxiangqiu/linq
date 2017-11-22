package org.malagu.linq.transform.impl;

import org.malagu.linq.transform.ResultTransformer;

/**
 * 查询结果转换器工厂
 * @author Kevin Yang (mailto:kevin.yang@bstek.com)
 * @since 2016年2月21日
 */
final public class Transformers {

	private Transformers() {}
	
	public static final AliasToMapResultTransformer ALIAS_TO_MAP =
			AliasToMapResultTransformer.INSTANCE;


	public static ResultTransformer aliasToBean(Class<?> target) {
		return new AliasToBeanResultTransformer(target);
	}
	
}