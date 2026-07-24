package br.edu.ifrs.osorio.tads.palomalumi.controller;

import br.edu.ifrs.osorio.tads.palomalumi.model.Censo;
import br.edu.ifrs.osorio.tads.palomalumi.model.Leitura;
import br.edu.ifrs.osorio.tads.palomalumi.model.Usuario;
import br.edu.ifrs.osorio.tads.palomalumi.repository.CensoRepository;
import br.edu.ifrs.osorio.tads.palomalumi.repository.LeituraRepository;
import br.edu.ifrs.osorio.tads.palomalumi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
//import org.springframework.security.core.Authentication; // Novo Import
//import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/censo") // Prefixo base: /censo
public class CensoWebController {

    @Autowired
    CensoRepository censoRepository;
    @Autowired
    LeituraRepository leituraRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping // GET /censo
    public String listarCenso(Model model) {
        model.addAttribute("censo", censoRepository.findAll());
        return "censo-lista"; // Removi a barra inicial
    }



    @GetMapping("/novo")
    public String novoCenso(Model model) {
        model.addAttribute("censo", new Censo());
        return "censo-form";
    }

//    @GetMapping("/novo")
//    public String novoCenso(Model model) {
//        Censo novoCenso = new Censo();
//
//        //Inicializa o objeto 'observador' para evitar NullPointerException no HTML
//        novoCenso.setObservador(new Usuario());
//
//        model.addAttribute("censo", novoCenso);
//        model.addAttribute("usuarios", usuarioRepository.findAll());
//        return "censo-form";
//    }

    @PostMapping // POST /censo
    public String salvarCenso(@ModelAttribute Censo censo) {

        // 1. Lógica de Observador (Ajuste para tratar o null)
//        Usuario observadorFixo = usuarioRepository.findById(1L).orElse(null);
//
//        if (observadorFixo != null) {
//            censo.setObservador(observadorFixo);
//        } else {
//            // Se o usuário fixo não existe, retorna com erro ou lança exceção controlada
//            throw new RuntimeException("Observador padrão (ID 1L) não encontrado.");
//        }

        // 2. Garante campos automáticos (Importante, pois o construtor vazio não faz isso)
        if (censo.getData() == null) {
            censo.setData(LocalDate.now());
        }
        if (censo.getHora() == null) {
            censo.setHora(LocalTime.now());
        }

        // 3. Garante a bidirecionalidade (muito importante)
        if (censo.getLeiturasSeguidoras() != null) {
            censo.getLeiturasSeguidoras().forEach(leitura -> leitura.setCenso(censo));
        }
        if (censo.getLeiturasContinuo() != null) {
            censo.getLeiturasContinuo().forEach(leitura -> leitura.setCenso(censo));
        }

        censoRepository.save(censo);
        return "redirect:/censo";
    }

    @GetMapping("/editar/{id}") // GET /censo/editar/X
    public String editarCenso(@PathVariable Long id, Model model) {
        // Uso de orElseThrow() é mais idiomático no Java moderno
        Censo censo = censoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Censo inválido: " + id));

        model.addAttribute("censo", censo);
        return "censo-form";
    }

    @GetMapping("/excluir/{id}") // GET /censo/excluir/X
    public String excluirCenso(@PathVariable Long id) {
        censoRepository.deleteById(id);
        return "redirect:/censo";
    }

    // LISTAGEM DE LEITURAS POR CENSO
    @GetMapping("/leituras/{censoId}") // GET /censo/leituras/X (URL corrigida)
    public String listarLeiturasPorCenso(@PathVariable Long censoId, Model model) {
        // 1. Busca as leituras filtradas pelo ID do Censo
        List<Leitura> leiturasDoCenso = leituraRepository.findByCensoId(censoId);

        // 2. Adiciona a lista e o ID do Censo ao Model
        model.addAttribute("leituras", leiturasDoCenso);
        model.addAttribute("censoId", censoId); // Útil para exibir no cabeçalho

        // 3. Retorna o template de lista de leituras
        return "leitura-lista";
    }

    @GetMapping("/nova-leitura") // GET /censo/nova-leitura
    public String novaLeitura(Model model) {
        model.addAttribute("leitura", new Leitura());
        return "leitura-form";
    }
}