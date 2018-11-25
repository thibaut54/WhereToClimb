package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.entity.BookingRequest;

public interface BookingRequestManager {

	void saveBookingRequest( BookingRequest bookingRequest );

	BookingRequest createBookingRequest( BookingRequest bookingRequest );

	BookingRequest findBookingRequestById( Integer id );

	void deleteBookingRequest( Integer id );
}
