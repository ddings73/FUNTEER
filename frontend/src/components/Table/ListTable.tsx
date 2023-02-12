import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import Collapse from '@mui/material/Collapse';
import IconButton from '@mui/material/IconButton';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Pagination from '@mui/material/Pagination';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import styles from './ListTable.module.scss';
import { requestDonationList } from '../../api/donation';

type DonationContentType = {
  id: number;
  title: string;
  content: string;
  startDate: string;
  endDate: string;
  amount: number;
};

function Row({ title, amount, content, endDate, id, startDate }: DonationContentType) {
  const [open, setOpen] = React.useState(false);

  return (
    <>
      <TableRow sx={{ '& > *': { borderBottom: 'unset' } }}>
        <TableCell>
          <IconButton aria-label="expand row" size="small" onClick={() => setOpen(!open)}>
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell component="th" scope="row">
          {title}
        </TableCell>
        <TableCell align="center">
          {startDate} ~ {endDate}
        </TableCell>
      </TableRow>
      {/* 개별 이벤트 클릭 후 펼쳤을 때 */}
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
              <Typography variant="h6" gutterBottom component="div" sx={{ fontWeight: '600' }}>
                종료 이벤트 요약
              </Typography>
              <Paper
                elevation={3}
                sx={{ width: '80%', height: 'auto', display: 'flex', flexDirection: 'column', alignItems: 'flex-start', justifyContent: 'center', padding: '5%' }}
              >
                <p className={styles.tableHead}>내용</p>
                <p className={styles.tableContent} dangerouslySetInnerHTML={{ __html: content }} />
                <br />
                <p className={styles.tableHead}>목표 금액</p>
                <p className={styles.tableContent}>{amount}원</p>
                <br />
                <p className={styles.tableHead}>진행기간</p>
                <p className={styles.tableContent}>
                  {startDate} ~ {endDate}
                </p>
              </Paper>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </>
  );
}

export function ListTable() {
  type DonationListDataType = {
    content: DonationContentType[];
    totalPages: number;
    totalElements: number;
    last: boolean;
    number: number;
    first: boolean;
    empty: boolean;
  };

  const [DonationListData, setDonationListData] = useState<DonationListDataType>({
    content: [
      {
        id: 0,
        title: '',
        content: '',
        startDate: '',
        endDate: '',
        amount: 0,
      },
    ],
    totalPages: 0,
    totalElements: 0,
    last: false,
    number: 0,
    first: true,
    empty: false,
  });
  const getDonationList = async () => {
    const size = 8;
    try {
      const res = await requestDonationList(0, size);
      setDonationListData(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getDonationList();
  }, []);

  console.log(DonationListData);

  return (
    <TableContainer component={Paper} sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <Table aria-label="collapsible table">
        <TableHead>
          <TableRow>
            <TableCell />
            <TableCell>이벤트명</TableCell>
            <TableCell align="center">진행기간</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {DonationListData.content.map((row) => (
            <Row title={row.title} amount={row.amount} content={row.content} endDate={row.endDate} startDate={row.startDate} id={row.id} key={row.id} />
          ))}
        </TableBody>
      </Table>
      <Pagination count={DonationListData.totalPages} variant="outlined" color="primary" sx={{ margin: '3% 0' }} />
    </TableContainer>
  );
}

export default ListTable;
