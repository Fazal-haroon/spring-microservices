package com.example.currencyexchangeservice.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Exchange_Value")
public class ExchangeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;
    private BigDecimal conversionMultiple;
    private int port;

    public ExchangeValue() {
    }

    public ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
