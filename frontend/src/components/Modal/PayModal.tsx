import React, { useEffect, useState } from 'react';
import { Button, DialogActions } from '@mui/material';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import FormControl from '@mui/material/FormControl';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';
import { useAppDispatch } from '../../store/hooks';
import { payModalType } from '../../types/modal';
import { closeModal } from '../../store/slices/payModalSlice';
import { payment } from '../../payment';
import { PayParams } from '../../types/payment';
import { requestUserInfo } from '../../api/user';

function PayModal({ isOpen }: payModalType) {
  const dispatch = useAppDispatch();

  /** 결제 정보 */
  const [payInfo, setPayInfo] = useState<PayParams>({
    pg: 'html5_inicis',
    pay_method: '',
    merchant_uid: `mid_${new Date().getTime()}`,
    amount: 0,
    name: 'FUNTEER 충전',
    buyer_name: '',
    buyer_tel: '',
    buyer_email: '',
  });

  /** 유저 정보 조회 */
  const requestUser = async () => {
    try {
      const { data } = await requestUserInfo();
      console.log('유저 정보 조회', data);
      setPayInfo({ ...payInfo, buyer_name: data.name, buyer_email: data.email, buyer_tel: data.phone });
    } catch (error) {
      console.log(error);
    }
  };

  /** 결제 수단 변경 */
  const [tab, setTab] = React.useState(0);
  const changeTabHandler = (e: React.SyntheticEvent, newTab: number) => {
    setTab(newTab);
    setPayInfo({ ...payInfo, pay_method: newTab.toString() });
  };

  /** 결제 액수 변경 */
  const changeAmountHandler = (e: SelectChangeEvent<any>) => {
    setPayInfo({ ...payInfo, amount: e.target.value });
  };

  /** 유저 정보를 결제 정보에 담음 */
  useEffect(() => {
    requestUser();
  }, []);

  /** 결제 창 닫기 */
  const onClickCloseBtn = () => {
    dispatch(closeModal());
  };

  /** 결제 버튼 */
  const onClickPayBtn = () => {
    payment(payInfo);
  };

  return (
    <Dialog fullWidth maxWidth="sm" open={isOpen}>
      <DialogTitle>결제 정보 입력</DialogTitle>
      <DialogContent>
        <DialogContentText>결제 방식과 금액을 선택해주세요.</DialogContentText>
        <FormControl color="warning" sx={{ mt: 2, width: '100%' }}>
          <Box sx={{ width: '100%' }}>
            <Tabs
              textColor="inherit"
              TabIndicatorProps={{
                sx: { backgroundColor: '#EC994B' },
              }}
              onChange={changeTabHandler}
              value={tab}
              selectionFollowsFocus
            >
              <Tab value="card" label="카드" />
              <Tab value="vbank" label="가상계좌" />
            </Tabs>
          </Box>
          <Select onChange={changeAmountHandler}>
            <MenuItem value="5000">5,000 원</MenuItem>
            <MenuItem value="10000">10,000 원</MenuItem>
            <MenuItem value="20000">20,000 원</MenuItem>
            <MenuItem value="30000">30,000 원</MenuItem>
            <MenuItem value="50000">50,000 원</MenuItem>
            <MenuItem value="100000">100,000 원</MenuItem>
          </Select>
        </FormControl>
      </DialogContent>
      <DialogActions>
        <Button color="warning" variant="contained" sx={{ margin: '0rem 0.5rem 1rem auto' }} onClick={onClickPayBtn}>
          결제
        </Button>
        <Button color="warning" variant="contained" sx={{ margin: '0rem 2rem 1rem auto' }} onClick={onClickCloseBtn}>
          취소
        </Button>
      </DialogActions>
    </Dialog>
  );
}

export default PayModal;
