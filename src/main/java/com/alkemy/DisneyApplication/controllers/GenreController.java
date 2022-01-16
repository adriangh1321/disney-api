package com.alkemy.DisneyApplication.controllers;

import com.alkemy.DisneyApplication.dto.GenreDTO;
import com.alkemy.DisneyApplication.services.GenreService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genres")
public class GenreController{

    
    private GenreService genreService;
    
    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll() {
        List<GenreDTO> characters = this.genreService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(characters);

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getDetailsByID(@PathVariable Long id) {
        GenreDTO result=this.genreService.getDetailsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
     @PostMapping
    public ResponseEntity<GenreDTO> save(@Valid @RequestBody GenreDTO genreDTO) {
        GenreDTO result = this.genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> modify(@PathVariable Long id ,@Valid @RequestBody GenreDTO dto) {
        
        GenreDTO result=this.genreService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.genreService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    
}
