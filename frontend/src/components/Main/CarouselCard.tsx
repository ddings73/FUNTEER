import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import Slider from 'react-slick';
import { NavLink } from 'react-router-dom';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import styles from './CarouselCard.module.scss';
import { requestFundingList } from '../../api/funding';

interface Props {
  setFundingLength: Dispatch<SetStateAction<number>>;
}

type fundCarouselType = {
  id: number;
  title: string;
  thumbnail: string;
};

export function CarouselCard({ setFundingLength }: Props) {
  const [fundDataList, setFundDataList] = useState<fundCarouselType[]>([
    {
      id: 0,
      title: '',
      thumbnail: '',
    },
  ]);

  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
    arrows: true,
    pauseOnHover: true, // hover시 정지
    responsive: [
      // 반응형 옵션
      {
        breakpoint: 480, // (숫자)px 이하일 경우
        settings: {
          slidesToShow: 1,
          arrows: true,
        },
      },
    ],
  };

  useEffect(() => {
    getRecentFundList();
  }, []);

  const getRecentFundList = async () => {
    try {
      const res = await requestFundingList('', '', '', 0, 10);
      console.log('캐러셀 용 펀딩 리스트 요청', res);
      setFundingLength(res.data.totalElements);
      setFundDataList([...res.data.fundingListResponses]);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div>
      <Slider {...settings}>
        {fundDataList.map((data) => (
          <NavLink to={`/funding/detail/${data.id}`} className={styles.box} key={data.id}>
            <img className={styles.boxImg} src={data.thumbnail} alt="Funding Thumbnail" />
            <div className={styles.boxImgLabel}>{data.title.length > 15 ? `${data.title.substring(0, 15)}...` : data.title}</div>
          </NavLink>
        ))}
      </Slider>
    </div>
  );
}

export default CarouselCard;
