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
@Table (name="pensionSystem")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PensionSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String PensionSystem;

    @OneToMany(mappedBy = "pensionSystem")
    private List<Employee> employees;

}
