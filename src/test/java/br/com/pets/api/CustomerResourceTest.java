package br.com.pets.api;

import br.com.pets.api.exceptionhandler.PetsExceptionHandler;
import br.com.pets.api.model.Customer;
import br.com.pets.api.resource.CustomerResource;
import br.com.pets.api.service.CustomerService;
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
public class CustomerResourceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerResource resource;

    @Mock
    private CustomerService customerService;

    public static String asJson(final Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
    }

    private Customer factory() {
        return Customer
                .builder()
                .name("teste")
                .email("teste@teste.com.br")
                .phone("1199999-9999")
                .build();
    }

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(resource).setControllerAdvice(new PetsExceptionHandler()).build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/customer/all")).andExpect(status().isOk());
    }

    @Test
    public void testFindEmail() throws Exception {
        mockMvc.perform(get("/api/customer/email")
                .param("email", "teste@teste.com.br")).andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(
                post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(factory()))
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(
                put("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(factory()))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/customer/{id}", 1l)).andExpect(status().isOk());
    }
}
