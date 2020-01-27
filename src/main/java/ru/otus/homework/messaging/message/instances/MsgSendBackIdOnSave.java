package ru.otus.homework.messaging.message.instances;

import ru.otus.homework.controller.FrontendService;
import ru.otus.homework.controller.messages.NewEntityNotification;
import ru.otus.homework.messaging.Address;
import ru.otus.homework.messaging.message.MsgToFrontend;
import ru.otus.homework.model.DtoEntity;

public class MsgSendBackIdOnSave extends MsgToFrontend {

    private String reqId;

    private DtoEntity entity;

    public MsgSendBackIdOnSave(Address from, Address to, String reqId, DtoEntity entity) {
        super(from, to);
        this.reqId = reqId;
        this.entity = entity;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.handleMessage(new NewEntityNotification(reqId, entity));
    }
}
