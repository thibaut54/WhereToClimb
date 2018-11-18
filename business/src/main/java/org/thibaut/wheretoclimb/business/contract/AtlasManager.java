package org.thibaut.wheretoclimb.business.contract;

import org.springframework.data.domain.Page;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;

public interface AtlasManager {

	Page< Atlas > getAtlases( int page, int size );

	Page< Atlas > searchAtlas( int page, int size, String keyword );

//	Page<Atlas> searchAtlasByNameAndCountryAndRegionAndDepartment( int page, int size, String typeObject, String name, String country, String region, String department, String city );

	void deleteAtlas( Integer id );

	void saveAtlas( Atlas atlas );

	Atlas findAtlasById( Integer id );

	Atlas findAtlasByName( String name );

	void saveBookingRequest( Integer atlasId, BookingRequest bookingRequest );

	Atlas createAtlas( Atlas atlasToCreate );
}
