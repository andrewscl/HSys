package com.hsys.dv1.Entities.HumanResources;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.hsys.dv1.Entities.Company.Company;
import com.hsys.dv1.Entities.Company.CostCenter;
import com.hsys.dv1.Entities.Company.Project;
import com.hsys.dv1.Entities.Configuration.Bank;
import com.hsys.dv1.Entities.Location.Comuna;
import com.hsys.dv1.Entities.Location.Region;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    private String rut;
    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate birthDate;
    private String address;
    private Long phone;
    private String mail;
    private Long accountBankNumber;
    private String shirtSize;
    private Long pantsSize;
    private String shoeSize;

    @ManyToOne
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "pensionSystem_id")
    private PensionSystem pensionSystem;

    @ManyToOne
    @JoinColumn(name = "ealthSystem_id")
    private EalthSystem ealthSystem;

    @ManyToOne
    @JoinColumn(name = "employeeStatus_id")
    private EmployeeStatus employeeStatus;
    
    @ManyToOne
    @JoinColumn(name = "jobTitle_id")
    private JobTitle jobTitle;

    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private Nationality nationality;

    @ManyToOne
    @JoinColumn(name = "workSchedule_id")
    private WorkSchedule workSchedule;

    @ManyToOne
    @JoinColumn(name = "scheduleType_id")
    private ScheduleType scheduleType;

    @ManyToOne
    @JoinColumn(name = "contractType_id")
    private ContractType contractType;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "costCenter_id")
    private CostCenter costCenter;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "educationLevel_id")
    private EducationLevel educationLevel;

    @ManyToOne
    @JoinColumn(name = "maritalStatus_id")
    private MaritalStatus maritalStatus;

    @ManyToOne
    @JoinColumn(name = "paymentMethod_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

}
