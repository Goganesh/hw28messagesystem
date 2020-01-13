package ru.otus.homework.messaging.message.instances;

import ru.otus.homework.dao.DbService;
import ru.otus.homework.messaging.Address;
import ru.otus.homework.messaging.MessageSystem;
import ru.otus.homework.messaging.message.MsgToDb;
import ru.otus.homework.model.DtoEntity;

public class MsgSaveEntity extends MsgToDb {

    private MessageSystem messageSystem;

    private DtoEntity entity;

    private String reqId;

    public MsgSaveEntity(MessageSystem messageSystem, Address from, Address to, DtoEntity entity, String reqId) {
        super(from, to);
        this.messageSystem = messageSystem;
        this.entity = entity;
        this.reqId = reqId;
    }

    @Override
    public void exec(DbService dbService) {
        DtoEntity entity = dbService.saveOrUpdate(this.entity);
        messageSystem.sendMessage(new MsgSendBackIdOnSave(getTo(), getFrom(), reqId, entity));
    }
}
