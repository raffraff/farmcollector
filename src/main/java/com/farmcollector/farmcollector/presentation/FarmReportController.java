package com.farmcollector.farmcollector.presentation;

import com.farmcollector.farmcollector.core.dto.Season;
import com.farmcollector.farmcollector.core.entity.Farm;
import com.farmcollector.farmcollector.core.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/report")
public class FarmReportController {

    private final FarmService farmService;

    @Autowired
    public FarmReportController(FarmService farmService) {
        this.farmService = farmService;
    }

    /**
     * This will display the web page for the farm reports
     * @param model
     * @param season
     * @return
     */
    @GetMapping("/{season}")
    public String hello(Model model, @PathVariable String season) {
        List<Farm> farms = farmService.farmBySeason(Season.getByName(season))
                .stream()
                .sorted(Comparator.comparing(Farm::getName))
                .collect(Collectors.toList());
        model.addAttribute("farms", farms);
        return "farmReport";
    }
}
