package demo.sapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.socgen.sis.aga.service.notice.ModuleNoticeService;
import fr.socgen.sis.common.web.AbstractSisController;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
@Controller
@RequestMapping("/")
public class HomeController extends AbstractSisController {

    @Value("${module.code}")
    private String moduleCode;

    @Autowired
    private ModuleNoticeService moduleNoticeService;

    @RequestMapping
    public String home(HttpServletRequest request) {
        if (request.getSession()
                .getAttribute(ModuleAttributeKeys.NOTICE_EXISTS) == null) {
            final Boolean noticeExists = moduleNoticeService
                    .existsNoticeByCode(moduleCode);
            request.getSession().setAttribute(
                    ModuleAttributeKeys.NOTICE_EXISTS, noticeExists);
        }
        return redirect(request, ModuleViews.URL_HOME, true);
    }

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
