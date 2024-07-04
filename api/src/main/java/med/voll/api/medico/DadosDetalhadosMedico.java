package med.voll.api.medico;

import med.voll.api.endereco.Endereco;

// este dto representara os dados atualizados do medico
public record DadosDetalhadosMedico(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhadosMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }

}
