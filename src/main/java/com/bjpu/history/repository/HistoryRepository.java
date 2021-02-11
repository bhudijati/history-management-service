package com.bjpu.history.repository;

import com.bjpu.history.domain.entity.HistoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryTransaction, Long> {

    @Modifying
    @Query(value = "INSERT INTO HISTORY_TRANSACTION" +
            "(ID_HISTORY_TRANSACTION,SOURCE_ACCOUNT " +
            ",BANK_CODE, DEST_ACCOUNT " +
            ",AMOUNT, DESTINATION_ACCOUNT_NAME " +
            ", ADMIN_FEE, REFERENCE_NUMBER, TRANSACTION_STAGE) VALUES " +
            "((SELECT HISTORY_TRX_SEQ.NEXTVAL), :queryParam)", nativeQuery = true)
    void storeTrxHistory(@Param("queryParam") List<Object> objectsParam);

    @Modifying
    @Query(value = "UPDATE HISTORY_TRANSACTION " +
            "SET TRANSACTION_STAGE=?1 WHERE " +
            "REFERENCE_NUMBER=?2 ", nativeQuery = true)
    void updateStageTrxHistory(String transactionStage, String referenceNumber);

    @Query(value = "SELECT * FROM HISTORY_TRANSACTION " +
            "WHERE REFERENCE_NUMBER=?1", nativeQuery = true)
    HistoryTransaction findByReference(String referenceNumber);
}
