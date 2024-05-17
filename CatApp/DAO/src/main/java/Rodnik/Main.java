package Rodnik;

import Rodnik.DAO.CatDAO;
import Rodnik.Entity.Cat;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Cat a = new Cat();
        a.setName("aboba");
        CatDAO b = new CatDAO();
        b.saveCat(a);
    }
}