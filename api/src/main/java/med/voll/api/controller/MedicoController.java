package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico){
        medicoRepository.save(new Medico(dadosCadastroMedico));
    }

    // metodo listar, iremos fazer uma lista de dados do medico, porem e necessario apenas dados como nome, email, crm e especialidade
    // se colocar return list de medicos ele trara varias informacoes como endereco, telefone, e sao informcaoes confidenciais
    // usaremos uma DTO chamada DadosListagemMedico onde convertera o medico para listagem, contendo apenas os dados necessarios
    @GetMapping
    public List<DadosListagemMedico> listar(){
        return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();

    }
}
