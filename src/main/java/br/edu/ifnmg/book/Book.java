package br.edu.ifnmg.book;

import br.edu.ifnmg.entity.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Book
        extends Entity {
    
    private String title;
    private String authors;
    private LocalDate acquisition;
    private Short pages;
    private Short year;
    private Byte edition;
    private BigDecimal price;

    public Book() {
    }

    public Book(Long id, String title, String authors, LocalDate acquisition, 
            Short pages, Short year, Byte edition, BigDecimal price) throws Exception{
        setId(id);
        
//        this.title = title;
        setTitle(title);  // Security problem!
        
//        this.authors = authors;
        setAuthors(authors);  // Security problem!
        
//        this.acquisition = acquisition;
        setAcquisition(acquisition);  // Security problem!
        
//        this.pages = pages;
        setPages(pages);  // Security problem!
        
//        this.year = year;
        setYear(year);  // Security problem!
        
//        this.edition = edition;
        setEdition(edition);  // Security problem!
        
//        this.price = price;
        setPrice(price);  // Security problem!
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Titulo nulo");
        } else if (title.length() > 150) {
            throw new IllegalArgumentException("O titulo excede 150 caracteres");
        }

        this.title = title;
    }
    
    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        if (authors == null) {
            throw new IllegalArgumentException("Autor nulo");
        } else if (authors.length() > 250) {
            throw new IllegalArgumentException("O autor excede 250 caracteres");
        }

        this.authors = authors;
    }

    public LocalDate getAcquisition() {
        return acquisition;
    }

    public void setAcquisition(LocalDate acquisition) {
        if (acquisition == null) {
            throw new IllegalArgumentException("Data de aquisiçao nula");
        } else if (acquisition.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de aquisiçao nao pode ser no futuro");
        }

        this.acquisition = acquisition;
    }
    
    public Short getPages() {
        return pages;
    }

    public void setPages(Short pages) {
        if (pages == null) {
            throw new IllegalArgumentException("Numero nulo de paginas");
        } else if (pages < 1) {
            throw new IllegalArgumentException("O numero de paginas deve ser maior que zero");
        }

        this.pages = pages;
    }
    
    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        if (year == null) {
            throw new IllegalArgumentException("Ano nulo");
        } 
//        else if (year < 1) {
//            throw new IllegalArgumentException("O numero do ano deve ser maior que zero");
//        }

        this.year = year;
    }
    
    public Byte getEdition() {
        return edition;
    }

    public void setEdition(Byte edition) {
        if (edition == null) {
            throw new IllegalArgumentException("Ediçao nula");
        } else if (edition < 1) {
            throw new IllegalArgumentException("O numero da edição deve ser maior que zero");
        }

        this.edition = edition;
    }
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Preço nulo");
        } else if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço nao deve ser negativo");
        }

        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" 
                + "title= " + title 
                + ", authors= " + authors 
                + ", acquisition= " + acquisition 
                + ", pages= " + pages 
                + ", year= " + year 
                + ", edition= " + edition 
                + ", price= " + price 
                + '}';
    }

}
