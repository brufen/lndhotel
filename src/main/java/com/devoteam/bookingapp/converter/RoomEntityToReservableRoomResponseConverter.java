package com.devoteam.bookingapp.converter;

import com.devoteam.bookingapp.entity.RoomEntity;
import com.devoteam.bookingapp.model.Links;
import com.devoteam.bookingapp.model.Self;
import com.devoteam.bookingapp.model.response.ReservableRoomResponse;
import com.devoteam.bookingapp.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;

public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse>{

    @Override
    public ReservableRoomResponse convert(RoomEntity source) {
        // TODO Auto-generated method stub

        ReservableRoomResponse reservableRoomResponse = new ReservableRoomResponse();
        reservableRoomResponse.setRoomNumber(source.getRoomNumber());
        reservableRoomResponse.setPrice( Integer.valueOf(source.getPrice()) );

        Links links = new Links();
        Self self = new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
        links.setSelf(self);

        reservableRoomResponse.setLinks(links);

        return reservableRoomResponse;
    }



}
