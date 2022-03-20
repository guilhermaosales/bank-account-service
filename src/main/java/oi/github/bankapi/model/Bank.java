package oi.github.bankapi.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tb_bank")
public class Bank implements Serializable {

    @Id
    @Column(nullable = false, length = 3)
    private String number;
    @Column(nullable = false, length = 60)
    private String name;
}
