
package com.company.domain.configdata.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.company.domain.configdata.entity.CityInfo;

/**
 * Repository to fetch City Information from Database
 * 
 * @author sumit.bhardwaj
 *
 */
public interface CityInfoRepository extends PagingAndSortingRepository<CityInfo, String> {
	
	@Query("select c.city from CityInfo c")
	public Stream<String> getAllCities();
	
	

}
