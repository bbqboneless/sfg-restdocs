package guru.springframework.sfgrestdocs.web.controller;

import guru.springframework.sfgrestdocs.repositories.BeerRepository;
import guru.springframework.sfgrestdocs.web.mappers.BeerMapper;
import guru.springframework.sfgrestdocs.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") UUID beerId){


        return new ResponseEntity<>(beerMapper.BeerToBeerDTO(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDTO beerDto){

        beerRepository.save(beerMapper.BeerDTOToBeer(beerDto));

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDTO beerDto){
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        });

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}