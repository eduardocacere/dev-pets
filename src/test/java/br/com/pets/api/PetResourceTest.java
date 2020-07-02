package br.com.pets.api;

import br.com.pets.api.exceptionhandler.PetsExceptionHandler;
import br.com.pets.api.model.Pet;
import br.com.pets.api.resource.PetResource;
import br.com.pets.api.service.PetService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
public class PetResourceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PetResource resource;

    @Mock
    private PetService petService;

    public static String asJson(final Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
    }

    private Pet factory() {
        return Pet
                .builder()
                .name("teste")
                .build();
    }

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(resource).setControllerAdvice(new PetsExceptionHandler()).build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/pet/all")).andExpect(status().isOk());
    }

    @Test
    public void testFindEmail() throws Exception {
        mockMvc.perform(get("/api/pet/name/{name}", "bob")).andExpect(status().isOk());
    }

    @Test
    public void testFindBreed() throws Exception {
        mockMvc.perform(get("/api/pet/breed/{id}", 1l)).andExpect(status().isOk());
    }

    @Test
    public void testFindSpecie() throws Exception {
        mockMvc.perform(get("/api/pet/specie/{id}", 1l)).andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(
                post("/api/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(factory()))
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(
                put("/api/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(factory()))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/pet/{id}", 1l)).andExpect(status().isOk());
    }
}
