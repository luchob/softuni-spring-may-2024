package bg.softuni.mobilele.offers.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bg.softuni.mobilele.offers.repository.OfferRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
    username = "pesho",
    roles = {"USER", "ADMIN"}
)
public class OfferControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private OfferRepository offerRepository;

  @Test
  public void createOffer() throws Exception {
    MvcResult result = mockMvc.perform(post("/offers")
        .contentType("application/json")
        .content("""
                {
                    "description": "Test offer 2",
                    "mileage": 20000,
                    "price": 220,
                    "engineType": "DIESEL"
                }
            """
        ))
        .andExpect(status().isCreated())
        .andReturn();    ;

    Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
    // Add assertions
    Assertions.assertEquals(1, offerRepository.count());

  }
}
