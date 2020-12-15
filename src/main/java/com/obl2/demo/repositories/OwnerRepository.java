package com.obl2.demo.repositories;

import com.eikholm.jpademo10.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
