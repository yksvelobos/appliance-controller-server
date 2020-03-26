package ru.yksvelobos.appliancecontrollerserver;

/**
 * Strategy of work
 *
 * @param <T> Entity fo process
 */
public interface Strategy<T> {
    void execute(T entity);
}
