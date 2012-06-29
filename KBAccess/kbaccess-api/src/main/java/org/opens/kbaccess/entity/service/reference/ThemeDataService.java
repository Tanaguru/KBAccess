package org.opens.kbaccess.entity.service.reference;

import java.util.Collection;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Theme;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface ThemeDataService extends GenericDataService<Theme, Long> {

    Theme getByCode(String code);

    Collection<Theme> getThemeListFromReference(Reference ref);
}
