package com.obl1.demo.Controller;
        import com.obl1.demo.model.Person;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.servlet.ModelAndView;

        import java.util.ArrayList;
        import java.util.List;

@RestController
public class HomeController {
    private List<Person> persons = new ArrayList<>();

    @GetMapping("/")
    public ModelAndView index(Model model) {
        model.addAttribute("persons", persons);
        ModelAndView mv = new ModelAndView("index");
        mv.getModel().put("persons", persons);
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView addUser(Person person, Model model) {
        persons.add(person);
        return index(model);
    }

}