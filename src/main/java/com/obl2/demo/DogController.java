package com.obl2.demo;


import com.obl2.demo.model.Dog;
import com.obl2.demo.model.Owner;
import com.obl2.demo.service.DogService;
import com.obl2.demo.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Controller
public class DogController {

    private DogService dogService;
    private OwnerService ownerService;
    private final String LOGINSTATUS = "loginstatus";
    public DogController(DogService dogService, OwnerService ownerService) {
        this.dogService = dogService;
        this.ownerService = ownerService;
    }


    @RequestMapping(value = "/dogs/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String usr,
                        @RequestParam("password") String pwd,
                        HttpSession httpSession){
        if(usr.equals("doglover") && pwd.equals("vow")) {
            httpSession.setAttribute(LOGINSTATUS, "yes");
           // return "dogs/doghome";
        }
        return "redirect:/dogs";
    }

    @RequestMapping(value = "/dogs/adopt", method = RequestMethod.POST)
    public String adopt(Owner owner, @RequestParam("dogid") String dogid){
        Optional<Dog> dog = dogService.findById(Long.parseLong(dogid));
        Optional<Owner> owner1 = ownerService.findById(owner.getId());
        if(dog.isPresent() && owner1.isPresent()){
            Owner o = owner1.get();
            if(o.getDogs() == null){ // check dette
                o.setDogs(new HashSet<>());
            }
            dog.get().setOwner(o);
            o.getDogs().add(dog.get());
            dogService.save(dog.get());
            ownerService.save(o);
        }
        return "redirect:/dogs";
    }


    @GetMapping({"/dogs", "/"})
    public String dogs(Model model){
        model.addAttribute("dogs", dogService.findAll());
//        model.addAttribute("dogs", dogService.getDogs(0,2));
        model.addAttribute("owners", ownerService.findAll());
        model.addAttribute("ownerlessdogs", dogService.getOwnerlessDogs());
        return "dogs/index";
    }

    @RequestMapping(value = "/dogs/addDog", method = RequestMethod.POST)
    public String addDog(Dog dog, @RequestParam("owner") String ownerId){
        System.out.println("/dogs/addDog kaldet med:  " + ownerId);
        Optional<Owner> owner = ownerService.findById(Long.parseLong(ownerId));
        if(owner.isPresent()){
            dog.setOwner(owner.get());
            owner.get().getDogs().add(dog);
        }
        dogService.save(dog);
        return "redirect:/dogs";
    }

}
