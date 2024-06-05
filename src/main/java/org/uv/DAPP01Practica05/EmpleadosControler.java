/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package org.uv.DAPP01Practica05;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;

/**
 *
 * @author krpcc
 */

 @RestController
 @RequestMapping("/api/v1")
 @CrossOrigin(origins = "*")
 public class EmpleadosControler {
 
     @Autowired
     private EmpleadoRepository repositoryEmpleado;
     
     @GetMapping("/empleado")
     public List<Empleado> list() {
         return repositoryEmpleado.findAll();
     }
     
     @GetMapping("/empleado/{id}")
     public ResponseEntity<Empleado> get(@PathVariable Long id) {
         Optional<Empleado> optempleado = repositoryEmpleado.findById(id);
         return optempleado.map(empleado -> ResponseEntity.ok().body(empleado))
                           .orElseGet(() -> ResponseEntity.notFound().build());
     }
     
     @PutMapping("/empleado/{id}")
     public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Empleado empleado) {
         if (!repositoryEmpleado.existsById(id)) {
             return ResponseEntity.notFound().build();
         }
         empleado.setClave(id);
         repositoryEmpleado.save(empleado);
         return ResponseEntity.noContent().build();
     }
     
     @PostMapping("/empleado")
     public ResponseEntity<Empleado> post(@RequestBody Empleado empleado) {
         Empleado savedEmpleado = repositoryEmpleado.save(empleado);
         return ResponseEntity.status(HttpStatus.CREATED).body(savedEmpleado);
     }
     
     @DeleteMapping("/empleado/{id}")
     public ResponseEntity<?> delete(@PathVariable Long id) {
         if (!repositoryEmpleado.existsById(id)) {
             return ResponseEntity.notFound().build();
         }
         repositoryEmpleado.deleteById(id);
         return ResponseEntity.noContent().build();
     }
 }
 
