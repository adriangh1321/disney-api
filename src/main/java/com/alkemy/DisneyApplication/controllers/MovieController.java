package com.alkemy.DisneyApplication.controllers;

import com.alkemy.DisneyApplication.dto.MovieBasicDTO;
import com.alkemy.DisneyApplication.dto.MovieDTO;
import com.alkemy.DisneyApplication.services.MovieService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getAll() {
        List<MovieBasicDTO> movies = this.movieService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(movies);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getDetailsByID(@PathVariable Long id) {
        MovieDTO result = this.movieService.getDetailsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO characterDTO) {
        MovieDTO result = this.movieService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> modify(@PathVariable Long id,@Valid @RequestBody MovieDTO dto) {
       
        MovieDTO result = this.movieService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{idMovie}/character/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long idCharacter, @PathVariable Long idMovie) {
        this.movieService.addCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{idMovie}/character/{idCharacter}")
    public ResponseEntity<Void> removeCharacter(@PathVariable Long idCharacter, @PathVariable Long idMovie) {
        this.movieService.removeCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Set<Long> genres,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieDTO> result = this.movieService.getDetailsByFilters(name, genres, order);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
