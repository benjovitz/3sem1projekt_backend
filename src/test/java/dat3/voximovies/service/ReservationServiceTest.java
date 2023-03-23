package dat3.voximovies.service;

import dat3.voximovies.dto.ReservationRequest;
import dat3.voximovies.dto.ReservationResponse;
import dat3.voximovies.entity.*;
import dat3.voximovies.repository.*;
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

  @Autowired
  public CinemaRepository cinemaRepository;

  @Autowired
  public MovieRepository movieRepository;

  ReservationService reservationService;

  boolean dataisReady = false;

  @BeforeEach
  void setUp() {
    if(!dataisReady){
      userRepository.saveAndFlush( new User("member1","test12", "dooo", "Benjamin Buttom","22222","kik 12","Smirum","3333"));
      User u1 = new User("member2","test12", "doood", "Doll Buttom","22222","kik 12","Smirum","3333");
      userRepository.saveAndFlush(u1);

      Cinema c1 = Cinema.builder().owner(u1).name("Daniels Bio").description("God hjemmebio").zip("2000").street("Aurikelvej 6 1 tv").city("Frederiksberg").build();
      cinemaRepository.saveAndFlush(c1);

      Movie m1 = new Movie("Up", 1.20, "feel good family movie", "family");
      movieRepository.saveAndFlush(m1);

      Showing s1 = new Showing(m1,c1,150, LocalDateTime.now());
      showingRepository.save(s1);

      reservationRepository.saveAndFlush(new Reservation(u1,s1,new ArrayList<>(Arrays.asList("a3","a4"))));
      dataisReady = true;
      reservationService = new ReservationService(reservationRepository, userRepository, showingRepository);
       }
  }
  @Test
  void addReservationTest(){ //showing id driller
    String username = "member1";
    ReservationRequest request = new ReservationRequest(2L,new ArrayList<>(Arrays.asList("a1","a2")));
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