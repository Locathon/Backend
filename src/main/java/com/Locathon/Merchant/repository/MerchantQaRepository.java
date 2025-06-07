package com.Locathon.Merchant.repository;

import com.Locathon.model.MerchantQa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MerchantQaRepository extends JpaRepository<MerchantQa, Long> {
    List<MerchantQa> findByMerchantId(String merchantId);

    MerchantQa findByMerchantIdAndQuestion(String merchantId, String question);
}
