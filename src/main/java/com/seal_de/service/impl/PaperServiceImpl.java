package com.seal_de.service.impl;

import com.seal_de.Dao.PaperRepository;
import com.seal_de.domain.Paper;
import com.seal_de.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sealde on 4/26/17.
 */
@Service
public class PaperServiceImpl extends AbstractServiceImpl<PaperRepository, Paper> implements PaperService {
    @Autowired
    public PaperServiceImpl(PaperRepository repository) {
        this.repository = repository;
    }
}
