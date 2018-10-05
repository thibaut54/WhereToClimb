package org.thibaut.wheretoclimb.business.contract;

import org.springframework.data.domain.Page;
import org.thibaut.wheretoclimb.model.entity.Area;

public interface AreaManager {
	Page< Area > searchArea( int page, int size, String keyword );
}
