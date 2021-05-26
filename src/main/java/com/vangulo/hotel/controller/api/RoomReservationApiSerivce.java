package com.vangulo.hotel.controller.api;

import com.vangulo.hotel.business.domain.RoomReservation;
import com.vangulo.hotel.business.service.ReservationService;
import com.vangulo.hotel.controller.util.DateUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class RoomReservationApiSerivce {
    private final ReservationService reservationService;

    @Autowired
    public RoomReservationApiSerivce(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @ApiOperation(value = "Finds reservations given a date.",
            notes = "Provide a date in yyyy-MM-dd format, if none provided, all reservations returned.",
            response = RoomReservation.class)
    public List<RoomReservation> getRoomReservation(
            @ApiParam(value ="Date of reservation to retrieve in yyyy-MM-dd format.")
            @RequestParam(value="date", required = false) String dateString){
        Date date = DateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }
}
