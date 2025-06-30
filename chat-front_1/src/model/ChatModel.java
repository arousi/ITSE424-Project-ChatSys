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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class ChatModel extends Observable {
    private final List<String> messages = new ArrayList<>();
    private Set<String> messageHistory = new HashSet<>();

    public void addMessage(String msg) {
        if (messageHistory.add(msg)) { // adds only if new
            messages.add(msg);
            setChanged();
            notifyObservers(msg);
        }
    }
    

    public List<String> getMessages() {
        return messages;
    }
}