package com.alkemy.DisneyApplication.controllers;

import com.alkemy.DisneyApplication.dto.CharacterBasicDTO;
import com.alkemy.DisneyApplication.dto.CharacterDTO;
import com.alkemy.DisneyApplication.services.CharacterService;
import com.alkemy.DisneyApplication.services.MovieService;
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
@RequestMapping("characters")
public class CharacterController {

    private CharacterService characterService;
    private MovieService movieService;

    @Autowired
    public CharacterController(CharacterService characterService, MovieService movieService) {
        this.characterService = characterService;
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getAll() {
        List<CharacterBasicDTO> characters = this.characterService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(characters);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsByID(@PathVariable Long id) {
        CharacterDTO result = this.characterService.getDetailsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@Valid @RequestBody CharacterDTO characterDTO) {
        CharacterDTO result = this.characterService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> modify(@PathVariable Long id, @Valid @RequestBody CharacterDTO dto) {

        CharacterDTO result = this.characterService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{idCharacter}/movie/{idMovie}")
    public ResponseEntity<Void> addMovie(@PathVariable Long idCharacter, @PathVariable Long idMovie) {
        this.movieService.addCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{idCharacter}/movie/{idMovie}")
    public ResponseEntity<Void> removeMovie(@PathVariable Long idCharacter, @PathVariable Long idMovie) {
        this.movieService.removeCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<CharacterDTO> result = this.characterService.getDetailsByFilters(name, age, movies, order);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
