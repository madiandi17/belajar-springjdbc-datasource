package com.wordpress.bmadi.belajar.springjdbc.datasource.dao.impl;

import com.wordpress.bmadi.belajar.springjdbc.datasource.dao.ProdukDao;
import com.wordpress.bmadi.belajar.springjdbc.datasource.model.Produk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("produkDao")
public class ProdukDaoImpl implements ProdukDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public void simpan(Produk p) {
        if (p.getId() == null) {
            try {
                String sql = "INSERT INTO produk (kode, nama, harga) "
                        + "VALUES (?, ?, ?)";

                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, p.getKode());
                ps.setString(2, p.getNama());
                ps.setBigDecimal(3, p.getHarga());
                ps.executeUpdate();

                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdukDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                String sql = "UPDATE produk SET kode=?,  nama=?, harga=? WHERE id=?";
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, p.getKode());
                ps.setString(2, p.getNama());
                ps.setBigDecimal(3, p.getHarga());
                ps.setInt(4, p.getId());
                ps.executeUpdate();

                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(ProdukDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void hapus(Produk p) {
        try {
            String sql = "DELETE FROM produk WHERE id=?";
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdukDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Produk> getById(Integer id) {
        List<Produk> hasil = new ArrayList<Produk>();
        try {
            String sql = "SELECT * FROM produk WHERE id=?";
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produk produks = konvert(rs);
                hasil.add(produks);
            }

            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdukDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    @Override
    public List<Produk> getByNama(String nama) {
        List<Produk> hasil = new ArrayList<Produk>();
        try {

            String sql = "SELECT * FROM produk WHERE nama LIKE ? ORDER BY nama";

            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nama + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produk produk = konvert(rs);
                hasil.add(produk);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdukDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hasil;
    }

    @Override
    public List<Produk> getAll() {
        List<Produk> hasil = new ArrayList<Produk>();
        try {

            String sql = "SELECT * FROM produk";
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produk produk = konvert(rs);
                hasil.add(produk);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdukDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    private Produk konvert(ResultSet rs) throws SQLException {
        Produk p = new Produk();
        p.setId(rs.getInt("id"));
        p.setKode(rs.getString("kode"));
        p.setNama(rs.getString("nama"));
        p.setHarga(rs.getBigDecimal("harga"));
        return p;
    }

}
