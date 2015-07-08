package de.bht.fpa.mail.s797981.fsnavigation;

import java.util.LinkedList;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797981.fsnavigation.items.TreeFolder;

/**
 * This class provide ability to catch selected on click tree item and wrap
 * it to {@link TreeFolder} and then print existing messages in this element
 * 
 * @author Novichkov Maxim
 *
 */
public class TreeSelectionChangedListener implements ISelectionChangedListener {

	/**
	 * By clicking on the folder icon, selected element will be wrapped in
	 * {@link TreeFolder}. Must not be null.
	 */
	public void selectionChanged(final SelectionChangedEvent event) {
		final IStructuredSelection ts = (IStructuredSelection) event.getSelection();
		final TreeFolder item = (TreeFolder) ts.getFirstElement();
		if (item != null) {
			printMessageInfo(item);
		}
	}

	/**
	 * Print to console existed messages for given directory
	 * 
	 * @param item The directory to check
	 */
	private void printMessageInfo(final TreeFolder item) {
		final LinkedList<Message> messages = item.getMessages();
		if (messages.size() != 0) {
			System.out.println("Selected: " + item.getName());
			System.out.println("Selected directory: " + item.getPath());
			System.out.println("Number of messages: " + messages.size());
			for (Message message : messages) {
				System.out.println(message.toShortString());
			}
		}
	}
}
