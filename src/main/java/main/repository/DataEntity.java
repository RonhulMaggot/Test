package main.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test")
public class DataEntity {
    @Id
    private String name;
    @Column(name = "\"value\"")
    private int value;
}
