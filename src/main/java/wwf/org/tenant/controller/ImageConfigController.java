package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wwf.org.tenant.entity.ImageConfig;
import wwf.org.tenant.service.ImageConfigService;
import wwf.org.tenant.serviceApi.FormatMessage;

import java.io.IOException;

import static wwf.org.tenant.serviceApi.ImageByteCompress.compressBytes;
import static wwf.org.tenant.serviceApi.ImageByteCompress.decompressBytes;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@RestController
@RequestMapping(value="/image")
public class ImageConfigController {

    @Autowired
    private ImageConfigService imageConfigService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<ImageConfig> getImage(@PathVariable("id") Long id) throws IOException {

        ImageConfig retrievedImage = imageConfigService.getImage(id);

        ImageConfig img = new ImageConfig();
        img.setImage(decompressBytes(retrievedImage.getImage()));
        img.setType(retrievedImage.getType());
        img.setName(retrievedImage.getName());


        return ResponseEntity.ok(img);
    }

    @PostMapping()
    public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

        ImageConfig img = new ImageConfig();
        img.setImage(compressBytes(file.getBytes()));
        img.setType(file.getContentType());
        img.setName(file.getOriginalFilename());

        imageConfigService.createImage(img);

        return ResponseEntity.status(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteImage(@PathVariable("id") Long id){

        Boolean action = imageConfigService.deleteImage(id);

        if ( action){
            return ResponseEntity.ok(action);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}