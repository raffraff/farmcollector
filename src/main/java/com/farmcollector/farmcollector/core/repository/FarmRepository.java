package com.farmcollector.farmcollector.core.repository;

import com.farmcollector.farmcollector.core.dto.Season;
import com.farmcollector.farmcollector.core.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    Farm findFarmByNameAndCrop(String name, String crop);

    List<Farm> findAllBySeason(Season season);
}
