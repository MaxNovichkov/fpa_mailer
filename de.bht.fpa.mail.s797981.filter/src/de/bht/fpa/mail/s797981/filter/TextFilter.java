package de.bht.fpa.mail.s797981.filter;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class TextFilter extends AStringFilter{
	
	public TextFilter(String searchedString, FilterOperator operator) {
		super(searchedString, operator);
	}

	boolean match(Message message) {
		return StringCompareHelper.matches(message.getText().toLowerCase(), searchedString, operator);
	}

}
