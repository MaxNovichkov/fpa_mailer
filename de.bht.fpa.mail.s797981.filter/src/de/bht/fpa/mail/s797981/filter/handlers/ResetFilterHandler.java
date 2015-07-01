package de.bht.fpa.mail.s797981.filter.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import de.bht.fpa.mail.s000000.common.filter.NullFilter;

/**
 * This class will be used for reset settled filter by returning NullFilter.
 * Null Filter - Messages will be added to the list without any filtering. 
 * 
 * @author Novichkov Maxim
 */
public class ResetFilterHandler extends AbstractHandler{

	@Override
	  public Object execute(ExecutionEvent event) throws ExecutionException {
	    return new NullFilter();
	  }

}
