package demo.sapi.tools;

import fr.socgen.sis.common.domain.model.CipherTool;
import fr.socgen.sis.sapi.common.tools.AesEncryptHelper;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface SapiConstants {

    // --comme php
    String AES_KEY = AesEncryptHelper.AES_KEY_HOLDER;
    String AES_KEY_BIRT = CipherTool.getDbCryptoKey();
    String DATE_TO_STR = "dd/MM/yyyy";
    String ADMIN_SEIGE = "AGA_ADMIN";
    String MODE_CONTROLEUR = "AGA_CDG";
    String MODE_USER = "AGA_USER";
}
