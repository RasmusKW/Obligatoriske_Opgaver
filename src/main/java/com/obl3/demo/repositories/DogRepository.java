package com.obl3.demo.repositories;


import com.obl3.demo.model.Dog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DogRepository extends PagingAndSortingRepository<Dog, Long>
{

}
