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
public class NiveauController extends AbstractSisController {

    @RequestMapping(value = "/niveaufilter1")
    public ModelAndView newcomm() {
        final ModelAndView mv = new ModelAndView();
        return mv;
    }
}
