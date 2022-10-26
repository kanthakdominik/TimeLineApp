package pl.kanthak.timelineapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kanthak.timelineapp.model.Album;
import pl.kanthak.timelineapp.service.AlbumService;
import pl.kanthak.timelineapp.util.ApiResponse;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping()
    public ResponseEntity<List<Album>> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbum(@PathVariable("id") Long id) {
        return albumService.getAlbum(id);
    }

    @PostMapping("")
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        return albumService.addAlbum(album);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable("id") Long id, @Valid @RequestBody Album newAlbum){
        return albumService.updateAlbum(id, newAlbum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAlbum(@PathVariable("id") Long id) {
        return albumService.deleteAlbum(id);
    }
}
