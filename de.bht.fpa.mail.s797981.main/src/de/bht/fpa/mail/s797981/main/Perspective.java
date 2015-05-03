package de.bht.fpa.mail.s797981.main;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public static final String PERSPECTIVE_ID = Activator.PLUGIN_ID + ".perspective";
	public static final String LEFT_ID = PERSPECTIVE_ID + ".left";
	public static final String TOP_ID = PERSPECTIVE_ID + ".top";
	public static final String BOTTOM_ID = PERSPECTIVE_ID + ".bottom";
	public static final float RATIO_TOP = 0.5f;
	public static final float RATIO_BOTTOM = 0.5f;
	public static final float RATIO_LEFT = 0.25f;
	
	public void createInitialLayout(IPageLayout layout) {
		String editorAreaId = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		layout.createPlaceholderFolder(LEFT_ID, IPageLayout.LEFT, RATIO_LEFT, editorAreaId);
		layout.createPlaceholderFolder(TOP_ID, IPageLayout.TOP, RATIO_TOP, editorAreaId);
		layout.createPlaceholderFolder(BOTTOM_ID, IPageLayout.BOTTOM, RATIO_BOTTOM, editorAreaId);
	}
}