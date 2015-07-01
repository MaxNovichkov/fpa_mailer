package de.bht.fpa.mail.s797981.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Sender;
/**
 * This class filter messages on provided {@link Sender} and {@link FilterOperator} value. 
 * 
 * @author Novichkov Maxim
 */
public class SenderFilter extends AStringFilter{

	/**
	 * Construct new SenderFilter -- search with LowerCase
	 * @param searchedString
	 * @param operator
	 */
	public SenderFilter(final String searchedString, final FilterOperator operator){
		super(searchedString, operator);
	}

	@Override
	  public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    Set<Message> result = new HashSet<Message>();
	    for (Message message : messagesToFilter) {
	      Sender sender = message.getSender();
	      if (StringCompareHelper.matches(sender.getEmail().toLowerCase(), searchedString, operator)
	          || StringCompareHelper.matches(sender.getPersonal().toLowerCase(), searchedString, operator)) {
	        result.add(message);
	      }
	    }
	    return result;
	  }
	
	@Override
	public String toString() {
		return "SenderFilter [searchedString=" + searchedString + ", operator="
				+ operator + "]";
	}

}
