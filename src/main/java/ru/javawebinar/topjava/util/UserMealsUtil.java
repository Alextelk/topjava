package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0),
                LocalTime.of(21, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        List<UserMealWithExcess> mealsToWithStreams = filteredByStreams(meals, LocalTime.of(7, 0),
                LocalTime.of(21, 0), 2000);
        mealsToWithStreams.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime
            , LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> factCaloriesPerDay = new HashMap<>();
        meals.forEach(userMeal ->
                factCaloriesPerDay.merge(userMeal.getDate(), userMeal.getCalories(), Integer::sum)
        );

        List<UserMealWithExcess> userMealWithExcess = new ArrayList<>();
        for (UserMeal userMeal : meals) {
            if (TimeUtil.isBetweenHalfOpen(userMeal.getTime(), startTime, endTime)) {
                userMealWithExcess.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(),
                        userMeal.getCalories(), factCaloriesPerDay.get(userMeal.getDate()) > caloriesPerDay));
            }
        }
        return userMealWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime,
                                                             LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> factCaloriesPerDay = meals.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));

        return meals.stream()
                .filter(m -> TimeUtil.isBetweenHalfOpen(m.getTime(), startTime, endTime))
                .map(m ->
                        new UserMealWithExcess(m.getDateTime(), m.getDescription(),
                                m.getCalories(), factCaloriesPerDay.get(m.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
