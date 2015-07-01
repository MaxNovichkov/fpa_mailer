package de.bht.fpa.mail.s797981.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class filter messages on provided isRead value. We can filter red and not red messages.
 * 
 * @author Novichkov Maxim
 */
public class ReadFilter implements IFilter{
	/**
	 * Actual read value
	 */
	final private boolean isRead;
	/**
	 * Construct new ReadFilter 
	 * 
	 * @param isRead Provided value
	 */
	public ReadFilter(final boolean isRead) {
		this.isRead = isRead;
	}
	
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    final Set<Message> result = new HashSet<Message>();
	    for (Message message : messagesToFilter) {
	      if (message.isRead() == isRead) {
	        result.add(message);
	      }
	    }
	    return result;
	  }
	

	@Override
	public String toString() {
		return "ReadFilter [isRead=" + isRead + "]";
	}
}
