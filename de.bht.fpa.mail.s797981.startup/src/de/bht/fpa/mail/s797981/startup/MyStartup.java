package de.bht.fpa.mail.s797981.startup;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindowConfigurer;

public class MyStartup implements IStartup {
	
	public void earlyStartup() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				System.out.println(window.getActivePage().getNavigationHistory());
				if (window != null) {
					// ...
				}
			}
		});
		
		
//		WorkbenchWindowConfigurer configurer = getWindowConfigurer();
	      // configurer.setInitialSize(new Point(WIDTH, HEIGHT));
	      // configurer.setShowCoolBar(false);
	      // configurer.setShowStatusLine(false);
	}
}
