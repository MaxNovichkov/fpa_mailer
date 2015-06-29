package de.bht.fpa.mail.s797981.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * Implement null filter pattern. All filtered messages will be added to the result list.
 */
public class NullFilter implements IFilter{

	@Override
	  public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    Set<Message> result = new HashSet<Message>();
	    for (Message message : messagesToFilter) {
	      result.add(message);
	    }
	    return result;
	  }

}
