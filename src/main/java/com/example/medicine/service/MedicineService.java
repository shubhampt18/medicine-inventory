package com.example.medicine.service;

import com.example.medicine.model.Medicine;
import com.example.medicine.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository repository;

    public Medicine addMedicine(Medicine medicine) {
        return repository.save(medicine);
    }

    public List<Medicine> getAllMedicines() {
        return repository.findAll();
    }

    public Optional<Medicine> getMedicineById(Long id) {
        return repository.findById(id);
    }

    public Medicine updateMedicine(Long id, Medicine updatedMedicine) {
        return repository.findById(id).map(medicine -> {
            medicine.setName(updatedMedicine.getName());
            medicine.setManufacturer(updatedMedicine.getManufacturer());
            medicine.setPrice(updatedMedicine.getPrice());
            medicine.setQuantity(updatedMedicine.getQuantity());
            return repository.save(medicine);
        }).orElseThrow(() -> new RuntimeException("Medicine not found!"));
    }

    public void deleteMedicine(Long id) {
        repository.deleteById(id);
    }
}
