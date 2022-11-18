package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String complemento;
    private String numero;
    private String uf;
    private String cidade;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.uf = endereco.uf();
        this.cidade = endereco.cidade();
        this.complemento = endereco.complemento();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
    }

    public void atualizarDadosEndereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();

        if(endereco.complemento() != null) this.complemento = endereco.complemento();
        if(endereco.numero() != null) this.numero = endereco.numero();
    }
}
