package de.bht.fpa.mail.s797981.filter.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import de.bht.fpa.mail.s797981.filter.NullFilter;

public class ResetFilterHandler extends AbstractHandler{

	@Override
	  public Object execute(ExecutionEvent event) throws ExecutionException {
	    return new NullFilter();
	  }

}
