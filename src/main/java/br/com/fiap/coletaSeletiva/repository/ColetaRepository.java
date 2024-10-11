package br.com.fiap.coletaSeletiva.repository;

import br.com.fiap.coletaSeletiva.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {
}
