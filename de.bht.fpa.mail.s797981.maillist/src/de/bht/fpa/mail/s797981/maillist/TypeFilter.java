package de.bht.fpa.mail.s797981.maillist;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Text;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;

/**
 * This class responsible for checking if typed by user searched text present in
 * {@link Message} in one of it "MessageValues" (importance, read, received,
 * sender, recipient, subject). This class act as a simple filter for all
 * corresponding "MessageValues". Search is not case sensitive (using of toLowerCase for match)
 * for more user friendly search and better experience. 
 * 
 * @author Novichkov Maxim
 *
 */
public class TypeFilter extends ViewerFilter {
	/**
	 * Searched text
	 */
	private final Text searchText;

	/**
	 * Construct new TypeFilter with searched text.
	 * 
	 * @param searchText Provided searched text
	 */
	public TypeFilter(final Text searchText) {
		this.searchText = searchText;
	}

	/**
	 * Compare two strings. Searched text and information from MessageValues
	 * will be converted to lower case for more comfortable and better match.
	 * 
	 * @return Returns true if searched text present in {@link Message} in it
	 * "MessageValues". 
	 * 
	 */
	public boolean select(final Viewer arg0, Object arg1, Object arg2) {
		final String searchString = searchText.getText().toLowerCase();
		final Message message = (Message) arg2;
		final List<String> searchList = new LinkedList<String>();
		searchList.add(message.getSubject());
		searchList.add(message.getText());
		searchList.add(MaillistTableViewerBuilder.SIMPLE_DATE_FORMAT.format(message.getReceived()));
		searchList.add(MaillistTableViewerBuilder.SIMPLE_DATE_FORMAT.format(message.getSent()));
		for (Recipient recipient : message.getRecipients()) {
			searchList.add(recipient.getEmail());
			searchList.add(recipient.getPersonal());
		}
		searchList.add(message.getSender().getEmail());
		searchList.add(message.getSender().getPersonal());

		for (String item : searchList) {
			if (item != null && item.toLowerCase().contains(searchString)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((searchText == null) ? 0 : searchText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeFilter other = (TypeFilter) obj;
		if (searchText == null) {
			if (other.searchText != null)
				return false;
		} else if (!searchText.equals(other.searchText))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TypeFilter [searchText=" + searchText + "]";
	}
}
