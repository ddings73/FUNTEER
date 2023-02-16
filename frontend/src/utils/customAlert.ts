import swal from 'sweetalert2';
import './customAlert.module.scss';

export const s1000 = {
  icon: 'success',
  iconColor: 'rgba(236, 153, 75, 1)',
  showConfirmButton: false,
  timer: 1500,
  timerProgressBar: true,
};

export const s1500 = {
  icon: 'success',
  iconColor: 'rgba(236, 153, 75, 1)',
  showConfirmButton: false,
  timer: 1500,
  timerProgressBar: true,
};

export const s2000 = {
  icon: 'success',
  iconColor: 'rgba(236, 153, 75, 1)',
  showConfirmButton: false,
  timer: 2000,
  timerProgressBar: true,
};

export const w1500 = {
  icon: 'warning',
  iconColor: 'rgba(211, 79, 4, 1)',
  showConfirmButton: false,
  timer: 1500,
  timerProgressBar: true,
};

export const w2000 = {
  icon: 'warning',
  iconColor: 'rgba(211, 79, 4, 1)',
  showConfirmButton: false,
  timer: 1500,
  timerProgressBar: true,
};

export const noTimeSuccess = {
  icon: 'success',
  iconColor: 'rgba(236, 153, 75, 1)',
  confirmButtonColor: 'rgba(236, 153, 75, 1)',
};

export const noTimeWarn = {
  icon: 'warning',
  iconColor: 'rgba(211, 79, 4, 1)',
  confirmButtonColor: 'rgba(211, 79, 4, 1)',
};

export const DefaultAlert = {
  confirmButtonColor: 'rgba(236, 153, 75, 1)',
};

export const customAlert = (alertType: object, title: string) => {
  swal.fire({ ...alertType, title });
};

export const customTextAlert = (alertType: object, title: string, text: string) => {
  swal.fire({ ...alertType, title, text });
};

export const customTextOnlyAlert = (alertType: object, text: string) => {
  swal.fire({ ...alertType, text });
};


export const customTextOnlyAlertOvenVidu = (alertType: object, text: string) => {
  swal.fire({ ...alertType, text }).then((result)=>{
    if(result.isConfirmed){
      window.location.href="/"
    }
  });
};