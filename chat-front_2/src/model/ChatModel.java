/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Sanad
 */

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatModel {
    private final List<String> messages = new ArrayList<>();
    private final Set<String> messageHistory = new HashSet<>();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addMessage(String sender, String timestamp, String content) {
        String formattedMessage = String.format("%-15s %-25s %-25s", sender, content, timestamp);
        if (messageHistory.add(formattedMessage)) { // adds only if new
            messages.add(formattedMessage);
            support.firePropertyChange("message", null, formattedMessage);
        }
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}