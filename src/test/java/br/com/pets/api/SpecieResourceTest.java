package br.com.pets.api;

import br.com.pets.api.exceptionhandler.PetsExceptionHandler;
import br.com.pets.api.model.Specie;
import br.com.pets.api.resource.SpecieResource;
import br.com.pets.api.service.SpecieService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
public class SpecieResourceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SpecieResource resource;

    @Mock
    private SpecieService specieService;

    public static String asJson(final Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
    }

    private Specie factory() {
        return Specie
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
        mockMvc.perform(get("/api/specie/all")).andExpect(status().isOk());
    }

    @Test
    public void testFindName() throws Exception {
        mockMvc.perform(get("/api/specie//name/{name}", "bob")).andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(
                post("/api/specie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(factory()))
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(
                put("/api/specie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(factory()))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/specie/{id}", 1l)).andExpect(status().isOk());
    }
}
