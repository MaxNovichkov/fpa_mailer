package de.bht.fpa.mail.s797981.filter;

import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class ImportanceFilter extends AFilter{
	
	final private Importance importance;
	
	public ImportanceFilter(final Importance importance) {
		this.importance = importance;
	}
	@Override
	boolean match(Message message) {
		return message.getImportance().equals(importance);
	}

}
