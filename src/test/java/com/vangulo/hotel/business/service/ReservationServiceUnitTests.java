package com.vangulo.hotel.business.service;

import com.vangulo.hotel.business.domain.RoomReservation;
import com.vangulo.hotel.controller.util.DateUtils;
import com.vangulo.hotel.data.entity.Guest;
import com.vangulo.hotel.data.entity.Reservation;
import com.vangulo.hotel.data.entity.Room;
import com.vangulo.hotel.data.repository.GuestRepository;
import com.vangulo.hotel.data.repository.ReservationRepository;
import com.vangulo.hotel.data.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ReservationServiceUnitTests {

    @Mock
    ReservationRepository reservationRepository ;
    @Mock
    GuestRepository guestRepository;
    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void testGetRoomReservationsForDate(){

        String dateString = "2020-01-01";
        Date date = DateUtils.createDateFromDateString(dateString);

        ArrayList<Room> mockRooms = new ArrayList<>();
        Room room = new Room();
        room.setRoomId(1L);
        room.setRoomName("Bedroom");
        room.setRoomNumber("10");
        mockRooms.add(room);

        Guest mockGuest = new Guest();
        mockGuest.setGuestId(12L);
        mockGuest.setFirstName("John");
        mockGuest.setLastName("Smith");

        ArrayList<Reservation> mockReservations = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setReservationId(123L);
        reservation.setRoomId(1L);
        reservation.setGuestId(12L);
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        reservation.setReservationDate(dateSql);
        mockReservations.add(reservation);

        when(guestRepository.findById(anyLong())).thenReturn(java.util.Optional.of(mockGuest));
        when(roomRepository.findAll()).thenReturn(mockRooms);
        when(reservationRepository.findReservationByReservationDate(any(java.sql.Date.class))).thenReturn(mockReservations);

        List<RoomReservation> newRoomReservations = reservationService.getRoomReservationsForDate(date);
        assertEquals("John",newRoomReservations.get(0).getFirstName());
        verify(guestRepository).findById(anyLong());
        verify(roomRepository).findAll();
        verify(reservationRepository).findReservationByReservationDate(any(java.sql.Date.class));
    }

    @Test
    public void testGetHotelGuests(){
        Guest mockGuest = new Guest();
        mockGuest.setGuestId(12L);
        mockGuest.setFirstName("John");
        mockGuest.setLastName("Smith");

        List<Guest> mockGuests = new ArrayList<>();

        mockGuests.add(mockGuest);

        when(guestRepository.findAll()).thenReturn(mockGuests);

        List<Guest> newGuests = reservationService.getHotelGuests();

        assertEquals("John",newGuests.get(0).getFirstName());
        assertEquals("Smith",newGuests.get(0).getLastName());
        assertEquals(12L,newGuests.get(0).getGuestId());
        verify(guestRepository).findAll();
    }

}
