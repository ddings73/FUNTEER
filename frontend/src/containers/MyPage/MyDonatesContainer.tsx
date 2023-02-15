import React, { useEffect, useState } from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { styled } from '@mui/material/styles';
import { Pagination } from '@mui/material';
import styles from './MyDonatesContainer.module.scss';
import SideBarList from '../../components/MyPageSideBar/SideBarList';
import { requestGiftList } from '../../api/user';
import { myGiftType } from '../../types/myPage';

export function MyDonatesContainer() {
  const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
    },
  }));

  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
      border: 0,
    },
  }));

  const [giftDataList, setGiftDataList] = useState<myGiftType[]>([
    {
      amount: 0,
      fundTitle: '',
      giftDate: '',
      giftId: 0,
      username: '',
    },
  ]);

  const getGiftHistory = async () => {
    const res = await requestGiftList();
    setGiftDataList(res.data);
    console.log('axios', res.data);
  };

  useEffect(() => {
    getGiftHistory();
  }, []);

  // useEffect(() => {
  //   if (!maxPage) {
  //     getMaxPage();
  //   } else {
  //     getGiftHistory();
  //   }
  // }, [page, maxPage]);

  /** 페이지 교체 */
  // const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
  //   setPage(selectedPage);
  // };

  /** 최대 페이지 */
  // const getMaxPage = async () => {
  //   try {
  //     const response = await getGiftHistory(0, 10000);
  //     const pageCalc = response.data.length % size ? Math.floor(response.data.length / size) + 1 : response.data.length / size;
  //     console.log('최대 페이지 요청', response);
  //     setMaxPage(pageCalc);
  //   } catch (error) {
  //     console.error(error);
  //   }
  // };

  return (
    <div className={styles.bodyContainer}>
      <SideBarList />
      <div className={styles.contentContainer}>
        <div className={styles.contentBox}>
          <h1 className={styles.titleHeader}> 도네이션 참여 내역</h1>
          <div className={styles.tableBox}>
            <TableContainer component={Paper}>
              <Table sx={{ minWidth: 700 }} aria-label="customized table">
                <TableHead sx={{ backgroundColor: '#E6750A' }}>
                  <TableRow>
                    <StyledTableCell className={styles.tableHeader}>단체명</StyledTableCell>
                    <StyledTableCell className={styles.tableHeader} align="center">
                      금액
                    </StyledTableCell>
                    <StyledTableCell className={styles.tableHeader} align="right">
                      참여일자
                    </StyledTableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {/* {giftDataList.map((data, idx) => (
                    // eslint-disable-next-line
                    <StyledTableRow key={idx}>
                      <StyledTableCell component="th" scope="row">
                        {data.giftId}
                      </StyledTableCell>
                      <StyledTableCell align="center">{data.amount}원</StyledTableCell>
                      <StyledTableCell align="right">{data.giftDate}</StyledTableCell>
                    </StyledTableRow>
                  ))} */}
                </TableBody>
              </Table>
            </TableContainer>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MyDonatesContainer;
