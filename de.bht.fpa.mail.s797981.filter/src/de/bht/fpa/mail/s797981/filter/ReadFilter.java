package de.bht.fpa.mail.s797981.filter;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class ReadFilter extends AFilter{
	
	final private boolean isRead;
	
	public ReadFilter(final boolean isRead) {
		this.isRead = isRead;
	}
	
	@Override
	boolean match(Message message) {
		return message.isRead() == isRead;
	}

	@Override
	public String toString() {
		return "ReadFilter [isRead=" + isRead + "]";
	}
}
