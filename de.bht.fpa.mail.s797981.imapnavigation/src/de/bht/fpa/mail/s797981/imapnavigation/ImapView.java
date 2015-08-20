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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s797981.imapnavigation.items.AccountListLoader;
import de.bht.fpa.mail.s797981.imapnavigation.items.AccountsList;

/**
 * 
 * This class represent imap navigation view.
 *
 */
public class ImapView extends ViewPart {
	public static final String ID = "de.bht.fpa.s797981.imapnavigation.NavigationView";
	private static TreeViewer viewer;

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
		/**
		 * Here we update ImapView in dependence on running job by setting 
		 * AccountsList with corresponding updated list of imap accounts.
		 */
		Job.getJobManager().addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				String jobName = event.getJob().getName();
				switch (jobName) {
				case "IMAP Loading":
					System.out.println("IMAP Loading =====================");
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							viewer.setInput(AccountsList.getInstance());
						}
					});
					break;
				case "IMAP Synchronising":
					System.out.println("IMAP Synchronising =====================");
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							viewer.setInput(AccountsList.getInstance());
						}
					});
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * Set up a model to initialize tree hierarchy.
	 */
	private Object createModel() {
		/**
		 * Load imap dummy account.
		 */
		AccountsList.getInstance().addAccount(AccountsList.generateDummyAccount());
		/**
		 * Load accounts from xml file, if file not exist, 
		 * test xml file placed in project folder files ("de.bht.fpa.mail.s797981.imapnavigation/files/accountsList.xml"), 
		 * please, just copy it to your user.home location
		 */
		AccountListLoader.loadFromFileImapAccounts();
		/**
		 * New job for loading existed imap account from db or if no imap account exist, 
		 * new one will be created. Loaded imap account will be settled to AccountsList.
		 */
		Job job = new Job("IMAP Loading") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Account loaded = ImapHelper.getAccount("FPA-Gmail");
				if (loaded == null) {
					loaded = AccountsList.getBeuthGmailAccount();
					ImapHelper.saveAccount(loaded);
				}
				if (monitor.isCanceled()) {
					return Status.CANCEL_STATUS;
				}
				
				AccountsList.getInstance().addAccount(loaded);

				return Status.OK_STATUS;
			}
		};
		job.schedule();

		return AccountsList.getInstance();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
