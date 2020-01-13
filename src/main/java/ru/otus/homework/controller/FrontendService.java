package ru.otus.homework.controller;

import ru.otus.homework.controller.messages.Notification;
import ru.otus.homework.controller.messages.Request;
import ru.otus.homework.controller.messages.Response;
import ru.otus.homework.messaging.Addressee;

public interface FrontendService extends Addressee {

    Response handleRequest(Request request);

    void handleMessage(Notification notification);
}
