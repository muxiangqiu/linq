package org.malagu.linq;

import java.util.ArrayList;
import java.util.List;

/**
 * 联合条件
 * @author Kevin Yang (mailto:muxiangqiu@gmail.com)
 * @since 2017年11月16日
 */
public class Junction {
	private List<Object> predicates = new ArrayList<Object>();
	public void add(Object predicate) {
		this.predicates.add(predicate);
	}
	
	public List<Object> getPredicates() {
		return this.predicates;
	}
}
