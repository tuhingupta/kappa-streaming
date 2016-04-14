
package io.example.cassandra.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import io.example.cassandra.model.TransactionSnapshot;

/**
 * @author Tuhin Gupta
 * 2016 
 */

@Repository
public interface TransactionSnapshotRepo extends CassandraRepository<TransactionSnapshot> {
	
	

}
