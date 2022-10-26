package pl.kanthak.timelineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.kanthak.timelineapp.exception.ResourceNotFoundException;
import pl.kanthak.timelineapp.model.Album;
import pl.kanthak.timelineapp.repository.AlbumRepository;
import pl.kanthak.timelineapp.util.ApiResponse;

import java.util.List;

@Service
public class AlbumService {

    private static final String ALBUM = "Album";
    private static final String ID = "Id";

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();

        if (albums.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No albums");
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    public ResponseEntity<Album> getAlbum(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ALBUM, ID, id));
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    public ResponseEntity<Album> addAlbum(Album album) {
        Album newAlbum = albumRepository.save(album);
        return new ResponseEntity<>(newAlbum, HttpStatus.CREATED);
    }

    public ResponseEntity<Album> updateAlbum(Long id, Album newAlbum) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ALBUM, ID, id));
        album.setTitle(newAlbum.getTitle());
        album.setDate(newAlbum.getDate());
        album.setText(newAlbum.getText());
        album.setDescription(newAlbum.getDescription());

        Album updatedAlbum = albumRepository.save(album);
        return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> deleteAlbum(Long id) {
        albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ALBUM, ID, id));
        albumRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "Album deleted"), HttpStatus.OK);
    }
}
