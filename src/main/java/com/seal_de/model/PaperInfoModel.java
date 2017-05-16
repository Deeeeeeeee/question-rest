package com.seal_de.model;

import com.seal_de.domain.PaperDetail;
import com.seal_de.domain.PaperItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sealde on 5/3/17.
 */
public class PaperInfoModel {
    private List<QuestionHeadline> questionHeadline;

    public PaperInfoModel(List<PaperDetail> paperDetails) {
        this.questionHeadline = new LinkedList<QuestionHeadline>();
        for(PaperDetail paperDetail : paperDetails) {
            List<PaperItem> questionList = paperDetail.getPaperItems() != null ?
                    paperDetail.getPaperItems() : new ArrayList<PaperItem>();
            this.questionHeadline.add(new QuestionHeadline(paperDetail.getQuestionType(), questionList));
        }
    }

    public List<QuestionHeadline> getQuestionHeadline() {
        return questionHeadline;
    }

    public void setQuestionHeadline(List<QuestionHeadline> questionHeadline) {
        this.questionHeadline = questionHeadline;
    }

    private class QuestionHeadline {
        private String questionType;
        private List<PaperItem> questionList;

        public QuestionHeadline(String questionType, List<PaperItem> questionList) {
            this.questionType = questionType;
            this.questionList = questionList;
        }

        public String getQuestionType() {
            return questionType;
        }

        public void setQuestionType(String questionType) {
            this.questionType = questionType;
        }

        public List<PaperItem> getQuestionList() {
            return questionList;
        }

        public void setQuestionList(List<PaperItem> questionList) {
            this.questionList = questionList;
        }
    }
}
