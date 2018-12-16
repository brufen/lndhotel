package com.devoteam.bookingapp.converter;

import com.devoteam.bookingapp.entity.ReservationEntity;
import com.devoteam.bookingapp.model.request.ReservationRequest;
import org.springframework.core.convert.converter.Converter;

public class ReservationRequestToReservationEntityConverter implements Converter<ReservationRequest, ReservationEntity> {

    @Override
    public ReservationEntity convert(ReservationRequest source) {

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCheckin(source.getCheckIn());
        reservationEntity.setCheckout(source.getCheckOut());
        if (null != source.getId())
            reservationEntity.setId(source.getId());

        return reservationEntity;
    }
}