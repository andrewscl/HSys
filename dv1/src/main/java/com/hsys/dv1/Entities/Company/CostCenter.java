package com.hsys.dv1.Entities.Company;

import java.util.List;

import com.hsys.dv1.Entities.HumanResources.Employee;

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
@Table (name="costCenter")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CostCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String CostCenter;

    @OneToMany(mappedBy = "costCenter")
    private List<Employee> employees;

}
