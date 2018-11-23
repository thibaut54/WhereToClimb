package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.BookingRequestManager;
import org.thibaut.wheretoclimb.consumer.repository.BookingRequestRepository;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;

import java.util.Optional;

@Component
public class BookingRequestManagerImpl extends AbstractManager implements BookingRequestManager {

	@Autowired
	BookingRequestRepository bookingRequestRepository;

	@Override
	public void saveBookingRequest( BookingRequest bookingRequest ){
		getDaoFactory().getBookingRequestRepository().save( bookingRequest );
	}


	@Override
	public BookingRequest createBookingRequest( BookingRequest bookingRequest ){
		getDaoFactory().getBookingRequestRepository().save( bookingRequest );
		return bookingRequest;
	}


	@Override
	public BookingRequest findBookingRequestById( Integer id ){

		if(id != null){
			Optional< BookingRequest > bookingRequest =  getDaoFactory().getBookingRequestRepository().findById( id );
			if(bookingRequest.isPresent()){
				return bookingRequest.get();
			}
		}
		return null;
	}


	@Override
	public void deleteBookingRequest( Integer id ){
		getDaoFactory().getBookingRequestRepository().deleteById( id );
	}
}
