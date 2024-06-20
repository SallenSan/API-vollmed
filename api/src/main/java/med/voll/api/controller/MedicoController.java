package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<DadosListagemMedico> listar(Pageable paginacao){
        return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico){
        

    }
}
