package med.voll.api.paciente;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private boolean ativos;

    public Paciente(DadosCadastroPaciente dadosCadastroPaciente) {
        this.ativos = true;
        this.nome = dadosCadastroPaciente.nome();
        this.email = dadosCadastroPaciente.email();
        this.telefone = dadosCadastroPaciente.telefone();
        this.cpf = dadosCadastroPaciente.cpf();
        this.endereco = new Endereco(dadosCadastroPaciente.dadosEndereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dadosAtualizacaoPaciente) {
        if(dadosAtualizacaoPaciente.nome() != null){
            this.nome = dadosAtualizacaoPaciente.nome();
        }
        if(dadosAtualizacaoPaciente.email() != null){
            this.email = dadosAtualizacaoPaciente.email();
        }
        if(dadosAtualizacaoPaciente.telefone() != null){
            this.telefone = dadosAtualizacaoPaciente.telefone();
        }
        if(dadosAtualizacaoPaciente.endereco() != null){
            this.endereco.atualizarEndereco(dadosAtualizacaoPaciente.endereco());
        }
    }

    public void desativar() {
        this.ativos = false;
    }
}
