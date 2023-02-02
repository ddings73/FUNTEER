import swal from 'sweetalert2';

export const s1000 = {
  icon: 'success',
  iconColor: 'rgba(211, 79, 4, 1)',
  showConfirmButton: false,
  timer: 1500,
  timerProgressBar: true,
};

export const s1500 = {
  icon: 'success',
  iconColor: 'rgba(211, 79, 4, 1)',
  showConfirmButton: false,
  timer: 1500,
  timerProgressBar: true,
};

export const w1500 = {
  icon: 'warning',
  iconColor: 'rgba(211, 79, 4, 1)',
  showConfirmButton: false,
  timer: 1500,
  timerProgressBar: true,
};

export const customAlert = (alertType: object, title: string) => {
  swal.fire({ ...alertType, title });
};

export const customTextAlert = (alertType: object, title: string, text: string) => {
  swal.fire({ ...alertType, title, text });
};
