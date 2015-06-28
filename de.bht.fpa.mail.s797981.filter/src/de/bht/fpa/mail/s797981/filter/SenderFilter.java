package de.bht.fpa.mail.s797981.filter;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Sender;

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
	boolean match(Message message) {
		final Sender sender = message.getSender();
		return StringCompareHelper.matches(sender.getEmail().toLowerCase(), searchedString, operator)
		          || StringCompareHelper.matches(sender.getPersonal().toLowerCase(), searchedString, operator);
	}
	
	@Override
	public String toString() {
		return "SenderFilter [searchedString=" + searchedString + ", operator="
				+ operator + "]";
	}

}
