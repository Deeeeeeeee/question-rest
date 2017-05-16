package com.seal_de.dao;

import com.seal_de.Dao.PaperDetailRepository;
import com.seal_de.domain.PaperDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sealde on 5/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback(false)
public class PaperDetailRepositoryTest {
    @Autowired
    private PaperDetailRepository paperDetailRepository;

    @Test
    @Transactional
    public void findByPaperId() {
        List<PaperDetail> list = paperDetailRepository.findByPaperId("ff8081815c06b949015c06be4e6f0003");
        System.out.println(list);
    }
}
