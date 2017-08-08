package demo.sapi.controller;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.socgen.sis.aga.domain.model.Rubrique;
import fr.socgen.sis.aga.service.RubriqueService;
import fr.socgen.sis.common.web.AbstractSisController;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
@Controller
@RequestMapping("/administration/")
public class RubriqueConfigController extends AbstractSisController {

    private static final Logger LOG = LoggerFactory
            .getLogger(RubriqueConfigController.class);

    @Autowired
    private RubriqueService rubriquedao;

    @RequestMapping("/rubrique")
    @PreAuthorize("hasAnyRole('AGA_ADMIN','ADMIN')")
    public ModelAndView page() {
        return new ModelAndView();
    }

    @RequestMapping("/rubrique/list")
    @ResponseBody
    @PreAuthorize("hasAnyRole('AGA_ADMIN','ADMIN')")
    public Page<Rubrique> getListRubriques(
            @RequestBody Map<String, Object> paramap) {
        int page = 0;
        try {
            if (paramap.get("page") != null) {
                page = Integer.parseInt(paramap.get("page").toString()) - 1;
            }
        } catch (final NumberFormatException e) {
            LOG.warn("Exception has occure in RubriqueConfigController", e);
        }
        int size = 25;
        try {
            if (paramap.get("rows") != null) {
                size = Integer.parseInt(paramap.get("rows").toString());
            }
        } catch (final NumberFormatException e) {

            LOG.warn("Exception has occure in RubriqueConfigController", e);
        }
        final String sortprop = StringUtils.isBlank((String) paramap
                .get("sidx")) ? "rubrique" : (String) paramap.get("sidx");
        Direction d = Direction.ASC;
        try {
            d = Direction.fromString((String) paramap.get("sord"));
        } catch (final IllegalArgumentException e) {

            LOG.warn("Exception has occure in RubriqueConfigController", e);
        }
        return rubriquedao.findAll(new PageRequest(page, size, new Sort(d,
                sortprop)));
    }

    @RequestMapping("/configrubrique")
    @PreAuthorize("hasAnyRole('AGA_ADMIN','ADMIN')")
    public ModelAndView configRubrique(@RequestParam Map<String, Object> paramap) {
        final ModelAndView mv = new ModelAndView();
        if (paramap.get("selectedid") != null) {
            try {
                mv.addObject(
                        "rubriqueDev",
                        rubriquedao.findOne(Integer.parseInt(paramap.get(
                                "selectedid").toString())));
            } catch (NumberFormatException e) {
                LOG.warn("Exception has occure in RubriqueConfigController", e);

            }
        } else {
            mv.addObject("rubriqueDev", null);
        }
        for (Entry<String, Object> e : paramap.entrySet()) {
            mv.addObject(e.getKey(), e.getValue());
        }
        return mv;
    }

    @RequestMapping("/saverubrique")
    @PreAuthorize("hasAnyRole('AGA_ADMIN','ADMIN')")
    public String saveRubrique(@RequestParam Map<String, Object> paramap,
            RedirectAttributes attr) {
        if (paramap.get("add") != null
                && Boolean.parseBoolean(paramap.get("add").toString())) {
            Rubrique rubrique = null;
            if (paramap.get("selectedid") != null) {
                try {
                    rubrique = rubriquedao.findOne(Integer.parseInt(paramap
                            .get("selectedid").toString()));
                } catch (NumberFormatException e) {
                    rubrique = new Rubrique();
                }
            }
            if (rubrique == null) {
                rubrique = new Rubrique();
            }

            if (paramap.get("sousrubriquetxt") != null) {
                rubrique.setDescr(paramap.get("sousrubriquetxt").toString());
            }
            rubrique.setRubrique(paramap.get("rubriquetxt").toString());
            rubriquedao.save(rubrique);
        }
        for (Entry<String, Object> e : paramap.entrySet()) {
            attr.addFlashAttribute(e.getKey(), e.getValue());
        }
        return "redirect:/administration/rubrique";
    }
}
