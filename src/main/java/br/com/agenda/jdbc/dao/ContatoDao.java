package br.com.agenda.jdbc.dao;

import br.com.agenda.jdbc.factory.ConnectionFactory;
import br.com.agenda.jdbc.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContatoDao {
    private final Connection con;

    public ContatoDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void adiciona(Contato contato) {
        String sql = "INSERT INTO contatos (nome, email, telefone, dataCadastro) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getTelefone());
            stmt.setDate(4, new Date(contato.getDataCadastro().getTimeInMillis()));

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contato> getLista() {
        List<Contato> lista = new ArrayList<>();
        String sql = "SELECT * FROM contatos";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setTelefone(rs.getString("telefone"));

                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataCadastro"));
                contato.setDataCadastro(data);

                lista.add(contato);
            }

            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, email = ?, telefone = ?, dataCadastro = ? WHERE id = ?";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getTelefone());
            stmt.setDate(4, new Date(contato.getDataCadastro().getTimeInMillis()));
            stmt.setLong(5, contato.getId());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Contato contato) {
        String sql = "DELETE FROM contatos WHERE id = ?";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setLong(1, contato.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Contato buscaPorId(Long id) {
        String sql = "SELECT * FROM contatos WHERE id = ?";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setTelefone(rs.getString("telefone"));

                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataCadastro"));
                contato.setDataCadastro(data);


                return contato;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}