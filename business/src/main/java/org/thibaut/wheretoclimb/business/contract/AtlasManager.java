package org.thibaut.wheretoclimb.business.contract;

import org.springframework.data.domain.Page;
import org.thibaut.wheretoclimb.model.entity.Atlas;

public interface AtlasManager {

//	public List< Atlas > getAtlases();

	Page< Atlas > getAtlases( int page, int size );

	Page <Atlas> searchAtlas( int page, int size, String keyword );

	void deleteAtlas( Integer id );

	void saveAtlas( Atlas atlas );
}
