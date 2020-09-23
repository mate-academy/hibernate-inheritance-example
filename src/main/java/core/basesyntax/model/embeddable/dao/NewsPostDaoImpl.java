package core.basesyntax.model.embeddable.dao;

import core.basesyntax.model.embeddable.NewsPost;
import core.basesyntax.model.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NewsPostDaoImpl implements NewsPostDao {
    @Override
    public NewsPost save(NewsPost newsPost) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(newsPost);
            transaction.commit();
            return newsPost;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("There was an error inserting news post "
                    + newsPost, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<NewsPost> findBySizeGreaterThan(long size) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<NewsPost> criteriaQuery
                    = criteriaBuilder.createQuery(NewsPost.class);
            Root<NewsPost> root = criteriaQuery.from(NewsPost.class);
            return session.createQuery(criteriaQuery.where(
                    criteriaBuilder.greaterThan(root.get("metadata").get("size"), size)))
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("There was an error retrieving news posts", e);
        }
    }
}
