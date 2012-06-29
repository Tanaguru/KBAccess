package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.NomenclatureElement;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface NomenclatureElementFactory extends
		GenericFactory<NomenclatureElement> {

	NomenclatureElement create(String code, String value);
}
