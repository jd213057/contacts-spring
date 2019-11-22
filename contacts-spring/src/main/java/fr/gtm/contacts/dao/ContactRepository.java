package fr.gtm.contacts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.gtm.contacts.entities.Adresse;
import fr.gtm.contacts.entities.Civilite;
import fr.gtm.contacts.entities.Contact;

@Repository()
public interface ContactRepository extends JpaRepository<Contact, Long>{
	
	List<Contact> getByNomStartingWith(String nom);
	
	
	@Query("SELECT c FROM Contact c JOIN FETCH c.adresses WHERE c.id =?1")
	Contact getContactById(long id);
	
	default List<Adresse> list(long id) {
		Contact contact = getContactById(id);
		return contact.getAdresses();
	}


//	void save(Civilite m, String nom, String prenom);
	

}
