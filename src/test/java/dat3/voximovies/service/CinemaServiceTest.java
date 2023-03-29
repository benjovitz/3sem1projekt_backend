package dat3.voximovies.service;

import dat3.voximovies.dto.CinemaRequest;
import dat3.voximovies.dto.CinemaResponse;
import dat3.voximovies.entity.Cinema;
import dat3.voximovies.entity.User;
import dat3.voximovies.repository.CinemaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class CinemaServiceTest {


    @Mock
    CinemaRepository cinemaRepository;

    CinemaService cinemaService;

    @BeforeEach
    void setUp(){
        cinemaService = new CinemaService(cinemaRepository,null,null,null);
    }
    @Test
    void getCinemas(){
        User user1 = new User("Lasse", "passwordUsedByAll", "u,mbjsak", "Lasse Dall", "1234", "Højgade 61", "København S", "2300");
        Cinema c1 = Cinema.builder().owner(user1).build();
        Cinema c2 = Cinema.builder().owner(user1).build();

        Mockito.when(cinemaRepository.findAll()).thenReturn(List.of(c1,c2));
        List<CinemaResponse> cr = cinemaService.getCinemas();

        assertEquals(2,cr.size());
    }
    @Test
    void addCinema(){
        User user1 = new User("Lasse", "aasd", "u,mbjsak", "Lasse Dall", "1234", "Højgade 61", "København S", "2300");
        Cinema c1 = Cinema.builder().owner(user1).name("Daniels Bio").description("God hjemmebio").zip("2000").street("Aurikelvej 6 1 tv").city("Frederiksberg").build();
        Mockito.when(cinemaRepository.save(any(Cinema.class))).thenReturn(c1);

        CinemaRequest cinemaRequest = new CinemaRequest(c1);

        CinemaResponse cinemaResponse = cinemaService.addCinema(cinemaRequest);

        assertEquals("Daniels Bio",cinemaResponse.getName());
    }
}