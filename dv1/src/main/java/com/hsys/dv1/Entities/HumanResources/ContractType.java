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
@Table (name="contractType")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ContractType;

    @OneToMany(mappedBy = "contractType")
    private List<Employee> employees;

}
