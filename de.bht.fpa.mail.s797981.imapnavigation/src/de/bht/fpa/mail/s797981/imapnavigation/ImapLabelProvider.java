package de.bht.fpa.mail.s797981.imapnavigation;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s797981.imapnavigation.items.AImapItem;

/**
 * This class provide name and image of selected tree item (ITreeDIrectory) element. In this case
 * provided element will be wrapped in ITreeElement.
 *
 */
public class ImapLabelProvider extends LabelProvider {
	/**
	 * Return name of provided element.
	 * 
	 * @param Provided element
	 * @return Name of provide element
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof AImapItem) {
			AImapItem p = (AImapItem) element;
			return p.getName();
		}
		return super.getText(element);
	}

	/**
	 * Return image (icon) of provided element.
	 * 
	 * @param Provided element
	 * @return Image of provided element
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof AImapItem) {
			AImapItem p = (AImapItem) element;
			return p.getImage();
		}
		return super.getImage(element);
	}

}
