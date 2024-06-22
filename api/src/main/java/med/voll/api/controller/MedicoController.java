package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public void criar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico){
        medicoRepository.save(new Medico(dadosCadastroMedico));
    }

    // metodo listar, iremos fazer uma lista de dados do medico, porem e necessario apenas dados como nome, email, crm e especialidade
    // se colocar return list de medicos ele trara varias informacoes como endereco, telefone, e sao informcaoes confidenciais
    // usaremos uma DTO chamada DadosListagemMedico onde convertera o medico para listagem, contendo apenas os dados necessarios
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        //return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new); //este metodo retornara todos os usuarios do banco, como queremos trazer os que estao ativos somente modificaremos a listagem
       return medicoRepository.findAllByAtivosTrue(paginacao).map(DadosListagemMedico::new);// aqui criamos um metodo na medico repository onde retornara somente os usuarios ativos no banco

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico){
        var medico = medicoRepository.getReferenceById(dadosAtualizacaoMedico.id());
        medico.atualizarInformacoes(dadosAtualizacaoMedico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        //medicoRepository.deleteById(id); //este metodo ira deletar do banco de dados completamente o ususario, no exercicio queremos
        // apenas desativar o usuario e na o apagar o mesmo de fato

        var medico = medicoRepository.getReferenceById(id);
        medico.desativar();

    }

}
