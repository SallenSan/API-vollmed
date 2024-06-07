package med.voll.api.medico;

import med.voll.api.endereco.DadosEndereco;

public record DadosCadastro(String nome, String email, String crm, Especialidade especialidade, DadosEndereco dadosEndereco   ) {
}
