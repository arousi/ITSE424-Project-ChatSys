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

// Factory Pattern
import java.time.LocalDateTime;

public class MessageFactory {
    public static String createMessage(String sender, String content) {
        return "[" + sender + "] " + content + " (" + LocalDateTime.now() + ")";
    }
}