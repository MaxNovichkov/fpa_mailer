package de.bht.fpa.mail.s797981.imapnavigation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.imapsync.SynchronizationException;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797981.imapnavigation.items.AccountListLoader;
import de.bht.fpa.mail.s797981.imapnavigation.items.AccountsList;

/**
 * 
 * This class represent navigation view. 
 * Is a receiver in command pattern.
 *
 */
public class ImapView extends ViewPart{
	public static final String ID = "de.bht.fpa.s797981.imapnavigation.NavigationView";
	private static TreeViewer viewer;
	private AccountsList accountsList;
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(Composite parent) {

		/**
		 * a TreeViewer is a Jface widget, which shows trees
		 */
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.BORDER);
		/**
		 * We set the ContentProvider for the TreeViewer. This class prepares
		 * data to be shown by the TreeViewer.
		 */
		viewer.setContentProvider(new ImapContentProvider());
		/**
		 * We set the LabelProvider. This class decides how elements in the tree
		 * are shown by returning a text and an optional icon.
		 */
		viewer.setLabelProvider(new ImapLabelProvider());
		/**
		 * Here we react on click on tree item. This class will wrap selected
		 * element with ITreeDirectory and print existing messages in selected
		 * folder (if exist and have right format)
		 */
		viewer.addSelectionChangedListener(new ImapSelectionChangedListener());
		/**
		 * Here we set the root of the tree. The framework will ask for more
		 * data when the user expands tree items.
		 */
		viewer.setInput(createModel());

		getSite().setSelectionProvider(viewer);
	}

	/**
	 * Set up a model to initialize tree hierarchy.
	 */
	private Object createModel() {
		accountsList = new AccountsList();
		
		
		Job job = new Job("My Job") {
			  @Override
			  protected IStatus run(IProgressMonitor monitor) {
			    // do something long running
				  Account toSync = ImapHelper.getAccount("FPA-Gmail");
				  try {
					toSync = AccountsList.getBeuthGmailAccount(); 
					ImapHelper.syncAllFoldersToAccount(toSync , monitor);
					if (monitor.isCanceled()) return Status.CANCEL_STATUS;
				} catch (SynchronizationException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
//				  ImapHelper.saveAccount(toSync);
				  accountsList.addAccount(toSync);
					  
			    // If you want to update the UI
			    return Status.OK_STATUS;
			  }
			};
			
			job.addJobChangeListener(new JobChangeAdapter() {
		        public void done(IJobChangeEvent event) {
		        if (event.getResult().isOK())
		           System.out.println("Job completed successfully");
		           else
		              System.out.println("Job did not complete successfully");
		        }
		     });
		  job.setSystem(true);

			// Start the Job
			job.schedule(); 
		
		accountsList.addAccount(AccountsList.generateDummyAccount());
//		accountsList = AccountListLoader.readImapAccount();
//		return new ImapAccount(DummyAccount.generateDummyAccount());
//		accountsList.writeImapAccount(accountsList);
		return accountsList;
	}
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
//	/**
//	 * We want update navigation view if path in SimpleRoot was changed by user. 
//	 * We use observer pattern for this.
//	 */
//	@Override
//	public void update(Observable o, Object directory) {
//		viewer.setInput(directory);
//	}
}
