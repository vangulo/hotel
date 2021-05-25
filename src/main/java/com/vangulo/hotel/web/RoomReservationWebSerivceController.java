package com.vangulo.hotel.web;

import com.vangulo.hotel.business.domain.RoomReservation;
import com.vangulo.hotel.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class RoomReservationWebSerivceController {
    private final ReservationService reservationService;

    @Autowired
    public RoomReservationWebSerivceController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<RoomReservation> getRoomReservation(@RequestParam(value="date", required = false) String dateString){
        Date date = DateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }
}
