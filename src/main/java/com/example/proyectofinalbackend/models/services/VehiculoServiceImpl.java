package com.example.proyectofinalbackend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.proyectofinalbackend.models.dao.VehiculoDAO;  // Cambiado a VehiculoDAO
import com.example.proyectofinalbackend.models.entities.Vehiculo;  // Cambiado a Vehiculo


@Service
public class VehiculoServiceImpl implements VehiculoService {

	private final VehiculoDAO vehiculoDAO;

	public VehiculoServiceImpl(
			VehiculoDAO vehiculoDAO
	) {
		this.vehiculoDAO = vehiculoDAO;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Vehiculo> findAll() {
		return (List<Vehiculo>) vehiculoDAO.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Vehiculo> findAll(Pageable pageable) {
		return vehiculoDAO.findAll(pageable);
	}

	@Transactional(readOnly = true)
	@Override
	public Vehiculo findOne(Long id) {
		return vehiculoDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void save(Vehiculo vehiculo) {
		vehiculoDAO.save(vehiculo);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		vehiculoDAO.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Long count() {
		return vehiculoDAO.count();
	}

}
