package org.opens.kbaccess.entity.service.reference;

import java.util.Collection;
import java.util.List;
import org.opens.kbaccess.entity.dao.reference.ThemeDAO;
import org.opens.kbaccess.entity.reference.Reference;
import org.opens.kbaccess.entity.reference.Theme;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author opens
 */
public class ThemeDataServiceImpl extends AbstractGenericDataService<Theme, Long> implements ThemeDataService {

    public ThemeDataServiceImpl() {
        super();
    }

    @Override
    public Theme read(Long key) {
        Theme entity = super.read(key);
        entity.getCriterionList().size();
        return entity;
    }

    @Override
    public Theme getByCode(String code) {
        return ((ThemeDAO) entityDao).findByCode(code);
    }

    @Override
    public Collection<Theme> getThemeListFromReference(Reference ref) {
        return (List<Theme>)((ThemeDAO) entityDao).findAll(ref);
    }

}
