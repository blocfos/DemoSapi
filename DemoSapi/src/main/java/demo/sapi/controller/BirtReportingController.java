package demo.sapi.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.socgen.sis.aga.security.AgaUserService;
import fr.socgen.sis.aga.tools.AgaConstants;
import fr.socgen.sis.common.web.AbstractSisController;
import fr.socgen.sis.sapi.common.isis.service.SystemNiveauXService;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
@Controller
public class BirtReportingController extends AbstractSisController {

    @Autowired
    private AgaUserService userService;
    @Autowired
    @Qualifier(value = "SystemNiveauXService")
    private SystemNiveauXService service;

    private static final Logger LOG = LoggerFactory
            .getLogger(BirtReportingController.class);

    private static final String all_value = "%";
    private static final String nv5Pm = "niveau5";
    private static final String nv4Pm = "niveau4";
    private static final String nv3Pm = "niveau3";
    private static final String nv2Pm = "niveau2";
    private static final String nv1Pm = "niveau1";
    private static final String nv0Pm = "niveau0";
    private static final String nv5SgPm = "niveau5sigle";
    private static final String nv4SgPm = "niveau4sigle";
    private static final String nv3SgPm = "niveau3sigle";
    private static final String nv2SgPm = "niveau2sigle";
    private static final String nv1SgPm = "niveau1sigle";
    private static final String nv0SgPm = "niveau0sigle";
    private static final String dateStartString = "dateStartString";
    private static final String dateEndString = "dateEndString";
    private static final String rubriquePm = "rubrique";
    private static final String ligne_aff = "ligne_aff";
    private static final String formatDate = "MM/yyyy";
    private static final String periode_au = "periode_au";
    private static final String periode_du = "periode_du";
    private static final String keyDecrypt = "keyDecrypt";
    private static final String operator = "operator";
    private static final String etat = "etat";
    private static final String type = "type";

    @Value("${birt.jdbc.driver.class}")
    private String jdbcDriver;
    @Value("${module.datasource.url}")
    private String datasourceUrl;
    @Value("${module.datasource.user}")
    private String user;
    @Value("${module.datasource.password}")
    private String password;

    @RequestMapping("/addparamBirt")
    @ResponseBody
    public void addparamBirt(HttpServletRequest request,
            @RequestBody Map<String, String> paraMap) {
        // set les valeur vers la session
        Date dateStart = null;
        Date dateEnd = null;

        final String datestartstr = StringUtils.isNotBlank(paraMap
                .get(dateStartString)) ? paraMap.get(dateStartString)
                .toString() : null;
        final String dateendstr = StringUtils.isNotBlank(paraMap
                .get(dateEndString)) ? paraMap.get(dateEndString).toString()
                : null;
        final String niveau5 = StringUtils.isNotBlank(paraMap.get(nv5Pm)) ? paraMap
                .get(nv5Pm).toString() : all_value;
        final String niveau4 = StringUtils.isNotBlank(paraMap.get(nv4Pm)) ? paraMap
                .get(nv4Pm).toString() : all_value;
        final String niveau3 = StringUtils.isNotBlank(paraMap.get(nv3Pm)) ? paraMap
                .get(nv3Pm).toString() : all_value;
        final String niveau2 = StringUtils.isNotBlank(paraMap.get(nv2Pm)) ? paraMap
                .get(nv2Pm).toString() : all_value;
        final String niveau1 = StringUtils.isNotBlank(paraMap.get(nv1Pm)) ? paraMap
                .get(nv1Pm).toString() : all_value;
        final String niveau0 = StringUtils.isNotBlank(paraMap.get(nv0Pm)) ? paraMap
                .get(nv0Pm).toString() : all_value;
        final String rubrique = StringUtils.isNotBlank(paraMap.get(rubriquePm)) ? paraMap
                .get(rubriquePm).toString() : all_value;
        final String ligneaffiche = StringUtils.isNotBlank(paraMap
                .get(ligne_aff)) ? paraMap.get(ligne_aff).toString()
                : all_value;

        final String niveau5sigle = StringUtils
                .isNotBlank(paraMap.get(nv5SgPm)) ? paraMap.get(nv5SgPm)
                .toString() : all_value;
        final String niveau4sigle = StringUtils
                .isNotBlank(paraMap.get(nv4SgPm)) ? paraMap.get(nv4SgPm)
                .toString() : all_value;
        final String niveau3sigle = StringUtils
                .isNotBlank(paraMap.get(nv3SgPm)) ? paraMap.get(nv3SgPm)
                .toString() : all_value;
        final String niveau2sigle = StringUtils
                .isNotBlank(paraMap.get(nv2SgPm)) ? paraMap.get(nv2SgPm)
                .toString() : all_value;
        final String niveau1sigle = StringUtils
                .isNotBlank(paraMap.get(nv1SgPm)) ? paraMap.get(nv1SgPm)
                .toString() : all_value;
        final String niveau0sigle = StringUtils
                .isNotBlank(paraMap.get(nv0SgPm)) ? paraMap.get(nv0SgPm)
                .toString() : all_value;
        if (datestartstr != null) {
            try {
                dateStart = new SimpleDateFormat(formatDate, Locale.FRENCH)
                        .parse(datestartstr);
            } catch (ParseException e) {
                LOG.warn("Exception has occure in BritReportingController", e);
            }
        }
        if (dateendstr != null) {
            try {
                dateEnd = new SimpleDateFormat(formatDate, Locale.FRENCH)
                        .parse(dateendstr);
                final Calendar cal = Calendar.getInstance();
                cal.setTime(dateEnd);
                cal.set(Calendar.DATE,
                        cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                dateEnd = cal.getTime();
            } catch (ParseException e) {
                LOG.warn("Exception has occure in BritReportingController", e);
            }
        } else {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(dateStart);
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            dateEnd = cal.getTime();
        }

        final List<String> listNiveau5 = new ArrayList<String>();
        final List<String> listNiveau4 = new ArrayList<String>();
        final List<String> listNiveau3 = new ArrayList<String>();

        if (all_value.equals(niveau5)) {
            listNiveau5.addAll(userService.getUserNiveau5ListString());
        } else {
            listNiveau5.add(niveau5);
        }
        if (all_value.equals(niveau4)) {
            listNiveau4.addAll(userService
                    .getUserNiveau4ListString(listNiveau5));
        } else {
            listNiveau4.add(niveau4);
        }
        if (all_value.equals(niveau3)) {
            listNiveau3.addAll(userService
                    .getUserNiveau3ListString(listNiveau4));
        } else {
            listNiveau3.add(niveau3);
        }

        request.getSession().setAttribute(periode_au, dateEnd);
        request.getSession().setAttribute(periode_du, dateStart);
        request.getSession().setAttribute(nv0Pm, niveau0);
        request.getSession().setAttribute(nv1Pm, niveau1);
        request.getSession().setAttribute(nv2Pm, niveau2);
        request.getSession().setAttribute(nv3Pm,
                this.getListNiveau(listNiveau3));
        request.getSession().setAttribute(nv4Pm,
                this.getListNiveau(listNiveau4));
        request.getSession().setAttribute(nv5Pm,
                this.getListNiveau(listNiveau5));
        request.getSession().setAttribute(rubriquePm, rubrique);
        request.getSession().setAttribute(ligne_aff, ligneaffiche);
        request.getSession().setAttribute(dateStartString, datestartstr);
        request.getSession().setAttribute(dateEndString, dateendstr);
        request.getSession()
                .setAttribute(keyDecrypt, SapiConstants.AES_KEY_BIRT);
        request.getSession().setAttribute(nv5SgPm, niveau5sigle);
        request.getSession().setAttribute(nv4SgPm, niveau4sigle);
        request.getSession().setAttribute(nv3SgPm, niveau3sigle);
        request.getSession().setAttribute(nv2SgPm, niveau2sigle);
        request.getSession().setAttribute(nv1SgPm, niveau1sigle);
        request.getSession().setAttribute(nv0SgPm, niveau0sigle);

        request.getSession().setAttribute("niveau3list",
                (Serializable) listNiveau3);
        request.getSession().setAttribute("niveau4list",
                (Serializable) listNiveau4);
        request.getSession().setAttribute("niveau5list",
                (Serializable) listNiveau5);

        setBirtDataSourceParam(request);

    }

    @RequestMapping("/addparamBirtConsom")
    @ResponseBody
    public void addparamBirtConsom(HttpServletRequest request,
            @RequestBody Map<String, String> paraMap) {
        // set les valeur vers la session
        Date dateStart = null;
        Date dateEnd = null;

        final String datestartstr = StringUtils.isNotBlank(paraMap
                .get(dateStartString)) ? paraMap.get(dateStartString)
                .toString() : null;
        final String dateendstr = StringUtils.isNotBlank(paraMap
                .get(dateEndString)) ? paraMap.get(dateEndString).toString()
                : null;
        final String niveau5 = StringUtils.isNotBlank(paraMap.get(nv5Pm)) ? paraMap
                .get(nv5Pm).toString() : all_value;
        final String niveau4 = StringUtils.isNotBlank(paraMap.get(nv4Pm)) ? paraMap
                .get(nv4Pm).toString() : all_value;
        final String niveau3 = StringUtils.isNotBlank(paraMap.get(nv3Pm)) ? paraMap
                .get(nv3Pm).toString() : all_value;
        final String niveau2 = StringUtils.isNotBlank(paraMap.get(nv2Pm)) ? paraMap
                .get(nv2Pm).toString() : all_value;
        final String niveau1 = StringUtils.isNotBlank(paraMap.get(nv1Pm)) ? paraMap
                .get(nv1Pm).toString() : all_value;
        final String niveau0 = StringUtils.isNotBlank(paraMap.get(nv0Pm)) ? paraMap
                .get(nv0Pm).toString() : all_value;

        final String operateur = (!"-1".equals(paraMap.get(operator))) ? paraMap
                .get(operator).toString() : all_value;
        final String lineStatut = (!"-1".equals(paraMap.get(etat))) ? paraMap
                .get(etat).toString() : all_value;
        final String materialType = (!"-1".equals(paraMap.get(type))) ? paraMap
                .get(type).toString() : all_value;
        final String ligneaffiche = StringUtils.isNotBlank(paraMap
                .get(ligne_aff)) ? paraMap.get(ligne_aff).toString()
                : all_value;
        final String niveau5sigle = StringUtils
                .isNotBlank(paraMap.get(nv5SgPm)) ? paraMap.get(nv5SgPm)
                .toString() : all_value;
        final String niveau4sigle = StringUtils
                .isNotBlank(paraMap.get(nv4SgPm)) ? paraMap.get(nv4SgPm)
                .toString() : all_value;
        final String niveau3sigle = StringUtils
                .isNotBlank(paraMap.get(nv3SgPm)) ? paraMap.get(nv3SgPm)
                .toString() : all_value;
        final String niveau2sigle = StringUtils
                .isNotBlank(paraMap.get(nv2SgPm)) ? paraMap.get(nv2SgPm)
                .toString() : all_value;
        final String niveau1sigle = StringUtils
                .isNotBlank(paraMap.get(nv1SgPm)) ? paraMap.get(nv1SgPm)
                .toString() : all_value;
        final String niveau0sigle = StringUtils
                .isNotBlank(paraMap.get(nv0SgPm)) ? paraMap.get(nv0SgPm)
                .toString() : all_value;

        if (datestartstr != null) {
            try {
                dateStart = new SimpleDateFormat(formatDate, Locale.FRENCH)
                        .parse(datestartstr);
            } catch (ParseException e) {
                LOG.warn("Exception has occure in BritReportingController", e);
            }
        }
        if (dateendstr != null) {
            try {
                dateEnd = new SimpleDateFormat(formatDate, Locale.FRENCH)
                        .parse(dateendstr);
                final Calendar cal = Calendar.getInstance();
                cal.setTime(dateEnd);
                cal.set(Calendar.DATE,
                        cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                dateEnd = cal.getTime();
            } catch (ParseException e) {
                LOG.warn("Exception has occure in BritReportingController", e);
            }
        } else {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(dateStart);
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            dateEnd = cal.getTime();
        }

        final List<String> listNiveau5 = new ArrayList<String>();
        final List<String> listNiveau4 = new ArrayList<String>();
        final List<String> listNiveau3 = new ArrayList<String>();
        List<String> listNiveau5Display = new ArrayList<String>();
        List<String> listNiveau4Display = new ArrayList<String>();
        List<String> listNiveau3Display = new ArrayList<String>();

        if (all_value.equals(niveau5)) {

            listNiveau5.addAll(userService.getUserNiveau5ListString());
        } else {
            listNiveau5.add(niveau5);
        }

        if (all_value.equals(niveau4)) {
            listNiveau4.addAll(userService
                    .getUserNiveau4ListString(listNiveau5));
        } else {
            listNiveau4.add(niveau4);
        }
        if (all_value.equals(niveau3)) {
            listNiveau3.addAll(userService
                    .getUserNiveau3ListString(listNiveau4));
        } else {
            listNiveau3.add(niveau3);
        }
        listNiveau5Display = listNiveau5;
        listNiveau4Display = listNiveau4;
        listNiveau3Display = listNiveau3;
        request.getSession().setAttribute(periode_au, dateEnd);
        request.getSession().setAttribute(periode_du, dateStart);
        request.getSession().setAttribute(nv0Pm, niveau0);
        request.getSession().setAttribute(nv1Pm, niveau1);
        request.getSession().setAttribute(nv2Pm, niveau2);
        request.getSession().setAttribute(nv3Pm,
                this.getListNiveau(listNiveau3));
        request.getSession().setAttribute(nv4Pm,
                this.getListNiveau(listNiveau4));
        request.getSession().setAttribute(nv5Pm,
                this.getListNiveau(listNiveau5));
        request.getSession().setAttribute("niveau3display",
                this.getListNiveau(listNiveau3Display));
        request.getSession().setAttribute("niveau4display",
                this.getListNiveau(listNiveau4Display));
        request.getSession().setAttribute("niveau5display",
                this.getListNiveau(listNiveau5Display));
        request.getSession().setAttribute(operator, operateur);
        request.getSession().setAttribute(etat, lineStatut);
        request.getSession().setAttribute(type, materialType);

        request.getSession().setAttribute(dateStartString, datestartstr);
        request.getSession().setAttribute(dateEndString, dateendstr);
        request.getSession()
                .setAttribute(keyDecrypt, SapiConstants.AES_KEY_BIRT);

        request.getSession().setAttribute(nv5SgPm, niveau5sigle);
        request.getSession().setAttribute(nv4SgPm, niveau4sigle);
        request.getSession().setAttribute(nv3SgPm, niveau3sigle);
        request.getSession().setAttribute(nv2SgPm, niveau2sigle);
        request.getSession().setAttribute(nv1SgPm, niveau1sigle);
        request.getSession().setAttribute(nv0SgPm, niveau0sigle);
        request.getSession().setAttribute(ligne_aff, ligneaffiche);
        final String isanonmony = userService.getUserDetails().getAgaUser()
                .isAnonymiser() ? "1" : "-1";
        request.getSession().setAttribute("isanonmony", isanonmony);

        request.getSession().setAttribute("niveau3list",
                (Serializable) listNiveau3);
        request.getSession().setAttribute("niveau4list",
                (Serializable) listNiveau4);
        request.getSession().setAttribute("niveau5list",
                (Serializable) listNiveau5);

        setBirtDataSourceParam(request);
    }

    private String getListNiveau(List<String> listSB) {
        if (listSB.isEmpty())
            return '"' + " " + '"';
        final List<String> condition = new ArrayList<>();
        for (String data : listSB) {
            condition.add('"' + data + '"');
        }
        final String listmetier = condition.toString();
        final String l1 = listmetier.replace('[', ' ');
        final String l2 = l1.replace(']', ' ');
        return l2;

    }

    private void setBirtDataSourceParam(HttpServletRequest request) {
        request.getSession().setAttribute("jdbcDriver", jdbcDriver);
        request.getSession().setAttribute("datasourceUrl", datasourceUrl);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("password", password);
    }
}