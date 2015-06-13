package de.bht.fpa.mail.s797981.maillist;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797981.fsnavigation.TreeDirectory;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

public class MaillistViewPart extends ViewPart implements ISelectionListener {

	private TableViewer tableViewer;

	private List<Message> messages;

	@Override
	public void setFocus() {
		this.tableViewer.getControl().setFocus();
	}

	public void selectionChanged(IWorkbenchPart part, ISelection sel) {
		if (!(sel instanceof IStructuredSelection)) {
			return;
		}
		IStructuredSelection ss = (IStructuredSelection) sel;
		Object o = ss.getFirstElement();
		if (o instanceof TreeDirectory) {
			messages = ((TreeDirectory) o).getMessages();
			this.tableViewer.setInput(messages);
			for (TableColumn column : this.tableViewer.getTable().getColumns()) {
				if (column.getText().equals(MaillistTableViewerBuilder.COLUMN_NAME_RECEIVED)) {
					tableViewer.getTable().setSortColumn(column);
					tableViewer.getTable().setSortDirection(SWT.DOWN);
				}
			}
			tableViewer.refresh();
		}
	};

	@Override
	public void createPartControl(Composite parent) {
		Composite tableComposite = new Composite(parent, SWT.NONE);
		TableViewerBuilder tvb = new MaillistTableViewerBuilder(tableComposite);
		this.tableViewer = tvb.getTableViewer();
		tvb.setInput(messages);

		getSite().getPage().addSelectionListener(this);
		getSite().setSelectionProvider(this.tableViewer);
	}

	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
	}
}
