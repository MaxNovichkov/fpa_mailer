package de.bht.fpa.mail.s797981.maillist;

import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;
import de.bht.fpa.mail.s000000.common.mail.model.Sender;
import de.bht.fpa.mail.s000000.common.table.MessageValues;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class MaillistTableViewerBuilder extends TableViewerBuilder {
	
	final private int IMPORTANCE_WIDTH = 2;
	final private int READ_WIDTH = 2;
	final private int RECEIVED_WIDTH = 4;
	final private int SENDER_WIDTH = 8;
	final private int RECIPIENT_WIDTH = 8;
	final private int SUBJECT_WIDTH = 10;
	final private String prefix = " <";
	final private String postfix = ">";
	final static String COLUMN_NAME_IMPORTANCE = "Imp";
	final static String COLUMN_NAME_READ = "Read";
	final static String COLUMN_NAME_RECEIVED = "Received";
	final static String COLUMN_NAME_SENDER = "Sender";
	final static String COLUMN_NAME_RECIPIENT = "Recipients";
	final static String COLUMN_NAME_SUBJECT = "Subject";
	
	public MaillistTableViewerBuilder(Composite tableComposite) {
		super(tableComposite);
		createColumnImportance(this);
		createColumnRead(this);
		createColumnReceived(this);
		createColumnSender(this);
		createColumnRecipients(this);
		createColumnSubject(this);
	}

	private void createColumnImportance(TableViewerBuilder tableCreator) {
		ColumnBuilder importance = tableCreator.createColumn(COLUMN_NAME_IMPORTANCE);
		importance.bindToValue(MessageValues.IMPORTANCE).setPercentWidth(IMPORTANCE_WIDTH).
														setCustomLabelProvider(new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				Object o = cell.getElement();
				if (o instanceof Message) {
					Message msg = (Message) o;
					String img = "icons/folder_s.png";
					if (msg.getImportance() == Importance.HIGH) {
						img = "icons/file_sm.png";
					} else if (msg.getImportance() == Importance.LOW) {
						img = "icons/folder_s.png";
					}
					Image image = Activator.imageDescriptorFromPlugin(
							Activator.PLUGIN_ID, img).createImage();
					cell.setImage(image);
				}
			}
		});
		importance.build();
	}

	private void createColumnRead(TableViewerBuilder tableCreator) {
		ColumnBuilder read = tableCreator.createColumn(COLUMN_NAME_READ);
		read.bindToValue(MessageValues.READ).setPercentWidth(READ_WIDTH).build();
	}

	private void createColumnReceived(TableViewerBuilder tableCreator) {
		ColumnBuilder received = tableCreator.createColumn(COLUMN_NAME_RECEIVED);
		received.bindToValue(MessageValues.RECEIVED).setPercentWidth(RECEIVED_WIDTH)
			.setCustomLabelProvider(new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				Object o = cell.getElement();
				if (o instanceof Message) {
					final Message msg = (Message) o;
					final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
					cell.setText(format.format(msg.getReceived()));
				}
			}
		});	
		received.build();
	}

	private void createColumnSender(TableViewerBuilder tableCreator) {
		ColumnBuilder sender = tableCreator.createColumn(COLUMN_NAME_SENDER);
		sender.bindToValue(MessageValues.SENDER).setPercentWidth(SENDER_WIDTH)
			.setCustomLabelProvider(new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				Object o = cell.getElement();
				StringBuilder result = new StringBuilder();
				if (o instanceof Message) {
					final Message msg = (Message) o;
					final Sender sender = msg.getSender();
					result.append(sender.getPersonal()).append(prefix).append(sender.getEmail()).append(postfix);
					cell.setText(result.toString());
				}
			}
		});	
		sender.build();
	}
	
	private void createColumnRecipients(TableViewerBuilder tableCreator) {
		ColumnBuilder recipient = tableCreator.createColumn(COLUMN_NAME_RECIPIENT);
		recipient.bindToValue(MessageValues.RECIPIENT).setPercentWidth(RECIPIENT_WIDTH).setCustomLabelProvider(new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				Object o = cell.getElement();
				if (o instanceof Message) {
					final Message msg = (Message) o;
					final List<Recipient> recipients = msg.getRecipients();
					final String comma = ", ";
					final StringBuilder result = new StringBuilder();
					for(int i = 0; i < recipients.size(); i++){
						result.append(recipients.get(i).getPersonal()).append(prefix).append(recipients.get(i)
								.getEmail()).append(postfix);
						if(i != recipients.size()-1){
							result.append(comma);
							
						}
					}
					cell.setText(result.toString());
				}
			}
		});	
		recipient.build();
	}

	private void createColumnSubject(TableViewerBuilder tableCreator) {
		ColumnBuilder subject = tableCreator.createColumn(COLUMN_NAME_SUBJECT);
		subject.bindToValue(MessageValues.SUBJECT).setPercentWidth(SUBJECT_WIDTH).build();
	}
}
