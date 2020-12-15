package com.obl2.demo.repositories;

import com.eikholm.jpademo10.model.Dog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DogRepository extends PagingAndSortingRepository<Dog, Long> {
}
