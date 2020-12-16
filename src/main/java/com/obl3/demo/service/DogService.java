package com.obl3.demo.service;



import com.obl3.demo.model.Dog;

import java.util.List;

public interface DogService extends CrudService<Dog,Long> {
    public List<Dog> getOwnerlessDogs();
    public List<Dog> getDogs(int start, int number);

}
