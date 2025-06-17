package org.bartoszwojcik.hydropol.service.notification;

import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.hydropol.telegram.NotificationBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationBot notificationBot;
    @Value("${telegram.admin.chat.id}")
    private String adminChatId;

    public void notifyYourBoss(String message, String username) {
        String info = "Worker \"" + username + "\" has been sent to you message:\n"
                + username;
        notificationBot.sendMessage(adminChatId, info);
    }

}
