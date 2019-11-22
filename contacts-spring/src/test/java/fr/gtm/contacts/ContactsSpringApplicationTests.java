package fr.gtm.contacts;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.gtm.contacts.dao.ContactRepository;
import fr.gtm.contacts.entities.Adresse;
import fr.gtm.contacts.entities.Contact;
import fr.gtm.contacts.entities.Civilite;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ContactsSpringApplicationTests {
	
	@Autowired ContactRepository repo;

	@Test
	void getAll() {
		List<Contact> contacts =repo.findAll();
		assertTrue(contacts.size()>0);
	}
	
	@Test
	void getByNomStartingWith() {
		List<Contact> contacts =repo.getByNomStartingWith("Laga");
		System.out.println(contacts);
		assertTrue(contacts.size()>0);
	}
	
	@Test
	void getContactById() {
		Contact contact = repo.getContactById(1);
		System.out.println(contact);
		System.out.println(contact.getAdresses());
		List<Adresse> adresses = repo.list(1L);
		System.out.println(adresses);
		assertNotNull(contact);
	}
	
	@Test
	void createContact() {
		Contact contact = new Contact(Civilite.M, "Dimur", "Jonathan");
//		repo.save(Civilite.M, "Dimur", "Jonathan");
		List<Contact> list1 = repo.findAll();
		repo.save(contact);
		List<Contact> contactTest = repo.getByNomStartingWith("Dimur");
		System.out.println(contactTest);
		List<Contact> list2 = repo.findAll();
		assertNotEquals(list1,list2);
		
	}

}
