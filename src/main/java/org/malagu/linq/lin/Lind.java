package org.malagu.linq.lin;

import javax.persistence.criteria.CriteriaDelete;

/**
 * 语言集成删除
 * @author Kevin Yang (mailto:muxiangqiu@gmail.com)
 * @since 2017年11月16日
 */
public interface Lind extends Lin<Lind, CriteriaDelete<?>>{

	/**
	 * 批量删除
	 * @return 删除条数
	 */
	int delete();

}
