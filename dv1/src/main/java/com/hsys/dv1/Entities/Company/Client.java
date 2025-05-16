package com.hsys.dv1.Entities.Company;

import java.util.Set;

import com.hsys.dv1.Entities.App;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="client")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String client;

    @ManyToMany (fetch = FetchType.EAGER) //Carga los roles junto con el usuario.
    @JoinTable (
        name = "clients_app",
        joinColumns = @JoinColumn(name="client_id"),
        inverseJoinColumns = @JoinColumn(name="app_id")
    )
    private Set<App> apps;
    
}

