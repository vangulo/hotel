package com.vangulo.hotel.business.service;

import com.vangulo.hotel.business.domain.RoomReservation;
import com.vangulo.hotel.controller.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ReservationServiceIntegrationTests {

    @Autowired
    private ReservationService reservationService;

    @Test
    public void testGetRoomReservationsForDate(){
        String dateString = "2020-01-01";
        Date date = DateUtils.createDateFromDateString(dateString);


        List<RoomReservation> newRoomReservations = reservationService.getRoomReservationsForDate(date);
        RoomReservation newRoomReservation =  newRoomReservations.get(1);

        assertNotNull(newRoomReservations);
        assertNotNull(newRoomReservation);
        assertEquals("Young", newRoomReservation.getLastName());

    }
}
