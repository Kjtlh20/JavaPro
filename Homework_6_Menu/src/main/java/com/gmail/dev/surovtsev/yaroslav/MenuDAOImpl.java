package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class MenuDAOImpl implements MenuDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public MenuDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("Menu");
        this.em = emf.createEntityManager();
    }

    @Override
    public Optional<Menu> get(long id) {
        Optional<Menu> result = Optional.empty();
        em.getTransaction().begin();
        try {
            Menu menu = new Menu();
            menu.setId(id);
            result = Optional.ofNullable(em.find(Menu.class, menu));
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public List<Menu> getAll() {
        Query query = em.createQuery("SELECT m FROM Menu m", Menu.class);
        List<Menu> list = (List<Menu>) query.getResultList();
        return list;
    }

    @Override
    public List<Menu> getAll(double priceFrom, double priceTo) {
        Query query = em.createQuery("SELECT m FROM Menu m WHERE m.price BETWEEN :priceFrom and :priceTo", Menu.class);
        query.setParameter("priceFrom", priceFrom);
        query.setParameter("priceTo", priceTo);
        List<Menu> list = (List<Menu>) query.getResultList();
        return list;
    }

    @Override
    public List<Menu> getAll(boolean isDiscountAvailable) {
        Query query = em.createQuery("SELECT m FROM Menu m WHERE m.isDiscountAvailable = :isDiscountAvailable", Menu.class);
        query.setParameter("isDiscountAvailable", isDiscountAvailable);
        List<Menu> list = (List<Menu>) query.getResultList();
        return list;
    }

    @Override
    public List<Menu> getAll(double weightTo) {
        Query query = em.createQuery("SELECT m FROM Menu m HAVING SUM(m.weight) < :weightTo", Menu.class);
        query.setParameter("weightTo", weightTo);
        List<Menu> list = (List<Menu>) query.getResultList();
        return list;
    }

    @Override
    public void add(Menu menu) {
        em.getTransaction().begin();
        try {
            em.persist(menu);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void update(Menu menu) {
        em.getTransaction().begin();
        try {
            em.merge(menu);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Menu menu) {
        Menu menuFromDB = em.getReference(Menu.class, menu.getId());
        if (menuFromDB == null) {
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(menu);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
}
