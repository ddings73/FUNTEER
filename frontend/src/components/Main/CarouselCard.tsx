import React, { useEffect, useState } from 'react';
import Slider from 'react-slick';
import { NavLink } from 'react-router-dom';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import styles from './CarouselCard.module.scss';
import { requestFundingList } from '../../api/funding';

type fundCarouselType = {
  id: number;
  title: string;
  thumbnail: string;
};

export function CarouselCard() {
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

  const getRecentFundList = async () => {
    try {
      const res = await requestFundingList(10);
      console.log('캐러셀 데이터', res.data.fundingListResponses.content);
      setFundDataList(res.data.fundingListResponses.content);
      console.log('ㅇㅇ', fundDataList);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getRecentFundList();
  }, []);

  return (
    <div>
      <Slider {...settings}>
        {fundDataList.map((data, i) => (
          <NavLink to={`/funding/detail/${data.id}`} className={styles.box} key={data.id}>
            <img className={styles.boxImg} src={data.thumbnail} alt="Img" />
            <div className={styles.boxImgLabel}>{data.title.length > 15 ? `${data.title.substring(0, 15)}...` : data.title}</div>
          </NavLink>
        ))}
      </Slider>
    </div>
  );
}

export default CarouselCard;
