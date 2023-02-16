import React, { useEffect, useRef } from 'react';
import lottie, { AnimationItem } from 'lottie-web';
import styles from './NotFoundContainer.module.scss';

function NotFoundContainer() {
  const container = useRef<HTMLDivElement>(null);
  let anim: AnimationItem | undefined;

  useEffect(() => {
    if (container.current) {
      anim = lottie.loadAnimation({
        container: container.current,
        renderer: 'svg',
        loop: true,
        autoplay: true,
        path: 'https://assets7.lottiefiles.com/packages/lf20_9rdjpz1f.json',
        // path: 'https://assets7.lottiefiles.com/datafiles/sPJTLSWjrBGgvJK/data.json',
      });
    }

    return () => {
      if (anim) {
        anim.destroy();
      }
    };
  }, []);

  return (
    <div className={styles.container}>
      <div ref={container} />
    </div>
  );
}

export default NotFoundContainer;
