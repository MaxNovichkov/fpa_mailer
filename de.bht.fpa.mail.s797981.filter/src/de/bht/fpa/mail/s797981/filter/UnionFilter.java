package de.bht.fpa.mail.s797981.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.NullFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
/**
 * This class act as container for different filters and provide ability to combine this filters
 * and choose only that messages, that "exist" (left after filtering) in one of provided filters.
 *  
 * @author Novichkov Maxim
 */
public class UnionFilter implements IFilter{
	/**
	 * Provided Filters
	 */
	final private IFilter[] filters;
	/**
	 * Construct new UnionFilter with provided filters
	 * 
	 * @param filters Provided filters
	 */
	public UnionFilter(final IFilter... filters) {
		this.filters = filters;
	}

	/**
	 * Returns list with passed messages.
	 * 
	 * @param messagesToFilter Message to check
	 */
	  @Override
	  public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    final Set<Message> result = new HashSet<Message>();
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
