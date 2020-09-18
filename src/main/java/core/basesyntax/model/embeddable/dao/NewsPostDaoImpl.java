package core.basesyntax.model.embeddable.dao;

import core.basesyntax.model.HibernateUtil;
import core.basesyntax.model.embeddable.NewsPost;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class NewsPostDaoImpl implements NewsPostDao {
    @Override
    public NewsPost save(NewsPost post) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
            return post;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot insert News post entity - " + post, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<NewsPost> getWithMetadataLargerThan(long size) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<NewsPost> query = session.createQuery(
                    "from NewsPost p where p.metadata.size > :size");
            query.setParameter("size", size);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find News posts with metadata size larger than  - "
                    + size, e);
        }
    }
}
