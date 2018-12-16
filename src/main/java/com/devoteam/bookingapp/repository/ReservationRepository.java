package com.devoteam.bookingapp.repository;

import com.devoteam.bookingapp.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
}
