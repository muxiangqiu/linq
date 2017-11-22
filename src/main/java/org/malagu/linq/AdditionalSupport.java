package org.malagu.linq;

import javax.persistence.Transient;

/**
 * @author Kevin Yang (mailto:muxiangqiu@gmail.com)
 * @since 2017年11月16日
 */
public class AdditionalSupport {

	@Transient
	private Object additional;

	public Object getAdditional() {
		return additional;
	}

	public void setAdditional(Object additional) {
		this.additional = additional;
	}
	
	
}
