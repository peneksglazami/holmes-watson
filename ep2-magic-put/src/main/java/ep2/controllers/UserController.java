package ep2.controllers;

import ep2.model.User;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/{login}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User getUser(@PathVariable("login") String login) {
        return new User(login, null, null);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User createUser(
            @RequestParam("login") String login,
            @RequestParam("file") MultipartFile uploadedFile) throws IOException {
        return new User(login, uploadedFile.getOriginalFilename(), IOUtils.toByteArray(uploadedFile.getInputStream()));
    }

    @PostMapping(value = "/{login}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User updateUser(
            @PathVariable("login") String login,
            @RequestParam("file") MultipartFile uploadedFile) throws IOException {
        return new User(login, uploadedFile.getOriginalFilename(), IOUtils.toByteArray(uploadedFile.getInputStream()));
    }
}
