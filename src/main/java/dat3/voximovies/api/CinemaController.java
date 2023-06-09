package dat3.voximovies.api;

import dat3.voximovies.dto.CinemaRequest;
import dat3.voximovies.dto.CinemaResponse;
import dat3.voximovies.service.CinemaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/cinema/")
public class CinemaController {

    CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService){
        this.cinemaService=cinemaService;
    }

    @GetMapping
    List<CinemaResponse> getCinemas(){
        return cinemaService.getCinemas();
    }

    @GetMapping("{id}")
    public CinemaResponse getSpecificCinema(@PathVariable Long id){
        return cinemaService.findCinema(id);
    }
    /*
    @PatchMapping("ranking/{id}/{rating}")
    ResponseEntity<Boolean> addRating(@PathVariable Double rating,@PathVariable Long id){
        return cinemaService.addRating(id,rating);
    }
     */

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CinemaResponse addCinema(@RequestBody CinemaRequest request,Principal p){
        return cinemaService.addCinema(request,p.getName());
    }
 
    @PreAuthorize("hasAuthority('CINEMATICER')")
    @DeleteMapping("/{id}")
    void deleteCinema(@PathVariable Long id, Principal p){
        cinemaService.deleteCinema(id,p.getName());
    }
    
    @PreAuthorize("hasAuthority('CINEMATICER')")
    @PutMapping("/{id}")
    CinemaResponse editCinema(@PathVariable Long id, @RequestBody CinemaRequest request){
        return cinemaService.editCinema(id, request);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit")
    List<CinemaResponse> findCinemaByUsername(Principal p){
        return cinemaService.getCinemasByUsername(p.getName());
    }

}
