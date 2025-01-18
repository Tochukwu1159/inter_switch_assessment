package com.interswitch.assessment.service;

import com.interswitch.assessment.dtos.StatementRequest;
import com.interswitch.assessment.dtos.StatementResponse;

public interface StatementService {
    StatementResponse getAccountStatement(StatementRequest request);
}
