package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.Supplier;
import com.example.hospitalstocks.Repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(UUID supplierId) {
        return supplierRepository.findById(supplierId).orElse(null);
    }
    // Other CRUD operations
}
