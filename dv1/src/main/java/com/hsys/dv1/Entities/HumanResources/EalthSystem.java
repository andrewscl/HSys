package com.hsys.dv1.Entities.HumanResources;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="ealthSystem")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EalthSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String EalthSystem;

    @OneToMany(mappedBy = "ealthSystem")
    private List<Employee> employees;

}
