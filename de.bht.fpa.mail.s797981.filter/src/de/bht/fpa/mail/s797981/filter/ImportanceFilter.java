package de.bht.fpa.mail.s797981.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class ImportanceFilter implements IFilter{
	
	final private Importance importance;
	
	public ImportanceFilter(final Importance importance){
		this.importance = importance;
	}
	
	@Override
	  public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    Set<Message> result = new HashSet<Message>();
	    for (Message message : messagesToFilter) {
	      if (message.getImportance().equals(importance)) {
	        result.add(message);
	      }
	    }
	    return result;
	  }
}
