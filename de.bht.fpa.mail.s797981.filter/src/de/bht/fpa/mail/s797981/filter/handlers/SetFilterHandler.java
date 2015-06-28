package de.bht.fpa.mail.s797981.filter.handlers;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterDialog;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;
import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s797981.filter.AFilter;
import de.bht.fpa.mail.s797981.filter.FactoryAdapterFilter;
import de.bht.fpa.mail.s797981.filter.ImportanceFilter;
import de.bht.fpa.mail.s797981.filter.IntersectionFilter;
import de.bht.fpa.mail.s797981.filter.ReadFilter;
import de.bht.fpa.mail.s797981.filter.RecipientsFilter;
import de.bht.fpa.mail.s797981.filter.SenderFilter;
import de.bht.fpa.mail.s797981.filter.SubjectFilter;
import de.bht.fpa.mail.s797981.filter.TextFilter;
import de.bht.fpa.mail.s797981.filter.UnionFilter;

public class SetFilterHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		FilterDialog filterDialog = new FilterDialog(window.getShell());
		filterDialog.open();

		List<FilterCombination> filterCombinations = filterDialog.getFilterCombinations();
		if (filterCombinations == null) {
			return null;
		}
		
//		return FactoryAdapterFilter.createFilter(filterCombinations, filterDialog);
		
		List<AFilter> filters = new LinkedList<AFilter>();
		for (FilterCombination filterCombination : filterCombinations) {
			final FilterType filterType = filterCombination.getFilterType();
			final FilterOperator filterOperator = filterCombination.getFilterOperator();
			final Object filterValue = filterCombination.getFilterValue();

			System.out.println("Filter: type:" + filterType + " operator:" + filterOperator + " value:" + filterValue);

			switch (filterType) {
			case SENDER:
				filters.add(new SenderFilter((String) filterValue, filterOperator));
				break;
			case RECIPIENTS:
				filters.add(new RecipientsFilter((String) filterValue, filterOperator));
				break;
			case SUBJECT:
				filters.add(new SubjectFilter((String) filterValue,	filterOperator));
				break;
			case TEXT:
				filters.add(new TextFilter((String) filterValue, filterOperator));
				break;
			case IMPORTANCE:
				filters.add(new ImportanceFilter((Importance) filterValue));
				break;
			case READ:
				filters.add(new ReadFilter((boolean) filterValue));
				break;
			default:
				break;
			}

			switch (filterDialog.getFilterGroupType()) {
			case INTERSECTION:
				System.out.println("INTERSECTION");
				return new IntersectionFilter(filters.toArray(new AFilter[filters.size()]));

			case UNION:
				System.out.println("UNION");
				return new UnionFilter(filters.toArray(new AFilter[filters.size()]));
			default:
				return null;
			}
		}
		return null;
	}

}
