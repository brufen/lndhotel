package com.devoteam.bookingapp.repository;

import com.devoteam.bookingapp.entity.RoomEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagableRoomRepository extends PagingAndSortingRepository<RoomEntity,Long> {
    Page<RoomEntity> findById(Long id, Pageable page);
}
