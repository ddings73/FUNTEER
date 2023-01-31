import React from 'react';

export enum TeamState {
  NotCertified = '인증 대기',
  Certified = '인증',
  Expired = '갱신 필요',
  Withdrawn = '탈퇴',
}

type adminTeamContainerItemType = {
  name: string;
  email: string;
  phone: string;
  vmsNum: string;
  lastPerformDate: string;
  files: string[];
  teamState: TeamState;
};

const tmp = new Date();
const today = tmp.toISOString().substring(0, 10);

export const AdminTeamContainerItem: adminTeamContainerItemType[] = [
  {
    name: 'FUNTEER1',
    email: 'funteer1@gmail.com',
    phone: '051-123-1234',
    vmsNum: '12341234',
    lastPerformDate: today,
    files: ['funteer_vms_file', 'funteer_perform_file'],
    teamState: TeamState.NotCertified,
  },
  {
    name: 'FUNTEER2',
    email: 'funteer2@gmail.com',
    phone: '051-123-1234',
    vmsNum: '12341234',
    lastPerformDate: today,
    files: ['funteer_vms_file', 'funteer_perform_file'],
    teamState: TeamState.Certified,
  },
  {
    name: 'FUNTEER3',
    email: 'funteer3@gmail.com',
    phone: '051-123-1234',
    vmsNum: '12341234',
    lastPerformDate: today,
    files: ['funteer_vms_file', 'funteer_perform_file'],
    teamState: TeamState.Expired,
  },
  {
    name: 'FUNTEER4',
    email: 'funteer4@gmail.com',
    phone: '051-123-1234',
    vmsNum: '12341234',
    lastPerformDate: today,
    files: ['funteer_vms_file', 'funteer_perform_file'],
    teamState: TeamState.Withdrawn,
  },
];

export default AdminTeamContainerItem;
