package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Provider;
import fr.gorisse.erp.backend.services.ProviderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/provider"))
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    @PostMapping("/add")
    @Transactional
    public Provider create(@RequestBody Provider provider){return this.providerService.create(provider);}
    @PostMapping("/edit")
    @Transactional
    public Provider edit(@RequestBody Provider provider){return this.providerService.create(provider);}

    @GetMapping("/getProviders")
    public List<Provider> create(){return this.providerService.getAll();}

    @GetMapping("/getProviderById")
    public Provider getProviderById(@RequestBody int provider_id){
        return this.providerService.getEntityById(provider_id);
    }
    @DeleteMapping("/deleteById")
    @Transactional
    public Provider deleteById(@RequestBody int provider_id){
        return this.providerService.deleteById(provider_id);
    }
    @DeleteMapping("/delete")
    @Transactional
    public Provider delete(@RequestBody Provider provider)
    {
        this.providerService.delete(provider);
        return provider;
    }
}
