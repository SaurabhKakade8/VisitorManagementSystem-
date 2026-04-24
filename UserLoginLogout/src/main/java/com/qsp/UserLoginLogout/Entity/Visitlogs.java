package com.qsp.UserLoginLogout.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Visit_Logs")
public class Visitlogs {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private LocalDate visitDate;
  private LocalTime checkIN;
  private LocalTime checkOut;
  private String status;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;
}