package de.bht.fpa.mail.s797981.filter;

import java.util.Arrays;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class IntersectionFilter extends AFilter {

	final private AFilter[] filters;

	public IntersectionFilter(final AFilter... filters) {
		this.filters = filters;
	}

	/**
	 * Returns true only if message passed (contains in) trough all filters
	 */
	boolean match(Message message) {
		boolean contain = true;
		for (AFilter filter : filters) {
			contain = contain && filter.match(message);
		}
		return contain;
	}

	@Override
	public String toString() {
		return "IntersectionFilter [filters=" + Arrays.toString(filters) + "]";
	}
}
