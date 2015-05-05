package de.bht.fpa.mail.s797981.fsnavigation;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TreeContentProvider implements ITreeContentProvider {

	

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IAbstractTree){
			IAbstractTree p = (IAbstractTree)parentElement;
			return p.getChildren();
		}
		return null;
	}


	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof IAbstractTree){
			IAbstractTree p = (IAbstractTree)element;
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
