package org.thibaut.wheretoclimb.business.contract;

import org.springframework.data.domain.Page;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;

import java.util.List;

public interface AtlasManager {

	Page< Atlas > getAtlases( int page, int size );

	Page< Atlas > searchAtlas( int page, int size, String keyword );

	void deleteAtlas( Integer id );

	void saveAtlas( Atlas atlas );

	Atlas findAtlasById( Integer id );

	Atlas findAtlasByName( String name );

	List<Atlas> findAtlasesByUserId( Integer userId );

	void saveBookingRequest( Integer atlasId, BookingRequest bookingRequest );

	Atlas createAtlas( Atlas atlasToCreate );
}
