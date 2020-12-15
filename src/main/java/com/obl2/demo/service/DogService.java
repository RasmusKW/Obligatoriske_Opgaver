package com.obl2.demo.service;



import com.obl2.demo.model.Dog;

import java.util.List;
import java.util.Set;

public interface DogService extends CrudService<Dog,Long> {
    public List<Dog> getOwnerlessDogs();
    public List<Dog> getDogs(int start, int number);

}
