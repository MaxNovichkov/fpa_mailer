package de.bht.fpa.mail.s797981.imapnavigation;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
/**
 * This class provide message view dialog with corresponding fields for sending email message.
 * 
 * @author Novichkov Maxim
 */
public class MessageView extends Dialog { 
	 private Composite container;
	 private Text tfRecipient;
	 private Text tfSubject;
	 private Text tfMessage;
	 private String recipient;
	 private String subject;
	 private String message;
	 private Label lblMessage;
	 /**
	  * Construct new MessageView 
	  */
	public MessageView(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MAX | SWT.RESIZE | SWT.TITLE | SWT.APPLICATION_MODAL);
	}
	/**
	 * Construct message view with corresponding fields
	 */
	 @Override
	  protected Control createDialogArea(Composite parent) {
	    container = (Composite) super.createDialogArea(parent);
	    container.setLayout(new GridLayout(2, false));
	    new Label(container, SWT.NONE);
	    
	    Label lblTo = new Label(container, SWT.NONE);
	    lblTo.setText("Recipient:");
	    new Label(container, SWT.NONE);
	    
	    tfRecipient = new Text(container, SWT.BORDER);
	    tfRecipient.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    new Label(container, SWT.NONE);
	    
	    Label lblSubject = new Label(container, SWT.NONE);
	    lblSubject.setText("Subject:");
	    new Label(container, SWT.NONE);
	    
	    tfSubject = new Text(container, SWT.BORDER);
	    tfSubject.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    new Label(container, SWT.NONE);
	   
	    lblMessage = new Label(container, SWT.NONE);
	    lblMessage.setText("Message:");
	    new Label(container, SWT.NONE);
	    
	    tfMessage = new Text(container, SWT.BORDER | SWT.WRAP | SWT.MULTI);
	    GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
	    gd_text_2.heightHint = 292;
	    tfMessage.setLayoutData(gd_text_2);
	    getShell().setText("Write new Message");
	    return container;
	  }
	 /**
	  * Default size of dialog window
	  */
	 protected Point getInitialSize() {
		 return new Point(700, 500);
	 }

	 @Override
	  protected void okPressed() {
	    recipient = tfRecipient.getText();
	    subject = tfSubject.getText();
		message = tfMessage.getText();
	    super.okPressed();
	  }
	 
	public String getRecipient() {
		return recipient;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getMessage() {
		return message;
	}
	
}