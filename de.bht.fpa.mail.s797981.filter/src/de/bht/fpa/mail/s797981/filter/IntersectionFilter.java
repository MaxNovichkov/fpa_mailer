package de.bht.fpa.mail.s797981.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
/**
 * This class act as container for different filters and provide ability to combine this filters
 * and choose only that messages, that "exist" (left after filtering) in all filters.
 *  
 * @author Novichkov Maxim
 */
public class IntersectionFilter implements IFilter {
	/**
	 * Provided array with Filters
	 */
	final private IFilter[] filters;
	/**
	 * Construct new IntersectionFilter with provided Filters
	 * 
	 * @param filters Provided filters
	 */
	public IntersectionFilter(final IFilter... filters) {
		this.filters = filters;
	}

	/**
	 * Returns list of filtered messages passed (contains in) trough all filters.
	 */
	 @Override
	  public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    final Set<Message> result = new HashSet<Message>();
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
