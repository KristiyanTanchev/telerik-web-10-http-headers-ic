package com.company.web.springdemo.helpers;

import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.BeerDto;
import com.company.web.springdemo.models.User;
import com.company.web.springdemo.repositories.BeerRepository;
import com.company.web.springdemo.services.BeerService;
import com.company.web.springdemo.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {

    private final StyleService styleService;
    private final BeerService beerService;

    @Autowired
    public BeerMapper(StyleService styleService, BeerService beerService) {
        this.styleService = styleService;
        this.beerService = beerService;
    }

    public Beer fromDto(int id, BeerDto dto) {
        Beer beer = fromDto(dto);
        beer.setId(id);
        User creator = beerService.get(id).getCreatedBy();
        beer.setCreatedBy(creator);
        return beer;
    }

    public Beer fromDto(BeerDto dto) {
        Beer beer = new Beer();
        beer.setName(dto.getName());
        beer.setAbv(dto.getAbv());
        beer.setStyle(styleService.get(dto.getStyleId()));
        return beer;
    }

}
