package ru.otus.homework.dao;

import ru.otus.homework.messaging.Addressee;
import ru.otus.homework.model.DtoEntity;

public interface DbService extends Addressee {

//    <T> List<T> listAll(Class<T> clazz);

    <T extends DtoEntity> T saveOrUpdate(T obj);
//
//    <T> T get(Class<T> dataClass, long id);
}
