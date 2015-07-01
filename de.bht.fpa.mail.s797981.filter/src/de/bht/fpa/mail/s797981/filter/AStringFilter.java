package de.bht.fpa.mail.s797981.filter;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;

/**
 * Abstract class for string based search, search with LowerCase, for case insensitive search.
 * 
 * @author Novichkov Maxm
 *
 */
public abstract class AStringFilter implements IFilter{
	/**
	 * Searched string
	 */
	final protected String searchedString;
	/**
	 * Searched filter operator
	 */
	final protected FilterOperator operator;

	/**
	 * Construct new AStringFilter, search with LowerCase, for case insensitive search.
	 * 
	 * @param searchedString String for search
	 * @param operator Filter operator
	 */
	public AStringFilter(final String searchedString, final FilterOperator operator) {
		this.searchedString = searchedString.toLowerCase();
		this.operator = operator;
	}
}
