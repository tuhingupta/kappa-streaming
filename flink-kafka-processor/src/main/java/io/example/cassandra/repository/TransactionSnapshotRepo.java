
package io.example.cassandra.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import io.example.cassandra.model.TransactionSnapshot;

/**
 * @author Tuhin Gupta
 * 2016 
 */

@Repository
public interface TransactionSnapshotRepo extends CassandraRepository<TransactionSnapshot> {
	
	  String FIND_EVENTS_BY_TYPE = "SELECT * FROM transaction_snapshot WHERE account_number = ?0";

	  @Query(FIND_EVENTS_BY_TYPE)
	  Iterable<TransactionSnapshot> findByType(final String accountNumber);

}
