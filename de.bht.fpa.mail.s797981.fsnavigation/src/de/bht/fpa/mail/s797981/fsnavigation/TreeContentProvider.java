package de.bht.fpa.mail.s797981.fsnavigation;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This class provide content information for selected element.
 *
 */
public class TreeContentProvider implements ITreeContentProvider {

	/**
	 * By clicking on the tree node item, this method return children of the
	 * selected element.
	 * 
	 * @param Selected element
	 * @return Children of selected element
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IAbstractTree) {
			IAbstractTree p = (IAbstractTree) parentElement;
			return p.getChildren();
		}
		return new Object[0];
	}

	/**
	 * Return true if selected element has children.
	 * 
	 * @param Selected element
	 * @return True if element has children
	 */
	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof IAbstractTree) {
			IAbstractTree p = (IAbstractTree) element;
			return p.getChildren().length > 0;
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
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}
}
