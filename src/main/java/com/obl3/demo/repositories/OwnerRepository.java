package com.obl3.demo.repositories;

import com.obl3.demo.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long>
{
}
