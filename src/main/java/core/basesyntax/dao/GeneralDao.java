package core.basesyntax.dao;

public interface GeneralDao<T> {
    T save(T entity);
}
