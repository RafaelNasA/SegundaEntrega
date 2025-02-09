package com.connectjob.servicesImpl;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connectjob.model.Empresa;
import com.connectjob.model.Vaga;
import com.connectjob.repositories.EmpresaRepository;
import com.connectjob.repositories.VagaRepository;
import com.connectjob.services.EmpresaServices;
import com.connectjob.services.VagaServices;

import jakarta.persistence.EntityManager;

@Service
public class VagaServiceImpl implements VagaServices {

	@Autowired
	private VagaRepository vagaRepository;
	
	
	@Override
	public List<Vaga> getAllVaga() {
		return vagaRepository.findAll();
	}

	@Override
	public Vaga saveVaga(Vaga vaga) {
		return vagaRepository.save(vaga);
	}

	@Override
	public Vaga updateVaga(Long id, Vaga vagaAtualizada) {
		Vaga vagaExistente = vagaRepository.findById(id).orElse(null);
		if (vagaExistente != null) {
			vagaExistente.setTitulo(vagaAtualizada.getTitulo());
			vagaExistente.setDescricao(vagaAtualizada.getDescricao());
			vagaExistente.setModalidade(vagaAtualizada.getModalidade());
			vagaExistente.setNivel(vagaAtualizada.getNivel());
			vagaExistente.setQuantidade(vagaAtualizada.getQuantidade());
			vagaExistente.setTipoContrato(vagaAtualizada.getTipoContrato());
			vagaExistente.setUf(vagaAtualizada.getUf());
			vagaExistente.setDataCadastro(vagaAtualizada.getDataCadastro());
			return vagaRepository.save(vagaExistente);
		} else {
			throw new RuntimeException("Vaga com o ID " + id + "não encontrada.");
		}
	}

	@Override
	public void deleteVaga(Long id) {
		vagaRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Vaga getVagaById(Long id) {
		return vagaRepository.findById(id).orElse(null);
	}


}
