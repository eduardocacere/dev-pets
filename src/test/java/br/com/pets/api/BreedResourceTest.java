package br.com.pets.api;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.pets.api.exceptionhandler.PetsExceptionHandler;
import br.com.pets.api.model.Breed;
import br.com.pets.api.resource.BreedResource;
import br.com.pets.api.service.BreedService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
public class BreedResourceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BreedResource resource;

    @Mock
    private BreedService breedService;

    public static String asJson(final Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
    }

    private Breed factory() {
        return Breed.builder().name("Buldoge").build();
    }

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(resource).setControllerAdvice(new PetsExceptionHandler()).build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/breed/all")).andExpect(status().isOk());
    }

    @Test
    public void testFindName() throws Exception {
        mockMvc.perform(get("/api/breed//name/{name}", "bob")).andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(
                post("/api/breed")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJson(factory()))
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(
                put("/api/breed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(factory()))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/breed/{id}", 1l)).andExpect(status().isOk());
    }



}
