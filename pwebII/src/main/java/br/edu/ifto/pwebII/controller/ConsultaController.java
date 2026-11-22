package br.edu.ifto.pwebII.controller;

import br.edu.ifto.pwebII.model.entity.Consulta;
import br.edu.ifto.pwebII.model.jdbc.repository.ConsultaRepository;
import br.edu.ifto.pwebII.model.jdbc.repository.MedicoRepository;
import br.edu.ifto.pwebII.model.jdbc.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Transactional
@Controller
@RequestMapping("consulta")
public class ConsultaController {

    @Autowired
    ConsultaRepository repository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("consulta") Consulta consulta,
                             BindingResult result,
                             RedirectAttributes attributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("consulta/form");

            mv.addObject("medicos", medicoRepository.medico());
            mv.addObject("pacientes", pacienteRepository.pacientes());

            return mv;
        }
        if (consulta.getId() == null) {
            repository.save(consulta);
        } else {
            repository.update(consulta);
        }
        return new ModelAndView("redirect:/consulta/list");
    }

    @GetMapping("/remove")
    public String remove(Long id) {
        repository.remove(id);
        return "redirect:/consulta/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("consultas", repository.consultas());
        return "consulta/list";
    }

    @GetMapping("/form")
    public ModelAndView form(Long id) {
        ModelAndView mv = new ModelAndView("consulta/form");
        mv.addObject("consulta", id != null ? repository.consulta(id) : new Consulta());
        mv.addObject("medicos", medicoRepository.medico());
        mv.addObject("pacientes", pacienteRepository.pacientes());
        return mv;
    }

    @GetMapping("/paciente/{id}")
    public String consultasPaciente(@PathVariable Long id, Model model) {
        model.addAttribute("consultas", repository.consultasPorPaciente(id));
        return "consulta/list";
    }

    @GetMapping("/medico/{id}")
    public String consultasMedico(@PathVariable Long id, Model model) {
        model.addAttribute("consultas", repository.consultasPorMedico(id));
        return "consulta/list";
    }
}
