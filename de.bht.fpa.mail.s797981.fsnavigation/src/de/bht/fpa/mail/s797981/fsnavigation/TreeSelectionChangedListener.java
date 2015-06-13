package de.bht.fpa.mail.s797981.fsnavigation;

import java.util.LinkedList;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class provide ability to catch selected on click tree item and wrap
 * it to {@link TreeDirectory} and then print existing messages in this element
 * 
 * @author Novichkov Maxim
 *
 */
public class TreeSelectionChangedListener implements ISelectionChangedListener {

	/**
	 * By clicking on the folder icon, selected element will be wrapped in
	 * {@link TreeDirectory}. Must not be null.
	 */
	public void selectionChanged(final SelectionChangedEvent event) {
		final IStructuredSelection ts = (IStructuredSelection) event.getSelection();
		final TreeDirectory directory = (TreeDirectory) ts.getFirstElement();
		if (directory != null) {
			printMessageInfo(directory);
		}
	}

	/**
	 * Print to console existed messages for given directory
	 * 
	 * @param directory The directory to check
	 */
	private void printMessageInfo(final TreeDirectory directory) {
		final LinkedList<Message> messages = directory.getMessages();
		if (messages.size() != 0) {
			System.out.println("Selected: " + directory.getName());
			System.out.println("Selected directory: " + directory.file.getAbsolutePath());
			System.out.println("Number of messages: " + messages.size());
			for (Message message : messages) {
				System.out.println(message.toShortString());
			}
		}
	}
}
