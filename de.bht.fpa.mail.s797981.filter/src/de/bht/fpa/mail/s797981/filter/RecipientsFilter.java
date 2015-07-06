package de.bht.fpa.mail.s797981.filter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;
/**
 * This class filter messages on provided {@link Recipient} and {@link FilterOperator} value. 
 * 
 * @author Novichkov Maxim
 */
public class RecipientsFilter extends AStringFilter {

	/**
	 * Construct new RecipientsFilter -- search with LowerCase
	 * 
	 * @param searchedString Provided value (as string)
	 * @param operator Provided searched FilterOperator
	 */
	public RecipientsFilter(final String searchedString, final FilterOperator operator) {
		super(searchedString, operator);
	}

	@Override
	  public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    Set<Message> result = new HashSet<Message>();
	    for (Message message : messagesToFilter) {
	      List<Recipient> recipients = message.getRecipients();
	      for (Recipient recipient : recipients) {
	        if (StringCompareHelper.matches(recipient.getEmail().toLowerCase(), searchedString, operator)
	            || StringCompareHelper.matches(recipient.getPersonal().toLowerCase(), searchedString, operator)) {
	          result.add(message);
	          continue;
	        }
	      }
	    }
	    return result;
	  }

	@Override
	public String toString() {
		return "RecipientFilter [searchedString=" + searchedString
				+ ", operator=" + operator + "]";
	}
}
