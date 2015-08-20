package de.bht.fpa.mail.s797981.imapnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.imapsync.SynchronizationException;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s797981.imapnavigation.items.AccountsList;

/**
* This class provide ability to load and synchronize imap accounts with accounts saved in db from context menu. 
* 
* @author Novichkov Maxim
*/
public class SynchronizeHandler extends AbstractHandler {
	/**
	 * Creates new job for synchronization imap accounts 
	 */
	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		Job job = new Job("IMAP Synchronising") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Account loaded = ImapHelper.getAccount("FPA-Gmail");
				if (loaded != null) {
					try {
						ImapHelper.syncAllFoldersToAccount(loaded, monitor);
						ImapHelper.saveAccount(loaded);

						if (monitor.isCanceled()) {
							return Status.CANCEL_STATUS;
						}
					} catch (SynchronizationException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Please, first load your account");
				}
				/**
				 * Add loaded account to the list of accounts
				 */
				AccountsList.getInstance().addAccountAndRemoveDuplicate(loaded);
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();
		return null;
	}

}
