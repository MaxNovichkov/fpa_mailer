package de.bht.fpa.mail.s797981.filter;

import java.util.Arrays;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class UnionFilter extends AFilter{

	final private AFilter[] filters;

	public UnionFilter(final AFilter... filters) {
		this.filters = filters;
	}

	/**
	 * If message match in one of filters - return true
	 * 
	 * @param Message to check
	 */
	boolean match(Message message) {
		if (filters.length == 0) {
		      return true;
		    }
		
		for (AFilter filter : filters) {
			if (filter.match(message)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "UnionFilter [filters=" + Arrays.toString(filters) + "]";
	}

}
