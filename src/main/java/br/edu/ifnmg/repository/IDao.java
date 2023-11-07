package br.edu.ifnmg.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface IDao<E> {

    // Save
    public String getSaveStatment();

    public String getUpdateStatment();

    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, E e);

    public Long saveOrUpdate(E e);

    // Get by ID
    public String getFindByIdStatment();

    public E findById(Long id);

    // Get all
    public String getFindAllStatment();

    public List<E> findAll();
    
    // Delete
    public String getDeleteStatement();

    public void delete(Long id);

    // Trash
    public String getMoveToTrashStatement();
    public void moveToTrash(E e);

    public String getRestoreFromTrashStatement();
    public void restoreFromTrash(Long id); // OU T e

    public String getFindAllOnTrashStatement();
    public List<E> findAllOnTrash();

    // Assembly objects
    public E extractObject(ResultSet resultSet);

    public List<E> extractObjects(ResultSet resultSet);

}
