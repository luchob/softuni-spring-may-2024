package bg.softuni.mobilele.offers.web;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import bg.softuni.mobilele.offers.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/offers")
@Tag(name = "Offers", description = "The offer API.")
public class OfferController {

  private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);
  private final OfferService offerService;

  public OfferController(OfferService offerService) {
    this.offerService = offerService;
  }

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation",
          content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = OfferDTO.class))
      }),
      @ApiResponse(responseCode = "404", description = "If the book was not found")
  })
  @GetMapping("/{id}")
  public ResponseEntity<OfferDTO> getById(@PathVariable("id") Long id) {
    return ResponseEntity
        .ok(offerService.getOfferById(id));
  }

  @PreAuthorize("@offerService.isOwner(#id, #userDetails)")
  @DeleteMapping("/{id}")
  public ResponseEntity<OfferDTO> deleteById(@PathVariable("id") Long id,
      @AuthenticationPrincipal UserDetails userDetails) {
    offerService.deleteOffer(id);
    return ResponseEntity
        .noContent()
        .build();
  }

  @GetMapping
  public ResponseEntity<List<OfferDTO>> getAllOffers(@AuthenticationPrincipal UserDetails userDetails) {

    if (userDetails != null) {
      System.out.println("Logged in user: " + userDetails.getUsername());
      System.out.println("Roles: " + userDetails.getAuthorities().stream().toList());
    }
    return ResponseEntity.ok(
        offerService.getAllOffers()
    );
  }

  @Operation(security = { @SecurityRequirement(name = "bearer-key") })
  @PostMapping
  public ResponseEntity<OfferDTO> createOffer(
      @RequestBody AddOfferDTO addOfferDTO
  ) {
    LOGGER.info("Going to create an offer {}", addOfferDTO);

    OfferDTO offerDTO = offerService.createOffer(addOfferDTO);
    return ResponseEntity.
        created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(offerDTO.id())
                .toUri()
        ).body(offerDTO);
  }

}
