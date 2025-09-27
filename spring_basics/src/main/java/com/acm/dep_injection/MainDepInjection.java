package com.acm.dep_injection;

public class MainDepInjection {
    interface MessageService{
        void send(String message);
    }

    static class EmailService implements MessageService{
        @Override
        public void send(String message) {
            System.out.println("Sending email with message: " + message);
        }
    }

    static class Notification{
        private final MessageService messageService;
        public Notification(MessageService messageService) {
            this.messageService = messageService;
        }
        public void notifyUser(String message){
            messageService.send(message);
        }
    }
    /**
     * Inyección de Dependencias (DI)
     * El código cliente (Notification) no crea la dependencia (EmailService),
     * sino que se la inyecta desde afuera (main).
     * Esto permite cambiar la implementación de MessageService sin modificar Notification.
     * @param args
     */
    public static void main(String[] args) {
        MessageService emailService = new EmailService();
        Notification notification = new Notification(emailService);
        notification.notifyUser("Hello World!");
    }
}
