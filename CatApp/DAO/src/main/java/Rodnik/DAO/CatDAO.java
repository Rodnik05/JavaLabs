package Rodnik.DAO;

import Rodnik.Entity.Cat;
import Rodnik.hibernate.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CatDAO {

    public void saveCat(Cat cat) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Cat getCatById(Long id) {
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            return session.get(Cat.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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


    public List<Cat> getAllFriends(Cat cat) {
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            return cat.getFriends();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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
