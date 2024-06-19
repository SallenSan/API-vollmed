package med.voll.api.paciente;

import med.voll.api.endereco.DadosEndereco;

public record DadosListagemPaciente(String nome, String email, String telefone) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
    }
}
