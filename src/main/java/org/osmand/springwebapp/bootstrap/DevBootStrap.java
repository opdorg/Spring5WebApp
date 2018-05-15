package org.osmand.springwebapp.bootstrap;

import org.osmand.springwebapp.model.Author;
import org.osmand.springwebapp.model.Book;
import org.osmand.springwebapp.model.Publisher;
import org.osmand.springwebapp.repositories.AuthorRepository;
import org.osmand.springwebapp.repositories.BookRepository;
import org.osmand.springwebapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    // constructor auto injection thorugh spring dependency injection
    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Author eric = new Author("Eric","Evans");
        Publisher hc = new Publisher("Harper Collins","New York, USA");
        Book ddd = new Book("Domain Driven Design", "123", hc);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        Author rod = new Author("Rod","Johnson");
        Publisher wx = new Publisher("Worx", "Texas, USA");
        Book noEJB = new Book("J2EE Development without EJB", "87768", wx);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        publisherRepository.save(hc);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        publisherRepository.save(wx);
        authorRepository.save(rod);
        bookRepository.save(noEJB);


    }
}
