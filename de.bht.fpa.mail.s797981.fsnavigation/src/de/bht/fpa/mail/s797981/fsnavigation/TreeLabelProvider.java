package de.bht.fpa.mail.s797981.fsnavigation;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TreeLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof IAbstractTree){
			IAbstractTree p = (IAbstractTree)element;
			return p.getName();
		}
		return super.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {
		if (element instanceof IAbstractTree){
			IAbstractTree p = (IAbstractTree)element;
			return p.getImage();
		}
		return super.getImage(element);
	}

}
