package com.demacs.moviemarket.persistence.dao.postgres;

import com.demacs.moviemarket.persistence.dao.UserDao;
import com.demacs.moviemarket.persistence.model.User;
import com.demacs.moviemarket.persistence.DBManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoPostgres implements UserDao {

    private final Connection conn;

    // Modifica del costruttore per ricevere DBManager
    public UserDaoPostgres(DBManager dbManager) {
        this.conn = dbManager.getConnection();  // Ottieni la connessione tramite DBManager
    }

    @Override
    public User findById(String id) {
        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                long date = rs.getDate("dob").getTime();
                user.setDob(new java.util.Date(date));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                long date = rs.getDate("dob").getTime();
                user.setDob(new java.util.Date(date));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                long date = rs.getDate("dob").getTime();
                user.setDob(new java.util.Date(date));
                users.add(user);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return users;
    }

    @Override
    public Boolean insert(User user) {
        if (findById(user.getId()) == null) {
            String insertStr = "INSERT INTO users (id, name, email, password, dob) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement st = conn.prepareStatement(insertStr)) {
                st.setString(1, user.getId());
                st.setString(2, user.getName());
                st.setString(3, user.getEmail());
                st.setString(4, user.getPassword());
                long date = user.getDob().getTime();
                st.setDate(5, new java.sql.Date(date));
                st.executeUpdate();

                return true;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void update(User user) {
        String updateStr = "UPDATE users SET name = ?, email = ?, password = ?, dob = ? WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(updateStr)) {
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            long date = user.getDob().getTime();
            st.setDate(4, new java.sql.Date(date));
            st.setString(5, user.getId());
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        String deleteStr = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(deleteStr)) {
            st.setString(1, user.getId());
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
