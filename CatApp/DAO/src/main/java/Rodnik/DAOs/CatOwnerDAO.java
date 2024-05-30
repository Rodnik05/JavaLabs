package Rodnik.DAOs;

import Rodnik.Entities.Cat;
import Rodnik.Entities.CatOwner;
import Rodnik.hibernate.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CatOwnerDAO implements ICatOwnerDAO{
    @Override
    public void saveCatOwner(CatOwner catOwner) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(catOwner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public CatOwner getCatOwnerById(Long id) {
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            return session.get(CatOwner.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCatOwner(CatOwner catOwner) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(catOwner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCatOwner(CatOwner catOwner) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(catOwner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<CatOwner> getAllCatOwners() {
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            return session.createQuery("from CatOwner", CatOwner.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void ownCat(CatOwner catOwner, Cat cat) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            cat.setOwner(catOwner);
            catOwner.getCats().add(cat);
            session.merge(catOwner);
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
    public void removeCat(CatOwner catOwner, Cat cat) {
        Transaction transaction = null;
        try (Session session = SessionFactorySingleton.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            catOwner.getCats().remove(cat);
            cat.setOwner(null);
            session.merge(catOwner);
            session.merge(cat);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
