package app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AddCarsUseCaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addCarsWorksThroughAllLayers() throws Exception {
        String bookInJson = "{ \"brand\": \"Toyota\", \"model\": \"Camry\", \"year\": 2010, \"month\": 1, \"engine\": 2.0, \"turbo\": true, \"power\": 150, \"transmission_type\" : \"AUTOMATIC\", \"drive_type\" : \"FRONT\", \"body_type\" : \"SEDAN\", \"color\": \"red\" }";

        mockMvc.perform(post("/api/cars")
                .contentType("application/json")
                .content(bookInJson))
                .andExpect(status().isOk());
    }
}
