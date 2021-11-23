package it.prova.paziente.repository;

public interface DottoreRepository extends PagingAndSortingRepository<Dottore, Long>, JpaSpecificationExecutor<Dottore> {

	public Dottore findByCodiceDipendente(String codiceInput);

}
