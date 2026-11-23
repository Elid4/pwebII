package br.edu.ifto.pwebII.controller;

import br.edu.ifto.pwebII.model.entity.Medico;
import br.edu.ifto.pwebII.model.jdbc.repository.MedicoRepository;
import br.edu.ifto.pwebII.model.jdbc.repository.PessoaFisicaRepository;
import br.edu.ifto.pwebII.model.jdbc.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Transactional
@Controller
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    MedicoRepository repository;
    @Autowired
    PessoaFisicaRepository pfRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/save")
    public ModelAndView save(@Valid
                             @ModelAttribute("medico") Medico medico,
                             BindingResult result,
                             RedirectAttributes attributes,
                             @RequestParam(required = false) String login,
                             @RequestParam(required = false) String senha) {
        if (pfRepository.cpfExiste(
                medico.getCpf(),
                medico.getId())) {

            result.rejectValue( "cpf",
                    "cpf.duplicado",
                    "CPF já cadastrado.");
        }
        if (login == null || login.isBlank()) {
            result.reject("login.obrigatorio", "Informe o login.");
        } else if (usuarioRepository.findByLogin(login).isPresent()) {
            result.reject("login.duplicado", "Esse login já está cadastrado.");
        }

        if (senha == null || senha.isBlank()) {
            result.reject("senha.obrigatoria", "Informe a senha.");
        }


        for (Medico m : repository.medico()) {
            if(medico.getCrm() != null){
                if (m.getCrm().equals(medico.getCrm())
                        && !m.getId().equals(medico.getId())) {

                    result.rejectValue("crm", "crm.duplicado",
                            "CRM já cadastrado.");

                    break;
                }
            }
        }


        if (result.hasErrors()) {
            return new ModelAndView("medico/form");
        }

        if (medico.getId() == null) {
            repository.save(medico);
        } else {
            repository.update(medico);
        }
        return new ModelAndView("redirect:/medico/list");
    }

    @GetMapping("/remove")
    public String remove(Long id) {
        repository.remove(id);
        return "redirect:/medico/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("medicos", repository.medico());
        return "medico/list";
    }

    @GetMapping("/form")
    public ModelAndView form(Long id) {
        ModelAndView mv = new ModelAndView("medico/form");
        mv.addObject("medico", id != null ? repository.medico(id) : new Medico());
        return mv;
    }
}
