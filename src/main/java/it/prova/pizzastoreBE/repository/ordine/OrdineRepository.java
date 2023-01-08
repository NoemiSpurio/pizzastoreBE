package it.prova.pizzastoreBE.repository.ordine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastoreBE.model.Cliente;
import it.prova.pizzastoreBE.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, CustomOrdineRepository {

	@Query("from Ordine o left join fetch o.pizze left join fetch o.cliente left join fetch o.fattorino where o.id = ?1")
	Optional<Ordine> findByIdEager(Long id);

	@Query("select coalesce(sum(o.costoTotale), 0) from Ordine o where o.data between ?1 and ?2")
	Integer ricaviTotaliTra(LocalDate dataInizio, LocalDate dataFine);

	Integer countByDataBetween(LocalDate dataInizio, LocalDate dataFine);

	@Query(value = "select count(op.pizza_id) from ordine_pizza op join ordine o on o.id = op.ordine_id where o.data between ?1 and ?2", nativeQuery = true)
	Integer countPizzeOrderedBetween(LocalDate dataInizio, LocalDate dataFine);

	@Query("select distinct c from Ordine o join o.cliente c where c is not null and o.costoTotale > 100 and o.data between ?1 and ?2")
	List<Cliente> findAllClientiVirtuosiBetween(LocalDate dataInizio, LocalDate dataFine);
	
	@Query("from Ordine o join o.fattorino f where o.closed = false and f.id =?1")
	List<Ordine> findAllOrdiniApertiPerFattorino(Long idFattorino);
}
