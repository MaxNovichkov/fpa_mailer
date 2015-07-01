package de.bht.fpa.mail.s797981.filter.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s797981.filter.ImportanceFilter;
import de.bht.fpa.mail.s797981.filter.IntersectionFilter;
import de.bht.fpa.mail.s797981.filter.SubjectFilter;
import de.bht.fpa.mail.s797981.filter.UnionFilter;

public class UnionIntersectionTest {
	final String unic_subject_string = "All for nothing";
	final String duplicate_subject_string = "Free Willy is finally free";
	final int duplicate_subjects_number = 2;
	final int unic_subject_number = 1;
	
	public List<Message> setUp() {
		final Message message_1 = new Message();
		final Message message_2 = new Message();
		final Message message_3 = new Message();
		final Message message_4 = new Message();
		message_1.setSubject(unic_subject_string);
		message_1.setImportance(Importance.LOW);
		
		message_2.setSubject(duplicate_subject_string);
		message_2.setImportance(Importance.NORMAL);
		
		message_3.setSubject(duplicate_subject_string);
		message_3.setImportance(Importance.NORMAL);
		
		message_4.setSubject(duplicate_subject_string);
		message_4.setImportance(Importance.HIGH);
		
		final List<Message> messages = new ArrayList<Message>();
		messages.add(message_1);
		messages.add(message_2);
		messages.add(message_3);
		messages.add(message_4);
		return messages;
	}
	
	@Test
	  public void intersectionSenderAndImportanceTest() {
		Set<Message> messages = new IntersectionFilter(
				new SubjectFilter(duplicate_subject_string, FilterOperator.IS), 
				new ImportanceFilter(Importance.NORMAL)).filter(setUp());
		
	    assertEquals("Number of messages", duplicate_subjects_number, messages.size());
	    assertNotEquals("Number of messages is wrong", unic_subject_number, messages.size());
	    for (Message message : messages) {
	      assertEquals("Subject is", duplicate_subject_string.toLowerCase(), message.getSubject().toLowerCase());
	      assertEquals("Importance is", Importance.NORMAL, message.getImportance());
	      assertNotEquals("Subject is not", unic_subject_string.toLowerCase(), message.getSubject().toLowerCase());
	      assertNotEquals("Importance is not", Importance.HIGH, message.getImportance());
	    }
	  }
	
	@Test
	  public void unionSenderAndImportanceTest() {
		Set<Message> messages = new UnionFilter(
				new SubjectFilter(duplicate_subject_string, FilterOperator.IS), 
				new ImportanceFilter(Importance.NORMAL)).filter(setUp());
		
	    assertEquals("Number of messages", duplicate_subjects_number + unic_subject_number, messages.size());
	    assertNotEquals("Number of messages is wrong", unic_subject_number, messages.size());
	    for (Message message : messages) {
	      assertEquals("Subject is", duplicate_subject_string.toLowerCase(), message.getSubject().toLowerCase());
	      assertTrue("Importance is ", message.getImportance().equals(Importance.NORMAL)
	    		  || message.getImportance().equals(Importance.HIGH));
	      assertNotEquals("Not contain importance: ", Importance.LOW, message.getImportance());
	      assertNotEquals("Subject is not", unic_subject_string.toLowerCase(), message.getSubject().toLowerCase());
	    }
	  }

}
