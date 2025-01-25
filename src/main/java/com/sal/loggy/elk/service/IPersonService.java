package com.sal.loggy.elk.service;

import com.sal.loggy.elk.entity.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    public List<Person> allPersons();
    public Person savePerson(Person person);
    public Optional<Person> getPersonById(Long id);
    public Person updatePerson(Long personId,Person personForUpdate);
    public void deletePersonById(Long id);
}
