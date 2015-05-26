package de.bht.fpa.mail.s797981.fsnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Invoker
 * 
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class DirectoryHandler extends AbstractHandler {
	/**
	 * Command 
	 */
	private ICommand command;
	
	/**
	 * The constructor.
	 */
	public DirectoryHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context. Selected by user path will be wrapped in the command object.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		DirectoryDialog dialog = new DirectoryDialog(window.getShell());
		final String filename = dialog.open();
		if (filename == null) {
		      return null;
		    }
		command = new SetDirectoryCommand(filename);
		command.execute();
		return null;
	}
}
