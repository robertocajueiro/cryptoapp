package com.roberto.cryptoapp.controller;

import com.roberto.cryptoapp.entity.Coin;
import com.roberto.cryptoapp.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/coin")
public class CoinController {

    @Autowired
    private CoinRepository coinRepository;

    @GetMapping
    public ResponseEntity get() {
        return new ResponseEntity<>(coinRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity get(@PathVariable String name) {
        try {
            return new ResponseEntity<>(coinRepository.getByName(name), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Coin coin) {
        try {
           coin.setDatetime(new Timestamp(System.currentTimeMillis()));
           return new ResponseEntity<>(coinRepository.insert(coin), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity put(@RequestBody Coin coin) {
        try {
            coin.setDatetime(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(coinRepository.update(coin), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        boolean response = false;
        try {
            response = coinRepository.remove(id);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

//    @Bean
//    public Coin init(){
//        Coin c1 = new Coin();
//        c1.setName("BITCOIN");
//        c1.setPrice(new BigDecimal("100"));
//        c1.setQuantity(new BigDecimal("0.0005"));
//        c1.setDatetime(new Timestamp(System.currentTimeMillis()));
//
//        Coin c2 = new Coin();
//        c2.setName("BITCOIN");
//        c2.setPrice(new BigDecimal("150"));
//        c2.setQuantity(new BigDecimal("0.0025"));
//        c2.setDatetime(new Timestamp(System.currentTimeMillis()));
//
//        Coin c3 = new Coin();
//        c3.setName("ETHEREUM");
//        c3.setPrice(new BigDecimal("500"));
//        c3.setQuantity(new BigDecimal("0.0045"));
//        c3.setDatetime(new Timestamp(System.currentTimeMillis()));
//
//        coinRepository.insert(c1);
//        coinRepository.insert(c2);
//        coinRepository.insert(c3);
//
//        return c1;
//    }

}
