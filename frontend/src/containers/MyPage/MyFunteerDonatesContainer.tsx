import React from 'react';
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

  function createData(name: string, calories: number, fat: number) {
    return { name, calories, fat };
  }

  const rows = [
    createData('Frozen yoghurt', 159, 6.0),
    createData('Ice cream sandwich', 237, 9.0),
    createData('Eclair', 262, 16.0),
    createData('Cupcake', 305, 3.7),
    createData('Gingerbread', 356, 16.0),
  ];
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
                  {rows.map((row) => (
                    <StyledTableRow key={row.name}>
                      <StyledTableCell component="th" scope="row">
                        {row.name}
                      </StyledTableCell>
                      <StyledTableCell align="center">{row.calories}원</StyledTableCell>
                      <StyledTableCell align="right">{row.fat}</StyledTableCell>
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
