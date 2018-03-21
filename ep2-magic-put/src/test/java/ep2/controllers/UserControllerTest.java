package ep2.controllers;

import ep2.config.ApplicationConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationConfig.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getUser() throws Exception {
        mockMvc.perform(get("/user/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", is("1")))
                .andDo(print());
    }

    @Test
    public void createUser() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "watson.jpg", "image/jpg",
                UserControllerTest.class.getClassLoader()
                        .getResource("watson.jpg").openStream());

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/user")
                .file(file)
                .param("login", "watson"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", is("watson")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filename", is("watson.jpg")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photo", notNullValue()));
    }

    @Test
    public void updateUser() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "holmes.jpg", "image/jpg",
                UserControllerTest.class.getClassLoader()
                        .getResource("holmes.jpg").openStream());
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/user/holmes")
                .file(file))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", is("holmes")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filename", is("holmes.jpg")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.photo", notNullValue()));
    }
}