import React from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import styles from './CarouselCard.module.scss';

export function CarouselCard() {
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
  return (
    <div>
      <Slider {...settings}>
        <div className={styles.box}>
          <h3>1</h3>
        </div>
        <div className={styles.box}>
          <h3>2</h3>
        </div>
        <div className={styles.box}>
          <h3>3</h3>
        </div>
        <div className={styles.box}>
          <h3>4</h3>
        </div>
        <div className={styles.box}>
          <h3>5</h3>
        </div>
        <div className={styles.box}>
          <h3>6</h3>
        </div>
      </Slider>
    </div>
  );
}

export default CarouselCard;
