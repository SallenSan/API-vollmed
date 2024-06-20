package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dadosCadastroMedico) {
        this.nome = dadosCadastroMedico.nome();
        this.email = dadosCadastroMedico.email();
        this.telefone = dadosCadastroMedico.telefone();
        this.crm = dadosCadastroMedico.crm();
        this.especialidade = dadosCadastroMedico.especialidade();
        this.endereco = new Endereco(dadosCadastroMedico.dadosEndereco());

    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        // como atualizacao e algo opcional, se nao tratarmos o que se recebe da dto, teremos o valor nulo
        // pois no json se nao enviarmos os campos, o spring retorna os valores como nulo
        if(dadosAtualizacaoMedico.nome() != null){
            this.nome = dadosAtualizacaoMedico.nome();
        }
        if(dadosAtualizacaoMedico.email() != null){
            this.email = dadosAtualizacaoMedico.email();
        }
        if(dadosAtualizacaoMedico.telefone() != null){
            this.telefone = dadosAtualizacaoMedico.telefone();
        }
        if(dadosAtualizacaoMedico.endereco() != null){
            this.endereco.atualizarEndereco(dadosAtualizacaoMedico.endereco());
        }

    }
}
