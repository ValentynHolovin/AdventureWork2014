package com.akvelon.server.controller;

import com.akvelon.server.domain.UnitMeasure;
import com.akvelon.server.service.api.UnitMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UnitMeasureController {
    @Autowired
    private UnitMeasureService unitMeasureService;

    @RequestMapping(value = "/get_unitmeasurecode")
    public List<UnitMeasure> getUnitMeasureCodes() {
        return unitMeasureService.getAll();
    }

    @RequestMapping(value = "/unitmeasures/{unitmeasureID}")
    public UnitMeasure getUnitMeasure(@PathVariable("unitmeasureID") String unitmeasureID) {
        return unitMeasureService.read(unitmeasureID);
    }

    @RequestMapping(value = "/unitmeasures/create")
    public String createUnitMeasure(@RequestBody UnitMeasure unitMeasure) {
        return unitMeasureService.create(unitMeasure);
    }

    @RequestMapping(value = "/unitmeasures/update")
    public void updateUnitMeasure(@RequestBody UnitMeasure unitMeasure) {
        unitMeasureService.update(unitMeasure);
    }
}
