package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    @CacheEvict(value = "cacheListagemPacientes", allEntries = true)
    public void cadastrar(@Valid @RequestBody DadosCadastroPaciente dados){ // DTO paciente
        pacienteRepository.save(new Paciente(dados));
    }

    @GetMapping
    @Cacheable(value = "cacheListagemPacientes")
    public Page<DadosListagemPaciente> listagemPacientes(@PageableDefault(sort = {"nome"}) Pageable paginacao){
        return pacienteRepository.findAllByAtivo(paginacao, true).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = "cacheListagemPacientes", allEntries = true)
    public void atualizar(@Valid @RequestBody DadosAtualizacaoPaciente dados){
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarInfo(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "cacheListagemPacientes", allEntries = true)
    public void excluir(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.setAtivo(false);
    }
}
