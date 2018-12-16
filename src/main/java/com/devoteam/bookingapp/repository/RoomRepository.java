package com.devoteam.bookingapp.repository;


import com.devoteam.bookingapp.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;


public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    RoomEntity findById();
    }
