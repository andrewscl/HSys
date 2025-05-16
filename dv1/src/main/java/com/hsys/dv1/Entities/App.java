package com.hsys.dv1.Entities;

import java.util.Set;

import com.hsys.dv1.Entities.Company.Client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "app")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class App {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String icon;
    private String route;
    private String color;
    private boolean isPublic;

    @ManyToMany(mappedBy = "apps")
    private Set<Client> clients; //Set garantia que no hayan elementos duplicados.
}
