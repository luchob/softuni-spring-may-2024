package bg.softuni.mobilele.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilele.model.enums.EngineTypeEnum;
import bg.softuni.mobilele.service.OfferService;
import java.util.List;
import org.springframework.security.test.context.support.WithMockUser;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
    username = "pesho",
    roles = {"USER", "ADMIN"}
)
public class OfferControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OfferService offerService;

  @Test
  public void createNewOffer() throws Exception {

    when(offerService.createOffer(any(AddOfferDTO.class)))
        .thenReturn(new OfferDetailsDTO(
            1L,
            "description",
            11,
            11,
            EngineTypeEnum.DIESEL,
            List.of()
        ));

    mockMvc.perform(post("/offers/add")
        .contentType("application/json")
            .with(csrf())
            .param("description", "description")
            .param("mileage", "11")
            .param("price", "23")
            .param("engineType", "DIESEL")
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string("Location", "/offers/1"));
  }

}
