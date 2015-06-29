package de.bht.fpa.mail.s797981.maillist;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.viewers.TableViewer;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.NullFilter;

public class ExecutionListener implements IExecutionListener {

	private final TableViewer viewer;

	public ExecutionListener(TableViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void notHandled(String commandId, NotHandledException exception) {
	}

	@Override
	public void postExecuteFailure(String commandId,
			ExecutionException exception) {
	}

	@Override
	public void postExecuteSuccess(String commandId, Object returnValue) {
		
		if (!(returnValue instanceof IFilter)) {
			return;
		}
		
		if (returnValue instanceof NullFilter) {
			viewer.resetFilters();
		}
		
		if (returnValue != null && returnValue instanceof IFilter) {
			IFilter filter = (IFilter) returnValue;
			viewer.resetFilters();
			viewer.addFilter(new AdapterFilter(filter));
			viewer.refresh();
		}
	}

	@Override
	public void preExecute(String commandId, ExecutionEvent event) {
	}

}
