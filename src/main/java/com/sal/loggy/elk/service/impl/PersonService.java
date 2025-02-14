package com.sal.loggy.elk.service.impl;


import com.sal.loggy.elk.entity.Person;
import com.sal.loggy.elk.repo.PersonRepository;
import com.sal.loggy.elk.service.IPersonService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService implements IPersonService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> allPersons() {
        LOGGER.info("PersonService | getPersons is started");
        List<Person> persons = new ArrayList<>(personRepository.findAll());
        LOGGER.info("PersonService | getPersons | persons Size :  {}", persons.size());

        return persons;
    }

    @Override
    public Person savePerson(Person person) {
        LOGGER.info("PersonService | savePerson is started");
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> getPersonById(Long id) {
        LOGGER.info("PersonService | getPersonById is started");
        LOGGER.info("PersonService | getPersonById | id : {}", id);
        return personRepository.findById(id);
    }

    @Override
    public Person updatePerson(Long personId, Person personForUpdate) {

        LOGGER.info("PersonService | updatePerson is started");
        LOGGER.info("PersonService | updatePerson | personId : {}", personId);

        Person updatedPerson = personRepository.findById(personId).get();

        updatedPerson.setName(personForUpdate.getName());
        updatedPerson.setSurname(personForUpdate.getSurname());
        updatedPerson.setUsername(personForUpdate.getUsername());
        updatedPerson.setBirthDate(personForUpdate.getBirthDate());
        updatedPerson.setEmail(personForUpdate.getEmail());
        updatedPerson.setPassword(personForUpdate.getPassword());

        LOGGER.info("PersonService | updatePerson | updatedPerson : {}", updatedPerson);

        return personRepository.save(updatedPerson);
    }

    @Override
    public void deletePersonById(Long id) {
        LOGGER.info("PersonService | deletePersonById is started");
        LOGGER.info("PersonService | deletePersonById | id : {}", id);
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            Person deletedPerson = person.get();
            personRepository.delete(deletedPerson);
        }
    }
}
