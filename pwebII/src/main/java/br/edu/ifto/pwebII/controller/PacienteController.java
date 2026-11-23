package br.edu.ifto.pwebII.controller;

import br.edu.ifto.pwebII.model.entity.Paciente;
import br.edu.ifto.pwebII.model.jdbc.repository.PacienteRepository;
import br.edu.ifto.pwebII.model.jdbc.repository.PerfilRepository;
import br.edu.ifto.pwebII.model.jdbc.repository.PessoaFisicaRepository;
import br.edu.ifto.pwebII.model.jdbc.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.edu.ifto.pwebII.model.entity.Usuario;
import br.edu.ifto.pwebII.model.jdbc.repository.UsuarioRepository;
import br.edu.ifto.pwebII.model.jdbc.repository.PerfilRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Controller
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @Autowired
    PessoaFisicaRepository pfRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/save")
    public ModelAndView save(
            @Valid
            @ModelAttribute("paciente")Paciente paciente,
            BindingResult result, //guarda problema
            RedirectAttributes attributes,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String senha) {
        if (pfRepository.cpfExiste(
                paciente.getCpf(),
                paciente.getId())) {

                result.rejectValue( "cpf",
                                "cpf.duplicado",
                            "CPF já cadastrado.");
        }

        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("paciente/form");
            mv.addObject("paciente", paciente);
            mv.addObject("login", login);
            return mv;
        }

        if (paciente.getId() == null) {
            repository.save(paciente);

            Usuario usuario = new Usuario();
            usuario.setLogin(login);
            usuario.setPassword(passwordEncoder.encode(senha));
            usuario.setPessoa(paciente);
            usuario.adicionarPerfil(perfilRepository.findByNome("PACIENTE"));

            usuarioRepository.save(usuario);
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
