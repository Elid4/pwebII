package br.edu.ifto.pwebII.controller;

import br.edu.ifto.pwebII.model.entity.Paciente;
import br.edu.ifto.pwebII.model.jdbc.repository.PacienteRepository;
import br.edu.ifto.pwebII.model.jdbc.repository.PessoaFisicaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Transactional
@Controller
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @Autowired
    PessoaFisicaRepository pfRepository;

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("paciente")Paciente paciente,
                       BindingResult result, //guarda problema
                       RedirectAttributes attributes) {
        if (pfRepository.cpfExiste(
                paciente.getCpf(),
                paciente.getId())) {

                result.rejectValue( "cpf",
                                "cpf.duplicado",
                            "CPF já cadastrado.");
        }

        if (result.hasErrors()) {
            return new ModelAndView("paciente/form");
        }

        if (paciente.getId() == null) {
            repository.save(paciente);
        } else {
            repository.update(paciente);
        }
        return new ModelAndView("redirect:/paciente/list");
    }

    @GetMapping("/remove")
    public String remove(Long id) {
        repository.remove(id);
        return "redirect:/paciente/list";
    }

    // lista
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("pacientes", repository.pacientes());
        return "paciente/list";
    }

    // buscar (novo cadastro se id não informado)
    @GetMapping("/form")
    public ModelAndView form(Long id) {
        ModelAndView mv = new ModelAndView("paciente/form");
        mv.addObject("paciente", id != null ? repository.paciente(id) : new Paciente());
        return mv;
    }

}
