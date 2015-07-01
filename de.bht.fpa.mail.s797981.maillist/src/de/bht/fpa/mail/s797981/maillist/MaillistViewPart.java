package de.bht.fpa.mail.s797981.maillist;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797981.fsnavigation.TreeDirectory;
import de.ralfebert.rcputils.tables.TableViewerBuilder;

/**
 * This class represents part of view with list of E-mails and quick search option for E-mails.
 * 
 * @author Novichkov Maxim
 *
 */
public class MaillistViewPart extends ViewPart implements ISelectionListener {
	/**
	 * TableViewer
	 */
	private TableViewer tableViewer;
	/**
	 * List with messages
	 */
	private List<Message> messages = new LinkedList<Message>();
	/**
	 * Object with searched text
	 */
	private Text searchText;

	@Override
	public void setFocus() {
		this.tableViewer.getControl().setFocus();
	}
	/**
	 * This method check which element was selected by user. If it is element {@link TreeDirectory} it will be 
	 * looked if there some {@link Message} exist and than filled created before {@link MaillistTableViewerBuilder}
	 * with list of {@link Message}. By default messages sorted by received time.
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection sel) {
		if (!(sel instanceof IStructuredSelection)) {
			return;
		}
		IStructuredSelection ss = (IStructuredSelection) sel;
		Object o = ss.getFirstElement();
		if (o instanceof TreeDirectory) {
			TreeDirectory directory = (TreeDirectory) o;
			messages = directory.getMessages();
			tableViewer.setInput(messages);
			final Table table = tableViewer.getTable();
			for (TableColumn column : table.getColumns()) {
				if (column.getText().equals(MaillistTableViewerBuilder.COLUMN_NAME_RECEIVED)) {
					table.setSortColumn(column); 
					table.setSortDirection(SWT.DOWN);
				}
			}
			tableViewer.refresh();
		}
	};

	/**
	 * In this method be create new {@link MaillistTableViewerBuilder}, which represent a table with columns
	 * for list of E-mails. As {@link MaillistTableViewerBuilder} is created it will be filled with list of {@link Message}.
	 * On start list with E-mails is empty. Additionally will be provided quick search on E-mail in special search bar, that will be constructed here.  
	 */
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(2, false));

		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		searchLabel.setText("Search:");

		searchText = new Text(parent, SWT.BORDER);
		searchText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite tableComposite = new Composite(parent, SWT.NONE);
		GridData area = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		tableComposite.setLayoutData(area);
		
		//test messages
//		Collection<Message> messages = new RandomTestDataProvider(50).getMessages();
		
		TableViewerBuilder tvb = new MaillistTableViewerBuilder(tableComposite);
		tableViewer = tvb.getTableViewer();
		tvb.setInput(messages);

		getSite().getPage().addSelectionListener(this);
		getSite().setSelectionProvider(tableViewer);

		tableViewer.addFilter(new TypeFilter(searchText));
		searchText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				tableViewer.refresh();
			}

		});
		//add filter/start up
		tableViewer.addFilter(new AdapterFilter());
		
		final IWorkbench workbench = PlatformUI.getWorkbench();
	    ICommandService commandService = (ICommandService) workbench.getService(ICommandService.class);
	    commandService.addExecutionListener(new ExecutionListener(tableViewer));
	}
	
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}
	@Override
	public String toString() {
		return "MaillistViewPart [tableViewer=" + tableViewer + ", messages="
				+ messages + ", searchText=" + searchText + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((messages == null) ? 0 : messages.hashCode());
		result = prime * result
				+ ((searchText == null) ? 0 : searchText.hashCode());
		result = prime * result
				+ ((tableViewer == null) ? 0 : tableViewer.hashCode());
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
		MaillistViewPart other = (MaillistViewPart) obj;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (searchText == null) {
			if (other.searchText != null)
				return false;
		} else if (!searchText.equals(other.searchText))
			return false;
		if (tableViewer == null) {
			if (other.tableViewer != null)
				return false;
		} else if (!tableViewer.equals(other.tableViewer))
			return false;
		return true;
	}
}
