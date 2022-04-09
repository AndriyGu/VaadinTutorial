package com.example.application.views.list;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ContactFormTest {
    private List<Company> companies;
    private List<Status> statuses;
    private Contact marcUsher;
    private Company company1;
    private Company company2;
    private Status status1;
    private Status status2;

    @Before
    public void setupData() {
        companies = new ArrayList<>();
        company1 = new Company();
        company1.setName("Vaadin Ltd");
        company2 = new Company();
        company2.setName("IT Mill");
        companies.add(company1);
        companies.add(company2);

        statuses = new ArrayList<>();
        status1 = new Status();
        status1.setName("Status 1");
        status2 = new Status();
        status2.setName("Status 2");
        statuses.add(status1);
        statuses.add(status2);

        marcUsher = new Contact();
        marcUsher.setFirstName("Mark");
        marcUsher.setLastName("Usher");
        marcUsher.setEmail("marc@usher.com");
        marcUsher.setStatus(status1);
        marcUsher.setCompany(company2);
    }

    @Test
    public void formFieldsPopulated(){
        ContactForm contactForm = new ContactForm(companies, statuses);
        contactForm.setContact(marcUsher);

        Assert.assertEquals("Mark", contactForm.getFirstName().getValue());
        Assert.assertEquals("Usher", contactForm.getLastName().getValue());
        Assert.assertEquals("marc@usher.com", contactForm.getEmail().getValue());
        Assert.assertEquals(company2, contactForm.getCompany().getValue());
        Assert.assertEquals(status1, contactForm.getStatus().getValue());
    }

    @Test
    public void tsaveEventHasCorrectValues() {
        ContactForm form = new ContactForm(companies, statuses);
        Contact contact = new Contact();
        form.setContact(contact);

        form.firstName.setValue("John");
        form.lastName.setValue("Doe");
        form.company.setValue(company1);
        form.email.setValue("john@doe.com");
        form.status.setValue(status2);

        AtomicReference<Contact> savedContactRef = new AtomicReference<>(null);
        form.addListener(ContactForm.SaveEvent.class, e -> {
            savedContactRef.set(e.getContact());
        });
        form.save.click();
        Contact savedContact = savedContactRef.get();

        Assert.assertEquals("John", savedContact.getFirstName());
        Assert.assertEquals("Doe", savedContact.getLastName());
        Assert.assertEquals("john@doe.com", savedContact.getEmail());
        Assert.assertEquals(company1, savedContact.getCompany());
        Assert.assertEquals(status2, savedContact.getStatus());
    }

    @Test
    public void saveEventHasCorrectValues(){
        ContactForm form = new ContactForm(companies, statuses);
        Contact contact = new Contact();
        form.setContact(contact);

        //form.firstName.setValue("John");
        //form.lastName.setValue("Doe");
        //form.company.setValue(company1);
        //form.email.setValue("john@doe.com");
        //form.status.setValue(status2);

        form.firstName.setValue("John");
        form.lastName.setValue("Doe");
        form.company.setValue(company1);
        form.email.setValue("Do@edoe.com");
        form.status.setValue(status2);

        String ds = form.firstName.getValue();
        String fff = form.lastName.getValue();
        String fdf = form.email.getValue();



        AtomicReference<Contact> savedContact = new AtomicReference<>(null);
        form.addListener(ContactForm.SaveEvent.class, e -> {savedContact.set(e.getContact());});

        form.save.click();
        Contact saved = savedContact.get();

        Assert.assertEquals("John", saved.getFirstName());
        Assert.assertEquals("Doe",saved.getLastName());
        Assert.assertEquals("Do@edoe.com",saved.getEmail());
        Assert.assertEquals(company1,saved.getCompany());
        Assert.assertEquals(status2,saved.getStatus());
    }
}
