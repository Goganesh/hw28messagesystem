package ru.otus.homework.messaging.message;

import ru.otus.homework.controller.FrontendService;
import ru.otus.homework.messaging.Address;
import ru.otus.homework.messaging.Addressee;

public abstract class MsgToFrontend extends Message {

    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendService) {
            exec((FrontendService) addressee);
        }
    }

    public abstract void exec(FrontendService frontendService);
}
