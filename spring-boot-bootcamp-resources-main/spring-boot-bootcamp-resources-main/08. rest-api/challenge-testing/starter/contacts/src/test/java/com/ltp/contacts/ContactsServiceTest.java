package com.ltp.contacts;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.contacts.exception.ContactNotFoundException;
import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.repository.ContactRepository;
import com.ltp.contacts.service.ContactService;
import com.ltp.contacts.service.ContactServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ContactsServiceTest {
    @Mock
    ContactRepository contactRepository;

    @InjectMocks
    ContactService contactService = new ContactServiceImpl();

    @Test
    public void testGetContactById(){
        List<Contact> contacts = Arrays.asList(
            new Contact("1", "John Williams", "23431"),
            new Contact("2", "James Horner", "123444"),
            new Contact("3", "Hanz Zimmer", "8654")
        );

        when(contactRepository.getContacts()).thenReturn(contacts);
        when(contactRepository.getContact(0)).thenReturn(contacts.get(0));

        Contact contact = contactService.getContactById(contacts.get(0).getId());

        assertEquals(contact.getName(), "John Williams");
        assertEquals(contact.getPhoneNumber(), "23431");
    }

    @Test
    public void testInvalidGetContactById(){

        String invalidId = "nonExistentId";

        List<Contact> contacts = Arrays.asList(
            new Contact("1", "John Williams", "23431"),
            new Contact("2","James Horner", "123444"),
            new Contact("3", "Hanz Zimmer", "8654")
        );

        when(contactRepository.getContacts()).thenReturn(contacts);
        assertThrows(ContactNotFoundException.class, () -> contactService.getContactById(invalidId));
    }    
}
