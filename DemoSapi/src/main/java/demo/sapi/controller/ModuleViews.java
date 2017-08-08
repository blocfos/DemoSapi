package demo.sapi.controller;

import fr.socgen.sis.common.web.CommonViews;

/**
 * Declare all module's views
 * 
 */
public interface ModuleViews extends CommonViews {

    /*
     * The HomeController witch maps the / url will forward to this url. It's
     * not a jsp page like other views. Change this URL and map it by an other
     * Controller. The URL correspond to the default tab of the module
     */
    String URL_HOME = "/index";

}
