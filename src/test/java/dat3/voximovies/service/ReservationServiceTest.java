package dat3.voximovies.service;

import dat3.voximovies.dto.ReservationRequest;
import dat3.voximovies.dto.ReservationResponse;
import dat3.voximovies.entity.Reservation;
import dat3.voximovies.entity.Showing;
import dat3.voximovies.entity.User;
import dat3.voximovies.repository.ReservationRepository;
import dat3.voximovies.repository.ShowingRepository;
import dat3.voximovies.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ReservationServiceTest {

  @Autowired
  public ReservationRepository reservationRepository;

  @Autowired
  public UserRepository userRepository;

  @Autowired
  public ShowingRepository showingRepository;

  ReservationService reservationService;

  boolean dataisReady = false;

  @BeforeEach
  void setUp() {
    if(!dataisReady){
      userRepository.saveAndFlush( new User("member1","test12", "dooo", "Benjamin Buttom","22222","kik 12","Smirum","3333"));
      User u1 = new User("member2","test12", "doood", "Doll Buttom","22222","kik 12","Smirum","3333");
      userRepository.saveAndFlush(u1);
      Showing s1 = new Showing(200,new ArrayList<>(),150, LocalDateTime.now());
      showingRepository.save(s1);

      reservationRepository.saveAndFlush(new Reservation(u1,s1,new ArrayList<>(Arrays.asList("a3","a4"))));
      dataisReady = true;
      reservationService = new ReservationService(reservationRepository, userRepository, showingRepository);
       }
  }
  @Test
  void addReservationTest(){ //showing id driller
    String username = "member1";
    ReservationRequest request = new ReservationRequest(2,new ArrayList<>(Arrays.asList("a1","a2")));
    reservationService.addReservation(username,request);
    List<ReservationResponse> reservations = reservationService.getAllUserReservations(username);
    assertEquals(1,reservations.size());
  }


  @Test
  void getUserReservationsTest(){
    String username = "member2";
    List<ReservationResponse> reservations = reservationService.getAllUserReservations(username);
    assertEquals(1,reservations.size());
  }

}