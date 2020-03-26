package ru.yksvelobos.appliancecontrollerserver;

/**
 * Task
 *
 * @author Ilia Sobolevsky
 */
public interface Task {
    Task setNext(Task task);

    void execute();
}
