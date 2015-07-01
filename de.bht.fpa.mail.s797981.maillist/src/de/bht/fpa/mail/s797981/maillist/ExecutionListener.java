package de.bht.fpa.mail.s797981.maillist;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.NullFilter;

/**
 * Provide ability to set configured by user filter and filter messages in
 * corresponding view (table).
 * 
 * @author Novichkov Maxim
 */
public class ExecutionListener implements IExecutionListener {
	/**
	 * Actual TableViewer
	 */
	private final TableViewer viewer;
	/**
	 * Construct new ExecutionListener
	 * @param viewer Provided TableViewer
	 */
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

	/**
	 * Set provided configured filter for TableViewer
	 * 
	 * @param returnValue Provided filter
	 */
	@Override
	public void postExecuteSuccess(String commandId, Object returnValue) {
		if (!(returnValue instanceof IFilter)) {
			return;
		}
		IFilter filter = (IFilter) returnValue;
		for (ViewerFilter viewerFilter : viewer.getFilters()) {
			if (viewerFilter instanceof AdapterFilter) {
				((AdapterFilter) viewerFilter).setFilter(filter);
			}
		}
		viewer.refresh();
	}

	@Override
	public void preExecute(String commandId, ExecutionEvent event) {
	}

}
