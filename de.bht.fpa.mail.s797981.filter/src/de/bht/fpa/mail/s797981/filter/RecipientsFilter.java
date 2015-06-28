package de.bht.fpa.mail.s797981.filter;

import java.util.List;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;

public class RecipientsFilter extends AStringFilter {

	/**
	 * Construct new RecipientsFilter -- search with LowerCase
	 * 
	 * @param searchedString
	 * @param operator
	 */
	public RecipientsFilter(final String searchedString, final FilterOperator operator) {
		super(searchedString, operator);
	}

	@Override
	boolean match(Message message) {
		for (Recipient recipient : message.getRecipients()) {
			if(StringCompareHelper.matches(recipient.getEmail().toLowerCase(), searchedString, operator)
			|| StringCompareHelper.matches(recipient.getPersonal().toLowerCase(), searchedString, operator)){
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "RecipientFilter [searchedString=" + searchedString
				+ ", operator=" + operator + "]";
	}
}
