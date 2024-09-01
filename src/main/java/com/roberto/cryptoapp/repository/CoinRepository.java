package com.roberto.cryptoapp.repository;

import com.roberto.cryptoapp.dto.CoinTransactionDTO;
import com.roberto.cryptoapp.entity.Coin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@EnableAutoConfiguration
public class CoinRepository {

    private EntityManager entityManager;

    public CoinRepository(JdbcTemplate jdbcTemplate) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Coin insert(Coin coin) {

        entityManager.persist(coin);
        return coin;
    }

    @Transactional
    public Coin update(Coin coin) {

        entityManager.merge(coin);
        return coin;
    }

    public List<CoinTransactionDTO> getAll() {
        String jpql = "select new com.roberto.cryptoapp.dto.CoinTransactionDTO(c.name, sum(c.quantity)) from Coin c group by c.name";
        TypedQuery<CoinTransactionDTO> query = entityManager.createQuery(jpql, CoinTransactionDTO.class);
        return query.getResultList();
    }

    public List<Coin> getByName(String name) {
        Object[] attr = new Object[]{ name };
        return jdbcTemplate.query(SELECT_BY_NAME, new RowMapper<Coin>() {
            @Override
            public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {

                Coin coin = new Coin();
                coin.setId(rs.getInt("id"));
                coin.setName(rs.getString("name"));
                coin.setPrice(rs.getBigDecimal("price"));
                coin.setQuantity(rs.getBigDecimal("quantity"));
                coin.setDatetime(rs.getTimestamp("datetime"));

                return coin;
            }
        }, attr);
    }

    public int remove(int id){
        return jdbcTemplate.update(DELETE, id);
    }


}
