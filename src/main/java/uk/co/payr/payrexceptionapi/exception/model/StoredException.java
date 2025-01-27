package uk.co.payr.payrexceptionapi.exception.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exceptions")
public class StoredException {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) private String id;
    private String timestamp;
    private String message;
    private String service;
    private String exception;
}