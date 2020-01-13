package ru.otus.homework.services;

import org.springframework.stereotype.Service;
import ru.otus.homework.model.DtoEntity;
import ru.otus.homework.model.User;

import java.util.Map;

@Service
public class DtoFactoryImpl implements DtoFactory {

    @Override
    public DtoEntity entity(String name, Map<String, String> values) {
        if ("user".equals(name)) {
            User user = new User();
            user.setName(values.get("fio"));
            user.setAge(Integer.valueOf(values.get("age")));
            return user;
        } else {
            throw new IllegalArgumentException("unknown entity");
        }
    }
}
