package br.edu.ifnmg.book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.edu.ifnmg.repository.Dao;
import br.edu.ifnmg.repository.DbConnection;
import java.util.ArrayList;

public class BookDao extends Dao<Book> {

    public static final String TABLE = "books";

    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE + "(titulo, autor, aquisicao, paginas, ano, edicao, preco)  values (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatment() {
        return "update " + TABLE + " set titulo = ?, autor = ?, aquisicao = ?,"
                + " paginas = ?, ano = ?, edicao = ?, preco = ? WHERE id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Book e) {
        try {
            pstmt.setString(1, e.getTitle());
            pstmt.setString(2, e.getAuthors());
            pstmt.setObject(3, e.getAcquisition(), java.sql.Types.DATE);
            pstmt.setShort(4, e.getPages());
            pstmt.setShort(5, e.getYear());
            pstmt.setByte(6, e.getEdition());
            pstmt.setBigDecimal(7, e.getPrice());

            if (e.getId() != null) {
                pstmt.setLong(8, e.getId());
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFindByIdStatment() {
        return "select id, titulo, autor, aquisicao, paginas, ano, edicao, preco"
                + " from " + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatment() {
        return "select id, titulo, autor, aquisicao, paginas, ano, edicao, preco"
                + " from " + TABLE;
    }
    
    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    @Override
    public Book extractObject(ResultSet resultSet) {

        Book book = null;

        try {
            book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setTitle(resultSet.getString("titulo"));
            book.setAuthors(resultSet.getString("autor"));
            book.setAcquisition(resultSet.getObject("aquisicao", LocalDate.class));
            book.setPages(resultSet.getShort("paginas"));
            book.setYear(resultSet.getShort("ano"));
            book.setEdition(resultSet.getByte("edicao"));
            book.setPrice(resultSet.getBigDecimal("preco"));
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return book;
    }
    
    @Override
public List<Book> extractObjects(ResultSet resultSet) {
    List<Book> books = new ArrayList<>();
    try {
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setTitle(resultSet.getString("titulo"));
            book.setAuthors(resultSet.getString("autor"));
            book.setAcquisition(resultSet.getObject("aquisicao", LocalDate.class));
            book.setPages(resultSet.getShort("paginas"));
            book.setYear(resultSet.getShort("ano"));
            book.setEdition(resultSet.getByte("edicao"));
            book.setPrice(resultSet.getBigDecimal("preco"));
            books.add(book);
        }
    } catch (SQLException ex) {
        Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return books;
}

    @Override
    public void delete(Long id) {

        try (PreparedStatement preparedStatement 
                = DbConnection.getConnection().prepareStatement(getDeleteStatement())) {
        preparedStatement.setLong(1, id);

        System.out.println(">> SQL: " + preparedStatement);

        preparedStatement.executeUpdate();

    } catch (Exception ex) {
        System.out.println("Exception: " + ex);
    }

    }

    @Override
    public String getMoveToTrashStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveToTrash(Book e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getRestoreFromTrashStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void restoreFromTrash(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getFindAllOnTrashStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Book> findAllOnTrash() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
