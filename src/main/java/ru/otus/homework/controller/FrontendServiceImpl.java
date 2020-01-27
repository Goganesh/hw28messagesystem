package ru.otus.homework.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.homework.controller.messages.Notification;
import ru.otus.homework.controller.messages.Request;
import ru.otus.homework.controller.messages.Response;
import ru.otus.homework.dao.DbService;
import ru.otus.homework.messaging.Address;
import ru.otus.homework.messaging.MessageSystem;
import ru.otus.homework.messaging.message.instances.MsgSaveEntity;
import ru.otus.homework.model.DtoEntity;
import ru.otus.homework.services.DtoFactory;
import javax.annotation.PostConstruct;

@Controller
public class FrontendServiceImpl implements FrontendService {

    private final Address ADDRESS = new Address("FE");

    private MessageSystem messageSystem;

    private DbService dbService;

    private DtoFactory dtoFactory;

    private SimpMessagingTemplate template;

    public FrontendServiceImpl(MessageSystem messageSystem,
                               DbService dbService,
                               DtoFactory dtoFactory,
                               SimpMessagingTemplate messagingTemplate) {
        this.messageSystem = messageSystem;
        this.dbService = dbService;
        this.dtoFactory = dtoFactory;
        this.template = messagingTemplate;
    }

    @PostConstruct
    public void init() {
        messageSystem.addAddressee(ADDRESS, this);
    }

    @MessageMapping("/message")
    @SendTo("/topic/response")
    @Override
    public Response handleRequest(Request request) {
        DtoEntity entity = dtoFactory.entity(request.getEntity(), request.getContent());
        switch (request.getCommand()) {
            case "create":
                messageSystem.sendMessage(
                        new MsgSaveEntity(messageSystem, this.getAddress(), dbService.getAddress(),
                                entity, request.getRequestId()));
                return new Response(0, "ok");
            default:
                return new Response(1, "unknown command");
        }
    }

    @Override
    public void handleMessage(Notification notification) {
        sentToClient(notification);
    }

    public void sentToClient(Notification notification) {
        template.convertAndSend("/topic/notification", notification);
    }

    @Override
    public Address getAddress() {
        return ADDRESS;
    }
}
