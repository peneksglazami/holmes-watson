package ep2.controllers;

import ep2.model.User;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;

@Path("/user")
public class UserController {

    @GET
    @Path("{login: \\w+}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("login") String login) {
        return new User(login, null, null);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(
            @FormDataParam("login") String login,
            @FormDataParam("file") InputStream uploadedFile,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
        return new User(login, fileDetail.getFileName(), IOUtils.toByteArray(uploadedFile));
    }

    @PUT
    @Path("{login: \\w+}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(
            @PathParam("login") String login,
            @FormDataParam("file") InputStream uploadedFile,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
        return new User(login, fileDetail.getFileName(), IOUtils.toByteArray(uploadedFile));
    }
}
