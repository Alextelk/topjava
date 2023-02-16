package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private final MealService service;

    private final Logger log = LoggerFactory.getLogger(getClass());

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id, int userId) {
        log.info("get meal{}", id);
        return service.get(id, userId);
    }

    public Meal create(Meal meal, int userId) {
        log.info("create meal{}", meal);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete meal{}", id);
        service.delete(id, userId);
    }

    public Collection<Meal> getAll(int userId) {
        log.info("getAll meals");
        return service.getAll(SecurityUtil.authUserId());
    }

}