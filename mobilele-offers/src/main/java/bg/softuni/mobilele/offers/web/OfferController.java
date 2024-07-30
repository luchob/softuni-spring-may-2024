package bg.softuni.mobilele.offers.web;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import bg.softuni.mobilele.offers.service.MonitoringService;
import bg.softuni.mobilele.offers.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
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
@Tag(
    name = "Offers",
    description = "The controller responsible for offer management."
)
public class OfferController {

  private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);
  private final OfferService offerService;
  private final MonitoringService monitoringService;

  public OfferController(OfferService offerService,
      MonitoringService monitoringService) {
    this.offerService = offerService;
    this.monitoringService = monitoringService;
  }

  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "The offer details",
              content = {
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = OfferDTO.class)
                  )
              }
          ),
          @ApiResponse(responseCode = "404", description = "If the offer was not found",
              content = {
                  @Content(
                      mediaType = "application/json"
                  )
              }
          )
      }
  )
  @GetMapping("/{id}")
  public ResponseEntity<OfferDTO> getById(@PathVariable("id") Long id) {
    return ResponseEntity
        .ok(offerService.getOfferById(id));
  }

  @Operation(
      security = @SecurityRequirement(
          name = "bearer-token"
      )
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<OfferDTO> deleteById(@PathVariable("id") Long id,
      @AuthenticationPrincipal UserDetails userDetails) {
    offerService.deleteOffer(userDetails, id);
    return ResponseEntity
        .noContent()
        .build();
  }

  @GetMapping
  public ResponseEntity<PagedModel<OfferDTO>> getAllOffers(
      @AuthenticationPrincipal UserDetails userDetails,
      @PageableDefault(
          size = 3,
          sort="id",
          direction = Direction.DESC
      ) Pageable pageable) {

    monitoringService.increaseOfferSearches();

    return ResponseEntity.ok(
        offerService.getAllOffers(pageable)
    );
  }

  @Operation(
      security = @SecurityRequirement(
          name = "bearer-token"
      )
  )
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
