package me.engelsergey.recipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String applicationRun() {
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public String getInformation() {

        return "Имя ученика: Энгель Сергей" + "Название проекта: Рецепты." + "Дата созания : 27.02.2023" + "Описание проекта: Приложение для хранения и создания рецептов приготовления блюд";
    }
}
