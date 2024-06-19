package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;


    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente){
        pacienteRepository.save(new Paciente(dadosCadastroPaciente));

    }

    // metodo listar, iremos fazer uma lista de dados do medico, porem e necessario apenas dados como nome, email, crm e especialidade
    // se colocar return list de medicos ele trara varias informacoes como endereco, telefone, e sao informcaoes confidenciais
    // usaremos uma DTO chamada DadosListagemMedico onde convertera o medico para listagem, contendo apenas os dados necessarios
    @GetMapping
    public Page<DadosListagemPaciente> listar(Pageable paginacao){
        return pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);

    }


}
