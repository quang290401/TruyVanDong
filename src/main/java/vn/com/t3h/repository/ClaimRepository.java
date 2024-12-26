package vn.com.t3h.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.t3h.dto.request.ClaimRequestFilter;
import vn.com.t3h.entity.ClaimEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaimRepository extends JpaRepository<ClaimEntity,Long> {

    /**
     query bằng native sử dụng sql thông thường
     select * from claim left join claim_status status on claim.status_id = status.id
     where (:claimCode is null or claim.code = :claimCode) and
     (:fromDate is null or claim.claim_date >= :fromDate ) and
     (:toDate is null or claim.claim_date <= :toDate) and
     (:statusCode is null or status.code = :statusCode) and
     (claim.deleted is null or false);
     */
    // sử dụng JPQL query thông qua Entity
    @Query(value = "SELECT claim FROM ClaimEntity claim LEFT JOIN claim.claimStatusEntity status " +
            "WHERE (:#{#filter.claimCode} IS NULL OR claim.code = :#{#filter.claimCode}) AND " +
            "(:#{#filter.fromDateQuery} IS NULL OR claim.claimDate >= :#{#filter.fromDateQuery}) AND " +
            "(:#{#filter.toDateQuery} IS NULL OR claim.claimDate <= :#{#filter.toDateQuery}) AND " +
//            "claim.deleted = false AND " +
            "(:#{#filter.statusCode} IS NULL OR status.code = :#{#filter.statusCode})")
    Page<ClaimEntity> findByFilter(@Param("filter")ClaimRequestFilter filter, Pageable pageable);

    @Query("SELECT c FROM ClaimEntity c WHERE c.customerEntity.id = :customerId")
    List<ClaimEntity> findAllClaimsByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "SELECT * FROM claim WHERE amount > :amount", nativeQuery = true)
    List<ClaimEntity> findClaimsWithAmountGreaterThanNative(@Param("amount") Double amount);

    Page<ClaimEntity> findAllByAmount(Double amount, Pageable pageable);


    @Query("SELECT c FROM ClaimEntity c WHERE c.id = :id")
    Optional<ClaimEntity> findClaimByIdOptional(@Param("id") Long id);

    @Query("SELECT c FROM ClaimEntity c WHERE c.id = :id")
    ClaimEntity findClaimById(@Param("id") Long id);


}
