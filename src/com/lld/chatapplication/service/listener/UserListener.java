package com.lld.chatapplication.service.listener;

import com.lld.chatapplication.model.Message;
import com.lld.chatapplication.model.User;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class UserListener implements Runnable {
    private User user;
    private BlockingQueue<Message> userQueue;

    public UserListener(User user, BlockingQueue<Message> userQueue) {
        this.user = user;
        this.userQueue = userQueue;
    }

    @Override
    public void run() {
        while (true) {
            Message message = null;
            try {
                message = this.userQueue.poll(100, TimeUnit.MILLISECONDS);
                if (message != null) {
                    System.out.println("User " + user.getName() + " received a message: " + message.getMessage());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
