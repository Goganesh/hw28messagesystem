package ru.otus.homework.messaging.message;

import ru.otus.homework.dao.DbService;
import ru.otus.homework.messaging.Address;
import ru.otus.homework.messaging.Addressee;

public abstract class MsgToDb extends Message {

    public MsgToDb(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DbService) {
            exec((DbService) addressee);
        }
    }

    public abstract void exec(DbService dbService);
}
