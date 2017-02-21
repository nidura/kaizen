/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.app.kaizen;

import com.mac.app.kaizen.model.TKaizen;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nidura Prageeth
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class KaizenService {

    @Autowired
    private KaizenRepository kaizenRepository;

    private static final String KAIZEN_PENDING = "PENDING";
    private static final String MANAGER_VIEW = "MANAGER_VIEW";
    private static final String COMMITTEE_VIEW = "COMMITTEE_VIEW";

    public List<TKaizen> allKaisen() {
        return kaizenRepository.findAll();
    }

    public TKaizen saveKazen(TKaizen kaizen) {
        kaizen.setIntroduceDate(new Date());
        kaizen.setReviewStatus(KAIZEN_PENDING);
        return kaizenRepository.save(kaizen);
    }

    public TKaizen kaizenUpdateByManager(TKaizen kaizen) {
        TKaizen kaizen1 = kaizenRepository.findOne(kaizen.getIndexNo());

        kaizen1.setManagerCost(kaizen.getManagerCost());
        kaizen1.setManagerCreativity(kaizen.getManagerCreativity());
        kaizen1.setManagerQuality(kaizen.getManagerQuality());
        kaizen1.setManagerSafety(kaizen.getManagerSafety());
        kaizen1.setManagerUtilization(kaizen.getManagerUtilization());
        kaizen1.setReviewStatus(MANAGER_VIEW);
        return kaizenRepository.save(kaizen1);
    }

    public TKaizen kaizenUpdateByCommittee(TKaizen kaizen) {
        TKaizen kaizen1 = kaizenRepository.findOne(kaizen.getIndexNo());

        kaizen1.setCommitteeCost(kaizen.getCommitteeCost());
        kaizen1.setCommitteeCreativity(kaizen.getCommitteeCreativity());
        kaizen1.setCommitteeQuality(kaizen.getCommitteeQuality());
        kaizen1.setCommitteeSafety(kaizen.getCommitteeSafety());
        kaizen1.setCommitteeUtilization(kaizen.getCommitteeUtilization());
        kaizen1.setReviewStatus(COMMITTEE_VIEW);
        return kaizenRepository.save(kaizen1);
    }

}