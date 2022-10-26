package pl.kanthak.timelineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.kanthak.timelineapp.model.Cover;
import pl.kanthak.timelineapp.repository.CoverRepository;

@Service
public class CoverService {

    private final CoverRepository coverRepository;

    @Autowired
    public CoverService(CoverRepository imageRepository) {
        this.coverRepository = imageRepository;
    }

    public Long upload(MultipartFile multipartFile) throws Exception{
        Cover image = new Cover();
        image.setName(multipartFile.getName());
        image.setType(multipartFile.getContentType());
        image.setData(multipartFile.getBytes());
        return coverRepository.save(image).getId();
    }

    public Resource download(Long id){
        byte[] image = coverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getData();
        return new ByteArrayResource(image);
    }

}
