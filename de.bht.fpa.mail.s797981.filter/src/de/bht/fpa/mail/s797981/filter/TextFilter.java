package de.bht.fpa.mail.s797981.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class TextFilter extends AStringFilter{
	
	public TextFilter(String searchedString, FilterOperator operator) {
		super(searchedString, operator);
	}

	@Override
	  public Set<Message> filter(Iterable<Message> messagesToFilter) {
	    Set<Message> result = new HashSet<Message>();
	    for (Message message : messagesToFilter) {
	      if (StringCompareHelper.matches(message.getText().toLowerCase(), searchedString, operator)) {
	        result.add(message);
	      }
	    }
	    return result;
	  }

}
