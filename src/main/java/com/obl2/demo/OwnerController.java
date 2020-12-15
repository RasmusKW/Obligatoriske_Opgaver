package com.obl2.demo;

import com.obl2.demo.model.Dog;
import com.obl2.demo.model.Owner;
import com.obl2.demo.service.DogService;
import com.obl2.demo.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.Set;

@Controller
public class OwnerController {

    private OwnerService ownerService; // Spring vil selv komme med objektet hertil
    private DogService dogService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/owners/","/owners", "owners/index"})
    public String getOwners(Model model){
        System.out.print("Owners, size: ");
        Set<Owner> owners = ownerService.findAll();
        model.addAttribute("owners", owners);
        System.out.println(owners.size());
        return "owners/index";
    }

    @PostMapping("/owners/addOwner")
    public String addOwner(@RequestParam("name") String name){
        Owner owner = new Owner();
        owner.setFirstName(name);
        ownerService.save(owner);
        return "redirect:/owners";
    }
}
