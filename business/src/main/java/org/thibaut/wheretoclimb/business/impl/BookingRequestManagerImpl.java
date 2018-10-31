package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.BookingRequestManager;
import org.thibaut.wheretoclimb.consumer.repository.BookingRequestRepository;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;

@Component
public class BookingRequestManagerImpl extends AbstractManager implements BookingRequestManager {

	@Autowired
	BookingRequestRepository bookingRequestRepository;

	@Override
	public void saveBookingRequest( BookingRequest bookingRequest ){
		getDaoFactory().getBookingRequestRepository().save( bookingRequest );
	}

}
