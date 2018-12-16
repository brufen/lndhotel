package com.devoteam.bookingapp.rest;

import com.devoteam.bookingapp.converter.RoomEntityToReservableRoomResponseConverter;
import com.devoteam.bookingapp.entity.ReservationEntity;
import com.devoteam.bookingapp.entity.RoomEntity;
import com.devoteam.bookingapp.model.response.ReservableRoomResponse;
import com.devoteam.bookingapp.model.request.ReservationRequest;
import com.devoteam.bookingapp.model.response.ReservationResponse;
import com.devoteam.bookingapp.repository.PagableRoomRepository;
import com.devoteam.bookingapp.repository.ReservationRepository;
import com.devoteam.bookingapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {
    @Autowired
    PagableRoomRepository pagableRoomRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ConversionService conversionService;

    @RequestMapping(path="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<ReservableRoomResponse>getAvailableRooms(
            @RequestParam(value="checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkIn,
            @RequestParam(value = "checkout")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkOut, Pageable pageable){

       Page <RoomEntity> roomEntityList = pagableRoomRepository.findAll(pageable);

                //new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);

        return  roomEntityList.map(new RoomEntityToReservableRoomResponseConverter());
    }
    @RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomEntity> getRoomById(
            @PathVariable
                    Long roomId) {

        RoomEntity roomEntity = roomRepository.findById(Integer.valueOf(roomId));

        return new ResponseEntity<>(roomEntity, HttpStatus.OK);
    }

    @RequestMapping(path="", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservableRoomResponse> createReservation(
            @RequestBody
                    ReservationRequest reservationRequest) {

        ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
        reservationRepository.save(reservationEntity);

        RoomEntity roomEntity = roomRepository.findById(reservationRequest.getRoomId());
        roomEntity.addReservationEntity(reservationEntity);
        roomRepository.save(roomEntity);
        reservationEntity.setRoomEntity(roomEntity);

        ReservationResponse reservationResponse =
                conversionService.convert(reservationEntity, ReservationResponse.class);


        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }
    @RequestMapping(path = "/{reservationId}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(
            @PathVariable
            Long reservstionId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
