package core.basesyntax.dao;

public interface ObjectDao<T extends Object> {
    T save(T item);
}
