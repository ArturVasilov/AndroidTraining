package ru.arturvasilov.sqlite.query;

/**
 * @author Artur Vasilov
 */
public interface Query {

    <T> QueryList<T> all(Class<T> clazz);

    <T> QueryObject<T> object(Class<T> clazz);
}
