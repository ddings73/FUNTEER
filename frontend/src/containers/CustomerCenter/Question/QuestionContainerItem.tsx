import React from 'react';

type QuestionItemType = {
  title: string;
  content: string;
  ans: string;
  state: string;
};

const QuestionContainerItem: QuestionItemType[] = [
  {
    title: '잠시만두는 왜 키보드가 필요한가요?',
    content: '잠시만두는 왜 키보드가 필요한가요?',
    ans: '',
    state: '대기중',
  },
  {
    title: '잠시만두는 왜 마우스를 샀나요?',
    content: '잠시만두는 왜 마우스를 샀나요?',
    ans: '모름띠',
    state: '답변 완료',
  },
];

export default QuestionContainerItem;
