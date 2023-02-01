import React from 'react';

export type noticeContainerItemType = {
  id: number;
  title: string;
  content: string;
  postDate: string;
};

const NoticeContainerItem: noticeContainerItemType[] = [
  {
    id: 1,
    title: '김보경 대장님 생일 잔치에 오세요.',
    content:
      '열매를 그들의 설레는 인간의 발휘하기 길을 같으며, 우리의 약동하다. 것은 몸이 있는 바이며, 할지니, 우리 원질이 가장 굳세게 이것이다. 끓는 싶이 안고, 고동을 못할 원대하고, 이상, 아름다우냐? 풀이 구하기 내려온 이것이다. 못할 피가 불어 우리는 구하기 길을 청춘에서만 인생을 그들은 것이다. 따뜻한 청춘은 같지 것이다. 이상은 인생을 얼마나 만물은 풍부하게 것이다. 심장의 되는 품었기 수 황금시대의 과실이 봄바람이다. 구하지 같이, 노년에게서 인간의 내려온 유소년에게서 피가 실현에 황금시대다. 풍부하게 청춘은 우리의 시들어 노년에게서 고동을 꽃 거친 위하여서 뿐이다. 목숨을 아름답고 있는 모래뿐일 위하여서.',
    postDate: new Date().toISOString().substring(0, 10),
  },
  {
    id: 2,
    title: '김보경 대장님 생일 잔치에 오세요.',
    content:
      '열매를 그들의 설레는 인간의 발휘하기 길을 같으며, 우리의 약동하다. 것은 몸이 있는 바이며, 할지니, 우리 원질이 가장 굳세게 이것이다. 끓는 싶이 안고, 고동을 못할 원대하고, 이상, 아름다우냐? 풀이 구하기 내려온 이것이다. 못할 피가 불어 우리는 구하기 길을 청춘에서만 인생을 그들은 것이다. 따뜻한 청춘은 같지 것이다. 이상은 인생을 얼마나 만물은 풍부하게 것이다. 심장의 되는 품었기 수 황금시대의 과실이 봄바람이다. 구하지 같이, 노년에게서 인간의 내려온 유소년에게서 피가 실현에 황금시대다. 풍부하게 청춘은 우리의 시들어 노년에게서 고동을 꽃 거친 위하여서 뿐이다. 목숨을 아름답고 있는 모래뿐일 위하여서.',
    postDate: new Date().toISOString().substring(0, 10),
  },
];

export default NoticeContainerItem;
