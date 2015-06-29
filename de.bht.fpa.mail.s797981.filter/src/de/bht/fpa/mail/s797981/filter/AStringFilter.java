package de.bht.fpa.mail.s797981.filter;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * Abstract class for string based search.
 * 
 * @author Max
 *
 */
public abstract class AStringFilter implements IFilter{
	
	final protected String searchedString;
	final protected FilterOperator operator;

	/**
	 * Construct new AStringFilter -- search with LowerCase
	 * 
	 * @param searchedString
	 * @param operator
	 */
	public AStringFilter(final String searchedString, final FilterOperator operator) {
		this.searchedString = searchedString.toLowerCase();
		this.operator = operator;
	}
}
