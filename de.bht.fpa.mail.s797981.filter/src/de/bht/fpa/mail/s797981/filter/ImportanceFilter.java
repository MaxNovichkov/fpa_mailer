package de.bht.fpa.mail.s797981.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class filter messages on provided {@link Importance} value.
 * 
 * @author Novichkov Maxim
 */
public class ImportanceFilter implements IFilter{
	/**
	 * Actual importance
	 */
	final private Importance importance;
	/**
	 * Construct new ImportanceFilter
	 * 
	 * @param importance Provided importance
	 */
	public ImportanceFilter(final Importance importance){
		this.importance = importance;
	}
	
	@Override
	  public Set<Message> filter(final Iterable<Message> messagesToFilter) {
	    final Set<Message> result = new HashSet<Message>();
	    for (Message message : messagesToFilter) {
	      if (message.getImportance().equals(importance)) {
	        result.add(message);
	      }
	    }
	    return result;
	  }

	@Override
	public String toString() {
		return "ImportanceFilter [importance=" + importance + "]";
	}
}
