package com.seal_de.dao;

import com.seal_de.Dao.PaperDetailRepository;
import com.seal_de.Dao.PaperItemRepository;
import com.seal_de.domain.PaperDetail;
import com.seal_de.domain.PaperItem;
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
public class PaperItemRepositoryTest {
    @Autowired
    private PaperItemRepository paperItemRepository;
    @Autowired
    private PaperDetailRepository paperDetailRepository;

    @Test
    @Transactional
    public void getByPaperDetailIdAndChildIndex() {
        PaperItem paperItem = paperItemRepository.getByPaperDetailIdAndChildIndex(
                "ff8081815c06b949015c06be4e870005", 1);
        System.out.println(paperItem);
    }

    @Test
    @Transactional
    public void findByPaperDetailId() {
        List<PaperItem> list = paperItemRepository.findByPaperDetailId("ff8081815c06b949015c06be4e870005");
        System.out.println(list);
    }

    @Test
    @Transactional
    public void delete() {
        PaperDetail paperDetail = paperDetailRepository.getById("ff8081815c06b949015c06be4e870005");
        PaperItem paperItem = paperItemRepository.getById("ff8081815c06b949015c06be4e870006");
        paperItemRepository.clear();
        paperItemRepository.delete(paperItem);
        System.out.println("success");
    }
}
