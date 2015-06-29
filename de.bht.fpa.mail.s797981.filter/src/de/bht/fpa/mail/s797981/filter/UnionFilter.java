package de.bht.fpa.mail.s797981.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class UnionFilter implements IFilter{

	final private IFilter[] filters;

	public UnionFilter(final IFilter... filters) {
		this.filters = filters;
	}

	/**
	 * If message match in one of filters - return true
	 * 
	 * @param Message to check
	 */
	  @Override
	  public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    Set<Message> result = new HashSet<Message>();
	    if (filters.length == 0) {
	      result.addAll(new NullFilter().filter(messagesToFilter));
	    }
	    for (IFilter filter : filters) {
	      result.addAll(filter.filter(messagesToFilter));
	    }
	    return result;
	  }

	@Override
	public String toString() {
		return "UnionFilter [filters=" + Arrays.toString(filters) + "]";
	}

}
