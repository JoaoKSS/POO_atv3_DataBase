package br.edu.ifnmg.persistencia;

import br.edu.ifnmg.book.Book;
import br.edu.ifnmg.book.BookDao;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author joaok
 */
public class Persistencia {

    public static void main(String[] args) {

        //<editor-fold defaultstate="collapsed" desc="Cria um livro com data de aquisição com 3 dias posteriores ao dia corrente">
        Long bookId = null;
        try {
            Book book = new Book(
                    null, // id
                    "O Senhor dos Anéis", // title
                    "J.R.R. Tolkien", // author
                    LocalDate.now().plusDays(3), // aquisition 
                    (short) 1211, // pages
                    (short) 1954, // year
                    (byte) 1, // edition
                    new BigDecimal("276.27") // price
            );

            System.out.println("Livro 0 incluido: " + book);

            bookId = new BookDao().saveOrUpdate(book);
            book.setId(bookId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Inclui três livros com dados à escolha">
        Long book1Id = null;
        Long book2Id = null;
        Long book3Id = null;

        try {
            Book book1 = new Book(
                    null, // id
                    "A cabana", // title
                    "J.R.R. Tolkien", // author
                    LocalDate.of(2022, 02, 22), // aquisition 
                    (short) 240, // pages
                    (short) 2008, // year
                    (byte) 1, // edition
                    new BigDecimal("43.99") // price
            );

            System.out.println("Livro 1 incluido: " + book1);

            book1Id = new BookDao().saveOrUpdate(book1);
            book1.setId(book1Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Book book2 = new Book(
                    null, // id
                    "Breaking Bad. O Livro Oficial", // title
                    "David Thomson", // author
                    LocalDate.of(2017, 10, 17), // aquisition 
                    (short) 224, // pages
                    (short) 2017, // year
                    (byte) 1, // edition
                    new BigDecimal("56.93") // price
            );

            System.out.println("Livro 2 incluido: " + book2);

            book2Id = new BookDao().saveOrUpdate(book2);
            book2.setId(book2Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Book book3 = new Book(
                    null, // id
                    "Java®: Como Programar", // title
                    " Paul Deitel  & Harvey Deitel", // author
                    LocalDate.of(2023, 07, 01), // aquisition 
                    (short) 968, // pages
                    (short) 2016, // year
                    (byte) 1, // edition
                    new BigDecimal("365.25") // price
            );

            System.out.println("Livro 3 incluido: " + book3);

            book3Id = new BookDao().saveOrUpdate(book3);
            book3.setId(book3Id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Atualizar registro">
        Book book1Aux = new BookDao().findById(book1Id);
        System.out.println("Buscando antigo: " + book1Aux);

        book1Aux.setTitle("POO");
        new BookDao().saveOrUpdate(book1Aux);
        System.out.println(">> O livro foi atulizado");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Recuperar 2 livros a escolha">
        try {
            BookDao buscaid = new BookDao();
            System.out.println("Livro encontrado: " + buscaid.findById(book2Id));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            BookDao buscaid = new BookDao();
            System.out.println("Livro encontrado: " + buscaid.findById(book3Id));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Buscar todos os books">
        try {
            BookDao BuscaBooks = new BookDao();
            System.out.println("Todos Livros: ");
            for (Book e : BuscaBooks.findAll()) {
                System.out.println(">> " + e);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Delete book 3">
        try {
            BookDao book = new BookDao();
            System.out.println("Apagando: ");
            book.delete(book3Id);
        } catch (Exception e) {
            System.out.println(">> " + e.getMessage());
        }
        //</editor-fold>
    }
}
