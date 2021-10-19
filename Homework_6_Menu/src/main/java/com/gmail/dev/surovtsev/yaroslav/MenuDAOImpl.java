package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.*;
import java.util.ArrayList;
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
        return Optional.ofNullable(em.find(Menu.class, id));
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
        List<Menu> list = getAll();
        List<Menu> resList = new ArrayList<>();
        double currWeight = 0.0;
        for (Menu dish: list) {
            currWeight += dish.getWeight();
            if (currWeight < weightTo) {
                resList.add(dish);
            }
        }
        return resList;
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
