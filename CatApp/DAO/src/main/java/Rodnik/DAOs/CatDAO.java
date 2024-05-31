package Rodnik.DAOs;

import Rodnik.Entities.Cat;
import Rodnik.hibernate.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CatDAO implements ICatDAO{
    @Override
    public long saveCat(Cat cat) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            long catId = (long) session.save(cat);
            transaction.commit();
            return catId;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public Cat getCatById(Long id) {
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            return session.get(Cat.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void updateCat(Cat cat) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCat(Cat cat) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Cat> getAllFriends(Cat cat) {
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            return cat.getFriends();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addFriend(Cat cat, Cat friend) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            cat.getFriends().add(friend);
            friend.getFriends().add(cat);
            session.merge(cat);
            session.merge(friend);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @Override
    public void removeFriend(Cat cat, Cat friend) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            cat.getFriends().remove(friend);
            friend.getFriends().remove(cat);
            session.merge(cat);
            session.merge(friend);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
