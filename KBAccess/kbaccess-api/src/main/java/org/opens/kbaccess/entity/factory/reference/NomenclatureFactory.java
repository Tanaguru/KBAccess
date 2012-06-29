package org.opens.kbaccess.entity.factory.reference;

import org.opens.kbaccess.entity.reference.Nomenclature;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author jkowalczyk
 * @version 1.0.0
 */
public interface NomenclatureFactory extends GenericFactory<Nomenclature> {

	Nomenclature create(String string);
}
