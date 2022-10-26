package pl.kanthak.timelineapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.kanthak.timelineapp.service.CoverService;

@RestController
public class CoverController {

    private final CoverService coverService;

    @Autowired
    public CoverController(CoverService coverService) {
        this.coverService = coverService;
    }

    @GetMapping(value = "/covers/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource downloadCover(@PathVariable Long id){
        return coverService.download(id);
    }

    @PostMapping("/covers")
    public Long uploadCover(@RequestParam MultipartFile multipartFile) throws Exception {
        return coverService.upload(multipartFile);
    }
}