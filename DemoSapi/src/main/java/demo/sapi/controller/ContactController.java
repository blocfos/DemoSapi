package demo.sapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.socgen.sis.common.web.AbstractSisController;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
@Controller
@RequestMapping("/contact")
public class ContactController extends AbstractSisController {

    @RequestMapping
    public ModelAndView redirectToContact() {
        return new ModelAndView("contact");
    }
}
