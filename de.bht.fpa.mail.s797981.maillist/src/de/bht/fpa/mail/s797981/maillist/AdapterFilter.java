package de.bht.fpa.mail.s797981.maillist;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class AdapterFilter extends ViewerFilter{
	
		  private IFilter filter;

		  public AdapterFilter(IFilter filter) {
		    this.filter = filter;
		  }

		  public void setFilter(IFilter filter) {
		    this.filter = filter;
		  }

	  /**
	     * Returns whether the given element makes it through this filter.
	     *
	     * @param viewer the viewer
	     * @param parentElement the parent element
	     * @param element the element
	     * @return <code>true</code> if element is included in the
	     *   filtered set, and <code>false</code> if excluded
	     */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
//		return filter.match((Message) element);
		Set<Message> result = new HashSet<Message>();
	    result.add((Message) element);
	    return !filter.filter(result).isEmpty();
	}
	
//	public AFilter getFilter() {
//		return filter;
//	}
//
//	public static FactoryAdapterFilter createFilter(List<FilterCombination> filterCombinations, FilterDialog filterDialog){
//		List<AFilter> filters = new LinkedList<AFilter>();
//		for (FilterCombination filterCombination : filterCombinations) {
//			final FilterType filterType = filterCombination.getFilterType();
//			final FilterOperator filterOperator = filterCombination.getFilterOperator();
//			final Object filterValue = filterCombination.getFilterValue();
//
//			System.out.println("Filter: type:" + filterType + " operator:" + filterOperator + " value:" + filterValue);
//
//			switch (filterType) {
//			case SENDER:
//				filters.add(new SenderFilter((String) filterValue, filterOperator));
//				break;
//			case RECIPIENTS:
//				filters.add(new RecipientsFilter((String) filterValue, filterOperator));
//				break;
//			case SUBJECT:
//				filters.add(new SubjectFilter((String) filterValue,	filterOperator));
//				break;
//			case TEXT:
//				filters.add(new TextFilter((String) filterValue, filterOperator));
//				break;
//			case IMPORTANCE:
//				filters.add(new ImportanceFilter((Importance) filterValue));
//				break;
//			case READ:
//				filters.add(new ReadFilter((boolean) filterValue));
//				break;
//			default:
//				break;
//			}
//
//			switch (filterDialog.getFilterGroupType()) {
//			
//			case INTERSECTION:
//				filter = new IntersectionFilter(filters.toArray(new AFilter[filters.size()]));
//
//			case UNION:
//				filter =  new UnionFilter(filters.toArray(new AFilter[filters.size()]));
//			default:
//				return null;
//			}
//		}
//		return new FactoryAdapterFilter(filter);
//	}

	
	

}
