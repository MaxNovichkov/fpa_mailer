package de.bht.fpa.mail.s797981.maillist;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.NullFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * Provide ability to use IFilter as Viewer filter.
 * 
 * @author Novichkov Maxim
 *
 */
public class AdapterFilter extends ViewerFilter {
	/**
	 * Actual IFilter
	 */
	private IFilter filter;
	
	/**
	 * Construct new default AdapterFilter
	 * with settled NullFilter
	 */
	public AdapterFilter() {
		this.filter = new NullFilter();
	}

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
	 * @param element the element (Message)
	 * @return <code>true</code> if element is included in the filtered set, and
	 *         <code>false</code> if excluded
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		final Set<Message> result = new HashSet<Message>();
		result.add((Message) element);
		return !filter.filter(result).isEmpty();
	}

	@Override
	public String toString() {
		return "AdapterFilter [filter=" + filter + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filter == null) ? 0 : filter.hashCode());
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
		AdapterFilter other = (AdapterFilter) obj;
		if (filter == null) {
			if (other.filter != null)
				return false;
		} else if (!filter.equals(other.filter))
			return false;
		return true;
	}
}
