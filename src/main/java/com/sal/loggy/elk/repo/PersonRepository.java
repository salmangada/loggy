package com.sal.loggy.elk.repo;

import com.sal.loggy.elk.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
