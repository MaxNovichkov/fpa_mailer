package de.bht.fpa.mail.s797981.filter.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797981.filter.SubjectFilter;

public class SubjectTest {
	
	final String unic_subject_string = "All for nothing";
	final String duplicate_subject_string = "Free Willy is finally free";
	final int duplicate_subjects_number = 2;
	final int unic_subject_number = 1;
	
	public List<Message> setUp() {
		final Message message_1 = new Message();
		final Message message_2 = new Message();
		final Message message_3 = new Message();
		message_1.setSubject(unic_subject_string);
		message_2.setSubject(duplicate_subject_string);
		message_3.setSubject(duplicate_subject_string);
		final List<Message> messages = new ArrayList<Message>();
		messages.add(message_1);
		messages.add(message_2);
		messages.add(message_3);
		return messages;
	}
	
	@Test
	  public void testIs() {
		Set<Message> messages = new SubjectFilter(duplicate_subject_string, FilterOperator.IS).filter(setUp());
		assertEquals("Number of messages with subject (IS)", duplicate_subjects_number, messages.size());
		assertNotEquals("Number of messages with wrong subject (IS)", unic_subject_number, messages.size());
	    for (Message message : messages) {
	    	assertEquals(duplicate_subject_string.toLowerCase(), message.getSubject().toLowerCase());
	    	assertNotEquals(unic_subject_string.toLowerCase(), message.getSubject().toLowerCase());
	    }
	  }
	
	 @Test
	  public void testContainsNot() {
	    Set<Message> messages = new SubjectFilter(duplicate_subject_string, FilterOperator.CONTAINS_NOT).filter(setUp());
	    assertEquals("Number of messages with subject (CONTAINS_NOT)", unic_subject_number , messages.size());
	    for (Message message : messages) {
	      assertTrue("Subject does contain: " + duplicate_subject_string, !message.getSubject().toLowerCase().contains(duplicate_subject_string.toLowerCase()));
	    }
	  }
	 
	 @Test
	  public void testContains() {
	    Set<Message> messages = new SubjectFilter(duplicate_subject_string, FilterOperator.CONTAINS).filter(setUp());
	    assertEquals("Number of messages with subject (CONTAINS)", duplicate_subjects_number , messages.size());
	    for (Message message : messages) {
	      assertTrue("Subject contains: " + duplicate_subject_string, message.getSubject().toLowerCase().contains(duplicate_subject_string.toLowerCase()));
	    }
	  }
	 
	 @Test
	  public void testStartsWith() {
		 final String true_search = "free";
		 final String  false_search = "all";
		 Set<Message> messages = new SubjectFilter(true_search, FilterOperator.STARTS_WITH).filter(setUp());
	    assertEquals("Number of messages STARTS_WITH subject", duplicate_subjects_number, messages.size());
	    for (Message message : messages) {
	      assertTrue("Subject start with " + true_search, message.getSubject().toLowerCase().startsWith(true_search));
	      assertFalse("Subject does not start with " + false_search, message.getSubject().toLowerCase().startsWith(false_search));
	    }
	  }
	 
	 @Test
	  public void testEndWith() {
		 final String true_search = "nothing";
		 final String  false_search = "free";
		 Set<Message> messages = new SubjectFilter(true_search, FilterOperator.ENDS_WITH).filter(setUp());
	    assertEquals("Number of messages ENDS_WITH subject", unic_subject_number, messages.size());
	    for (Message message : messages) {
	      assertTrue("Subject end with " + true_search, message.getSubject().toLowerCase().endsWith(true_search));
	      assertFalse("Subject does not end with " + false_search, message.getSubject().toLowerCase().endsWith(false_search));
	    }
	  }
}
