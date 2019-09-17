package br.com.unifacef.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.com.unifacef.api.entities.Empresa;
import br.com.unifacef.api.repositories.EmpresaRepository;

@SpringBootApplication
public class MeuPrimeiroProjetoSpringApplication {
	@Autowired
	private EmpresaRepository empresaRepository;

	public static void main(String[] args) {
		SpringApplication.run(MeuPrimeiroProjetoSpringApplication.class, args);
	}

@Bean
public CommandLineRunner commandLineRunner() {
return args -> {
	Empresa empresa = new Empresa();
	empresa.setRazaoSocial("Depto de Computação");
	empresa.setCnpj("456");
	this.empresaRepository.save(empresa);
	List<Empresa> empresas = empresaRepository.findAll();
	empresas.forEach(System.out::println);
	
	Empresa empresaDb = empresaRepository.findById(1L).orElse(null);
	System.out.println("Empresa por ID: " + empresaDb);
	
	empresaDb.setRazaoSocial("Uni-FACEF Centro Universitário");
	this.empresaRepository.save(empresaDb);
	
	Empresa empresaCnpj = empresaRepository.findByCnpj("123");
	System.out.println("Empresa por CNPJ: " + empresaCnpj);
	
	this.empresaRepository.delete(empresaCnpj);
	empresas = empresaRepository.findAll();
	empresas.forEach(System.out::println);
};
}
}