package de.bht.fpa.mail.s797981.imapnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s797981.imapnavigation.MessageView;
import de.bht.fpa.mail.s797981.imapnavigation.SendMessage;
/**
 * This class provide ability to call {@link MessageView} from menu, create and send message by user. 
 * 
 * @author Novichkov Maxim
 */
public class SendMessageHandler extends AbstractHandler{
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
	    MessageView dialog = new  MessageView(window.getShell());
	    if (dialog.open() == Window.OK) {
	    	Account account = new Account();
	    	account.setHost("smtp.gmail.com");
	    	account.setUsername("fpabht@gmail.com");
	    	account.setPassword("FPAG-mail");	
	    	SendMessage.send(account, dialog.getRecipient(), dialog.getSubject(), dialog.getMessage());
	    }
	    return null;
	}

}
