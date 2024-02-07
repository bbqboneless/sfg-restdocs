package guru.springframework.sfgrestdocs.web.mappers;

import guru.springframework.sfgrestdocs.domain.Beer;
import guru.springframework.sfgrestdocs.web.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface BeerMapper {
    BeerDTO BeerToBeerDTO(Beer beer);
    Beer BeerDTOToBeer(BeerDTO beerDTO);
}
