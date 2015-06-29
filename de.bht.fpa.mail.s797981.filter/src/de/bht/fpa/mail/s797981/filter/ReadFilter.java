package de.bht.fpa.mail.s797981.filter;

import java.util.HashSet;
import java.util.Set;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;


public class ReadFilter implements IFilter{
	
	final private boolean isRead;
	
	public ReadFilter(final boolean isRead) {
		this.isRead = isRead;
	}
	
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    Set<Message> result = new HashSet<Message>();
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
