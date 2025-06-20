package org.bartoszwojcik.hydropol.model.enums;

/**
 * Enum określający możliwe role użytkowników w systemie.
 *
 * <ul>
 *   <li>{@link #OWNER} - właściciel systemu z najwyższymi uprawnieniami.</li>
 *   <li>{@link #WORKER} - pracownik realizujący zadania.</li>
 *   <li>{@link #USER} - zwykły użytkownik systemu.</li>
 * </ul>
 */
public enum UserRole {
    OWNER,
    WORKER,
    USER,
}
