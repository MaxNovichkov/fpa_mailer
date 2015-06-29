package de.bht.fpa.mail.s797981.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class IntersectionFilter implements IFilter {

	final private IFilter[] filters;

	public IntersectionFilter(final IFilter... filters) {
		this.filters = filters;
	}

	/**
	 * Returns true only if message passed (contains in) trough all filters
	 */
//	boolean match(Message message) {
//		boolean contain = true;
//		for (AFilter filter : filters) {
//			contain = contain && filter.match(message);
//		}
//		return contain;
//	}
	
	 @Override
	  public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    Set<Message> result = new HashSet<Message>();
	    for (Message message : messagesToFilter) {
	      result.add(message);
	    }
	    for (IFilter filter : filters) {
	      result.retainAll(filter.filter(result));
	    }
	    return result;
	  }

	@Override
	public String toString() {
		return "IntersectionFilter [filters=" + Arrays.toString(filters) + "]";
	}
}
