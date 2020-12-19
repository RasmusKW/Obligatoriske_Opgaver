package com.obl3.demo;


import com.obl3.demo.model.Dog;
import com.obl3.demo.model.Owner;
import com.obl3.demo.service.DogService;
import com.obl3.demo.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public String adopt(@ModelAttribute Owner owner, @RequestParam("dogid") String dogid)
    {
        System.out.println("id = "+owner.getId());
        Optional<Dog> OptionalDog = dogService.findById(Long.parseLong(dogid));
        Optional<Owner> optionalOwner = ownerService.findById(owner.getId());
        if(OptionalDog.isPresent() && optionalOwner.isPresent()){
            owner = optionalOwner.get();

            if(owner.getDogs() == null){ // check dette
                owner.setDogs(new HashSet<>());
            }

            OptionalDog.get().setOwner(owner);
            owner.getDogs().add(OptionalDog.get());
            dogService.save(OptionalDog.get());
            ownerService.save(owner);
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
    public String addDog(Dog dog, @RequestParam("owner") long ownerId){
        System.out.println("/dogs/addDog kaldet med:  " + ownerId);
        Optional<Owner> owner = ownerService.findById(ownerId);
        if(owner.isPresent()){
            dog.setOwner(owner.get());
            owner.get().getDogs().add(dog);
        }
        dogService.save(dog);
        return "redirect:/dogs";
    }

}
