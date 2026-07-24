package br.edu.ifrs.osorio.tads.palomalumi.controller;
import br.edu.ifrs.osorio.tads.palomalumi.model.Observador;
import br.edu.ifrs.osorio.tads.palomalumi.repository.ObservadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/observadores")
public class ObservadorController {

    @Autowired
    private ObservadorRepository observadorRepository;

    @GetMapping("/listar")
    public List<Observador> getAllObservadores() {
        return observadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Observador> getObservadorById(@PathVariable Long id) {
        Optional<Observador> observador = observadorRepository.findById(id);
        return observador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping ("/salvar")
    public Observador createObservador(@RequestBody Observador observador) {
        return observadorRepository.save(observador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Observador> updateObservador(@PathVariable Long id, @RequestBody Observador observadorDetails) {
        Optional<Observador> observador = observadorRepository.findById(id);
        if (observador.isPresent()) {
            Observador existingObservador = observador.get();
            existingObservador.setObservador(observadorDetails);
            return ResponseEntity.ok(observadorRepository.save(existingObservador));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObservador(@PathVariable Long id) {
        if (observadorRepository.existsById(id)) {
            observadorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


