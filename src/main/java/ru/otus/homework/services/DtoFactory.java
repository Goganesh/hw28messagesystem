package ru.otus.homework.services;

import ru.otus.homework.model.DtoEntity;

import java.util.Map;

public interface DtoFactory {

    DtoEntity entity(String name, Map<String, String> values);
}