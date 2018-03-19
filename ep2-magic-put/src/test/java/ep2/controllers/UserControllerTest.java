package ep2.controllers;

import ep2.model.User;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;

import static javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA_TYPE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserControllerTest extends JerseyTest {

    @Override
    public Application configure() {
        ResourceConfig resourceConfig = new ResourceConfig(UserController.class);
        resourceConfig.register(MultiPartFeature.class);
        return resourceConfig;
    }

    @Override
    protected void configureClient(ClientConfig clientConfig) {
        clientConfig.register(MultiPartFeature.class);
    }

    @Test
    public void getUser() {
        Response res = target("/user/1").request().get();
        User user = res.readEntity(User.class);

        assertEquals("1", user.getLogin());
    }

    @Test
    public void createUser() {
        File file = new File(UserControllerTest.class.getClassLoader()
                .getResource("watson.jpg").getFile());
        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", file, new MediaType("image", "jpg"));
        MultiPart entity = new FormDataMultiPart().field("login", "watson").bodyPart(fileDataBodyPart);
        User user = target().path("/user").request()
                .post(Entity.entity(entity, MULTIPART_FORM_DATA_TYPE), User.class);

        assertEquals("watson", user.getLogin());
        assertEquals("watson.jpg", user.getFilename());
        assertNotNull(user.getPhoto());
    }

    @Test
    public void updateUser() {
        File file = new File(UserControllerTest.class.getClassLoader()
                .getResource("holmes.jpg").getFile());
        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", file, new MediaType("image", "jpg"));
        MultiPart entity = new FormDataMultiPart().bodyPart(fileDataBodyPart);
        User user = target().path("/user/holmes").request()
                .put(Entity.entity(entity, MULTIPART_FORM_DATA_TYPE), User.class);

        assertEquals("holmes", user.getLogin());
        assertEquals("holmes.jpg", user.getFilename());
        assertNotNull(user.getPhoto());
    }
}