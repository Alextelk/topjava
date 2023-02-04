package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.MealsUtil.meals;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private final MealsUtil mealsUtil = new MealsUtil();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        List<MealTo> mealsList = mealsUtil.getMealTo(meals, CALORIES_PER_DAY);
        request.setAttribute("mealsList", mealsList);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
