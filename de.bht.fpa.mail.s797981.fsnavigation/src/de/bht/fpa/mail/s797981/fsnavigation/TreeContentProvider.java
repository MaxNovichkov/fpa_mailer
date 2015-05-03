package de.bht.fpa.mail.s797981.fsnavigation;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TreeContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
//		if (inputElement instanceof Person){
//			Person p = (Person)inputElement;
//			return p.getChildren();
//		}
//		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ITreeProvider){
			ITreeProvider p = (ITreeProvider)parentElement;
			return p.getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof ITreeProvider){
			ITreeProvider p = (ITreeProvider)element;
			return p.getChildren().length > 0;
		}
		return false;
	}
	

}
