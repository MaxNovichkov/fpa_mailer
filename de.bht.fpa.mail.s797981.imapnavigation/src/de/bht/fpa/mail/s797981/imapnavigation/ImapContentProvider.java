package de.bht.fpa.mail.s797981.imapnavigation;

import java.util.LinkedList;
import java.util.List;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import de.bht.fpa.mail.s000000.common.mail.model.IMessageTreeItem;

/**
 * This class provide content information for selected element.
 *
 */
public class ImapContentProvider implements ITreeContentProvider {

	/**
	 * By clicking on the tree node (ITreeDirectory) item, this method return children of the
	 * selected element.
	 * 
	 * @param Selected element
	 * @return Children of selected element
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IMessageTreeItem) {
			final IMessageTreeItem p = (IMessageTreeItem) parentElement;
			return p.getChildren().toArray();
		}
		final List<IMessageTreeItem> list = new LinkedList<IMessageTreeItem>();
		return list.toArray();
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/**
	 * Return true if selected element has children.
	 * 
	 * @param Selected element
	 * @return True if element has children
	 */
	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof AImap) {
			AImap p = (AImap) element;
			return p.getChildren().size() > 0;
		}
		return false;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
	}

	

	@Override
	public Object getParent(Object element) {
		return null;
	}
}
