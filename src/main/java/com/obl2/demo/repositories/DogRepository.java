package com.obl2.demo.repositories;


import com.obl2.demo.model.Dog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DogRepository extends PagingAndSortingRepository<Dog, Long>
{

}
