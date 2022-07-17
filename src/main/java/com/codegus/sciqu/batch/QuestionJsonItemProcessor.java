package com.codegus.sciqu.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.codegus.sciqu.entity.QuestionChoiceEntity;
import com.codegus.sciqu.entity.QuestionEntity;
import com.codegus.sciqu.model.QuestionJson;
import com.codegus.sciqu.repository.QuestionRepository;

public class QuestionJsonItemProcessor implements ItemProcessor<QuestionJson, List<QuestionChoiceEntity>> {

	@Autowired
	QuestionRepository questionRepository;
	
	@Override
	public List<QuestionChoiceEntity> process(QuestionJson item) throws Exception {
		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setQuestion(item.getQuestion());
		int id = questionRepository.save(questionEntity).getQuestionId();
		List<QuestionChoiceEntity> choiceList = new ArrayList<>();
		for(int i = 0 ; i <= 3 ; i++) {
			QuestionChoiceEntity choice = new QuestionChoiceEntity();
			choice.setQuestionId(id);
			switch(i) {
			case 0 :
				choice.setChoice(item.getOptionA());
				break;
			case 1 :
				choice.setChoice(item.getOptionB());
				break;
			case 2 :
				choice.setChoice(item.getOptionC());
				break;
			case 3 :
				choice.setChoice(item.getOptionD());
			}
			choice.setIsCorrect(item.getOptionD().equals(choice.getChoice()) ? true : false);
			choiceList .add(choice);
		}
		return choiceList;
	}

}
