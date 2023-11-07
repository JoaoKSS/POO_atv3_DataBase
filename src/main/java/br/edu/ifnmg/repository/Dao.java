package br.edu.ifnmg.repository;

import br.edu.ifnmg.entity.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Dao<E>
        implements IDao<E> {

    public static final String DB = "biblioteca";

    @Override
    public Long saveOrUpdate(E e) {

        // Primary key
        Long id = 0L;

        if (((Entity) e).getId() == null
                || ((Entity) e).getId() == 0) {

            // Insert a new register
            // try-with-resources
            try (PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(
                            getSaveStatment(),
                            Statement.RETURN_GENERATED_KEYS)) {

                // Assemble the SQL statement with the data (->?)
                composeSaveOrUpdateStatement(preparedStatement, e);

                // Show the full sentence
                System.out.println(">> SQL: " + preparedStatement);

                // Performs insertion into the database
                preparedStatement.executeUpdate();

                // Retrieve the generated primary key
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                // Moves to first retrieved data
                if (resultSet.next()) {

                    // Retrieve the returned primary key
                    id = resultSet.getLong(1);
                }

            } catch (Exception ex) {
                System.out.println(">> " + ex);
            }

        } else {
            // Update existing record
            try (PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(
                            getUpdateStatment())) {

                // Assemble the SQL statement with the data (->?)
                composeSaveOrUpdateStatement(preparedStatement, e);

                // Show the full sentence
                System.out.println(">> SQL: " + preparedStatement);

                // Performs the update on the database
                preparedStatement.executeUpdate();

                // Keep the primary key
                id = ((Entity) e).getId();

            } catch (Exception ex) {
                System.out.println("Exception: " + ex);
            }
        }

        return id;
    }

    @Override
    public E findById(Long id) {

        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(
                        getFindByIdStatment())) {

            // Assemble the SQL statement with the id
            preparedStatement.setLong(1, id);

            // Show the full sentence
            System.out.println(">> SQL: " + preparedStatement);

            // Performs the query on the database
            ResultSet resultSet = preparedStatement.executeQuery();

            // Returns the respective object if exists
            if (resultSet.next()) {
                return extractObject(resultSet);
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        return null;
    }

    @Override
    public List<E> findAll() {

        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(
                        getFindAllStatment())) {

            // Show the full sentence
            System.out.println(">> SQL: " + preparedStatement);

            // Performs the query on the database
            ResultSet resultSet = preparedStatement.executeQuery();

            // Returns the respective object
            return extractObjects(resultSet);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        return null;
    }

    @Override
    public void delete(Long id) {

        // Validate the ID
        if (id == null) {
            throw new IllegalArgumentException("The ID cannot be null.");
        }

        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(getDeleteStatement())) {

            // Assemble the SQL statement with the ID
            preparedStatement.setLong(1, id);

            // Show the full sentence
            System.out.println(">> SQL: " + preparedStatement);

            // Performs the deletion on the database
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
