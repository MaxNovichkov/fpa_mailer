package de.bht.fpa.mail.s797981.maillist;

import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;
import de.bht.fpa.mail.s000000.common.mail.model.Sender;
import de.bht.fpa.mail.s000000.common.table.MessageValues;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

/**
 * Create mail table view with corresponding columns and fill it with "messages".
 * 
 * @author Novichkov Maxim
 *
 */
public class MaillistTableViewerBuilder extends TableViewerBuilder {

	final private int IMPORTANCE_WIDTH = 1;
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
	final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	/**
	 * Construct new TableViewerBuilder with defined columns.
	 */
	public MaillistTableViewerBuilder(final Composite tableComposite) {
		super(tableComposite);
		createColumnImportance(this);
		createColumnRead(this);
		createColumnReceived(this);
		createColumnSender(this);
		createColumnRecipients(this);
		createColumnSubject(this);
	}
	
	/**
	 * This method define column importance and put corresponding
	 * icons.
	 * 
	 * @param tableCreator
	 *            Instance of TableViewerBuilder
	 */
	private void createColumnImportance(final TableViewerBuilder tableCreator) {
		final ColumnBuilder importance = tableCreator.createColumn(COLUMN_NAME_IMPORTANCE);
		importance.bindToValue(MessageValues.IMPORTANCE).setPercentWidth(IMPORTANCE_WIDTH)
				.setCustomLabelProvider(new CellLabelProvider() {

					@Override
					public void update(final ViewerCell cell) {
						final Object o = cell.getElement();
						if (o instanceof Message) {
							final Message msg = (Message) o;
							String img = "icons/general.png";
							if (msg.getImportance() == Importance.HIGH) {
								img = "icons/important.png";
							} else if (msg.getImportance() == Importance.LOW) {
								img = "icons/not_important.png";
							}
							final Image image = Activator.imageDescriptorFromPlugin(
									Activator.PLUGIN_ID, img).createImage();
							cell.setImage(image);
						}
					}
				});
		importance.build();
	}

	/**
	 * Define column read and fill it.
	 * 
	 * @param tableCreator
	 *            Instance of TableViewerBuilder
	 */
	private void createColumnRead(final TableViewerBuilder tableCreator) {
		final ColumnBuilder read = tableCreator.createColumn(COLUMN_NAME_READ);
		read.bindToValue(MessageValues.READ).setPercentWidth(READ_WIDTH).build();
	}

	/**
	 * This method define column received and fill it.
	 * 
	 * @param tableCreator
	 *            Instance of TableViewerBuilder
	 */
	private void createColumnReceived(final TableViewerBuilder tableCreator) {
		final ColumnBuilder received = tableCreator.createColumn(COLUMN_NAME_RECEIVED);
		received.bindToValue(MessageValues.RECEIVED).setPercentWidth(RECEIVED_WIDTH)
				.setCustomLabelProvider(new CellLabelProvider() {

					@Override
					public void update(final ViewerCell cell) {
						final Object o = cell.getElement();
						if (o instanceof Message) {
							final Message msg = (Message) o;
							cell.setText(SIMPLE_DATE_FORMAT.format(msg
									.getReceived()));
						}
					}
				});
		received.build();
	}

	/**
	 * Define column sender and fill it.
	 * 
	 * @param tableCreator
	 *            Instance of TableViewerBuilder
	 */
	private void createColumnSender(final TableViewerBuilder tableCreator) {
		final ColumnBuilder sender = tableCreator.createColumn(COLUMN_NAME_SENDER);
		sender.bindToValue(MessageValues.SENDER).setPercentWidth(SENDER_WIDTH)
				.setCustomLabelProvider(new CellLabelProvider() {

					@Override
					public void update(final ViewerCell cell) {
						final Object o = cell.getElement();
						final StringBuilder result = new StringBuilder();
						if (o instanceof Message) {
							final Message msg = (Message) o;
							final Sender sender = msg.getSender();
							result.append(sender.getPersonal()).append(prefix)
									.append(sender.getEmail()).append(postfix);
							cell.setText(result.toString());
						}
					}
				});
		sender.build();
	}

	/**
	 * Define column recipient and fill it.
	 * 
	 * @param tableCreator
	 *            Instance of TableViewerBuilder
	 */
	private void createColumnRecipients(final TableViewerBuilder tableCreator) {
		final ColumnBuilder recipient = tableCreator.createColumn(COLUMN_NAME_RECIPIENT);
		recipient.bindToValue(MessageValues.RECIPIENT).setPercentWidth(RECIPIENT_WIDTH)
				.setCustomLabelProvider(new CellLabelProvider() {

					@Override
					public void update(final ViewerCell cell) {
						Object o = cell.getElement();
						if (o instanceof Message) {
							final Message msg = (Message) o;
							final List<Recipient> recipients = msg.getRecipients();
							final String comma = ", ";
							final StringBuilder result = new StringBuilder();
							for (int i = 0; i < recipients.size(); i++) {
								result.append(recipients.get(i).getPersonal())
										.append(prefix)
										.append(recipients.get(i).getEmail())
										.append(postfix);
								if (i != recipients.size() - 1) {
									result.append(comma);
								}
							}
							cell.setText(result.toString());
						}
					}
				});
		recipient.build();
	}

	/**
	 * Define column subject and fill it.
	 * 
	 * @param tableCreator
	 *            Instance of TableViewerBuilder
	 */
	private void createColumnSubject(final TableViewerBuilder tableCreator) {
		final ColumnBuilder subject = tableCreator.createColumn(COLUMN_NAME_SUBJECT);
		subject.bindToValue(MessageValues.SUBJECT).setPercentWidth(SUBJECT_WIDTH).build();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IMPORTANCE_WIDTH;
		result = prime * result + READ_WIDTH;
		result = prime * result + RECEIVED_WIDTH;
		result = prime * result + RECIPIENT_WIDTH;
		result = prime * result + SENDER_WIDTH;
		result = prime * result + SUBJECT_WIDTH;
		result = prime * result + ((postfix == null) ? 0 : postfix.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaillistTableViewerBuilder other = (MaillistTableViewerBuilder) obj;
		if (IMPORTANCE_WIDTH != other.IMPORTANCE_WIDTH)
			return false;
		if (READ_WIDTH != other.READ_WIDTH)
			return false;
		if (RECEIVED_WIDTH != other.RECEIVED_WIDTH)
			return false;
		if (RECIPIENT_WIDTH != other.RECIPIENT_WIDTH)
			return false;
		if (SENDER_WIDTH != other.SENDER_WIDTH)
			return false;
		if (SUBJECT_WIDTH != other.SUBJECT_WIDTH)
			return false;
		if (postfix == null) {
			if (other.postfix != null)
				return false;
		} else if (!postfix.equals(other.postfix))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MaillistTableViewerBuilder [IMPORTANCE_WIDTH="
				+ IMPORTANCE_WIDTH + ", READ_WIDTH=" + READ_WIDTH
				+ ", RECEIVED_WIDTH=" + RECEIVED_WIDTH + ", SENDER_WIDTH="
				+ SENDER_WIDTH + ", RECIPIENT_WIDTH=" + RECIPIENT_WIDTH
				+ ", SUBJECT_WIDTH=" + SUBJECT_WIDTH + ", prefix=" + prefix
				+ ", postfix=" + postfix + "]";
	}

}
