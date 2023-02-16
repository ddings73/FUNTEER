import React, { useEffect, useState } from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { styled } from '@mui/material/styles';
import styles from './MyFunteerDonatesContainer.module.scss';
import SideBarList from '../../components/MyPageSideBar/SideBarList';
import { requestDonationHistory } from '../../api/donation';
import { myDonateListType, myDonateType } from '../../types/myPage';

export function MyFunteerDonatesContainer() {
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

  const size = 8;
  const [DonateData, setDonateData] = useState<myDonateType>({
    list: [
      {
        amount: 0,
        payDate: '',
        postId: 0,
        postName: '',
      },
    ],
    totalElements: 0,
    totalPages: 0,
  });

  const getGiftHistory = async () => {
    const res = await requestDonationHistory(0, size);
    console.log('doDa', res.data);
    setDonateData(res.data);
    console.log('데에에에에에이이이이이터터터터ㅓ', res.data);
  };

  useEffect(() => {
    getGiftHistory();
  }, []);

  return (
    <div className={styles.bodyContainer}>
      <SideBarList />
      <div className={styles.contentContainer}>
        <div className={styles.contentBox}>
          <h1 className={styles.titleHeader}> 펀티어 기부 참여 내역</h1>
          <div className={styles.tableBox}>
            <TableContainer component={Paper} className={styles.tableContent}>
              <Table sx={{ minWidth: 700 }} aria-label="customized table">
                <TableHead sx={{ backgroundColor: '#E6750A' }}>
                  <TableRow>
                    <StyledTableCell className={styles.tableHeader}>기부 타이틀</StyledTableCell>
                    <StyledTableCell className={styles.tableHeader} align="center">
                      금액
                    </StyledTableCell>
                    <StyledTableCell className={styles.tableHeader} align="right">
                      참여일자
                    </StyledTableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {DonateData.list.map((data) => (
                    // eslint-disable-next-line
                    <StyledTableRow key={data.postId}>
                      <StyledTableCell component="th" scope="row">
                        {data.postName}
                      </StyledTableCell>
                      <StyledTableCell align="center">{data.amount}원</StyledTableCell>
                      <StyledTableCell align="right">{data.payDate}</StyledTableCell>
                    </StyledTableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MyFunteerDonatesContainer;
