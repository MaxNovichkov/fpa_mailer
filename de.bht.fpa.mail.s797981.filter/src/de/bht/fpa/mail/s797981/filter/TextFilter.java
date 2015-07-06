package de.bht.fpa.mail.s797981.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
/**
 * This class filter messages on provided message text and {@link FilterOperator} value. 
 * 
 * @author Novichkov Maxim
 */
public class TextFilter extends AStringFilter {
	/**
	 * Construct new TextFilter -- search with LowerCase
	 * 
	 * @param searchedString Provided value (as string)
	 * @param operator Provided searched FilterOperator
	 */
	public TextFilter(String searchedString, FilterOperator operator) {
		super(searchedString, operator);
	}

	@Override
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
		final Set<Message> result = new HashSet<Message>();
		for (Message message : messagesToFilter) {
			if (StringCompareHelper.matches(message.getText().toLowerCase(), searchedString, operator)) {
				result.add(message);
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "TextFilter [searchedString=" + searchedString + ", operator="
				+ operator + "]";
	}
}
