package org.thibaut.wheretoclimb.business.contract;

import org.springframework.data.domain.Page;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;

public interface AtlasManager {

//	public List< Atlas > getAtlases();

	Page< Atlas > getAtlases( int page, int size );

	Page< Atlas > searchAtlas( int page, int size, String keyword );

	Page<Atlas> searchAtlasByNameAndCountryAndRegionAndDepartment( int page, int size, String typeObject, String name, String country, String region, String department, String city );

	void deleteAtlas( Integer id );

	void saveAtlas( Atlas atlas );

	Atlas findById( Integer id );

	Atlas findByName( String name );

	void saveBookingRequest( Integer atlasId, BookingRequest bookingRequest );
}
