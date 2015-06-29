package de.bht.fpa.mail.s797981.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public abstract class TestFilter implements IFilter {
	
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
		return null;
	}
	
//	public Set<Message> filter(Iterable<Message> messagesToFilter) {
//		Set<Message> result = new HashSet<Message>();
//		for (Message message : messagesToFilter) {
//			if (match(message)) {
//				result.add(message);
//			}
//		}
//		return result;
//	}
//
//	/**
//	 * Check if message contain searched parameter.
//	 * 
//	 * @param message Message to check
//	 * @return Returns true if match
//	 */
//	abstract boolean match(Message message);

}
