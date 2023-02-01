import React from 'react';

type QuestionItemType = {
  title: string;
  ques: string;
  ans: string;
  state: string;
};

const QuestionContainerItem: QuestionItemType[] = [
  {
    title: '잠시만두는 왜 키보드가 필요한가요?',
    ques: '잠시만두는 왜 키보드가 필요한가요?',
    ans: '모름띠',
    state: '대기중',
  },
  {
    title: '잠시만두는 왜 마우스를 샀나요?',
    ques: '잠시만두는 왜 마우스를 샀나요?',
    ans: '모름띠',
    state: '답변 완료',
  },
];

export default QuestionContainerItem;
