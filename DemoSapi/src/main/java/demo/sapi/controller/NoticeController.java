package demo.sapi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.socgen.sis.aga.domain.model.notice.SisModuleNotice;
import fr.socgen.sis.aga.service.notice.ModuleNoticeService;
import fr.socgen.sis.common.web.AbstractSisController;
import fr.socgen.sis.common.web.MimeTypeEnum;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends AbstractSisController {

    @Value("${module.code}")
    private String moduleCode;

    @Autowired
    private ModuleNoticeService moduleNoticeService;

    @RequestMapping
    public void downloadNotice(HttpServletResponse response) throws IOException {
        final SisModuleNotice notice = moduleNoticeService
                .findNoticeByCode(moduleCode);
        if (notice != null) {
            final MimeTypeEnum mimeType = MimeTypeEnum.valueOf(notice
                    .getExtension().toUpperCase());
            response.setContentType(mimeType.getContentType());
            response.setHeader("Content-Disposition",
                    mimeType.createAttachmentHeader("notice_" + moduleCode));
            response.getOutputStream().write(notice.getData());
        }
    }
}
