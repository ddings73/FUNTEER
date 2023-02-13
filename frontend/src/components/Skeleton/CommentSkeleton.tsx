import { Box, Skeleton } from '@mui/material';
import React from 'react';

export function commentSkeleton() {
  return (
    <Box sx={{ marginTop: '25px', boxSizing: 'border-box', padding: '15px', width: '80%', display: 'flex' }}>
      <Skeleton variant="circular" width="50px" height="50px" sx={{ marginRight: '10px' }} />
      <Skeleton variant="rounded" width="100%" height={60} />
    </Box>
  );
}
export default commentSkeleton;
