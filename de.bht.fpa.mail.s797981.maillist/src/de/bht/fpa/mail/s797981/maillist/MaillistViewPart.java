package de.bht.fpa.mail.s797981.maillist;

import java.util.Collection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class MaillistViewPart extends ViewPart {

	private TableViewer tableViewer;
	
	@Override
	public void createPartControl(Composite parent) {
		Composite tableComposite = new Composite(parent, SWT.NONE);
		TableViewerBuilder tvb = new MaillistTableViewerBuilder(tableComposite);
		Collection<Message> messages = new RandomTestDataProvider(50).getMessages();
		tvb.setInput(messages);
		this.tableViewer = tvb.getTableViewer();
	}

	@Override
	public void setFocus() {
		this.tableViewer.getControl().setFocus();
	}

}
