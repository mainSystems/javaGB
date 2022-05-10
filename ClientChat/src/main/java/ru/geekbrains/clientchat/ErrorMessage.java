package ru.geekbrains.clientchat;

public enum ErrorMessage {
    CANT_SEND_MESSAGE_TO_SERVER("Не возможно отправить сообщение"),
    CANT_CONNECT_TO_SERVER("Не возможно подключиться к серверу"),
    CANT_CLOSE_CONNECT_TO_SERVER("Соединение с сервером не закрыто"),
    ERROR_NETWORK_COMMUNICATION("Ошибка сети"),
    FAILED_TO_GET_MESSAGE("Не удалось получить сообщение"),
    FAILED_TO_BIND_PORT("Порт занят"),
    NO_SUCH_USER("Такого пользователя нет");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
