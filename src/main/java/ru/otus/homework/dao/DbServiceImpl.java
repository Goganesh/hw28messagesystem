package ru.otus.homework.dao;

import org.springframework.stereotype.Service;
import ru.otus.homework.messaging.Address;
import ru.otus.homework.messaging.MessageSystem;
import ru.otus.homework.model.DtoEntity;
import ru.otus.homework.model.User;
import ru.otus.homework.repositories.UserRepository;

import javax.annotation.PostConstruct;

@Service
public class DbServiceImpl implements DbService {

    private final Address ADDRESS = new Address("DB");

    private final MessageSystem messageSystem;

    private UserRepository userRepository;

    public DbServiceImpl(MessageSystem messageSystem,
                         UserRepository userRepository) {
        this.messageSystem = messageSystem;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        messageSystem.addAddressee(ADDRESS, this);
    }

    @Override
    public <T extends DtoEntity> T saveOrUpdate(T obj) {
        if (obj.getClass() == User.class) {
            return (T) userRepository.save((User) obj);
        }
        throw new IllegalArgumentException("Unknown type to handle: " + obj.getClass());
    }

    @Override
    public Address getAddress() {
        return ADDRESS;
    }

}
