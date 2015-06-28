package de.bht.fpa.mail.s797981.filter;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class SubjectFilter extends AStringFilter{
	
	/**
	 * Construct new SubjectFilter -- search with LowerCase
	 * 
	 * @param searchedString
	 * @param operator
	 */
	public SubjectFilter(final String searchedString, final FilterOperator operator) {
		super(searchedString, operator);
	}

	@Override
	boolean match(Message message) {
		return StringCompareHelper.matches(message.getSubject().toLowerCase(), searchedString, operator);
	}

}
