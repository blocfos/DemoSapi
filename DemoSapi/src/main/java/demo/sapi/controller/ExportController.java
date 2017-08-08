package demo.sapi.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.socgen.sis.common.web.AbstractSisController;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
@Controller
@RequestMapping("exportGrid")
public class ExportController extends AbstractSisController {

    @RequestMapping(method = RequestMethod.POST)
    public void exportGrid(@RequestParam("exportField") String exportField,
            HttpServletResponse response) throws IOException {
        response.setContentType("text/csv; charset=iso-8859-1");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"export.csv\"");
        response.getOutputStream().write(
                convertUtf8String2Iso88591(exportField));
        response.flushBuffer();
    }

    private byte[] convertUtf8String2Iso88591(String exportField) {
        return new String(exportField.getBytes(StandardCharsets.ISO_8859_1),
                StandardCharsets.UTF_8).getBytes(StandardCharsets.ISO_8859_1);
    }
}
